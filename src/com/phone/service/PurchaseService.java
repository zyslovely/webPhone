package com.phone.service;

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
	public boolean addPurchase(String brand, String phoneCode, String phoneModel, double purchasePrice, double DecideSellPirce);
}
