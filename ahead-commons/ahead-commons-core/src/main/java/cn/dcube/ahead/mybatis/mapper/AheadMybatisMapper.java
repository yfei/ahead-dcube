package cn.dcube.ahead.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.dcube.ahead.mybatis.entity.MybatisEntity;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

/**
 * Mybatis顶层mapper。后续继续封装
 * @date: 2021-12-21 15:09 <br>
 * @author: yangfei <br>
 * @version: 1.0
 */
@ConditionalOnBean(BaseMapper.class)
public interface AheadMybatisMapper<T extends MybatisEntity> extends BaseMapper<T> {

}
