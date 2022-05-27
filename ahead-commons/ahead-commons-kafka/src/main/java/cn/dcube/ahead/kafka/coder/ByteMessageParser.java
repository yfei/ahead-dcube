package cn.dcube.ahead.kafka.coder;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Descriptors.EnumValueDescriptor;
import com.google.protobuf.MessageLite;

import cn.dcube.ahead.base.util.StringUtils;
import cn.dcube.ahead.base.util.ZipUtils;
import cn.dcube.ahead.commons.proto.transport.EventTransportEntity;
import cn.dcube.ahead.commons.proto.transport.EventTransportEntity.MessageType;
import cn.dcube.ahead.commons.proto.transport.EventTypeEnum;
import cn.dcube.ahead.commons.proto.util.ProtoBufUtils;
import cn.dcube.ahead.kafka.event.KafkaEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 字符串数组转换为EventTransportEntity
 * 
 * @author yangfei
 *
 */
@Slf4j
public class ByteMessageParser {

	/**
	 * byte数组反序列化为EventTransportEntity
	 * 
	 * @param messageType
	 * @param record
	 * @return
	 */
	public static EventTransportEntity deserializer(KafkaEvent record, MessageType messageType, String eventType) {
		EventTransportEntity eventTransportEntity = null;
		if (messageType.compareTo(MessageType.TRANSPORT) == 0) { // transport
			try {
				eventTransportEntity = ProtoBufUtils.deserializer(ZipUtils.unGZip((byte[]) record.getValue()),
						EventTransportEntity.class);
			} catch (Exception e) {
				log.error("kafka消息反序列化异常 topicName={},key={}", record.getTopic(), record.getKey());
			}
		} else if (messageType.compareTo(MessageType.PROTOBUF) == 0) { // protobuf
			eventTransportEntity = new EventTransportEntity(StringUtils.getUUID(), record.getTopic(), eventType);
			if (EventTypeEnum.EVENT.getCode().equals(eventType)) {
				Map<String, Object> objectMap = compatibleProcessingDeserializer(record.getTopic(),
						(byte[]) record.getValue());
				eventTransportEntity.setEventData(objectMap);
			} else {
				log.warn("不支持的Protobuf事件类型{}!", eventType);
			}
		} else if (messageType.compareTo(MessageType.JSON) == 0) { // json
			eventTransportEntity = new EventTransportEntity(StringUtils.getUUID(), record.getTopic(), eventType);
			try {
				Map<String, Object> objectMap = JSON.parseObject(new String((byte[]) record.getValue(), "UTF-8"));
				eventTransportEntity.setEventData(objectMap);
			} catch (UnsupportedEncodingException e) {
				log.error("kafka消息反序列化异常 topicName={},key={}", record.getTopic(), record.getKey());
			}
		} else if (messageType.compareTo(MessageType.STRING) == 0) { // 字符串
			eventTransportEntity = new EventTransportEntity(StringUtils.getUUID(), record.getTopic(), eventType);
			try {
				eventTransportEntity.setEventData(new String((byte[]) record.getValue(), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				log.error("kafka消息反序列化异常 topicName={},key={}", record.getTopic(), record.getKey());
			}
		}
		return eventTransportEntity;
	}

	/**
	 * 兼容处理
	 * 
	 * @param topic
	 * @param protobufData
	 * @return
	 */
	private static Map<String, Object> compatibleProcessingDeserializer(String topic, byte[] protobufData) {
		Map<String, Object> resultMap = new ConcurrentHashMap<>();
		boolean UnPackageUtilIsPresent = isPresent("cn.dcube.goku.lucy.data.packaging.UnPackageUtil");
		boolean CB_PigsyMetaDataIsPresent = isPresent("cn.dcube.goku.lucy.data.entity.CB_PigsyMetaData");
		boolean PB_QUERYIsPresent = isPresent("cn.dcube.goku.net.protobuf.PB_Query");
		boolean CB_BodyIsPresent = isPresent("cn.dcube.goku.lucy.data.entity.CB_Body");
		if (UnPackageUtilIsPresent && CB_PigsyMetaDataIsPresent && PB_QUERYIsPresent && CB_BodyIsPresent) {
			try {
				Class<?> UnPackageUtilCls = Class.forName("cn.dcube.goku.lucy.data.packaging.UnPackageUtil");
				Class<?> CB_PigsyMetaDataCls = Class.forName("cn.dcube.goku.lucy.data.entity.CB_PigsyMetaData");
				Class<?> CB_BodyCls = Class.forName("cn.dcube.goku.lucy.data.entity.CB_Body");

				Method unPackage = UnPackageUtilCls.getDeclaredMethod("unPackage", boolean.class, boolean.class,
						byte[].class);
				Object CB_PigsyMetaDataObj = unPackage.invoke(UnPackageUtilCls.newInstance(), false, false,
						protobufData);
				Object CB_BodyObj = CB_PigsyMetaDataCls.getMethod("getBody").invoke(CB_PigsyMetaDataObj);
				Object PB_QUERY_EventObj = CB_BodyCls.getMethod("getMessage").invoke(CB_BodyObj);
				if ("cn.dcube.goku.net.protobuf.PB_Query$Event".equals(PB_QUERY_EventObj.getClass().getName())) {
					Map<Descriptors.FieldDescriptor, Object> allFields = (Map<Descriptors.FieldDescriptor, Object>) PB_QUERY_EventObj
							.getClass().getMethod("getAllFields").invoke(PB_QUERY_EventObj);
					for (Map.Entry<Descriptors.FieldDescriptor, Object> entry : allFields.entrySet()) {
						if (entry.getValue() instanceof List) {
							for (com.google.protobuf.MapEntry<String, Object> en : (List<com.google.protobuf.MapEntry>) entry
									.getValue()) {
								resultMap.put(en.getKey(), en.getValue());
							}
						} else if (entry.getValue() instanceof EnumValueDescriptor) {
							// 处理枚举类型
							resultMap.put(entry.getKey().getName(),
									((EnumValueDescriptor) entry.getValue()).getNumber());
						} else {
							resultMap.put(entry.getKey().getName(), entry.getValue());
						}
					}
				}
			} catch (Exception e) {
				log.error("消费主题为 {} 的兼容消息时发生异常：", topic, e);
			}
		}
		return JSON.parseObject(JSON.toJSONString(resultMap), Map.class);
	}

	/**
	 * 序列化消息
	 * 
	 * @param topic
	 * @param messageType
	 * @param message
	 */
	public static byte[] serializer(String topic, MessageType messageType, Object message) {
		// 序列化成ProtoBuf数据结构
		byte[] messageData = null;
		if (messageType.compareTo(MessageType.STRING) == 0 || messageType.compareTo(MessageType.JSON) == 0) {
			if (message instanceof String) {
				messageData = ((String) message).getBytes();
			}
		} else {
			if (message instanceof EventTransportEntity) { // 如果是transport
				if (messageType.compareTo(MessageType.TRANSPORT) == 0) {
					messageData = ZipUtils.gZip(ProtoBufUtils.serializer(message));
				} else if (messageType.compareTo(MessageType.PROTOBUF) == 0) { // 如果发送protobuf
					messageData = compatibleProcessingSerializer(topic,
							((EventTransportEntity) message).getEventData());
				}
			} else {
				log.warn("主题 {} 为无效主题，自动丢弃", topic);
			}
		}
		if (messageData == null || messageData.length == 0) {
			log.warn("主题为 {} 的消息体为空，自动丢弃", topic);
		}

		return messageData;
	}

	/**
	 * 兼容处理
	 * 
	 * @param topic
	 * @param dataObj
	 * @return
	 */
	private static byte[] compatibleProcessingSerializer(String topic, Object dataObj) {
		// 序列化成ProtoBuf数据结构
		byte[] protobufData = new byte[0];

		if (!(dataObj instanceof Map)) {
			if (dataObj instanceof MessageLite) {
				try {
					Class<?> MonkeyPackageSrvImpl_Cls = Class
							.forName("cn.dcube.goku.lucy.data.packaging.MonkeyPackageSrvImpl");
					Class<?> CB_SandyHeader_Cls = Class.forName("cn.dcube.goku.lucy.data.entity.CB_SandyHeader");
					Object header = CB_SandyHeader_Cls.getMethod("toBytes").invoke(CB_SandyHeader_Cls.newInstance());

					Method doPackage = MonkeyPackageSrvImpl_Cls.getMethod("doPackage", boolean.class, boolean.class,
							byte[].class, MessageLite.class);
					protobufData = (byte[]) doPackage.invoke(MonkeyPackageSrvImpl_Cls.newInstance(), false, false,
							header, (MessageLite) dataObj);
					return protobufData;
				} catch (Exception e) {
					log.error("发送主题为 {} 的兼容消息时发生异常：", topic, e);
				}
			}
			log.warn("消息兼容处理仅支持Map类型，请更换消息体的数据类型");
			return protobufData;
		}

		Map<String, Object> dataMap = (Map<String, Object>) dataObj;

		boolean PB_QUERYIsPresent = isPresent("cn.dcube.goku.net.protobuf.PB_Query");
		boolean MonkeyPackageSrvImplIsPresent = isPresent("cn.dcube.goku.lucy.data.packaging.MonkeyPackageSrvImpl");
		boolean CB_SandyHeaderIsPresent = isPresent("cn.dcube.goku.lucy.data.entity.CB_SandyHeader");
		if (PB_QUERYIsPresent && MonkeyPackageSrvImplIsPresent && CB_SandyHeaderIsPresent) {
			try {
				Class<?> PB_QUERY_Cls = Class.forName("cn.dcube.goku.net.protobuf.PB_Query");
				Class<?> MonkeyPackageSrvImpl_Cls = Class
						.forName("cn.dcube.goku.lucy.data.packaging.MonkeyPackageSrvImpl");
				Class<?> CB_SandyHeader_Cls = Class.forName("cn.dcube.goku.lucy.data.entity.CB_SandyHeader");

				Class<?> PB_QUERY_EventCls = null;
				for (Class<?> aClass : PB_QUERY_Cls.getClasses()) {
					if ("cn.dcube.goku.net.protobuf.PB_Query$Event".equals(aClass.getName())) {
						PB_QUERY_EventCls = aClass;
						break;
					}
				}

				/* 以下调用无参的、私有构造函数 */
				Constructor c0 = PB_QUERY_EventCls.getDeclaredConstructor();
				c0.setAccessible(true);
				Object builder = PB_QUERY_EventCls.getMethod("newBuilder").invoke(c0.newInstance());
				Class<?> builderClass = builder.getClass();
				Method[] methods = builderClass.getMethods();
				Set<String> notExtendsFields = Collections.synchronizedSet(new HashSet<>());
				for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
					String methodName = "set".concat(entry.getKey());
					for (Method method : methods) {
						if (methodName.equals(method.getName())) {
							method.invoke(builder, entry.getValue());
							notExtendsFields.add(entry.getKey());
							break;
						}
					}
				}

				Method putExtendsValmethod = builderClass.getMethod("putMappingfield", String.class, String.class);
				for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
					if (!notExtendsFields.contains(entry.getKey())) {
						putExtendsValmethod.invoke(builder, entry.getKey(), entry.getValue().toString());
					}
				}

				Object build = builderClass.getMethod("build").invoke(builder);

				Object header = CB_SandyHeader_Cls.getMethod("toBytes").invoke(CB_SandyHeader_Cls.newInstance());
				Method doPackage = MonkeyPackageSrvImpl_Cls.getMethod("doPackage", boolean.class, boolean.class,
						byte[].class, MessageLite.class);
				protobufData = (byte[]) doPackage.invoke(MonkeyPackageSrvImpl_Cls.newInstance(), false, false, header,
						(MessageLite) build);
			} catch (Exception e) {
				log.error("发送主题为 {} 的兼容消息时发生异常：", topic, e);
			}
		}

		return protobufData;
	}

	/**
	 * 判断由提供的类名(类的全限定名)标识的类是否存在并可以加载
	 * 
	 * @param name 要检查的类的名称
	 * @return
	 */
	public static boolean isPresent(String name) {
		try {
			Thread.currentThread().getContextClassLoader().loadClass(name);
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}
}