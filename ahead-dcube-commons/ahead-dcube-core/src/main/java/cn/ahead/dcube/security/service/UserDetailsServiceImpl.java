package cn.ahead.dcube.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.ahead.dcube.base.constant.AheadSysConstant;
import cn.ahead.dcube.base.exception.AheadServiceException;
import cn.ahead.dcube.commons.util.StringUtils;
import cn.ahead.dcube.security.dto.SysLoginUser;

/**
 * 用户验证处理
 *
 * @author ruoyi
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private IUserSecurityService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysLoginUser user = userService.selectUserByUserName(username);
		if (StringUtils.isNull(user)) {
			log.info("登录用户：{} 不存在.", username);
			throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
		}
		else if (AheadSysConstant.USER_STATUS_DISABLED.equals(user.getStatus())) {
			log.info("登录用户：{} 已被停用.", username);
			throw new AheadServiceException("对不起，您的账号：" + username + " 已停用");
		}
		return user;
	}

}
