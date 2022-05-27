package cn.dcube.ahead.module.curd.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import cn.dcube.ahead.mybatis.entity.MybatisEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@TableName("tb_user_mybatis")
public class UserEntity extends MybatisEntity {
	
	private String name;
	
	private int age;
	
	private String remark;
	
	@TableField("nick_name")
	private String nickName;

}
