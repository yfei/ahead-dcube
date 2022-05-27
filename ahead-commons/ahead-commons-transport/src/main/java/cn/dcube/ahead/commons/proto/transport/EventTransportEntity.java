package cn.dcube.ahead.commons.proto.transport;

import io.protostuff.Tag;
import lombok.Getter;
import lombok.Setter;

/**
 * 基础 kafka消息通讯实体定义
 * @date: 2021-12-21 9:47 <br>
 * @author: yangfei <br>
 * @version: 1.0
 */
@Getter
@Setter
public class EventTransportEntity {

	public enum MessageType {
		PROTOBUF, TRANSPORT, STRING, JSON
	}

	/**
	 * 事件唯一标识
	 */
	@Tag(1)
	private String eventId;
	/**
	 * 事件名称,一般为topic
	 */
	@Tag(2)
	private String eventName;
	/**
	 * 事件值(简单值)
	 */
	@Tag(3)
	private String eventVal;
	/**
	 * 事件类型
	 */
	@Tag(4)
	private String eventType;

	/**
	 * 事件数据(按需传参)
	 */
	@Tag(5)
	private Object eventData;

	/**
	 * @param eventId   事件ID
	 * @param eventName 事件名称
	 * @param eventType 事件类型
	 */
	public EventTransportEntity(String eventId, String eventName, String eventType) {
		this(eventId, eventName, eventType, null);
	}

	/**
	 * @param eventId   事件ID
	 * @param eventName 事件名称
	 * @param eventVal  事件值(简单值)
	 * @param eventType 事件类型
	 */
	public EventTransportEntity(String eventId, String eventName, String eventVal, String eventType) {
		this(eventId, eventName, eventVal, eventType, null);
	}

	/**
	 * @param eventId   事件ID
	 * @param eventName 事件名称
	 * @param eventType 事件类型
	 * @param eventData 事件数据(按需传参)
	 */
	public EventTransportEntity(String eventId, String eventName, String eventType, Object eventData) {
		this(eventId, eventName, null, eventType, eventData);
	}

	/**
	 * @param eventId   事件ID
	 * @param eventName 事件名称
	 * @param eventVal  事件值(简单值)
	 * @param eventType 事件类型
	 * @param eventData 事件数据(按需传参)
	 */
	public EventTransportEntity(String eventId, String eventName, String eventVal, String eventType, Object eventData) {
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventVal = eventVal;
		this.eventType = eventType;
		this.eventData = eventData;
	}

}
