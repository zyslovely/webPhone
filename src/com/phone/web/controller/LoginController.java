package com.phone.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.phone.security.MySecurityDelegatingFilter;
import com.phone.security.MyUser;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-20 上午01:50:06
 * @see Class Description
 */
@Controller("loginController")
public class LoginController extends AbstractBaseController {
	/**
	 * 显示首页
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showIndex(HttpServletRequest request, HttpServletResponse response) {
		logger.info(request.getSession().getId());
		String sessionId = request.getSession().getId();
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(sessionId);
		if (myUser != null) {
			try {
				response.sendRedirect("/phone/index/");
				return null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 如果已经登陆，直接重定向
		return new ModelAndView("webIndex");
	}

	/**
	 * 登陆页面
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		logger.info(request.getSession().getId());
		try {
			response.sendRedirect("/phone/index/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
