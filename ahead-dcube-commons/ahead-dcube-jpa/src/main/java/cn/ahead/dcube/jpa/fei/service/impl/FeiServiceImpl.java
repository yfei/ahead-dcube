package cn.ahead.dcube.jpa.fei.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ahead.dcube.base.dao.IDao;
import cn.ahead.dcube.base.entity.IEntity;
import cn.ahead.dcube.base.exception.AheadServiceException;
import cn.ahead.dcube.jpa.fei.dao.IFeiDao;
import cn.ahead.dcube.jpa.fei.page.Pagination;
import cn.ahead.dcube.jpa.fei.query.QueryCondition;
import cn.ahead.dcube.jpa.fei.query.filter.IFilter;
import cn.ahead.dcube.jpa.fei.query.sort.ISort;
import cn.ahead.dcube.jpa.fei.service.IFeiService;

/**
 * JPAService实现类,当不需要每个entity一个service的时候, 则可以在controller中注入该service
 * create on 2016/8/22 10:43
 *
 * @author yangfei.
 * @version 1.0
 * @since 1.0
 */
@Service(value = "feiService")
public class FeiServiceImpl implements IFeiService {
    /**
     * the logger
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name="feiDao")
    protected IFeiDao dao;

    public IFeiDao getDao() {
        return dao;
    }

    @Override
    public void setDao(IDao dao) {
        if (dao instanceof IFeiDao) {
            this.dao = (IFeiDao) dao;
        } else {
            logger.error("the dao is not instance of IFeiDao.This service only support IFeiDao.");
            throw new AheadServiceException("the dao is not instance of IFeiDao.This service only support IFeiDao.");
        }
    }

    @Transactional
    @Override
    public <T extends IEntity> void persist(T entity) {
        dao.persist(entity);
    }

    @Transactional
    @Override
    public <T extends IEntity> void persistBatch(List<T> entities) {
        //dao.persistBatch(entities);
        for (T entity : entities) {
            dao.getEntityManager().merge(entity);
        }
        dao.getEntityManager().flush();
        dao.getEntityManager().clear();
    }

    @Transactional
    @Override
    public <T extends IEntity> void update(T entity) {
        dao.update(entity);
    }

    @Override
    public <T extends IEntity> T getById(Class<T> clazz, Serializable id) {
        return dao.getById(clazz, id);
    }

    @Override
    public <T extends IEntity> List<T> getAll(Class<T> clazz) {
        return dao.getAll(clazz);
    }

    @Transactional
    @Override
    public <T extends IEntity> void delete(T entity) {
        dao.delete(entity);
    }


    @Override
    public <T extends IEntity> List<T> get(Class<T> clazz, QueryCondition<T> condition) {
        return dao.get(clazz, condition);
    }

    @Override
    public <T extends IEntity> List<T> get(Class<T> clazz, IFilter filter) {
        return dao.get(clazz, filter);
    }

    @Override
    public <T extends IEntity> List<T> get(Class<T> clazz, IFilter filter, ISort sort) {
        return dao.get(clazz, filter, sort);
    }

    @Override
    public <T extends IEntity> Pagination<T> getByPageination(Class<T> clazz, QueryCondition<T> condition) {
        return dao.getByPageination(clazz, condition);
    }


}
