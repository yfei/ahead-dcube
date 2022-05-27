package cn.dcube.ahead.module.curd.service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.transaction.Transactional;

import cn.dcube.ahead.jpa.fei.query.filter.IFilter;
import cn.dcube.ahead.jpa.fei.query.filter.impl.SimpleFilter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import cn.dcube.ahead.context.SpringContext;
import cn.dcube.ahead.dynamicDS.DynamicDS;
import cn.dcube.ahead.module.curd.entity.UserEntity;
import cn.dcube.ahead.module.curd.entity.UserEntity2;
import cn.dcube.ahead.module.curd.mapper.UserMapper;
import cn.dcube.ahead.module.curd.repository.UserRepository;
import cn.dcube.ahead.jpa.fei.service.IFeiService;

import java.util.List;

@Service
@Primary
public class CurdService implements ICurdService{

	@Resource
	private UserRepository REPO;

	@Resource(name="feiService")
	private IFeiService service;

	@Resource
	private UserMapper userMapper;

	@Transactional
	@DynamicDS(name="master")
	public void test1() {
		System.out.println(">>>>>>1123123");
		UserEntity2 userData = new UserEntity2();
		userData.setName("SPRING-DATA-JDBC");
		userData.setNickName("测试1");
		userData.setAge(30);
		service.persist(userData);
	}

	@Transactional
	@DynamicDS(name="slave")
	public void test2() {
		UserEntity user = new UserEntity();
		user.setName("SPRING-DATA-JDBC");
		user.setNickName("JDBC");
		user.setAge(30);
		userMapper.insert(user);
	}

}