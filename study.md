#springCloud==分步式微服务架构的一站式解决方案，微服务架构技术的集合体。
包括(服务注册与发现，服务调用，服务熔断，负载均衡，服务降级，服务消息队列，配置中心管理，服务网关，服务监控，全链路追踪，自动化构建部署，服务定时任务)

查看springcloud-springboot对应版本的问题。https://start.spring.io/actuator/info

#Eureka介绍  github:https://github.com/Netflix/eureka
1、Eureka概念：
Eureka采用了C/S的设计架构，Eureka-Server作为服务注册的功能服务器，他是服务注册中心，而系统中的其他为服务，使用Eureka-Client连接到Eureka-Server、
并且维持心跳连接，就可以通过Eureka-Server来监控各个人微服务是否注正常运行。
在服务注册与发现中，有一个注册中心，当服务启动的时候，会把自己的服务器信息注册到服务中心上，另一方消费者去注册中心获取实际的通讯地址，然后在实现本地RPC调用
在任何RPC框架中，都会有一个注册中心来存放服务地址相关信息。
2、Eureka组件
Eureka Server ==德力西物业和Eureka Client==天阙科技
服务端提供服务注册，当客户端服务启动的时候，会主动向服务端进行注册，服务端会存储所有已经注册服务节点信息。
服务端会管理这些节点信息，并且会将异常的节点移除服务列表

Eureka Client：是一个java客户端，用于简化和Eureka-Server的交互。客户端同时具备一个内置的、使用轮询负载算法的负载均衡服务器。
在应用启动后，客户端会每隔30秒发送心跳给服务端，告诉服务端自己是可用的。如果一个服务长时间没有发送心跳，大约90秒内会被移除服务器列表。
另外客户端有缓存功能，所以即便Eureka集群中所有节点都失效，或者发生网络分割故障导致客户端不能访问任何一台Eureka服务器；Eureka服务的消费者仍然可以通过Eureka客户端缓存来获取现有的服务注册信息。
3、本项目，cloud-eureka-server-7001，cloud-consumer-order80，cloud-provider-payment8001运行原理：
 1):先启动cloud-eureka-server-7001，Eureka注册中心
 2):启动服务提供者cloud-provider-payment8001支付服务
 3):支付服务启动后会把自身的信息(比如服务地址以别名的方式注册进Eureka)
 4):消费者cloud-consumer-order80在需要调用支付服务时，使用服务别名去注册中心获取实际的rpc远程调用地址
 5):消费者获得调用地址后，底层实际是利用HttpClient技术实现的远程调用
 6):消费者获得服务地址后会缓存在本地jvm内存中，默认每间隔30s更新一次服务调用地址。
4、Eureka的自我保护机制
1))什么是自我保护模式：
某时刻某一个微服务不可用了，Eureka不会自动清理，依旧会对该服务的信息进行保存，属于cap理论的ap分支
eureka设置了一个阀值，当挂掉的服务超过阀值时，server认为很大程度上出现了网络故障，将不在删除心跳过期的服务。这就是eureka server的自我保护模式
阈值：15分钟内超过85%的节点都没有正常的心跳   

#Consul介绍 官网：https://learn.hashicorp.com/consul   github:https://github.com/hashicorp/consul  cloud整合地址：https://cloud.spring.io/spring-cloud-zookeeper/reference/html/
Consul是一套开源的分布式服务发现和配置管理系统，提供了微服务系统中的服务发现，支持Ribbon，支持Zuul,分布式配置,控制总线等功能。每一个功能都可以单独使用，也可以一起使用来构建全方位的服务网格。
Consul提供的服务有服务发现，健康检查，key-value存储，多数据中心，可视化的web页面。
linux启动consul ./consul agent -dev -client 0.0.0.0 -ui

#CAP理论
CAP理论是分布式架构中重要理论
一致性(Consistency) (所有节点在同一时间具有相同的数据)
可用性(Availability) (保证每个请求不管成功或者失败都有响应)
分隔容忍(Partition ) (系统中任意信息的丢失或失败不会影响系统的继续运作)

