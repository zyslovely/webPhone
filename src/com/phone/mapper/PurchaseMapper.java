package com.phone.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.phone.meta.Purchase;

public interface PurchaseMapper {

	/**
	 * ���Purchase
	 * 
	 * @param purchase
	 */
	public int addPurchase(Purchase purchase);

	/**
	 * 通过手机型号查找手机列表
	 * 
	 * @param phoneModel
	 * @return
	 */
	public List<Purchase> getPuchaseList(
			@Param(value = "phoneModel")String phoneModel);
}