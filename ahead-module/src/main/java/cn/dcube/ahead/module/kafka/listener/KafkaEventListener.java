package cn.dcube.ahead.module.kafka.listener;

import org.springframework.stereotype.Service;

import cn.dcube.ahead.kafka.event.KafkaEvent;
import cn.dcube.ahead.kafka.listener.DefaultKafkaEventListener;
import lombok.extern.slf4j.Slf4j;

/**
 * kafka消息监听器
 * 
 * @author yangfei
 *
 */
@Service
@Slf4j
public class KafkaEventListener extends DefaultKafkaEventListener {

	@Override
	protected void handle(KafkaEvent event) {
		try {
			System.out.println(event);
		} catch (Exception e) {
			log.error("", e);
		}

	}

	@Override
	protected void statistics(KafkaEvent event) {
		// TODO Auto-generated method stub

	}

}
