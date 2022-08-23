package cn.ahead.dcube.security.log;

import java.util.TimerTask;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityLogFactory {

	public static final String OPER_LOGIN = "LOGIN";

	public static final String OPER_LOGOUT = "LOGOUT";

	public static final String OPER_CHANGEPASS = "CHANGEPASS";

	public static final String RESULT_SUCCESS = "SUCCESS";

	public static final String RESULT_ERROR = "ERROR";

	public static TimerTask recordLogininfo(final String userName, final String oper, final String result,
			final Integer code, final String message) {
		return new TimerTask() {
			@Override
			public void run() {
				log.info("用户:{},操作: {}, 结果: {}, 响应代码: {}, 消息:{}", userName, oper, result,code, message);
			}
		};
	}

}
