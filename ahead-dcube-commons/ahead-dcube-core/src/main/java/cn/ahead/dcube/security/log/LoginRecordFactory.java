package cn.ahead.dcube.security.log;

import java.util.Date;
import java.util.TimerTask;

import cn.ahead.dcube.context.SpringContext;
import cn.ahead.dcube.security.log.dto.LoginRecordDTO;
import cn.ahead.dcube.security.log.service.ILoginRecordService;
import cn.ahead.dcube.utils.IpUtils;
import cn.ahead.dcube.utils.ServletUtils;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginRecordFactory {

	public static final String OPER_LOGIN = "LOGIN";

	public static final String OPER_LOGOUT = "LOGOUT";

	public static final String OPER_CHANGEPASS = "CHANGEPASS";

	public static final String RESULT_SUCCESS = "SUCCESS";

	public static final String RESULT_ERROR = "FAILURE";

	public static TimerTask recordLogininfo(final String userName, final String oper, final String result,
			final Integer code, final String message) {

		final UserAgent userAgent = UserAgentUtil.parse(ServletUtils.getRequest().getHeader("User-Agent"));
		final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
		return new TimerTask() {
			@Override
			public void run() {
				// 获取客户端操作系统
				String platform = userAgent.getPlatform().getName();
				// 获取客户端浏览器
				String browser = userAgent.getBrowser().getName();
				// 封装对象
				LoginRecordDTO logininfor = new LoginRecordDTO();
				logininfor.setUserName(userName);
				logininfor.setOper(oper);
				logininfor.setStatus(result);
				logininfor.setCode(code);
				logininfor.setIpaddr(ip);
				logininfor.setBrowser(browser);
				logininfor.setPlatform(platform);
				logininfor.setMsg(message);
				logininfor.setTime(new Date());
				// 插入数据
				ILoginRecordService service = SpringContext.getBean(ILoginRecordService.class);
				if (service != null) {
					service.save(logininfor);
				} else {
					log.info("用户:{},操作: {}, 结果: {}, 响应代码: {}, 消息:{}", userName, oper, result, code, message);
				}
			}
		};
	}

}
