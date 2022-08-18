package cn.ahead.dcube.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 描述：<p>&nbsp;&nbsp;&nbsp;&nbsp;功能描述,该部分必须以中文句号结尾。</p>
 * 创建日期：2016年12月23日 下午5:11:20<br>
 * @author：yangfei<br> 
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public class IAuditEntity extends OptimisticLockEntity {
	/**
	 * 创建用户
	 */
	@Column(name="create_user")
	private String createUser;

	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;
	/**
	 * 最后修改用户
	 */
	@Column(name="last_modify_user")
	private String lastModifyUser;

	/**
	 * 最后修改时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_modify_time")
	private Date lastModifyTime;


}
