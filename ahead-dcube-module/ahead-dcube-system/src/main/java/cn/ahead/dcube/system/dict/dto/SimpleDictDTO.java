package cn.ahead.dcube.system.dict.dto;

import java.util.List;

import javax.persistence.Column;

import lombok.Data;

/**
 * simple dto
 * @author yangfei
 *
 */
@Data
public class SimpleDictDTO {
	
	public static final int STATUS_NORMAL = 0;
	
	public static final int STATUS_UNUSE = 1;

	private Long id;

	// 字典类型
	private String type;

	// 字典code
	private String code;

	// 字典名称/value
	private String name;

	// 备注
	private String remark;

	private List<SimpleDictDTO> children;
	
	
	/**
	 * 字典状态 0 正常 1 停用
	 */
	private Integer status;
	/**
	 * 字典顺序
	 */
	private Integer sortNum;

}
