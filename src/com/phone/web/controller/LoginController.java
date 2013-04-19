package com.phone.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-20 上午01:50:06
 * @see Class Description
 */
@Controller("loginController")
public class LoginController extends MultiActionController {
	/**
	 * 显示首页
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showIndex(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("webIndex");
	}
}
