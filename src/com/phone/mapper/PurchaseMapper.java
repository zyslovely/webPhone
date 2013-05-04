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
	 * 通过ids获取
	 * 
	 * @auther zyslovely@gmail.com
	 * @param ids
	 * @return
	 */
	public List<Purchase> getPurchaseListByIds(@Param(value = "ids") List<Long> ids);

	/**
	 * 通过phoneCode查找Purchase
	 * 
	 * @param phoneCode
	 * @return
	 */
	public Purchase getPurchaseByPhoneCode(Map<String, Object> hashmMap);

	/**
	 * 获得手机数量
	 * 
	 * @auther zyslovely@gmail.com
	 * @param phoneModel
	 * @param shopId
	 * @return
	 */
	public int getPurchaseCountByPhoneModel(@Param(value = "phoneModel") String phoneModel, @Param(value = "shopId") long shopId,
			@Param(value = "status") int status);

	/**
	 * 更换店铺
	 * 
	 * @param phoneCode
	 * @param shopId
	 * @param newShopId
	 * @return
	 */
	public int changeShop(@Param(value = "phoneCode") String phoneCode, @Param(value = "shopId") long shopId,
			@Param(value = "newShopId") long newShopId);

	/**
	 * 更新价格
	 * 
	 * @auther zyslovely@gmail.com
	 * @return
	 */
	public int updatePurchasePrice(@Param(value = "purchasePrice") double purchasePrice, @Param(value = "shopId") long shopId,
			@Param(value = "id") long id);

	public int updatePurchasea(Purchase purchase);

}