package com.phone.security;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.ServletRequestUtils;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-8 下午11:48:44
 * @see Class Description
 */
public class MySecurityDelegatingFilter extends HttpServlet implements Filter {

	private static final String[] noAuthURIConfig = { "/**/phonePub.do*", "/**/login.do*" };

	private static final String[] noAdminURIConfig = { "/**/phone.do*", "/**/*.dwr" };

	private static final PathMatcher urlMatcher = new AntPathMatcher();

	public static ConcurrentHashMap<String, MyUser> userMap = new ConcurrentHashMap<String, MyUser>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(MySecurityDelegatingFilter.class);

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		logger.info("in MySecurityDelegatingFilter");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String uri = httpRequest.getRequestURI();
		if (this.noNeedAuthConfig(uri, httpRequest) && this.noNeedAdminConfig(uri, httpRequest)) {
			throw new ServletException();
		}
		if (this.noNeedAuthConfig(uri, httpRequest)) {
			String actionName = ServletRequestUtils.getStringParameter(httpRequest, "action", "null");
			if (actionName != null && actionName.equals("login")) {
				String userName = ServletRequestUtils.getStringParameter(httpRequest, "username", null);
				String passWord = ServletRequestUtils.getStringParameter(httpRequest, "password", null);
				if (userName.equals("zys") && passWord.equals("123")) {
					MyUser myUser = new MyUser();
					myUser.setUserId(1L);
					myUser.setSessionStr(httpRequest.getSession().getId());
					myUser.setShopId(1L);
					userMap.put(httpRequest.getSession().getId(), myUser);
					arg2.doFilter(request, response);
					return;
				} else {
					logger.error("账号密码失败");
				}
			}
		}

		// 如果需要认证
		if (this.noNeedAdminConfig(uri, httpRequest) && !this.noNeedAuthConfig(uri, httpRequest)) {
			String sessionId = httpRequest.getSession().getId();
			MyUser myUser = userMap.get(sessionId);
			if (myUser == null) {
				// 做一个重定向
				throw new ServletException("用户操作失败，需要重新登陆");
			}
		}
		arg2.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	private boolean noNeedAuthConfig(String uri, HttpServletRequest request) {
		if (ArrayUtils.isEmpty(noAuthURIConfig)) {
			return false;
		}
		for (String ptn : noAuthURIConfig) {
			if (urlMatcher.match(ptn, uri)) {
				return true;
			}
		}
		return false;
	}

	private boolean noNeedAdminConfig(String uri, HttpServletRequest request) {
		if (ArrayUtils.isEmpty(noAdminURIConfig)) {
			return false;
		}
		for (String ptn : noAdminURIConfig) {
			if (urlMatcher.match(ptn, uri)) {
				return true;
			}
		}
		return false;
	}

}
