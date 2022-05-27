# 框架使用手册
## Kafka 支持 
#### 依赖引入
```
<dependency>
	<groupId>cn.dcube</groupId>
	<artifactId>ahead-commons-kafka</artifactId>
	<version>${ahead.version}</version>
</dependency>
```
#### 使用
##### 消费者-编写kafkaListener
kafka消费数据后,通过ApplicationEventPublisher发布出来,需要自己实现KafkaListener来实现数据处理逻辑.

```
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
## Elastic支持
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
## Redis支持
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