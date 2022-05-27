package cn.dcube.ahead.kafka.config;

import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

import lombok.Data;

/**
 * Kafka配置项
 * 
 * @author：yangfei<br>
 * @date：2021年3月30日下午5:44:55
 * @since 1.0
 */
@EnableKafka
@Configuration
@ConfigurationProperties(prefix = "spring.kafka.topics")
// 存在spring.kafka.bootstrap-servers时才生效
@ConditionalOnProperty(prefix = "spring.kafka", name = { "bootstrap-servers" })
@Data
public class KafkaTopicConfig {

	// 消费者topic
	private String consumerTopics;

	// 生产者topic
	private Map<String, String> producerTopics;

	public String getProducerTopic(String key, String defaultV) {
		if (producerTopics.containsKey(key)) {
			return producerTopics.get(key);
		}
		return defaultV;
	}
}
