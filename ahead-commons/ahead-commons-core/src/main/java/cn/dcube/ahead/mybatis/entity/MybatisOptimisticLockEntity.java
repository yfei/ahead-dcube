package cn.dcube.ahead.mybatis.entity;

import com.baomidou.mybatisplus.annotation.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public abstract class MybatisOptimisticLockEntity extends MyBatisIdentifierEntity {

	@Version
	private Long version;

}
