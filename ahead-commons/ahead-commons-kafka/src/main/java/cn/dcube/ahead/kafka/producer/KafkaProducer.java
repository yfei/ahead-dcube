package cn.dcube.ahead.kafka.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

import cn.dcube.ahead.commons.proto.transport.EventTransportEntity;
import cn.dcube.ahead.commons.proto.transport.EventTransportEntity.MessageType;
import cn.dcube.ahead.commons.proto.util.ProtoBufUtils;
import cn.dcube.ahead.kafka.coder.ByteMessageParser;
import cn.dcube.ahead.kafka.config.KafkaConfig;
import cn.dcube.ahead.base.util.ZipUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * Kafka生产者
 * 
 * @author：yangfei<br>
 * @date：2021年3月30日下午2:30:47
 * @since 1.0
 */
@Slf4j
@Component
@ConditionalOnBean(KafkaConfig.class)
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, byte[]> kafkaTemplate;

	public void sendMessage(String topic, MessageType messageType, EventTransportEntity eventTransportEntity) {
		byte[] protobufData = ByteMessageParser.serializer(topic, messageType, eventTransportEntity);
		sendByteMessage(topic, protobufData, null, null);
	}

	public void sendMessage(String topic, MessageType messageType, EventTransportEntity eventTransportEntity,
			@Nullable SuccessCallback<SendResult<String, byte[]>> successCallback,
			@Nullable FailureCallback failureCallback) {
		byte[] protobufData = ByteMessageParser.serializer(topic, messageType, eventTransportEntity);
		sendByteMessage(topic, protobufData, successCallback, failureCallback);
	}

	public void sendProtobufMessage(String topic, EventTransportEntity eventTransportEntity) {
		byte[] protobufData = ByteMessageParser.serializer(topic, MessageType.PROTOBUF, eventTransportEntity);
		sendByteMessage(topic, protobufData, null, null);
	}

	public void sendProtobufMessage(String topic, EventTransportEntity eventTransportEntity,
			@Nullable SuccessCallback<SendResult<String, byte[]>> successCallback,
			@Nullable FailureCallback failureCallback) {
		byte[] protobufData = ByteMessageParser.serializer(topic, MessageType.PROTOBUF, eventTransportEntity);
		sendByteMessage(topic, protobufData, successCallback, failureCallback);
	}

	public void sendTransportMessage(String topic, EventTransportEntity eventTransportEntity) {
		byte[] protobufData = ZipUtils.gZip(ProtoBufUtils.serializer(eventTransportEntity));
		sendByteMessage(topic, protobufData, null, null);
	}

	public void sendTransportMessage(String topic, EventTransportEntity eventTransportEntity,
			@Nullable SuccessCallback<SendResult<String, byte[]>> successCallback,
			@Nullable FailureCallback failureCallback) {
		byte[] protobufData = ZipUtils.gZip(ProtoBufUtils.serializer(eventTransportEntity));
		sendByteMessage(topic, protobufData, successCallback, failureCallback);
	}

	public void sendStringMessage(String topic, String message) {
		byte[] msgByte = message.getBytes();
		sendByteMessage(topic, msgByte, null, null);
	}

	public void sendStringMessage(String topic, String message,
			@Nullable SuccessCallback<SendResult<String, byte[]>> successCallback,
			@Nullable FailureCallback failureCallback) {
		byte[] msgByte = message.getBytes();
		sendByteMessage(topic, msgByte, successCallback, failureCallback);
	}

	public void sendByteMessage(String topic, byte[] message,
			@Nullable SuccessCallback<SendResult<String, byte[]>> successCallback,
			@Nullable FailureCallback failureCallback) {
		if (message == null || message.length == 0) {
			log.warn("主题为 {} 的消息体为空，自动丢弃", topic);
			return;
		}
		ListenableFuture<SendResult<String, byte[]>> sender = kafkaTemplate.send(new ProducerRecord<>(topic, message));
		sender.addCallback(result -> {
			log.debug("kafka数据发送成功,主题为{}!", topic);
			if (successCallback != null) {
				successCallback.onSuccess(result);
			}
		}, ex -> {
			log.error("kafka数据发送失败!");
			if (failureCallback != null) {
				failureCallback.onFailure(ex);
			}
		});
	}

}
