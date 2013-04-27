package com.phone.mapper;

import java.util.List;

import com.phone.meta.Profile;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime：2013-4-24 上午01:50:29 Class Description
 */
public interface ProfileMapper {

	/**
	 * 添加账户
	 * 
	 * @param profile
	 * @return
	 */
	public int addProfile(Profile profile);

	/**
	 * 获取账户
	 * 
	 * @param UserId
	 * @return
	 */
	public Profile getProfile(long UserId);

	/**
	 * 获取账户列表
	 * 
	 * @return
	 */
	public List<Profile> getProfileList();
}
