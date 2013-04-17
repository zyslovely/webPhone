package com.phone.mapper;

import java.util.List;

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
	public List<Purchase> getPurchaseList(@Param(value = "phoneModel") String phoneModel);
}