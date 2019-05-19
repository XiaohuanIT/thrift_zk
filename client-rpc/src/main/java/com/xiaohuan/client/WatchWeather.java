package com.xiaohuan.client;

import com.xiaohuan.client.constants.WeatherConstants;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Configuration
public class WatchWeather implements Watcher {
	@Value("${zk.connection.hosts}")
	private String zkConnectionHosts;
	@Value("${zk.connection.timeOut}")
	private int zkConnectionTimeOut;

	public static List<String> weatherlist = new ArrayList<String>();

	private ZooKeeper zookeeper = null;
	// 扇子锁
	private static CountDownLatch conDown = new CountDownLatch(1);

	public void watch() throws IOException {
		zookeeper = new ZooKeeper(zkConnectionHosts, // l链接地址字符串，可以多个用逗号分割
				zkConnectionTimeOut, // 超时设置
				(Watcher) new WatchWeather()// 监听函数
		);
		try {
			conDown.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			weatherlist = zookeeper.getChildren(WeatherConstants.RPCNAME, this);
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			zookeeper.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 注册监听函数
	@Override
	public void process(WatchedEvent event) {
		if (KeeperState.SyncConnected == event.getState())
			conDown.countDown();
		if (EventType.NodeChildrenChanged == event.getType()) {
			try {
				weatherlist = zookeeper.getChildren(WeatherConstants.RPCNAME, this);
			} catch (KeeperException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
