package cn.dcube.ahead.module.es;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.stereotype.Component;

import cn.dcube.ahead.elastic.service.ElasticService;

// @Component
public class ElasticsearchTest {

	@Autowired
	private ElasticService elasticService;

	// @PostConstruct
	public void test() {
		long start = new Date().getTime();
		System.out.println(">>>>start ");
		List<EventBase4ES> lists = new ArrayList<EventBase4ES>();
		for (int i = 0; i <= 1000000; i++) {
			EventBase4ES eb = new EventBase4ES();
			eb.setId(i + 1L);
			eb.setSrc_asset_level(3);
			eb.setClazz(4);
			eb.setStr1("dsafasdfasdfasdfasddfasfadsfasfdasfdasfasdfasd" + i);
			lists.add(eb);
			if (i > 0 && i % 10000 == 0) {
				elasticService.getElasticTemplate().save(lists);
				lists = new ArrayList<EventBase4ES>();
				long end = new Date().getTime();
				System.out.println("save " + i + "。耗时:" + (end - start));
				start = end;
			}
		}
//		EventBase4ES eb = new EventBase4ES();
//		eb.setId(1L);
//		eb.setSrc_asset_level(3);
//		eb.setClazz(4);
//		eb.setStr1("dsafasdfasdfasdfasddfasfadsfasfdasfdasfasdfasd");
//		elasticService.getElasticTemplate().save(eb);
//		System.out.println(elasticService.getElasticTemplate().indexOps(EventBase4ES.class).exists());

	}

}
