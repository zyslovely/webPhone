package com.phone.security;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-28 下午09:43:18
 * @see Class Description
 */
public class MyUser {

	private long userId;
	private long shopId;
	private String sessionStr;
	public static boolean isTest = true;
	public static long testUserId = 2L;
	public static long testShopId = 1L;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	public String getSessionStr() {
		return sessionStr;
	}

	public void setSessionStr(String sessionStr) {
		this.sessionStr = sessionStr;
	}

	public static MyUser setTestUser() {
		MyUser myUser = new MyUser();
		myUser.setUserId(2/* MyUser.testUserId */);
		myUser.setShopId(2/* MyUser.testShopId */);
		return myUser;
	}

	public static long getMyUser(HttpServletRequest request) {
		if (isTest) {
			return MyUser.testUserId;
		}
		Object obj = request.getSession().getAttribute("userId");
		if (obj == null) {
			return 0;
		}
		return Long.valueOf(obj.toString());

	}
}
