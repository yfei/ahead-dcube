package cn.dcube.ahead.kafka.event;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * kafkaEvent
 * 
 * @author：yangfei<br>
 * @date：2021年3月30日下午2:46:40
 * @since 1.0
 */
@Getter
@Setter
public class KafkaEvent extends ApplicationEvent {

	private static final long serialVersionUID = -8206408664339671568L;

	public String topic;

	public Object key;

	public Object value;

	public KafkaEvent(ConsumerRecord<?, ?> record) {
		super(record);
		this.topic = record.topic();
		this.key = record.key();
		this.value = record.value();
	}
}
