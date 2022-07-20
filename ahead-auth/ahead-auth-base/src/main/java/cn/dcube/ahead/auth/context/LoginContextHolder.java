package cn.dcube.ahead.auth.context;

import cn.dcube.ahead.context.SpringContext;

/**
 * 当前登录用户信息获取的接口
 *
 */
public class LoginContextHolder {

    public static LoginContext me() {
        return SpringContext.getBean(LoginContext.class);
    }

}
