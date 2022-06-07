/*
 *  Copyright (c) Mr.Yang 2012-2016 All Rights Reserved
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *       http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package cn.dcube.ahead.jpa.fei.service;

import java.io.Serializable;
import java.util.List;

import cn.dcube.ahead.entity.IEntity;
import cn.dcube.ahead.dao.IDao;
import cn.dcube.ahead.jpa.fei.page.Pagination;
import cn.dcube.ahead.jpa.fei.query.QueryCondition;
import cn.dcube.ahead.jpa.fei.query.filter.IFilter;
import cn.dcube.ahead.jpa.fei.query.sort.ISort;

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
