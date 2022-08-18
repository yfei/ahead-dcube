package cn.ahead.dcube.jpa.fei.query.filter.impl;

import java.util.Arrays;
import java.util.List;

import cn.ahead.dcube.jpa.fei.query.filter.IFilter;
import cn.ahead.dcube.jpa.fei.query.filter.exception.InvalidFilterException;
import cn.ahead.dcube.jpa.fei.query.filter.operator.ConditionOperator;

/**
 * 
 * 
 * 描述：
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp;变量值Filter。
 * </p>
 * 创建日期：2016年11月16日 下午6:45:10<br>
 * @author：yangfei<br>
 * 
 * @since 1.0
 */
class ConstantFilter extends BaseFilter {
	/**
	 * 变量值
	 */
	private Object value;

	public ConstantFilter(Object value) {
		this.value = value;
	}

	@Override
	public IFilter append(IFilter filter, ConditionOperator operator) throws InvalidFilterException {
		throw new InvalidFilterException("filter constant cannot append filter");
	}

	/**
	 * use"?" instead of real value, and then set real value by
	 * Query.setParameter().
	 */
	@Override
	public String getString() {
		return "?";
	}
	/**
	 * get value list.
	 */
	@Override
	public List<Object> getValues() {
		return Arrays.asList(value);
	}
}
