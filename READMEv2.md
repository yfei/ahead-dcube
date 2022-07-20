## Ahead 开发框架
本框架基于SpringBoot+Vue-element-admin进行搭建。

## 框架目录
```
 ahead
    |-- ahead-commons 框架通用包
        |-- ahead-commons-base 框架基础包,提供了框架的工具类、相应代码枚举等
        |-- ahead-commons-exception 异常通用封装
        |-- ahead-commons-jpa JPA操作封装
        |-- ahead-commons-mybatis mybatis操作封装(仅规划,暂不实现)
        |-- ahead-commons-redis redis 服务
        |-- ahead-commons-kafka kafka 服务
        |-- ahead-commons-elastic es 服务
        |-- ahead-commons-auth 授权认证
        |-- ahead-commons-system 系统功能,包含用户、角色、组织、menu等管理(可以考虑减少包)
        	   |-- ahead-commons-system controller
        	   |-- ahead-commons-service service层
        	   |-- ahead-commons-system-jpa service接口的JPA实现
        	   |-- ahead-commons-system-mybatis service接口的mybatis实现(仅规划,暂不实现)
        |-- ahead-commons-log 日志工具
        	   |-- ahead-commons-log-api api接口
        	   |-- ahead-commons-log-jpa api接口的JPA实现
        	   |-- ahead-commons-log-mybatis api接口的mybatis实现(仅规划,暂不实现)
        	   |-- ahead-commons-log-es api接口的ES实现(仅规划,暂不实现)


    |-- ahead-gateway 网关服务
        |-- ahead-gateway-springcloud
        |-- ahead-gateway-gRpc
    |-- ahead-spark 大数据分析平台
```
## 版本变化

## 开源协议
MIT