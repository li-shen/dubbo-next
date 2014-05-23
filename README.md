Continue making efforts on dubbo for a Chinese e-commerce company.

Work in progress...


目前，修改优化了一下dubbo的序列化体系并添加了两个新的开源Java高效序列化方式：kryo和FST

直接在dubbo协议中将序列化方式设为kryo或者fst即可：

    <dubbo:protocol name="dubbo" serialization="kryo"/>

    <dubbo:protocol name="dubbo" serialization="fst"/>


从序列化和反序列化的CPU开销来说，目前观察提升并不明显。

但对序列化中最为重要的生成字节码大小（决定了远程调用的网络传输时间和带宽占用），应该很多应用中会有较明显的改进（越小越好）。

下面基于一个较为接近真实的广告竞价service，测试传输字节码大小（基于默认dubbo RPC协议 + 不同序列化方式）

大致结果是：

新加的kryo和FST序列化，在请求消息的体积上是dubbo默认的hessian lite序列化的1/2，是dubbo自己还未完全成熟的java高效序列化方式的2/3

它们在响应消息体积上是dubbo默认的hessian lite序列化的1/3，是dubbo自己还未完全成熟的java高效序列化方式的1/3

另外，由于dubbo默认RPC协议自身传输了额外大量元数据，在总体积中比例较大

具体结果如下：

### kryo （新加序列化方式）

响应消息：
the resulting byte size: 39

请求消息：
the resulting byte size: 248

### FST （新加序列化方式）

响应消息：
the resulting byte size of encoding is 42

请求消息：
the resulting byte size of encoding is 264


### hessian lite （dubbo默认序列化）

响应消息：
the resulting byte size: 129

请求消息：
the resulting byte size: 503


### dubbo （dubbo尚未成熟的高效序列化）

响应消息：
the resulting byte size: 117

请求消息：
the resulting byte size: 390

### fastjson （用阿里的fastjson库做json序列化）

响应消息：
the resulting byte size: 78

请求消息：
the resulting byte size: 431


### native java （java自带序列化）

响应消息：
the resulting byte size: 277

请求消息：
the resulting byte size: 885

=============================================


同时也测试了一下简单的hello world service，由于传输数据太简单，各种序列化方式差别就不大了：

### kryo

the resulting byte size: 27

the resulting byte size: 181

### FST

the resulting byte size of encoding is 28

the resulting byte size of encoding is 192


### hessian lite


the resulting byte size: 26

the resulting byte size: 195

### dubbo

the resulting byte size: 29

the resulting byte size: 220


另外，对于kryo和FST，要达到最优的序列化大小，用户要按照如下这种方式在客户端和服务器端部署一个自启动的spring bean，来注册要序列化的类，否则就和dubbo原来的方式差别不太大了：

    public class SerializationOptimizer {

        public void init() {
            SerializableClassRegistry.registerClass(BidRequest.class);
            SerializableClassRegistry.registerClass(BidResponse.class);
            SerializableClassRegistry.registerClass(Device.class);
            SerializableClassRegistry.registerClass(Geo.class);
            SerializableClassRegistry.registerClass(Impression.class);
            SerializableClassRegistry.registerClass(SeatBid.class);
        }
    }


    <bean class="com.alibaba.dubbo.demo.SerializationOptimizer" init-method="init"/>
