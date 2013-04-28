package com.phone.service;

import java.util.List;

import com.phone.meta.Phone;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime：2013-4-16 下午03:21:23 Class Description
 */
public interface PhoneService {
	/**
	 * 通过phoneModel有限制获得手机列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param phoneModel
	 * @return
	 */
	public List<Phone> getPhoneList(String phoneModel, long operatorId,
			long shopId, int limit, int offset);

	/**
	 * 通过条形码获取
	 * 
	 * @auther zyslovely@gmail.com
	 * @param phoneCode
	 * @return
	 */
	public List<Phone> getPhonesByPhoneCode(String phoneCode, long operatorId,
			long shopId);

	/**
	 * 通过phoneModel获得手机列表
	 * 
	 * @param phoneModel
	 * @return
	 */
	public List<Phone> getPhoneListByPhoneModel(String phoneModel,
			long operatorId, long shopId);
}
