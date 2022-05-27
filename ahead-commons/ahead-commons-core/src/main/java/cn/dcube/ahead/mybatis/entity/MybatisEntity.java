package cn.dcube.ahead.mybatis.entity;

import java.time.Instant;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * mybatis实体抽象类
 * 
 * @author yangfei
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public abstract class MybatisEntity extends MybatisOptimisticLockEntity {

	@CreatedBy
	@TableField("create_user")
	private String createUser;

	@LastModifiedBy
	@TableField("last_modify_user")
	private String lastModifyUser;

	@CreatedDate
	@TableField("create_time")
	private Instant createTime;

	@LastModifiedDate
	@TableField("last_modify_time")
	private Instant lastModifyTime;
}
