package com.phone.service;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime£º2013-4-15 ÏÂÎç03:16:10 Class Description
 */
public interface PurchaseService {

	/**
	 * Ìí¼ÓPurchase
	 * 
	 * @param phoneCode
	 * @param phoneModel
	 * @param purchasePrice
	 * @param DecideSellPirce
	 */
	public boolean addPurchase(String phoneCode, String phoneModel,
			double purchasePrice, double DecideSellPirce);
}
