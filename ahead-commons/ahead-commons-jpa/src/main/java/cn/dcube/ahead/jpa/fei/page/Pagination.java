package cn.dcube.ahead.jpa.fei.page;

/**
 * 
 * 
 * 描述：
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp;分页对象类。
 * </p>
 * 创建日期：2016年11月16日 下午6:34:21<br>
 * 
 * @author：yangfei<br>
 * 
 * @since 1.0
 */
public class Pagination<T> extends cn.dcube.ahead.base.page.Pagination<T> {

	public Pagination(int currentPage, int recordsPerPage) {
		super(currentPage, recordsPerPage);
	}

}
