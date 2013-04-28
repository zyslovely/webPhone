package com.phone.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.phone.mapper.ProfileMapper;
import com.phone.meta.Profile;
import com.phone.service.ProfileService;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime：2013-4-24 上午02:00:40 Class Description
 */
@Service("profileService")
public class ProfileServiceImpl implements ProfileService {
	@Resource
	private ProfileMapper profileMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.ProfileService#addProfile(java.lang.String,
	 * java.lang.String, int)
	 */
	@Override
	public boolean addProfile(String UserName, String Password, int level) {
		if (UserName == null || Password == null || level < 0) {
			return false;
		}
		Profile profile = new Profile();
		profile.setUserName(UserName);
		profile.setPassword(Password);
		profile.setCreateTime(new Date().getTime());
		profile.setLevel(level);
		if (profileMapper.addProfile(profile) > 0) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.ProfileService#getProfile(long)
	 */
	@Override
	public List<Profile> getProfile(long UserId) {
		Profile profile = profileMapper.getProfile(UserId);
		if (profile.getLevel() != Profile.ProfileLevel.Manager.getValue()) {
			return null;
		}
		return profileMapper.getProfileList();
	}
}
