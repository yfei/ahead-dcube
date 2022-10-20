package cn.ahead.dcube.system.dict.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import cn.ahead.dcube.jpa.entity.OptimisticLockEntity;
import cn.ahead.dcube.system.dict.dto.SimpleDictDTO;
import lombok.Data;

/**
 * 简单字典
 * 
 * @author yangfei
 *
 */
@Table
@Entity(name = "tb_sys_simple_dict")
@Data
public class SimpleDictEntity extends OptimisticLockEntity {

	// 父id,针对层级字典
	@Column(name = "p_code")
	private String parentCode;

	// 字典类型
	private String type;

	// 字典code
	private String code;

	// 字典名称/value
	private String name;

	// 备注
	private String remark;

	/**
	 * 字典状态 0 正常 1 停用
	 */
	private Integer status;
	/**
	 * 字典顺序
	 */
	@Column(name = "sort_num")
	private Integer sortNum;
	
	
	public SimpleDictDTO convert() {
		SimpleDictDTO dto = new SimpleDictDTO();
		BeanUtils.copyProperties(this, dto);
		return dto;
	}

}
