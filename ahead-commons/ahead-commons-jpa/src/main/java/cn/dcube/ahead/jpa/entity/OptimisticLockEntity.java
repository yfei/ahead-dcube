package cn.dcube.ahead.jpa.entity;

import javax.persistence.Version;

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
public abstract class OptimisticLockEntity extends IdentifierEntity {

	@Version
	private Long version;

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
