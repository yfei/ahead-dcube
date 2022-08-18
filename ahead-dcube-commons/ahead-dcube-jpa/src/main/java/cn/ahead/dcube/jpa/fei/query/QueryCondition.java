package cn.ahead.dcube.jpa.fei.query;

import cn.ahead.dcube.jpa.fei.page.Pagination;
import cn.ahead.dcube.jpa.fei.query.filter.IFilter;
import cn.ahead.dcube.jpa.fei.query.sort.ISort;

/**
 * 
 * 
 * 描述：
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp;封装分页、Filter和Sort对象。
 * </p>
 * 创建日期：2016年11月16日 下午6:41:10<br>
 * @author：yangfei<br>
 * 
 * @since 1.0
 */
public class QueryCondition<T> {
	/**
	 * default alias
	 */
	public static final String DEFAULT_ALIAS = "o";

	/**
	 * the filter
	 */
	private IFilter filter;

	/**
	 * the sort
	 */
	private ISort sort;

	/**
	 * the pagination
	 */
	private Pagination<T> pagination;

	public IFilter getFilter() {
		return filter;
	}

	public void setFilter(IFilter filter) {
		this.filter = filter;
	}

	public ISort getSort() {
		return sort;
	}

	public void setSort(ISort sort) {
		this.sort = sort;
	}

	public Pagination<T> getPagination() {
		return pagination;
	}

	public void setPagination(Pagination<T> pagination) {
		this.pagination = pagination;
	}
}
