package cn.ahead.dcube.jpa.fei.service;

import java.io.Serializable;
import java.util.List;

import cn.ahead.dcube.base.dao.IDao;
import cn.ahead.dcube.base.entity.IEntity;
import cn.ahead.dcube.jpa.fei.page.Pagination;
import cn.ahead.dcube.jpa.fei.query.QueryCondition;
import cn.ahead.dcube.jpa.fei.query.filter.IFilter;
import cn.ahead.dcube.jpa.fei.query.sort.ISort;

/**
 * <p>
 * &nbsp;&nbsp;&nbsp;&nbsp; JPA Service接口.
 * </p>
 * create on 2016/8/22 10:43
 *
 * @author yangfei.
 * @version 1.0
 * @since 1.0
 */
public interface IFeiService {

	public IDao getDao();

	public void setDao(IDao dao);

	public <T extends IEntity> void persist(T entity);

	public <T extends IEntity> void persistBatch(List<T> entities);

	public <T extends IEntity> void update(T entity);

	public <T extends IEntity> T getById(Class<T> clazz, Serializable id);

	public <T extends IEntity> List<T> getAll(Class<T> clazz);

	public <T extends IEntity> void delete(T entity);

	public <T extends IEntity> List<T> get(Class<T> clazz, QueryCondition<T> condition);

	public <T extends IEntity> List<T> get(Class<T> clazz, IFilter filter);

	public <T extends IEntity> List<T> get(Class<T> clazz, IFilter filter, ISort sort);

	public <T extends IEntity> Pagination<T> getByPageination(Class<T> clazz, QueryCondition<T> condition);
}
