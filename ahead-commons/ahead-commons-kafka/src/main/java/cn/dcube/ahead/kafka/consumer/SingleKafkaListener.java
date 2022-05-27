package cn.dcube.ahead.kafka.consumer;

import java.util.Optional;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import cn.dcube.ahead.kafka.config.KafkaConfig;
import cn.dcube.ahead.kafka.event.KafkaEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 监听Kafka主题消息,收到的Kafka消息,通过ApplicationEventPublisher发布出来
 * 
 * @author：yangfei<br>
 * @date：2021年3月24日上午10:45:31
 * @since 1.0
 */
@Slf4j
@Component
@ConditionalOnBean(KafkaConfig.class)
@ConditionalOnExpression("#{'SINGLE'.equals('${spring.kafka.listener.type}')}")
public class SingleKafkaListener {

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@KafkaListener(id = "singleKafkaListener", autoStartup = "true", containerFactory = "kafkaListenerFactory", groupId = "${spring.kafka.consumer.group-id}", topics = {
			"#{'${spring.kafka.topics.consumer-topics}'.split(',')}" })
	public void singleListener(ConsumerRecord<?, ?> record) {
		try {
			Optional<?> message = Optional.ofNullable(record.value());
			if (message.isPresent()) {
				eventPublisher.publishEvent(new KafkaEvent(record));
			}
		} catch (Exception e) {
			log.error("Kafka监听异常" + e.getMessage(), e);
		}
	}

}
