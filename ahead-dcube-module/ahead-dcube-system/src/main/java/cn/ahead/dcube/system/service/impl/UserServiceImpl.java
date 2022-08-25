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

import cn.ahead.dcube.base.dto.LoginUserModel;
import cn.ahead.dcube.base.dto.OrgDTO;
import cn.ahead.dcube.base.response.code.ResponseCode;
import cn.ahead.dcube.base.response.code.SecurityResponseCode;
import cn.ahead.dcube.schedule.AsyncScheduler;
import cn.ahead.dcube.security.exception.AheadSecurityException;
import cn.ahead.dcube.security.log.SecurityLogFactory;
import cn.ahead.dcube.security.token.service.TokenService;
import cn.ahead.dcube.security.util.SecurityUtil;
import cn.ahead.dcube.system.dao.UserRepository;
import cn.ahead.dcube.system.entity.UserEntity;
import cn.ahead.dcube.system.service.IOrgService;
import cn.ahead.dcube.system.service.IUserService;

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
	public LoginUserModel selectUserByUserName(String account) {
		UserEntity user = repository.findByAccount(account);
		if (user != null) {
			LoginUserModel userModel = new LoginUserModel();
			BeanUtils.copyProperties(user, userModel);
			OrgDTO org = orgService.getOrg(user.getOrgId(), false);
			userModel.setOrg(org);
			return userModel;
		} else {
			return new LoginUserModel();
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
				AsyncScheduler.me()
						.execute(SecurityLogFactory.recordLogininfo(account, SecurityLogFactory.OPER_LOGIN,
								SecurityLogFactory.RESULT_ERROR, SecurityResponseCode.USERNOTFOUND.getCode(),
								SecurityResponseCode.USERNOTFOUND.getMsg()));
				throw new AheadSecurityException(SecurityResponseCode.USERNOTFOUND);
			} else {
				AsyncScheduler.me()
						.execute(SecurityLogFactory.recordLogininfo(account, SecurityLogFactory.OPER_LOGIN,
								SecurityLogFactory.RESULT_ERROR, SecurityResponseCode.USERNOTFOUND.getCode(),
								SecurityResponseCode.USERNOTFOUND.getMsg()));
				throw new AheadSecurityException(e);
			}
		}
		AsyncScheduler.me().execute(SecurityLogFactory.recordLogininfo(account, SecurityLogFactory.OPER_LOGIN,
				SecurityLogFactory.RESULT_SUCCESS, ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg()));
		LoginUserModel loginUser = (LoginUserModel) authentication.getPrincipal();
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
		LoginUserModel currentUser = SecurityUtil.getCurrentUser();
		UserEntity user = repository.getById(currentUser.getId());
		if (SecurityUtil.matchesPassword(oldPass, user.getPassword())) {
			repository.chpass(user.getId(), SecurityUtil.encryptPassword(newPass));
		} else {
			throw new AheadSecurityException(SecurityResponseCode.PASSERROR);
		}
	}

}
