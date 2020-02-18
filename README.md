主要参考于[基于ZooKeeper和Thrift构建动态RPC调用](https://blog.csdn.net/likewindy/article/details/51352179) 、[Apache Curator入门实战](https://www.cnblogs.com/seaspring/p/5536338.html) 

- **thrift-rpc** 定义thrift idl文件
- **service-rpc** server服务端（java）
- **client-rpc** client调用端（java）
- **client-rpc-python**  采用python的方式进行序列化、反序列化。



在本地mac机器上，安装VirtualBox，然后安装两台ubuntu server。并且分别配置hosts

```
192.168.31.164 mac
192.168.31.37 ubuntu1
192.168.31.97 ubuntu2
```

因为每次重启ubuntu虚拟机之后，它们的ip地址会改变。这样配置hosts之后，就可以只用修改hosts之后，不用修改代码重新部署项目。



idl代码自动生成java、python代码命令：

```
thrift -r -gen java weather_service.thrift
thrift -r -gen py weather_service.thrift
```





client和server启动后，

post请求    localhost:9998/Weather/ListAllweather.action    ，可以得到zk中server的地址。

                  localhost:9998/Weather/CallWeatherRPC.action    ，可以得到调用的返回。





#### 其他

当使用`org.apache.thrift:libthrift:0.12.0`时候，server采用TThreadPoolServer的时候，client调用server，client可以得到正确的返回，但是server端，却会有多的异常打印信息。报错异常信息如下：

```java
20:45:37.838 [pool-1-thread-1] ERROR org.apache.thrift.server.TThreadPoolServer - Thrift error occurred during processing of message.
org.apache.thrift.transport.TTransportException: null
	at org.apache.thrift.transport.TIOStreamTransport.read(TIOStreamTransport.java:132)
	at org.apache.thrift.transport.TTransport.readAll(TTransport.java:86)
	at org.apache.thrift.protocol.TJSONProtocol$LookaheadReader.read(TJSONProtocol.java:272)
	at org.apache.thrift.protocol.TJSONProtocol.readJSONSyntaxChar(TJSONProtocol.java:344)
	at org.apache.thrift.protocol.TJSONProtocol.readJSONArrayStart(TJSONProtocol.java:854)
	at org.apache.thrift.protocol.TJSONProtocol.readMessageBegin(TJSONProtocol.java:866)
	at org.apache.thrift.TBaseProcessor.process(TBaseProcessor.java:27)
	at org.apache.thrift.server.TThreadPoolServer$WorkerProcess.run(TThreadPoolServer.java:310)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
```



问题原因：`compile 'org.apache.thrift:libthrift:0.12.0'` 改成 `compile org.apache.thrift:libthrift:0.11.0` ，是libthrift版本的问题。不过也没有影响到实际使用。