#微服务：注册中心ZooKeeper、Eureka、Consul 、Nacos对比
服务注册中心本质上是为了解耦服务提供者和服务消费者
微服务的提供者的数量和分布地址往往是动态变化的，也是无法预先确定的。因此，需要引入额外的组件来管理微服务提供者的注册与发现，这就是服务中心。
##ZooKeeper：CP 
master宕机的话 其他节点会重新进行leader选举。问题在于，选举leader的时间太长，30~120s，而且选举期间整个zk集群都是不可用的，这就导致在选举期间注册服务瘫痪.
##Eureka:AP 
Eureka Server 可以运行多个实例来构建集群,不同于 ZooKeeper 的leader选举过程 采用的是节点间互相注册 
在集群环境中如果某台 Eureka—Server宕机,Eureka-Client的请求会自动切换到新的Eureka-Server节点上，当宕机的服务器重新恢复后，Eureka 会再次将其纳入到服务器集群管理之中。
当节点开始接受客户端请求时，所有的服务信息都会在节点间进行同步。Eureka-Server与Eureka-client维持心跳连接，超过阈值进入保护模式，Eureka-client具备缓存功能。
##Consul:CP 
Leader挂掉时，重新选举期间整个consul不可用。保证了强一致性但牺牲了可用性。

#Spring Cloud Ribbon github：https://github.com/Netflix/ribbon
基于netflix Ribbon实现的一套客户端负载均衡工具。
通过Spring Cloud的封装，可以让我们轻松地将REST模版请求自动转换成客户端负载均衡的服务调用.
Ribbon能干嘛：一句话ribbon就是客户端负载均衡+restTemplate
#Ribbon本地负载均衡客户端和Nginx的区别：
Nginx是服务器负载均衡，客户端所有请求都会交给服务器，由nginx实现转发请求，由服务器实现.
Ribbon本地负载均衡，在调用微服务接口的时候，会在注册中心获取注册信息服务列表之后缓存到JVM本地，从而本地实现RPC远程服务调用技术。

#RestTemplate 实现负载均衡的原理
RestTemplate bean 加一个@LoadBalanced注解,
LoadBalanced注解并且标记了@Qualifier,
溯源到LoadBalancerAutoConfiguration这个类
    @LoadBalanced
    @Autowired(required = false)
    private List<RestTemplate> restTemplates = Collections.emptyList();  
这个集合就拥有所有标记了@loadBalance的restTemplate的Bean.
然后给所有标记了@loadBalance的restTemplate的Bean注入loadBalancerInterceptor拦截器。
当我们restTemplate执行请求操作时，就会被拦截器拦截进入intercept方法，在执行execute方法
根据serviceId 获取对应的loadBalancer
根据loadBalancer获取具体的server（这里根据负载均衡规则，获取到具体的服务实例）
创建ribbonServer
执行具体请求

#IRule 路由
IRule接口的实现类有以下几种：
RoundRobinRule:轮询策略
RandomRule :随机策略
RetryRule：重试策略 先按照轮询策略，如果获取服务失败，则在指定时间进行重试。
WeightedResponseTimeRule:加权策略 ，在轮询的基础上，计算响应速度，速度越快，服务实例的权重越大，越容易被选择。
BestAvailableRule:请求数最少策略，过滤掉不能被请求的服务，然后选择一个并发数最小的服务
轮询算法讲解：接口的请求次数%集群的数量==实际调用的服务器下标，然后从服务实例列表集合索引出服务器地址，每次服务器重启，从新开始                       
   
#OpenFeign   github地址：https://github.com/spring-cloud/spring-cloud-openfeign
开发微服务，免不了需要服务间调用。Spring Cloud框架提供了RestTemplate和OpenFeign两个方式完成服务间调用 .
使用OpenFeign只需要创建一个接口加上对应的注解(@FeignClient(value = "CLOUD-PAYMENT-SERVICE-EUREKA") )即可完成远程的服务调用  
1）远程调用：可结合ribbon实现负载均衡的能力
2）超时控制：OpenFeign的客户端默认等待服务1s，如果超过这个时间，直接返回报错
3) 日志打印：先注入Logger.Level ，再在配置文件配置，日志打印的类，和级别

#hystrix github:https://github.com/Netflix/Hystrix/wiki
在微服务架构中，根据业务来拆分成一个个的服务，服务与服务之间可以相互调用（RPC） 。为了保证其高可用，单个服务通常会集群部署。
由于网络原因或者自身的原因，服务并不能保证100%可用，如果单个服务出现问题，调用这个服务就会出现线程阻塞，此时若有大量的请求涌入，Servlet容器的线程资源会被消耗完毕，导致服务瘫痪。
服务与服务之间的依赖性，故障会传播，会对整个微服务系统造成灾难性的严重后果，这就是服务故障的“雪崩”效应.
Hystrix是一个处理分布式系统的延迟和容错的开源库，在分布式系统里，许多依赖不可避免的会调用失败，比如：超时，异常，等hystrix能够保证在一个依赖服务出问题的情况下：不会导致整体服务
失败，避免级联故障，以此来提高分布式系统的可用性。
hystrix可以做：服务降级，服务熔断，接近实时的监控.

