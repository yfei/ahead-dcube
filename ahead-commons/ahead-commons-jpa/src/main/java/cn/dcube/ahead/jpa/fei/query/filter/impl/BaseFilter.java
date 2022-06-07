package cn.dcube.ahead.jpa.fei.query.filter.impl;

import java.util.Collections;
import java.util.List;

import cn.dcube.ahead.jpa.fei.query.filter.IFilter;
import cn.dcube.ahead.jpa.fei.query.filter.exception.InvalidFilterException;
import cn.dcube.ahead.jpa.fei.query.filter.operator.ConditionOperator;

/**
 * 
 * 
 * 描述：
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp;IFilter默认的Append实现类。
 * </p>
 * 创建日期：2016年11月16日 下午6:40:21<br>
 * @author：yangfei<br>
 * 
 * @since 1.0
 * @version 1.0
 */
public abstract class BaseFilter implements IFilter {

	@Override
	public IFilter appendAnd(IFilter filter) throws InvalidFilterException {
		return append(filter, ConditionOperator.AND);
	}

	@Override
	public IFilter appendOr(IFilter filter) throws InvalidFilterException {
		return append(filter, ConditionOperator.OR);
	}

	@Override
	public IFilter append(IFilter filter, ConditionOperator operator) throws InvalidFilterException {
		return new SimpleFilter(this, filter, operator);
	}

	@Override
	public List<Object> getValues() {
		return Collections.emptyList();
	}

	public void setTableAlias(String alias) {
	}
}
