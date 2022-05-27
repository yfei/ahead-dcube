package cn.dcube.ahead.module.es;

import java.util.Map;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.DynamicTemplates;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;
import org.springframework.data.util.TypeInformation;
import org.springframework.stereotype.Component;

import cn.dcube.ahead.commons.proto.transport.EventTransportEntity;
import cn.dcube.ahead.elastic.model.BaseElasticModel;
import lombok.Data;

@Data
@Document(indexName = "event-base", shards = 5, replicas = 1)
@Setting(settingPath = "eb.json")
@Component("eventBase4Es")
public class EventBase4ES extends BaseElasticModel {

	@Field(index = false, store = true, type = FieldType.Integer)
	private int a;

	/**
	 * 帐户
	 */
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String account;

	/**
	 * bugtraq
	 */
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String bugtraq;

	@Field(index = false, store = true, type = FieldType.Integer)
	private int bwl_flag;

	@Field(index = false, store = true, type = FieldType.Integer)
	private int c;

	@Field(index = false, store = true, type = FieldType.Keyword)
	private String class_name;

	/**
	 * 分类
	 */
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer clazz;

	/**
	 * Collector的Id
	 */
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer collector_id;

	/**
	 * Collector的IP
	 */
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String collector_ip;

	/**
	 * 事件次数
	 */
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer counts;

	/**
	 * 客户id
	 */
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer customer_id;

	/**
	 * cve
	 */
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String cve;

	@Field(index = false, store = true, type = FieldType.Double)
	private double df1;

	@Field(index = false, store = true, type = FieldType.Double)
	private double df2;

	@Field(index = false, store = true, type = FieldType.Double)
	private double df3;

	@Field(index = false, store = true, type = FieldType.Double)
	private double df4;

	@Field(index = false, store = true, type = FieldType.Double)
	private double df5;

	@Field(index = false, store = true, type = FieldType.Keyword)
	private String dns;

	@Field(index = false, store = true, type = FieldType.Integer)
	private int dst_asset_level;
	@Field(index = false, store = true, type = FieldType.Integer)
	private int dst_assetid;
	@Field(index = false, store = true, type = FieldType.Integer)
	private int dst_busi_id;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String dst_busi_link;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String dst_busi_name;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String dst_geo1_code;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String dst_geo1_name;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String dst_geo2_code;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String dst_geo2_name;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String dst_geo3_code;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String dst_geo3_name;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String dst_hostname;

	/**
	 * dstin
	 */
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer dst_in;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String dst_in_name;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String dst_ip_str;

	/**
	 * 目的ipv4
	 */
	@Field(index = false, store = true, type = FieldType.Long)
	private Long dst_ipv4 = 0L;

	/**
	 * 目的ipv6
	 */
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String dst_ipv6;

	@Field(index = false, store = true, type = FieldType.Keyword)
	private String dst_latitude;

	@Field(index = false, store = true, type = FieldType.Keyword)
	private String dst_longitude;

	@Field(index = false, store = true, type = FieldType.Keyword)
	private String dst_org_name;

	/**
	 * 目的组织id
	 */
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer dst_orgid;

	/**
	 * 目的端口
	 */
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer dst_port;

	/**
	 * 结束时间 格式：yyyy-MM-dd HH:mm:ss
	 */
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String end_time;

	/**
	 * 扩展分类
	 */
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer family;

	@Field(index = false, store = true, type = FieldType.Keyword)
	private String family_name;
	@Field(index = false, store = true, type = FieldType.Integer)
	private int i;

	@Field(index = false, store = true, type = FieldType.Keyword)
	private String is_bad_code;

	@Field(index = false, store = true, type = FieldType.Keyword)
	private String is_bad_dstip;

	@Field(index = false, store = true, type = FieldType.Keyword)
	private String is_bad_mail_bcc;

	@Field(index = false, store = true, type = FieldType.Keyword)
	private String is_bad_mail_cc;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String is_bad_mail_from;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String is_bad_mail_to;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String is_bad_srcip;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String is_bad_url;

	/**
	 * 消息级别
	 */
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer level;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String mail_bcc;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String mail_cc;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String mail_from;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String mail_to;

	/**
	 * info_effect1
	 */
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer num1;

	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer num10;

	/**
	 * info_effect2
	 */
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer num2;

	/**
	 * info_effect3
	 */
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer num3;

	/**
	 * info_effect4
	 */
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer num4;

	/**
	 * info_effect5
	 */
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer num5;

	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer num6;
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer num7;
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer num8;
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer num9;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String org_name;
	@Field(index = false, store = true, type = FieldType.Integer)
	private int orgid;

	/**
	 * 原始日志
	 */
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String original;
	@Field(index = false, store = true, type = FieldType.Integer)
	private int platform_id;

	/**
	 * 协议类型
	 */
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer protocol;

	/**
	 * 厂商事件id
	 */
	@Field(index = false, store = true, type = FieldType.Long)
	private Long raw_id;

	/**
	 * 厂商事件level
	 */
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String raw_level;

	/**
	 * 接收时间 格式：yyyy-MM-dd HH:mm:ss
	 */
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String receive_time;

	/**
	 * 传感器id
	 */
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer sensor_id;

	/**
	 * 传感器ip
	 */
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String sensor_ip;

	/**
	 * 传感器掩码
	 */
	@Field(index = false, store = true, type = FieldType.Long)
	private long sensor_mask;

	/**
	 * 传感器类型
	 */
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer sensor_model;
	@Field(index = false, store = true, type = FieldType.Integer)
	private int src_asset_level;
	@Field(index = false, store = true, type = FieldType.Integer)
	private int src_assetid;
	@Field(index = false, store = true, type = FieldType.Integer)
	private int src_busi_id;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String src_busi_link;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String src_busi_name;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String src_geo1_code;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String src_geo1_name;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String src_geo2_code;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String src_geo2_name;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String src_geo3_code;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String src_geo3_name;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String src_hostname;

	/**
	 * srcin
	 */
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer src_in;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String src_in_name;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String src_ip_str;

	/**
	 * 源ipv4
	 */
	@Field(index = false, store = true, type = FieldType.Long)
	private Long src_ipv4 = 0L;

	/**
	 * 源ipv6
	 */
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String src_ipv6;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String src_latitude;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String src_longitude;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String src_org_name;

	/**
	 * 源组织id
	 */
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer src_orgid;

	/**
	 * 源端口
	 */
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer src_port;

	/**
	 * 发生时间 格式：yyyy-MM-dd HH:mm:ss
	 */
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String start_time;

	/**
	 * info_str1
	 */
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String str1;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String str10;

	/**
	 * info_str2
	 */
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String str2;

	/**
	 * info_str3
	 */
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String str3;

	/**
	 * info_str4
	 */
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String str4;

	/**
	 * info_str5
	 */
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String str5;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String str6;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String str7;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String str8;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String str9;

	/**
	 * 子类
	 */
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer subclass;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String subclass_name;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String summary;
	@Field(index = false, store = true, type = FieldType.Keyword)
	private String url;
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer src_domain_id;
	@Field(index = false, store = true, type = FieldType.Integer)
	private Integer dst_domain_id;

	public static EventBase4ES trans(EventTransportEntity transportEvent) {
		EventBase4ES eb = new EventBase4ES();
		eb.setId(Long.valueOf(transportEvent.getEventId()));
		Map<String, Object> eventData = (Map<String, Object>) transportEvent.getEventData();
		return eb;
	}
	
}
