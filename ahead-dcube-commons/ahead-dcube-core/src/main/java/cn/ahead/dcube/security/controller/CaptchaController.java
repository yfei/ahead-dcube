package cn.ahead.dcube.security.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.Producer;

import cn.ahead.dcube.base.constant.AheadSysConstant;
import cn.ahead.dcube.base.response.Response;
import cn.ahead.dcube.commons.crypto.Base64;
import cn.ahead.dcube.security.config.CaptchaConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * 验证码操作处理
 * 
 * @author yangfei
 */
@RestController
@ConditionalOnBean(CaptchaConfig.class)
@Slf4j
public class CaptchaController {

	@Resource(name = "captchaProducer")
	private Producer captchaProducer;

	@Resource(name = "captchaProducerMath")
	private Producer captchaProducerMath;

	@Autowired
	private CaptchaConfig config;

	/**
	 * 生成验证码
	 */
	@GetMapping("/captcha")
	public Response getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 保存验证码信息

		String capStr = null, code = null;
		BufferedImage image = null;

		// 生成验证码
		if ("math".equals(config.getType())) {
			String capText = captchaProducerMath.createText();
			capStr = capText.substring(0, capText.lastIndexOf("@"));
			code = capText.substring(capText.lastIndexOf("@") + 1);
			image = captchaProducerMath.createImage(capStr);
		} else if ("char".equals(config.getType())) {
			capStr = code = captchaProducer.createText();
			image = captchaProducer.createImage(capStr);
		}
		request.getSession().setAttribute(AheadSysConstant.SESSION_CAPTCHA, capStr);
		// 转换流信息写出
		FastByteArrayOutputStream os = new FastByteArrayOutputStream();
		try {
			ImageIO.write(image, "jpg", os);
		} catch (IOException e) {
			log.error("", e);
			return Response.error("验证码生成失败!");
		}

		return Response.success(Base64.encode(os.toByteArray()));
	}
}
