package cn.ahead.dcube.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AheadRepository<T, ID> extends JpaRepository<T, ID> {

}
