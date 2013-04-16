package com.phone.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.phone.meta.Purchase;

public interface PurchaseMapper {

	/**
<<<<<<< HEAD
	 * ���Purchase
=======
	 * 添加Purchase
>>>>>>> a85e592608014a1476691a5119e9151f30532af6
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