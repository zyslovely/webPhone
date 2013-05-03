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
	public List<Phone> getPhoneList(String phoneModel, long shopId, int limit,
			int offset,int status);

	/**
	 * 通过条形码获取
	 * 
	 * @auther zyslovely@gmail.com
	 * @param phoneCode
	 * @return
	 */
	public List<Phone> getPhonesByPhoneCode(String phoneCode, long shopId,int status);

	/**
	 * 更改手机库存店铺
	 * 
	 * @param phoneCode
	 * @param shopId
	 * @param newShopId
	 * @return
	 */
	public boolean changeShop(String phoneCode, long shopId, long newShopId);
}
