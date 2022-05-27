package cn.dcube.ahead.module.curd.entity;

import cn.dcube.ahead.jpa.entity.IdentifierEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;


@Data
@EqualsAndHashCode(callSuper=false)
@Entity(name="tb_user_mybatis")
public class UserEntity2 extends IdentifierEntity {
	
	private String name;
	
	private int age;
	
	private String remark;
	
	@Column(name="nick_name")
	private String nickName;

}
