package cn.dcube.ahead.kafka.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.ApplicationListener;

import cn.dcube.ahead.kafka.event.KafkaEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * kafkaEvent的监听。
 * @author yangfei
 *
 */
@Slf4j
public abstract class DefaultKafkaEventListener implements ApplicationListener<KafkaEvent> {

	Map<String, AtomicLong> topicMessageCounter = new HashMap<String, AtomicLong>();

	@Override
	public void onApplicationEvent(KafkaEvent event) {
		this.handle(event);
		// 这里进行topic的统计
		this.statistics(event);
	}

	protected abstract void handle(KafkaEvent event);

	protected abstract void statistics(KafkaEvent event);

}
