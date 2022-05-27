package cn.dcube.ahead.kafka.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Listener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

import javax.annotation.Resource;

/**
 * Kafka配置项
 * 
 * @author：yangfei<br>
 * @date：2021年3月30日下午5:44:55
 * @since 1.0
 */
@EnableKafka
@Configuration
// 存在spring.kafka.bootstrap-servers时才生效
@ConditionalOnProperty(prefix = "spring.kafka", name = { "bootstrap-servers" })
public class KafkaConfig {

	@Resource
	private KafkaProperties kafkaProperties;

	@Resource
	private KafkaListenerEndpointRegistry registry;

	public boolean isBatchListener() {
		return kafkaProperties.getListener().getType().compareTo(Listener.Type.SINGLE) != 0;
	}

	public boolean isAutoCommit() {
		return kafkaProperties.getConsumer().getEnableAutoCommit();
	}

	public boolean isManual() {
		return kafkaProperties.getListener().getAckMode().compareTo(ContainerProperties.AckMode.MANUAL) == 0
				|| kafkaProperties.getListener().getAckMode()
						.compareTo(ContainerProperties.AckMode.MANUAL_IMMEDIATE) == 0;

	}

	/**
	 * 普通主题监听器配置
	 *
	 * @return
	 */
	@Bean(name = "kafkaListenerFactory")
	@ConditionalOnMissingBean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, byte[]>> kafkaListenerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, byte[]> factory = kafkaListenerContainerFactory();
		factory.setConcurrency(kafkaProperties.getListener().getConcurrency());
		// 是否批量
		factory.setBatchListener(isBatchListener());
		// 设置提交偏移量的方式
		// MANUAL_IMMEDIATE 表示消费一条提交一次；MANUAL表示批量提交一次
		factory.getContainerProperties().setAckMode(kafkaProperties.getListener().getAckMode());
		return factory;
	}

	/**
	 * 并发KafkaListener
	 * 
	 * @return
	 */
	private ConcurrentKafkaListenerContainerFactory<String, byte[]> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, byte[]> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	private ConsumerFactory<String, byte[]> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}

	private Map<String, Object> consumerConfigs() {
		return kafkaProperties.buildConsumerProperties();
	}

	@Bean
	public KafkaTemplate<String, byte[]> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	/**
	 * producerFactory
	 * 
	 * @return
	 */
	private ProducerFactory<String, byte[]> producerFactory() {
		DefaultKafkaProducerFactory<String, byte[]> producerFactory = new DefaultKafkaProducerFactory<>(
				producerConfigs());
		return producerFactory;
	}

	private Map<String, Object> producerConfigs() {
		return kafkaProperties.buildProducerProperties();
	}

}
