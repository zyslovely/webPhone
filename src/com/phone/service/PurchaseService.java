package com.phone.service;

import com.phone.meta.Purchase;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime 2013-4-15 03:16:10 Class Description
 */
public interface PurchaseService {

	/**
	 * 添加Purchase
	 * 
	 * @param phoneCode
	 * @param phoneModel
	 * @param purchasePrice
	 * @param DecideSellPirce
	 */
	public boolean addPurchase(String brand, String phoneCode,
			String phoneModel, double purchasePrice, double DecideSellPirce,
			long shopId);

	/**
	 * 通过phoneid查找Purchase
	 * 
	 * @param phoneid
	 * @return
	 */
	public Purchase getPurchase(long phoneid);

	/**
	 * 通过改变Status变相删除Purchase
	 * 
	 * @param phoneid
	 * @param Status
	 * @return
	 */
	public boolean deletePurchase(long phoneid);
}
