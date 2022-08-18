package cn.ahead.dcube.jpa.entity;

import javax.persistence.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 
 * 描述：
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp;乐观锁Entity抽象类。
 * </p>
 * 创建日期：2016年11月16日 下午6:31:29<br>
 * @author：yangfei<br>
 * 
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class OptimisticLockEntity extends IdentifierEntity {

	@Version
	private Long version;

}
