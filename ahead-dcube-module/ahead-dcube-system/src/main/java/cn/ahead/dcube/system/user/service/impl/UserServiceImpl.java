package cn.ahead.dcube.system.user.service.impl;

import java.util.Date;
import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import cn.ahead.dcube.base.constant.AheadSysConstant;
import cn.ahead.dcube.base.dto.CommonLoginUser;
import cn.ahead.dcube.base.dto.SysOrg;
import cn.ahead.dcube.base.response.code.ResponseCode;
import cn.ahead.dcube.base.response.code.SecurityResponseCode;
import cn.ahead.dcube.security.dto.SysLoginUser;
import cn.ahead.dcube.security.exception.AheadSecurityException;
import cn.ahead.dcube.security.log.LoginRecordFactory;
import cn.ahead.dcube.security.token.service.TokenService;
import cn.ahead.dcube.security.util.SecurityUtil;
import cn.ahead.dcube.system.org.service.IOrgService;
import cn.ahead.dcube.system.user.dao.UserRepository;
import cn.ahead.dcube.system.user.dao.UserSNSRepository;
import cn.ahead.dcube.system.user.entity.UserEntity;
import cn.ahead.dcube.system.user.entity.UserSNSEntity;
import cn.ahead.dcube.system.user.service.IUserService;
import cn.ahead.dcube.task.AsyncTaskScheduler;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserSNSRepository snsRepository;

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
	    SysLoginUser userModel = this.convert(user);
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

    @Transactional
    @Override
    public void bindSNS(String type, String account, String passwd, String unionid) {
	UserEntity user = repository.findByAccount(account);
	if (user == null || AheadSysConstant.USER_STATUS_DISABLED == user.getStatus()
		|| !SecurityUtil.matchesPassword(passwd, user.getPassword())) {
	    throw new AheadSecurityException(SecurityResponseCode.USERNOTFOUND);
	}
	UserSNSEntity entity = snsRepository.findByTypeAndUnionid(type, unionid);
	if (entity == null) {
	    entity = new UserSNSEntity();
	    entity.setType(type);
	    entity.setUserId(user.getId());
	    entity.setDisabled("N");
	    entity.setUnionid(unionid);
	    entity.setBindDate(new Date());
	} else if (entity.isDisabled()) {
	    // 已经绑定.更新
	    entity.setType(type);
	    entity.setUserId(user.getId());
	    entity.setDisabled("N");
	    entity.setUnionid(unionid);
	    entity.setBindDate(new Date());
	} else {
	    // 更新
	    entity.setType(type);
	    entity.setUserId(user.getId());
	    entity.setDisabled("N");
	    entity.setUnionid(unionid);
	    entity.setBindDate(new Date());
	}
	snsRepository.save(entity);

    }

    @Transactional
    @Override
    public void unbindSNS(String type, String passwd, String unionid) {
	UserSNSEntity entity = snsRepository.findByTypeAndUnionid(type, unionid);
	// 不存在或者关联账号不对或者已禁用
	if (entity == null || entity.getUser() == null
		|| entity.getUser().getPassword().equals(SecurityUtil.encryptPassword(passwd)) || entity.isDisabled()) {
	    throw new AheadSecurityException(SecurityResponseCode.SNSNOTFOUND);
	} else if (!SecurityUtil.matchesPassword(passwd, entity.getUser().getPassword())) {
	    throw new AheadSecurityException(SecurityResponseCode.PASSERROR);
	}
	entity.setDisabled("Y");
	entity.setDisabledDate(new Date());
	snsRepository.save(entity);
    }

    @Override
    public SysLoginUser getBySNS(String type, String unionid) {
	UserSNSEntity userSnsInfo = snsRepository.findByTypeAndUnionid(type, unionid);
	if (Optional.ofNullable(userSnsInfo).isPresent() && !Optional.of(userSnsInfo).get().isDisabled()) {
	    UserEntity user = Optional.of(userSnsInfo).get().getUser();
	    if (Optional.ofNullable(user).isPresent()) {
		return this.convert(user);
	    }
	}
	return new SysLoginUser();
    }

    public SysLoginUser convert(UserEntity user) {
	if (user != null) {
	    SysLoginUser userModel = new SysLoginUser();
	    BeanUtils.copyProperties(user, userModel);
	    SysOrg org = orgService.getOrg(user.getOrgId(), false);
	    userModel.setOrg(org);
	    return userModel;
	}
	return new SysLoginUser();
    }
}
