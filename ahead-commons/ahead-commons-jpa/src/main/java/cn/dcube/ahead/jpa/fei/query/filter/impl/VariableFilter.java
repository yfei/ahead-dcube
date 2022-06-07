package cn.dcube.ahead.jpa.fei.query.filter.impl;

import java.util.Collections;
import java.util.List;

import cn.dcube.ahead.jpa.fei.query.QueryCondition;
import cn.dcube.ahead.jpa.fei.query.filter.IFilter;
import cn.dcube.ahead.jpa.fei.query.filter.exception.InvalidFilterException;
import cn.dcube.ahead.jpa.fei.query.filter.operator.ConditionOperator;

/**
 * 
 * 
 * 描述：
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp;变量Filter.无论何种Filter,解析到最后都是VariableFilter和ConstantFilter。
 * </p>
 * 创建日期：2016年11月16日 下午6:49:05<br>
 * @author：yangfei<br>
 * 
 * @since 1.0
 */
class VariableFilter extends BaseFilter {
	/**
	 * the alias.
	 */
	private String alias = QueryCondition.DEFAULT_ALIAS;

	/**
	 * the variable.
	 */
	private String variable;

	public VariableFilter(String variable) {
		this.variable = variable;
	}

	@Override
	public IFilter append(IFilter filter, ConditionOperator operator) throws InvalidFilterException {
		throw new InvalidFilterException("filter variable cannot append filter");
	}

	@Override
	public String getString() {
		return alias + "." + variable;
	}

	@Override
	public List<Object> getValues() {
		return Collections.emptyList();
	}

	@Override
	public void setTableAlias(String alias) {
		this.alias = alias;
	}
}
