package com.xiaohuan.server;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import com.xiaohuan.server.constants.WeatherConstants;
import com.xiaohuan.server.utils.GetIP;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RegisterWeatherRPC implements Watcher {

	@Value("${zk.connection.hosts}")
	private String zkConnectionHosts;
	@Value("${zk.connection.timeOut}")
	private int zkConnectionTimeOut;

	private static ZooKeeper zookeeper = null;
	// 扇子锁
	private static CountDownLatch conDown = new CountDownLatch(1);

	public void register() throws IOException {
		zookeeper = new ZooKeeper(zkConnectionHosts,
				zkConnectionTimeOut, // 超时设置
				new RegisterWeatherRPC()// 监听函数
		);

		try {
			conDown.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			checkServerName();
			createServerHost();
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createServerHost() throws KeeperException, InterruptedException {
		Stat stat = zookeeper.exists(WeatherConstants.RPCNAME + "/" + GetIP.IP() + ":" + WeatherConstants.WeahterPort,false);
		if (stat == null) {
			try {
				// 这里是临时的节点，会因服务器的宕机而消失
				zookeeper.create(WeatherConstants.RPCNAME + "/" + GetIP.IP() + ":" + WeatherConstants.WeahterPort, "".getBytes(),
						Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
			} catch (KeeperException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void checkServerName() throws KeeperException, InterruptedException {
		Stat stat = zookeeper.exists(WeatherConstants.RPCNAME, false);
		// 没有则创建
		if (stat == null) {
			// 这里是固定的节点
			String path = zookeeper.create(WeatherConstants.RPCNAME, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		}
	}

	@Override
	public void process(WatchedEvent event) {
		if (KeeperState.SyncConnected == event.getState())
			conDown.countDown();
	}
}
