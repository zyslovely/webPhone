package com.phone.service;

import java.util.List;

import com.phone.meta.Profile;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime：2013-4-24 上午02:00:20 Class Description
 */
public interface ProfileService {

	/**
	 * 添加账户
	 * 
	 * @param userName
	 * @param Password
	 * @param level
	 * @return
	 */
	public boolean addProfile(String userName, String Password, int level);

	/**
	 * 获取账户列表
	 * 
	 * @param UserId
	 * @return
	 */
	public List<Profile> getProfile(long UserId);
}
