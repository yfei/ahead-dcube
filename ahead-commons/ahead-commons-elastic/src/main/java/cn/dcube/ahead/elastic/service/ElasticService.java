package cn.dcube.ahead.elastic.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

@Service
public class ElasticService {

	@Autowired
	private ElasticsearchRestTemplate elasticTemplate;

	// 主要用于索引管理
	@Autowired
	private RestHighLevelClient highLevelClient;

	public ElasticsearchRestTemplate getElasticTemplate() {
		return elasticTemplate;
	}

	public void setElasticTemplate(ElasticsearchRestTemplate elasticTemplate) {
		this.elasticTemplate = elasticTemplate;
	}

	public List<String> getIndices(String indexPattern) throws IOException {
		GetIndexRequest getIndexRequest = new GetIndexRequest(indexPattern);
		// 构建获取所有索引的请求org.elasticsearch.client.indices.GetIndexRequest
		GetIndexResponse getIndexResponse = highLevelClient.indices().get(getIndexRequest, RequestOptions.DEFAULT);
		// 获取所有的索引
		String[] indices = getIndexResponse.getIndices();
		return Arrays.asList(indices);
	}

}
