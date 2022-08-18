package cn.ahead.dcube.jpa.fei.query.filter;

import java.util.List;

import cn.ahead.dcube.jpa.fei.query.filter.operator.ConditionOperator;

/**
 * 
 * 
 * 描述：
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp;Filter接口。
 * </p>
 * 创建日期：2016年11月16日 下午6:35:41<br>
 * @author：yangfei<br>
 * 
 * @since 1.0
 */
public interface IFilter {
	/**
	 * append and another filter.
	 * 
	 * @param filter
	 *            the and-appending filter.
	 * @return filter
	 */
	public IFilter appendAnd(IFilter filter);

	/**
	 * append or another filter.
	 * 
	 * @param filter
	 *            the or-appending filter.
	 * @return filter
	 */
	public IFilter appendOr(IFilter filter);

	/**
	 * append another filter
	 * 
	 * @param filter
	 *            the appending filter
	 * @param operator
	 *            the append operator,and/or
	 * @return filter
	 */
	public IFilter append(IFilter filter, ConditionOperator operator);

	/**
	 * set table alias
	 * 
	 * @param alias
	 */
	public void setTableAlias(String alias);

	/**
	 * get filter string
	 * 
	 * @return the string .
	 */
	public String getString();

	/**
	 * get filter values
	 * 
	 * @return the values.
	 */
	public List<Object> getValues();

}
