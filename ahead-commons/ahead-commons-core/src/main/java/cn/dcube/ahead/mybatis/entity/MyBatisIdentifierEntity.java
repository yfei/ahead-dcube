
package cn.dcube.ahead.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import cn.dcube.ahead.entity.IEntity;
import lombok.Data;

@Data
public abstract class MyBatisIdentifierEntity implements IEntity {

	@TableId(type = IdType.AUTO)
	private Long id;


}
