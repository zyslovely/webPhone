package com.phone.service;

import java.util.List;

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
			long operatorId, long shopId);

	/**
	 * 通过phoneid查找Purchase
	 * 
	 * @param phoneid
	 * @return
	 */
	public Purchase getPurchase(long phoneid, long shopId);

	/**
	 * 通过改变Status变相删除Purchase
	 * 
	 * @param phoneid
	 * @param Status
	 * @return
	 */
	public boolean deletePurchase(long phoneid, long operatorId, long shopId);

	/**
	 * 得到所有的手机数量
	 * 
	 * @auther zyslovely@gmail.com
	 * @param shopId
	 * @param phoneModel
	 * @return
	 */
	public int getPurchaseCountByPhoneModel(long shopId, String phoneModel,
			int status);

	/**
	 * 通过phoneCode获取入库
	 * 
	 * @param shopId
	 * @param phoneCode
	 * @return
	 */
	public Purchase getPurchaseByPhoneCode(long shopId, String phoneCode);

	/**
	 * 获得品牌列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @return
	 */
	public List<String> getBrandList();

	/**
	 * 添加新品牌名称
	 * 
	 * @auther zyslovely@gmail.com
	 * @param brandName
	 * @return
	 */
	public boolean addNewBrand(String brandName);

	/**
	 * 得到所有的手机数量
	 * 
	 * @auther zyslovely@gmail.com
	 * @param shopId
	 * @param phoneModel
	 * @return
	 */
	public int getPurchaseCountByBrand(long shopId, String brandId, int status);
}
