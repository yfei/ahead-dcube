package cn.ahead.dcube.jpa.fei.page;

import java.util.List;

/**
 * 
 * 
 * 描述：
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp;分页对象类。
 * </p>
 * 创建日期：2016年11月16日 下午6:34:21<br>
 * @author：yangfei<br>
 * 
 * @since 1.0
 */
public class Pagination<T> {
	/**
	 * 每页返回的对象
	 */
	private List<T> records;

	/**
	 * 当前页
	 */
	private int currentPage;

	/**
	 * 每页展示的条数
	 */
	private int recordsPerPage = 10;

	/**
	 * 总页数
	 */
	private long totalPage;


	/**
	 * 总计数
	 */
	private Long recordsCount;

	/**
	 * 
	 * @param currentPage
	 *            当前页数
	 * @param recordsPerPage
	 *            每页的记录数
	 */
	public Pagination(int currentPage, int recordsPerPage) {
		this.currentPage = currentPage;
		this.recordsPerPage = recordsPerPage;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartRowPosition() {
		return (this.currentPage - 1) * this.recordsPerPage;
	}

	public Long getRecordsCount() {
		return recordsCount;
	}

	public void setRecordsCount(Long recordsCount) {
		this.recordsCount = recordsCount;
		this.totalPage = (long) Math.ceil(this.recordsCount.doubleValue()/this.recordsPerPage);
	}
	
}
