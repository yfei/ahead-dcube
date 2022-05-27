package cn.dcube.ahead.elastic.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

/**
 * 抽象model
 * 
 * @author yangfei
 *
 */
@Data
public class BaseElasticModel {
	@Id
	private Long id;
}
