
package cn.dcube.ahead.jpa.entity;


import cn.dcube.ahead.entity.IEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;


/**
 * JDBC操作的id。该类型的实体类的注解使用org.springframework.data包中注解。
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
