# 开发框架
## 概述
开发框架基于SpringBoot,提供了快速搭建、开发后台业务代码的能力.框架支持微服务,建议使用nacos配置和管理微服务.当然,您也可以使用自行搭建微服务管理端,只要支持Ahead-gateway的网关协议即可。
## 框架目录
``` 
  ahead
	|-- ahead-commons 通用开发框架
		|-- ahead-commons-utils 通用工具
		|-- ahead-commons-base 框架基础依赖
		|-- ahead-commons-core 核心框架
		|-- ahead-commons-web web框架
		|-- ahead-commons-redis redis 服务
		|-- ahead-commons-kafka kafka 服务
		|-- ahead-commons-elastic es 服务
	|-- ahead-gateway 网关服务
		|-- ahead-gateway-springcloud
		|-- ahead-gateway-gRpc
	|-- ahead-spark 大数据分析平台
```
## 框架使用
### 基础框架
支持Spring-data-jpa、Spring-data-jdbc和Spring-mybatis(plus).具体使用参考相关文档,这里面没做过多封装.
#### 依赖引入
```xml
<dependency>
	<groupId>cn.dcube</groupId>
	<artifactId>ahead-commons-core</artifactId>
	<version>${ahead.version}</version>
</dependency>
```
### Kafka 支持 
#### 依赖引入
```
<dependency>
	<groupId>cn.dcube</groupId>
	<artifactId>ahead-commons-kafka</artifactId>
	<version>${ahead.version}</version>
</dependency>
```
#### 使用
##### 消费者-kafkaListener
kafka消费数据后,通过ApplicationEventPublisher发布出来,需要自己实现KafkaListener来实现数据处理逻辑.

```java
@Component
@Slf4j
public class KafkaEventListener implements ApplicationListener<KafkaEvent> {
	
	@Override
	public void onApplicationEvent(KafkaEvent event) {
		// 处理kafkaEvent
		log.info(event.getTopic() + "<<<<<<<<<<<<<" + event.getValue());
	}

}
```
##### 生产者-KafkaProducer
```
	@Autowired
	private KafkaProducer kafkaProducer
	
	// 发送kafka消息,自定义的kafka listener里会对数据做处理
	kafkaProducer.sendStringMessage(${topic}, "this is a test");
	kafkaProducer.sendProtobufMessage(${topic}, protobufMsg);
```
#### kafka配置
参考commons-kafka项目中的kafka-config-template.yml. 具体可参考Spring-kafka.
### Elastic支持
#### 依赖引入
```
<dependency>
	<groupId>cn.dcube</groupId>
	<artifactId>ahead-commons-elastic</artifactId>
	<version>${ahead.version}</version>
</dependency>
```
#### elastic配置
参考commons-elastic项目中的elastic-config-template.yml.
#### 使用
```
	@Autowired
	private ElasticService elasticHelper;
	// 获取索引
	Set<String> indexes = elasticHelper.getIndices();
	indexes.forEach(item -> {
		System.out.println("es>>>>>>>>>>" + item);
	});
```
### Redis支持
#### 依赖引入
```
<dependency>
	<groupId>cn.dcube</groupId>
	<artifactId>ahead-commons-redis</artifactId>
	<version>${ahead.version}</version>
</dependency>
```
#### redis配置
参考commons-elastic项目中的redis-config-template.yml.
#### 使用
```
	@Autowired
	private RedisService redisService;
```
## 打包部署
采用Assembly支持打包.

```
	assembly打包结构(打包结构在assembly中已经定义)
      |—— bin  # 执行脚本
      |—— config #配置文件 
      |—— lib # 依赖包
      |—— logs # 日志
     
    1. 将assembly复制到项目中(不复制lib下的jar包)
    2. 修改bin中的脚本,将应用jar包换成自己的jar包名称
        java -jar -Dloader.path=../lib,../config ../lib/应用jar包.jar
    3. 将配置文件(不包含mapper中的xml)复制到config中
    4. 配置pom.xml,需要修改finalName、spring-boot-maven-plugin中的mainClass
        <build>
            <finalName>dcube</finalName>
            <resources>
                <resource>
                    <directory>src/main/resources</directory>
                </resource>
            </resources>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.1</version>
                    <configuration>
                        <source>1.8</source> <!-- 源代码使用的开发版本 -->
                        <target>1.8</target> <!-- 需要生成的目标class文件的编译版本 -->
                        <encoding>UTF-8</encoding>
                        <compilerVersion>1.8</compilerVersion>
                        <verbose>true</verbose>
                    </configuration>
                </plugin>
    
                <!--打包时将依赖包打包到lib下-->
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-dependency-plugin</artifactId>
                    <executions>
                        <execution>
                            <id>copy-dependencies</id>
                            <phase>package</phase>
                            <goals>
                                <goal>copy-dependencies</goal>
                            </goals>
                            <configuration>
                                <outputDirectory>assembly/lib</outputDirectory>
                                <excludeTransitive>false</excludeTransitive>
                                <stripVersion>false</stripVersion>
                                <includeScope>runtime</includeScope>
                            </configuration>
                        </execution>
                    </executions>
                </plugin>
    
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-jar-plugin</artifactId>
                    <configuration>
                        <includes>
                            <!--注意从编译结果目录开始算目录结构-->
                            <include>mapper/*.xml</include>
                            <include>**/*.class</include>
                        </includes>
                    </configuration>
                </plugin>
    
                <plugin>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                    <configuration>
                        <!-- 剔除spring-boot打包的org和BOOT-INF文件夹(用于子模块打包) -->
                        <skip>false</skip>
                        <!-- 指定该jar包启动时的主类[建议] -->
                        <mainClass>cn.diting.kuwise.vul.scanner.VulScannerMain</mainClass>
                        <layout>ZIP</layout>
                        <includes>
                            <!-- 排除第三方依赖jar(只保留本项目的jar) -->
                            <include>
                                <groupId>${project.groupId}</groupId>
                                <artifactId>${project.artifactId}</artifactId>
                            </include>
                        </includes>
                    </configuration>
                    <executions>
                        <execution>
                            <goals>
                                <goal>repackage</goal>
                            </goals>
                        </execution>
                    </executions>
                </plugin>
    
    
                <plugin>
                    <!--主要使用的是maven提供的assembly插件完成-->
                    <artifactId>maven-assembly-plugin</artifactId>
                    <executions>
                        <execution>
                            <configuration>
                                <appendAssemblyId>false</appendAssemblyId>
                                <!--具体的配置文件-->
                                <descriptors>${project.basedir}/assembly/package.xml</descriptors>
                            </configuration>
                            <id>assembly</id>
                            <!--绑定到maven操作类型上-->
                            <phase>package</phase>
                            <!--运行一次-->
                            <goals>
                                <goal>single</goal>
                            </goals>
                        </execution>
                    </executions>
                </plugin>
            </plugins>
        </build>
      5. 执行mvn package,打包.打包后的zip文件在target中
```
## 微服务发布
Spring Cloud
## ROADMAP(TODO)
1. 多数据源支持
2. 节点监控
3. 图数据库支持
4. 网关服务

## 版本变化
1. V1.0.0_20210331