概念：
1）服务降级：fallback    : 服务器繁忙，请稍后重试，不让客户端等待，并且立刻返回一个友好提示。触发原因：程序异常，超时，服务熔断触发服务降级，线程池满
2) 服务熔断：break       ：达到最大阈值后，直接拒绝访问，然后调用服务降级的方法并且返回友好提示。   服务降级--达到阈值->服务熔断--5s后半开状态允许进入一个请求-->恢复链路调用 出现情况：当服务调用失败进入降级后再达到阈值，默认是5秒20次调用失败，触发熔断机制
3）服务限流: flowlimit   ：秒杀高并发等操作，严禁一窝蜂过来拥挤，大家排队，一秒钟N个，有序进行
处理：
#Spring Cloud Gateway--服务网关，替换了netflix的zuul 官网：https://cloud.spring.io/spring-cloud-static/Hoxton.SR1/reference/htmlsingle/#spring-cloud-gateway    
一、Spring Cloud Gateway 与Zuul1的区别：
Zuul1：使用java写的基于servlet规范使用同步阻塞式io模型，每次i/o操作都是从工作线程中选择一个执行，请求线程被阻塞直到工作线程完成(nginx类似)。
Spring Cloud Gateway 是基于spring5,SpringBoot2.x,WebFlux框架实现的，而WebFlux框架底层则使用了高性能的Reactor模式的通信框架netty。是异步非阻塞的i/模型，还支持WebSocket
二、servlet生命周期：
  1)初始化：当Servlet容器(Tomcat)启动时，容器查找一个配置文件web.xml，这个文件中记录了可以提供服务的Servlet，Servlet容器将调用每个Servlet的init方法来实例化每个Servlet。(<load-on-startup>1</load-on-startup>)
  如果初始化失败，执行init()方法抛出ServletException异常，Servlet对象将会被垃圾回收器回收，当客户端第一次访问服务器时加载Servlet实现类，创建对象并执行初始化方法。
  注释：servelt是否在容器启动立即加载可由<load-on-startup>整数</load-on-startup>配置，如果该元素的值为负数或者没有设置，则容器会当Servlet被请求时再加载，如果值为正整数或者0时，表示容器在应用启动时就加载并初始化这个servlet，值越小，servlet的优先级越高，就越先被加载
  2)调用service:当容器收到请求，就会为每个请求分配一个线程(从线程池中取出空闲线程)然后调用Servlet的service()方法处理请求
  3)容器关闭时调用Servlet的destroy()方法销毁Servlet。
  注释：Servlet3.1之前是采用同步阻塞的网络i/o模型，遇见高并发的时候，线程数量上涨，导致内存消耗大，上下文不断切换，严重影响性能。另外SpringMVC也是基于ServletApi和封装Servlet开发的，是同步阻塞的IO模型。
三、Spring Cloud Gateway的执行流程：

请求--------->NettyServer---------->Predicate-------------->Filter------------>NettyClient----------->服务  （NettyServer--->NettyClient：就是WebFlux）

四、Gateway的动态路由
默认情况下Gateway会根据注册中心注册的服务列表，以微服务名称为路径创建动态路由进行转发，从而实现动态路由的功能。
五、断言工厂  Route Predicate Factories --规则进行匹配，true则进行路由
1)After 
       predicates:
         - After=2017-01-20T17:42:47.789-07:00[America/Denver]
2)Before
       predicates:
         - Before=2017-01-20T17:42:47.789-07:00[America/Denver]
3)Between
       predicates:
         - Between=2017-01-20T17:42:47.789-07:00[America/Denver], 2017-01-21T17:42:47.789-07:00[America/Denver]   
4)Cookie
       predicates:
          - Cookie=username,hww
5)Header
       predicates:
          - Cookie=username,hww          
六、网关过滤器工厂  GatewayFilter Factories    可用于修改进入的HTTP请求和返回的Http响应，路由过滤器只能指定路由进行使用   
具体见官网，有常用过滤器，全局过滤器，自定义过滤器
实现自定义过滤器：cloud-gateway9527.com.hww.filter.SelfFilter
  