package com.phone.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.phone.meta.Purchase;

public interface PurchaseMapper {

	/**
	 * 添加购买
	 * 
	 * @auther zyslovely@gmail.com
	 * @param purchase
	 * @return
	 */
	public int addPurchase(Purchase purchase);

	/**
	 * 通过手机型号查找手机列表
	 * 
	 * @param phoneModel
	 * @return
	 */
	public List<Purchase> getPurchaseList(Map<String, Object> hashmMap);

	/**
	 * 更新Purchase
	 * 
	 * @param hashMap
	 * @return
	 */
	public int updatePurchase(Map<String, Object> hashMap);

	/**
	 * 通过phoneid查找Purchase
	 * 
	 * @param phoneid
	 * @return
	 */
	public Purchase getPurchase(Map<String, Object> hashMap);

	/**
	 * 通过phoneCode查找Purchase
	 * 
	 * @param phoneCode
	 * @return
	 */
	public Purchase getPurchaseByPhoneCode(Map<String, Object> hashmMap);

	/**
	 * 通过phoneModel查找Purchase
	 * 
	 * @param phoneModel
	 * @return
	 */
	public List<Purchase> getPurchaseListByPhoneModel(
			@Param(value = "phoneModel") String phoneModel,
			@Param(value = "shopId") long shopId);
}