package cn.ahead.dcube.system.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import cn.ahead.dcube.base.dto.CommonLoginUser;
import cn.ahead.dcube.base.dto.SysOrg;
import cn.ahead.dcube.base.response.code.ResponseCode;
import cn.ahead.dcube.base.response.code.SecurityResponseCode;
import cn.ahead.dcube.security.dto.SysLoginUser;
import cn.ahead.dcube.security.exception.AheadSecurityException;
import cn.ahead.dcube.security.log.LoginRecordFactory;
import cn.ahead.dcube.security.token.service.TokenService;
import cn.ahead.dcube.security.util.SecurityUtil;
import cn.ahead.dcube.system.dao.UserRepository;
import cn.ahead.dcube.system.entity.UserEntity;
import cn.ahead.dcube.system.service.IOrgService;
import cn.ahead.dcube.system.service.IUserService;
import cn.ahead.dcube.task.AsyncTaskScheduler;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private IOrgService orgService;

	@Resource
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@Override
	public SysLoginUser selectUserByUserName(String account) {
		UserEntity user = repository.findByAccount(account);
		if (user != null) {
			SysLoginUser userModel = new SysLoginUser();
			BeanUtils.copyProperties(user, userModel);
			SysOrg org = orgService.getOrg(user.getOrgId(), false);
			userModel.setOrg(org);
			return userModel;
		} else {
			return new SysLoginUser();
		}
	}

	@Override
	public String login(String account, String password) {
		// 用户验证
		Authentication authentication = null;
		try {
			// 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(account, password));
		} catch (Exception e) {
			if (e instanceof BadCredentialsException) {
				AsyncTaskScheduler.me()
						.execute(LoginRecordFactory.recordLogininfo(account, LoginRecordFactory.OPER_LOGIN,
								LoginRecordFactory.RESULT_ERROR, SecurityResponseCode.USERNOTFOUND.getCode(),
								SecurityResponseCode.USERNOTFOUND.getMsg()));
				throw new AheadSecurityException(SecurityResponseCode.USERNOTFOUND);
			} else {
				AsyncTaskScheduler.me()
						.execute(LoginRecordFactory.recordLogininfo(account, LoginRecordFactory.OPER_LOGIN,
								LoginRecordFactory.RESULT_ERROR, SecurityResponseCode.USERNOTFOUND.getCode(),
								SecurityResponseCode.USERNOTFOUND.getMsg()));
				throw new AheadSecurityException(e);
			}
		}
		AsyncTaskScheduler.me().execute(LoginRecordFactory.recordLogininfo(account, LoginRecordFactory.OPER_LOGIN,
				LoginRecordFactory.RESULT_SUCCESS, ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg()));
		CommonLoginUser loginUser = (CommonLoginUser) authentication.getPrincipal();
		// 生成token
		return tokenService.setToken(loginUser);
	}

	@Override
	public String logout(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public void chpass(String oldPass, String newPass) {
		// 获取当前用户
		CommonLoginUser currentUser = SecurityUtil.getCurrentUser();
		UserEntity user = repository.getById(currentUser.getId());
		if (SecurityUtil.matchesPassword(oldPass, user.getPassword())) {
			repository.chpass(user.getId(), SecurityUtil.encryptPassword(newPass));
		} else {
			throw new AheadSecurityException(SecurityResponseCode.PASSERROR);
		}
	}

}
