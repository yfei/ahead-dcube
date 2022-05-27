
package cn.dcube.ahead.jpa.fei.query.sort.impl;

import cn.dcube.ahead.jpa.fei.query.sort.ISort;
import cn.dcube.ahead.base.util.StringUtils;

/**
 * <p>
 * Desc: .
 * </p>
 * Author:yangfei <br/>
 * Date:2016/2/1 14:54 <br/>
 * Version:1.0 <br/>
 */
public class Sort implements ISort {

	public static final String ASC = "ASC";

	public static final String DESC = "DESC";

	private String variable;

	private String direction = ASC;

	private String alias;

	private ISort next;

	public Sort(String variable, String direction) {
		this.variable = variable;
		this.direction = direction;
	}

	public Sort(String variable) {
		this.variable = variable;
	}

	public Sort(String variable, String direction, String alias) {
		this.variable = variable;
		this.direction = direction;
		if (StringUtils.isNotEmpty(direction)) {
			// 全部处理为大写
			this.direction = this.direction.toUpperCase();
		}
		this.alias = alias;
	}

	@Override
	public ISort append(ISort appendent) {
		if (this.next == null) {
			this.next = appendent;
		} else {
			this.next.append(appendent);
		}
		return this;
	}

	@Override
	public String getString() {
		StringBuffer str = new StringBuffer(alias + "." + variable + " " + direction);
		if (next != null) {
			str.append(", ").append(next.getString());
		}
		return str.toString();
	}

	@Override
	public void setAlias(String alias) {
		this.alias = alias;
		if (this.next != null) {
			this.next.setAlias(alias);
		}

	}
}
