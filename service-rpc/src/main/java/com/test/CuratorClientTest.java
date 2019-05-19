package com.test;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;


/**
 * Curator的Maven依赖如下，一般直接使用curator-recipes就行了，
 * 如果需要自己封装一些底层些的功能的话，例如增加连接管理重试机制等，则可以引入curator-framework包。
 */


/**
 * Curator framework's client test.
 * Output:
 *  $ create /zktest hello
 *  $ ls /
 *  [zktest, zookeeper]
 *  $ get /zktest
 *  hello
 *  $ set /zktest world
 *  $ get /zktest
 *  world
 *  $ delete /zktest
 *  $ ls /
 *  [zookeeper]
 */
public class CuratorClientTest {

    /** Zookeeper info */
    //private static final String ZK_ADDRESS = "192.168.1.100:2181";
    //private static final String ZK_ADDRESS = GetIP.IP()+":2181";
    private static final String ZK_ADDRESS = "192.168.31.164:2181,192.168.31.37:2181,192.168.31.97:2181";;
    private static final String ZK_PATH = "/zktest";

    public static void main(String[] args) throws Exception {
        // 1.Connect to zk
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                ZK_ADDRESS,
                new RetryNTimes(10, 5000)
        );
        client.start();
        System.out.println("zk client start successfully!");

        // 2.Client API test
        // 2.1 Create node
        String data1 = "hello";
        print("create", ZK_PATH, data1);
        Stat stat = client.checkExists().forPath(ZK_PATH);
        if(stat == null) {
            client.create().
                    creatingParentsIfNeeded().
                    forPath(ZK_PATH, data1.getBytes());
        }else{
            if(stat.getEphemeralOwner() > 0){
                System.out.println("node exist, and the mode is" + CreateMode.EPHEMERAL);
            }else{
                System.out.println("node exist, and the mode is" + CreateMode.PERSISTENT);
            }

        }

        // 2.2 Get node and data
        print("ls", "/");
        print(client.getChildren().forPath("/"));
        print("get", ZK_PATH);
        print(client.getData().forPath(ZK_PATH));

        // 2.3 Modify data
        String data2 = "world";
        print("set", ZK_PATH, data2);
        client.setData().forPath(ZK_PATH, data2.getBytes());
        print("get", ZK_PATH);
        print(client.getData().forPath(ZK_PATH));

        // 2.4 Remove node
        print("delete", ZK_PATH);
        client.delete().forPath(ZK_PATH);
        print("ls", "/");
        print(client.getChildren().forPath("/"));
    }

    private static void print(String... cmds) {
        StringBuilder text = new StringBuilder("$ ");
        for (String cmd : cmds) {
            text.append(cmd).append(" ");
        }
        System.out.println(text.toString());
    }

    private static void print(Object result) {
        System.out.println(
                result instanceof byte[]
                        ? new String((byte[]) result)
                        : result);
    }

}
