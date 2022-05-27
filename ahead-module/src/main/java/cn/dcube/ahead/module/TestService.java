package cn.dcube.ahead.module;

import cn.dcube.ahead.dynamicDS.DynamicDataSource;
import cn.dcube.ahead.jpa.fei.query.filter.IFilter;
import cn.dcube.ahead.jpa.fei.query.filter.impl.SimpleFilter;
import cn.dcube.ahead.jpa.fei.service.IFeiService;
import cn.dcube.ahead.module.curd.entity.UserEntity2;
import cn.dcube.ahead.module.curd.service.ICurdService;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * @date：2021-12-29 14:39<br>
 * @author：yangfei<br>
 * @version: v1.0
 */
@Service
public class TestService {

    @Resource(name="curdService")
    private ICurdService curdService;

    @Resource(name="feiService")
    private IFeiService service;


    @PostConstruct
    public void init() {
        IFilter filter = new SimpleFilter("name", "SPRING-DATA-JDBC").appendAnd(new SimpleFilter("age",30));
        List<UserEntity2> users = service.get(UserEntity2.class,filter);
        System.out.println(users.get(0).getName());

    }
}
