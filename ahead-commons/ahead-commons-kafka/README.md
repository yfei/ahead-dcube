### 项目介绍
框架的Kafka支持。
### 技术框架
采用spring-kafka技术.接收到Kafka消息后,封装为KafkaEvent,通过Spring的ApplicationEventPublisher进行发布和订阅。
### 配置信息
```
spring:
  # 所有的time为毫秒。topics为自定义结构
  kafka:
    bootstrap-servers: 192.168.31.36:9092
    producer:
      bootstrap-servers: 192.168.31.36:9092
      batch-size: 16785  #一次最多发送数据量
      retries: 3  #发送失败后的重复发送次数
      buffer-memory: 33554432 #32M批处理缓冲区
      linger: 1
      # key和value反序列化（默认，可以不设置）
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
    consumer:
      bootstrap-servers: 192.168.31.36:9092
      group-id: log-hs-grou20
      auto-offset-reset: latest # earliest：从头开始消费   latest：从最新的开始消费   默认latest
      max-poll-records: 4639  #批量消费一次最大拉取的数据量
      enable-auto-commit: false  #是否开启自动提交
      auto-commit-interval: 1000   #自动提交的间隔时间
      session-timeout: 6000   #连接超时时间
    listener:
      type: BATCH # 消费类型  BATCH 批量  SINGLE 单个
      # ackMode: 解释如下
      # BATCH:Commit whatever has already been processed before the next poll.
      # RECORD:Commit after each record is processed by the listener.
      # TIME:Commit pending updates after ackTime has elapsed.
      # COUNT pending updates after ackCount has been exceeded.
      # COUNT_TIME：Commit pending updates after ackTime has elapsed or ackCount has been exceeded.
      # MANUAL:User takes responsibility for acks using an AcknowledgingMessageListener
      # MANUAL_IMMEDIATE:User takes responsibility for acks using an AcknowledgingMessageListener,The consumer immediately processes the commit.
      ackMode: BATCH
      # ackTime: 5000 # ackMode为TIME或者COUNT_TIME时生效,ackTime
      # ackCount: 1 # ackMode为COUNT或者COUNT_TIME时生效,ackCount
      concurrency: 3     #设置消费的线程数
      poll-timeout: 1500   #如果消息队列中没有消息，等待timeout毫秒后，调用poll()方法。如果队列中有消息，立即消费消息，每次消费的消息的多少可以通过max.poll.records配置。
    topics: # 主题
      consumer-topics: sdap-eventbase-collector
      producer-topics:
        dp-event: sdap-eventbase-dp
```