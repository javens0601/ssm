###文章地址
- https://www.pianshen.com/article/7093151968/

### 典型应用场景
- 应用间隔离及跨应用调用
    - 在ServiceComb框架中，一个应用下包含多个微服务。
  同一个微服务实例，可以作为公共服务部署到多个应用，通过指定不同的APPLICATION_ID来实现。
- 开发环境互相隔离及快速开发
    - ServiceComb框架通过设置environment，可以将微服务实例标记为开发、测试、预生产、生产环境，实现了在实例级别的天然隔离；客户端在查找服务端实例的时候，只能发现相同environment下的服务端实例
    - 仅支持以下枚举值：development,testing,acceptance,production，不配置的情况下缺省值为""（空）。   
      - 方法1：通过JVM启动参数-Dservice_description.environment=development（枚举值）进行设置；   
      - 方法2：通过microservice.yaml配置文件来指定   
      - 方法3：通过环境变量SERVICECOMB_ENV来指定（仅限于windows系统），若是开发态，其值配置为development；
- 两地三中心
    - 在以两地三中心的解决方案进行跨地区部署服务的场景，同一套服务存在于多个availableZone中，需要实现优先调用同一个AZ内的应用，若本AZ出现问题，要能够访问另一个AZ下的应用，从而保证服务的可靠性。
      ServiceComb提供了数据中心配置，来实现对微服务的分区和管理。数据中心包含3个属性：servicecomb.datacenter.name, servicecomb.datacenter.region, servicecomb.datacenter.availableZone，数据中心信息不提供隔离能力，微服务可以发现其他数据中心的实例。但是可以通过启用实例亲和性，来优先往指定的区域或者Zone发消息。