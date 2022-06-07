/*
 * Copyright (c) cecgw 2012-2016 All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.dcube.ahead.jpa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * 描述：<p>&nbsp;&nbsp;&nbsp;&nbsp;功能描述,该部分必须以中文句号结尾。</p>
 * 创建日期：2016年12月23日 下午5:11:20<br>
 * @author：yangfei<br> 
 * @since 1.0
 */
@MappedSuperclass
public class IAuditEntity extends OptimisticLockEntity {
	/**
	 * 创建用户
	 */
	@Column(name="create_user")
	private String createUser;

	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;
	/**
	 * 最后修改用户
	 */
	@Column(name="last_modify_user")
	private String lastModifyUser;

	/**
	 * 最后修改时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_modify_time")
	private Date lastModifyTime;

	/**
	 * @return the createUser
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser the createUser to set
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the lastModifyUser
	 */
	public String getLastModifyUser() {
		return lastModifyUser;
	}

	/**
	 * @param lastModifyUser the lastModifyUser to set
	 */
	public void setLastModifyUser(String lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	/**
	 * @return the lastModifyTime
	 */
	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	/**
	 * @param lastModifyTime the lastModifyTime to set
	 */
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

}
