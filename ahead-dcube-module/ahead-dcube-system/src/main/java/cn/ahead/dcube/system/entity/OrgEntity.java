package cn.ahead.dcube.system.entity;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import cn.ahead.dcube.base.constant.AheadSysConstant;
import cn.ahead.dcube.jpa.entity.IAuditEntity;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity(name = "tb_sys_org1")
@Getter
@Setter
public class OrgEntity extends IAuditEntity {

	@Column(name = "parent_id")
	private Long parentId;
	
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "parent_id", insertable = false, updatable = false, nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
	private OrgEntity parentOrg;

	private String code;

	private String name;

	@Column(name = "short_name")
	private String shortName;

	/** 行政区划 */
	private String region;

	/** 负责人 */
	private String leader;

	/** 联系电话 */
	private String phone;

	/** 邮箱 */
	private String email;

	/** 部门状态:0正常,1停用 */
	private Integer status = AheadSysConstant.ORG_STATUS_NORMAL;
	
	private String remark;

}
