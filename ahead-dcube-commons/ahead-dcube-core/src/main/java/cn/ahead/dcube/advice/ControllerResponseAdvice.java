package cn.ahead.dcube.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.ahead.dcube.advice.annotation.NoResponseAdvice;
import cn.ahead.dcube.base.exception.AheadRuntimeException;
import cn.ahead.dcube.base.response.Response;

/**
 * controller返回拦截器,针对非Response的返回
 * 
 * @author yangfei
 *
 */
@RestControllerAdvice
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		// 如果非response返回的数据,进行封装
		return !this.isSwagger(returnType) && (!Response.class.isAssignableFrom(returnType.getParameterType())
				|| returnType.hasMethodAnnotation(NoResponseAdvice.class));
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		// String类型不能直接包装
		if (returnType.getGenericParameterType().equals(String.class)) {
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				// 将数据包装在SuccessResponse里后转换为json串进行返回
				return objectMapper.writeValueAsString(Response.success(body));
			} catch (JsonProcessingException e) {
				throw new AheadRuntimeException(e.getMessage());
			}
		}
		// 否则直接包装成SuccessResponse返回
		return Response.success(body);
	}

	private boolean isSwagger(MethodParameter returnType) {
		return returnType.getMethod().getDeclaringClass().getCanonicalName()
				.indexOf("springfox.documentation.swagger") > -1;

	}

}
