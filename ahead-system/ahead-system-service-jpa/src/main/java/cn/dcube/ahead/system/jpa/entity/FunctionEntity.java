package cn.dcube.ahead.system.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.dcube.ahead.jpa.entity.IAuditEntity;

@Table
@Entity(name = "tb_sys_function")
public class FunctionEntity extends IAuditEntity {

}
