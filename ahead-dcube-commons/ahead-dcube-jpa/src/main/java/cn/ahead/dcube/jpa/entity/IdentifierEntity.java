
package cn.ahead.dcube.jpa.entity;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import cn.ahead.dcube.base.entity.IEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @date: 2021-12-21 14:06 <br>
 * @author: yangfei <br>
 * @version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public abstract class IdentifierEntity implements IEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;


}
