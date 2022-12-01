package cn.ahead.dcube.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import cn.ahead.dcube.base.dao.IDao;

/**
 * 
 * @desc: aheadRepository
 * @date: 2022年11月28日 上午9:24:54<br>
 * @author:yangfei<br>
 * @since 1.0.0
 */
@NoRepositoryBean
public interface AheadRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T>, IDao {

}
