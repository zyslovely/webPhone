package com.phone.web.controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.phone.meta.Profile;
import com.phone.security.MyUser;
import com.phone.service.ProfileService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@corp.netease.com
 * @version CreateTime2012-3-5 01:45:43 Class Description
 */
public abstract class AbstractBaseController extends MultiActionController {
	@Resource(name = "paramResolver")
	private MethodNameResolver methodNameResolver;

	@Resource
	private ProfileService profileService;

	/**
	 * 初始化
	 */
	@PostConstruct
	public void baseInit() {
		super.setMethodNameResolver(methodNameResolver);
	}

	/**
	 * 初始数据
	 * 
	 * @auther zyslovely@gmail.com
	 * @param mv
	 */
	public void setUD(ModelAndView mv, HttpServletRequest request) {
		Long userId = MyUser.getMyUser(request);
		Profile profile = profileService.getProfile(userId);
		mv.addObject("name", profile.getName());
		if (profile.getLevel() == 0) {
			mv.addObject("levelName", "店员");
		} else {
			mv.addObject("levelName", "店铺管理员");
		}
		mv.addObject("shopName", Profile.getShopName(profile.getShopId()));
		mv.addObject("level", profile.getLevel());
	}
}
