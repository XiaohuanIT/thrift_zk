主要参考于[基于ZooKeeper和Thrift构建动态RPC调用](https://blog.csdn.net/likewindy/article/details/51352179) 、[Apache Curator入门实战](https://www.cnblogs.com/seaspring/p/5536338.html) 

- **thrift-rpc** 定义thrift idl文件
- **service-rpc** server服务端
- **client-rpc** client调用端

现在我遇到的一个问题是：对于thrift，采用多线程服务模型时候，client调用可以得到正常返回，但是server端会多打印一个异常信息。为了单独方便展示，特意在server、clent的test目录下，分别创建了一个简单的调用demo。

当server采用TThreadPoolServer的时候，client调用server，client可以得到正确的返回，但是server端，却会有多的异常打印信息。我有尝试过找原因，使用`TJSONProtocol`协议，发现是server端最后多接收了一个字符串"]"，导致报错。报错具体信息如下：

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



当server使用单线程服务模型时候，server端没有任何报错，目前为止，实在没有找到报错的原因。为了便于直接找到错误原因，在client和server项目的test目录下，均有对应的简化代码，可以直接运行，错误与上述一致！





client和server启动后，

post请求    localhost:9998/Weather/ListAllweather.action    ，可以得到zk中server的地址。

​                  localhost:9998/Weather/CallWeatherRPC.action    ，可以得到调用的返回。