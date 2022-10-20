package cn.ahead.dcube.system.dict.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.ahead.dcube.jpa.entity.IdentifierEntity;
import lombok.Data;

/**
 * 简单字典
 * @author yangfei
 *
 */
@Table
@Entity(name = "tb_sys_simple_dict_type")
@Data
public class SimpleDictTypeEntity extends IdentifierEntity {

	// 字典类型
	private String type;

	// 字典名称/value
	private String name;

	// 备注
	private String remark;

}
