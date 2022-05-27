package cn.dcube.ahead.elastic.service;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AheadElasticRepository<T,ID> extends ElasticsearchRepository<T,ID>{

}
