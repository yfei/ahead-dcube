package cn.ahead.dcube.log.aspect;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import com.alibaba.fastjson.JSON;

import cn.ahead.dcube.base.dto.CommonLoginUser;
import cn.ahead.dcube.commons.util.StringUtils;
import cn.ahead.dcube.log.OperLogFactory;
import cn.ahead.dcube.log.annotation.Log;
import cn.ahead.dcube.log.dto.OperLogDTO;
import cn.ahead.dcube.security.util.SecurityUtil;
import cn.ahead.dcube.task.AsyncTaskScheduler;
import cn.ahead.dcube.utils.IpUtils;
import cn.ahead.dcube.utils.ServletUtils;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 操作日志记录处理
 * 
 * @author ruoyi
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

	private static final int STATUS_SUCCESS = 0;

	private static final int STATUS_ERROR = 1;

	// 配置织入点
	@Pointcut("@annotation(cn.ahead.dcube.log.annotation.Log)")
	public void logPointCut() {
	}

	/**
	 * 处理完请求后执行
	 *
	 * @param joinPoint 切点
	 */
	@AfterReturning(pointcut = "logPointCut()", returning = "responseResult")
	public void doAfterReturning(JoinPoint joinPoint, Object responseResult) {
		handleLog(joinPoint, null, responseResult);
	}

	/**
	 * 拦截异常操作
	 * 
	 * @param joinPoint 切点
	 * @param e         异常
	 */
	@AfterThrowing(value = "logPointCut()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
		handleLog(joinPoint, e, null);
	}

	protected void handleLog(final JoinPoint joinPoint, final Exception e, Object response) {
		try {
			// 获得注解
			Log controllerLog = getAnnotationLog(joinPoint);
			if (controllerLog == null) {
				return;
			}

			// *========数据库日志=========*//
			OperLogDTO operLog = new OperLogDTO();
			String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
			final UserAgent userAgent = UserAgentUtil.parse(ServletUtils.getRequest().getHeader("User-Agent"));
			// 获取客户端操作系统
			String platform = userAgent.getPlatform().getName();
			// 获取客户端浏览器
			String browser = userAgent.getBrowser().getName();
			operLog.setOperOS(platform);
			operLog.setOperBrower(browser);
			operLog.setOperIp(ip);
			operLog.setOperTime(new Date());
			operLog.setStatus(STATUS_SUCCESS);
			// 返回参数
			operLog.setResponseResult(JSON.toJSONString(response));
			// 请求的地址
			operLog.setUrl(ServletUtils.getRequest().getRequestURI());

			// 获取当前的用户
			CommonLoginUser loginUser = SecurityUtil.getCurrentUser();
			if (loginUser != null) {
				operLog.setOperUser(loginUser.getAccount());
				if (loginUser.getOrg() != null) {
					operLog.setOperOrg(loginUser.getOrg().getName());
				}
			}

			if (e != null) {
				operLog.setStatus(STATUS_ERROR);
				operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
			}
			// 设置方法名称
			String className = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			operLog.setFunctionMethod(className + "." + methodName + "()");
			// 设置请求方式
			operLog.setMethod(ServletUtils.getRequest().getMethod());
			// 处理设置注解上的参数
			getControllerMethodDescription(joinPoint, controllerLog, operLog);
			// 保存数据库
			AsyncTaskScheduler.me().execute(OperLogFactory.record(operLog));
		} catch (Exception exp) {
			// 记录本地异常日志
			log.error("日志记录失败,{}", exp.getMessage());
			log.error("", exp);
		}
	}

	/**
	 * 是否存在注解，如果存在就获取
	 */
	private Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method method = methodSignature.getMethod();

		if (method != null) {
			return method.getAnnotation(Log.class);
		}
		return null;
	}

	public void getControllerMethodDescription(JoinPoint joinPoint, Log log, OperLogDTO operLog) throws Exception {
		// 设置模块和功能
		operLog.setModule(log.module());
		operLog.setFunctionName(log.function());
		// 设置操作人类别
		operLog.setOper(log.oper().name());
		// 是否需要保存request，参数和值
		if (log.details()) {
			// 获取参数的信息，传入到数据库中。
			setRequestValue(joinPoint, operLog);
		}
		operLog.setCallby(log.callby().name());
	}

	/**
	 * 获取请求的参数，放到log中
	 * 
	 * @param operLog 操作日志
	 * @throws Exception 异常
	 */
	private void setRequestValue(JoinPoint joinPoint, OperLogDTO operLog) throws Exception {
        String params = argsArrayToString(joinPoint.getArgs());
        operLog.setRequestParam(StringUtils.substring(params, 0, 2000));
	}

	/**
	 * 参数拼装
	 */
	private String argsArrayToString(Object[] paramsArray) {
		String params = "";
		if (paramsArray != null && paramsArray.length > 0) {
			for (int i = 0; i < paramsArray.length; i++) {
				if (!isFilterObject(paramsArray[i])) {
					Object jsonObj = JSON.toJSON(paramsArray[i]);
					params += jsonObj.toString() + " ";
				}
			}
		}
		return params.trim();
	}

	/**
	 * 判断是否需要过滤的对象。
	 * 
	 * @param o 对象信息。
	 * @return 如果是需要过滤的对象，则返回true；否则返回false。
	 */
	public boolean isFilterObject(final Object o) {
		return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse;
	}

}
