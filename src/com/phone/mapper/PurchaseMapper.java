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
	public List<Purchase> getPurchaseListByIds(
			@Param(value = "ids") List<Long> ids);

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
	public int getPurchaseCountByPhoneModel(
			@Param(value = "phoneModel") String phoneModel,
			@Param(value = "shopId") long shopId,
			@Param(value = "status") int status);

	/**
	 * 更换店铺
	 * 
	 * @param phoneCode
	 * @param shopId
	 * @param newShopId
	 * @return
	 */
<<<<<<< HEAD
	public int changeShop(@Param(value = "phoneCode") String phoneCode,
			@Param(value = "shopId") long shopId,
=======
	public int changeShop(@Param(value = "id") long phoneId,
>>>>>>> master1
			@Param(value = "newShopId") long newShopId);

	/**
	 * 更新价格
	 * 
	 * @auther zyslovely@gmail.com
	 * @return
	 */
	public int updatePurchasePrice(
			@Param(value = "purchasePrice") double purchasePrice,
			@Param(value = "shopId") long shopId, @Param(value = "id") long id);

	/**
<<<<<<< HEAD
	 * 
	 * @param purchase
	 * @return
	 */
	public int updatePurchasea(Purchase purchase);
=======
	 * 更新
	 * 
	 * @auther zyslovely@gmail.com
	 * @param purchase
	 * @return
	 */
	public int updatePurchaseWithMeta(Purchase purchase);

	/**
	 * 重置盘点
	 * 
	 * @auther zyslovely@gmail.com
	 * @return
	 */
	public int resetAllInventory(@Param(value = "shopId") long shopId);

	/**
	 * 得到没有在盘点中的
	 * 
	 * @auther zyslovely@gmail.com
	 * @param limit
	 * @param shopId
	 * @param offset
	 * @return
	 */
	public List<Purchase> getNotInventoryList(
			@Param(value = "limit") int limit,
			@Param(value = "shopId") long shopId,
			@Param(value = "offset") int offset);

	/**
	 * 没有盘点的数量
	 * 
	 * @auther zyslovely@gmail.com
	 * @param shopId
	 * @return
	 */
	public int getPurchaseCountNotInventory(@Param(value = "shopId") long shopId);

	/**
	 * 通过品牌获得
	 * 
	 * @auther zyslovely@gmail.com
	 * @param brandIds
	 * @param limit
	 * @param shopId
	 * @param offset
	 * @return
	 */
	public List<Purchase> getPurchaseListByBrandIds(
			@Param(value = "list") List<Long> brandIds,
			@Param(value = "limit") int limit,
			@Param(value = "shopId") long shopId,
			@Param(value = "offset") int offset);

	/**
	 * 根据品牌得到数量
	 * 
	 * @auther zyslovely@gmail.com
	 * @param brandIds
	 * @param shopId
	 * @return
	 */
	public int getPurchaseCountByBrandIds(
			@Param(value = "list") List<Long> brandIds,
			@Param(value = "shopId") long shopId);
>>>>>>> master1

	/**
	 * 通过品牌获取
	 * 
	 * @param hashMap
	 * @return
	 */
	public List<Purchase> getPurchaseListByBrand(Map<String, Object> hashMap);

	/**
	 * 通过品牌获取手机数量
	 * 
	 * @param brand
	 * @param shopId
	 * @param status
	 * @return
	 */
	public int getPurchaseCountByBrand(@Param(value = "brandId") long brandId,
			@Param(value = "shopId") long shopId,
			@Param(value = "status") int status);
}