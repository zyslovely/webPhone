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
			int offset, int status);

	/**
	 * 没有被盘点的
	 * 
	 * @auther zyslovely@gmail.com
	 * @param shopId
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<Phone> getPhoneListNoInventory(long shopId, int limit,
			int offset);

	/**
	 * 通过条形码获取
	 * 
	 * @auther zyslovely@gmail.com
	 * @param phoneCode
	 * @return
	 */
	public List<Phone> getPhonesByPhoneCode(String phoneCode, long shopId,
			int status);

	/**
	 * 更改手机库存店铺
	 * 
	 * @param phoneCode
	 * @param shopId
	 * @param newShopId
	 * @return
	 */
	public boolean changeShop(long phoneId, long newShopId, long shopId,
			long operatorUserId);

	/**
	 * 退货
	 * 
	 * @auther zyslovely@gmail.com
	 * @param phoneId
	 * @return
	 */
	public boolean returnPhone(long phoneId, long shopId, long operatorUserId);

	/**
	 * 修改购入价格
	 * 
	 * @auther zyslovely@gmail.com
	 * @param phoneId
	 * @param price
	 * @return
	 */
	public boolean purchasePriceChange(long phoneId, double price, long shopId);

	/**
	 * 修改购入价格
	 * 
	 * @auther zyslovely@gmail.com
	 * @param phoneId
	 * @param price
	 * @return
	 */
	public boolean sellPriceChange(long phoneId, double price, long shopId);

	/**
	 * 通过品牌
	 * 
	 * @auther zyslovely@gmail.com
	 * @param brandName
	 * @param limit
	 * @param offset
	 * @param shopId
	 * @return
	 */
	public List<Phone> getPhoneListByBrandName(String brandName, int limit,
			int offset, long shopId);

	/**
	 * 根据品牌名称获得手机数量
	 * 
	 * @auther zyslovely@gmail.com
	 * @param brandName
	 * @param shopId
	 * @return
	 */
	public int getPhoneCountByBrandName(String brandName, long shopId);
}
