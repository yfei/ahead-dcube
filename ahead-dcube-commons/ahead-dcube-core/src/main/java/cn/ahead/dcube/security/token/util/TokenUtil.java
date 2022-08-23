package cn.ahead.dcube.security.token.util;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import cn.ahead.dcube.base.dto.LoginUserModel;
import cn.ahead.dcube.commons.util.StringUtils;

/**
 * jwt token 工具类
 * 
 * @author yangfei
 *
 */
public class TokenUtil {

	private static Logger logger = LoggerFactory.getLogger(TokenUtil.class);

	private static final String TOKEN_SECRET = "ahead-jtkxy"; // 密钥盐

	/**
	 * 签名生成
	 *
	 * @param user
	 * @return
	 */
	public static String sign(LoginUserModel user) {
		String token = null;
		try {
			token = JWT.create().withIssuer("auth0").withClaim("account", user.getAccount())
					.withClaim("username", user.getName()).withClaim("phone", user.getPhone())
					.withClaim("userType", user.getUserType()).withClaim("time", new Date())
					.withClaim("salt", StringUtils.getUUID())
					// 使用了HMAC256加密算法。
					.sign(Algorithm.HMAC256(TOKEN_SECRET));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;

	}

	/**
	 * token验证
	 * 
	 * @param token
	 * @return
	 */
	public static boolean verifyOnly(String token) {
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
			DecodedJWT jwt = verifier.verify(token);
			logger.debug("{}认证通过,account is {}", token, jwt.getClaim("account").asString());
			return true;
		} catch (Exception e) {
			logger.warn("{}认证失败", token);
			logger.error("", e);
		}
		return false;
	}

	public static String verifyAndGetAccount(String token) {
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
			DecodedJWT jwt = verifier.verify(token);
			String account = jwt.getClaim("account").asString();
			logger.debug("{}认证并获取用户账号通过,account is {}", token, jwt.getClaim("account").asString());
			return account;
		} catch (Exception e) {
			logger.warn("{}认证并获取用户账号失败", token);
			logger.error("", e);
		}
		return null;
	}

}
