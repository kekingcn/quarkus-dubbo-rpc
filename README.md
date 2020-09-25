# quarkus-dubbo-rpc
Quarkus integrated extension of Apache dubbo framework
## Quick start
- 1、Introduce maven coordinates
```
     <dependency>
         <groupId>org.github.keking</groupId>
         <artifactId>quarkus-dubbo-rpc-ext</artifactId>
         <version>1.0-SNAPSHOT</version>
     </dependency>
```
- 2、Add the following configuration in the application.properties file
```
#dubbo
quarkus.dubbo.name = kl
quarkus.dubbo.registr-addr=nacos://nacos-pre.xxx.com:8848
quarkus.dubbo.protocol.name = dubbo
quarkus.dubbo.protocol.port  = 20330
```
- 3、Inject remote services
```
@Singleton
@Startup
public class ConfigService {
    
    @DubboReference(check = false)
    Client client;

    @PostConstruct
    public void print(){
        System.out.println(client.list("kl"));
    }
}
```
- 4、Expose dubbo service
```
@DubboService(interfaceClass = ServiceApi.class)
public class ServiceImpl implements ServiceApi {
    @Override
    public String hello(String name) {
        return "hello" + name;
    }
}

interface ServiceApi {
    String hello(String name);
}
```
Note: The above example annotations are based on dubbo 2.7.6 and above,Currently, only part of the annotation parameters of import and export services are supported，For more Apache Dubbo usage, please refer to the official documentation

## Other resources
- dubbo : https://github.com/apache/dubbo
- quarkus: https://github.com/quarkusio/quarkus
- klblog : http://www.kailing.pub/