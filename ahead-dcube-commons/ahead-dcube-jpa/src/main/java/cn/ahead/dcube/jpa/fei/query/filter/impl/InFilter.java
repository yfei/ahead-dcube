
package cn.ahead.dcube.jpa.fei.query.filter.impl;

import java.util.List;

import org.springframework.util.Assert;

import cn.ahead.dcube.jpa.fei.query.filter.operator.ConditionOperator;


/**
 * <p>
 * Desc: .
 * </p>
 * Author:yangfei <br/>
 * Date:2016/2/1 14:11 <br/>
 * Version:1.0 <br/>
 */
public class InFilter extends BaseFilter {

	private VariableFilter variable;

	private List<Object> values;

	public InFilter(String variable, List<Object> values) {
		Assert.notEmpty(values, "the params can not be empty");
		this.variable = new VariableFilter(variable);
		this.values = values;
	}

	@Override
	public String getString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < values.size(); i++) {
			sb.append("?,");
		}
		String placeHolder = sb.toString().substring(0, sb.length() - 1);
		return ConditionOperator.IN.getValue().replace("%0", variable.getString()).replace("%1", placeHolder);
	}

	@Override
	public List<Object> getValues() {
		return values;
	}

	@Override
	public void setTableAlias(String alias) {
		variable.setTableAlias(alias);
	}
}
