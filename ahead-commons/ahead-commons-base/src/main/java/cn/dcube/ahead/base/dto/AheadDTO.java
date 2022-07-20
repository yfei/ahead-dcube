package cn.dcube.ahead.base.dto;

import java.util.Map;

/**
 * 抽象DTO
 * @author yangfei
 *
 * @param <T>
 */
public abstract class AheadDTO<T> {
	
	private T id;
	
	// 其他的查询条件
	private Map<String,Object> queryParams;

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}

	public Map<String, Object> getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Map<String, Object> queryParams) {
		this.queryParams = queryParams;
	}
}
