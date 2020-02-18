client-rpc包结构说明：

client：client的主要逻辑

rpc：idl自动生成的代码

thrift_demo_1：`TSimpleServer`的**工作模式**采用最简单的**阻塞**IO，实现方法简洁明了，便于理解，但是一次只能接收和处理一个`socket`连接，效率比较低。它主要用于演示`Thrift`的工作过程，在实际开发过程中很少用到它。



thrift_demo_2：`TThreadPoolServer`模式采用**阻塞**`socket`方式工作，主线程负责**阻塞式**监听是否有新`socket`到来，具体的业务处理交由一个**线程池**来处理。



thrift_demo_3：`TNonblockingServer`模式也是**单线程工作**，但是采用`NIO`的模式，借助`Channel/Selector`机制, 采用`IO`**事件模型**来处理。所有的`socket`都被注册到`selector`中，在一个**线程**中通过`seletor`**循环监控**所有的`socket`。每次`selector`循环结束时，处理所有的处于**就绪状态**的`socket`，对于有数据到来的`socket`进行**数据读取**操作，对于有数据发送的socket则进行**数据发送**操作，对于监听`socket`则产生一个新业务`socket`并将其**注册**到`selector`上。



thrift_demo_4：鉴于`TNonblockingServer`的缺点，`THsHaServer`继承于`TNonblockingServer`，引入了**线程池**提高了任务处理的**并发能力**。`THsHaServer`是**半同步半异步**(`Half-Sync/Half-Async`)的处理模式，`Half-Aysnc`用于`IO`**事件处理**(`Accept/Read/Write`)，`Half-Sync`用于业务`handler`对`rpc`的**同步处理**上。



thrift_demo_5：`TThreadedSelectorServer`是对`THsHaServer`的一种扩充，它将`selector`中的**读写**`IO`**事件**(`read/write`)从**主线程**中分离出来。同时引入`worker`**工作线程池**，它也是种`Half-Sync/Half-Async`的服务模型。`TThreadedSelectorServer`模式是目前`Thrift`提供的最高级的**线程服务模型**。



以上结构，跟service-rpc结构对应。

service-rpc

thrift_test：使用thrift进行序列化、反序列化。

com.test：对zookeeper的操作



初次之外，client-rpc 与service-rpc的test目录下，也是可以互通工作的。

