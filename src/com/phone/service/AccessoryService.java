package com.phone.service;

import java.util.List;

import com.phone.meta.Accessory;
import com.phone.meta.AccessoryInfo;
import com.phone.meta.AccessoryProfitVO;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-27 下午07:43:27
 * @see Class Description
 */
public interface AccessoryService {
	/**
	 * 添加配件类型信息
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 */
	public boolean addAccessoryInfo(String name);

	/**
	 * 添加配件
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @param count
	 * @param accessoryInfoId
	 * @return
	 */
	public boolean addAccessory(String name, int count, long accessoryInfoId,
			double unitPrice, long userId, long shopId);

	/**
	 * 卖出配件
	 * 
	 * @auther zyslovely@gmail.com
	 * @return
	 */
	public boolean descCountAccessoryById(long id, int count, double soldPrice,
			long shopId, long userId);

	/**
	 * 获取所有配件类型
	 * 
	 * @auther zyslovely@gmail.com
	 * @return
	 */
	public List<AccessoryInfo> getAllAccessoryInfo();

	/**
	 * 获取配件
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public Accessory getAccessoryById(long id, long shopId);

	/**
	 * 获取配件列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @param limit
	 * @param offset
	 * @param accessoryInfoId
	 * @return
	 */
	public List<Accessory> getAccessoryList(String name, long shopId,
			int limit, int offset, long accessoryInfoId);

	/**
	 * 获取配件数量
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @param accessoryInfoId
	 * @return
	 */
	public int getAccessoryCount(String name, long accessoryInfoId, long shopId);

	/**
	 * 利润列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param startTime
	 * @param endTime
	 * @param shopId
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<AccessoryProfitVO> getProfitList(long startTime, long endTime,
			long shopId, int limit, int offset);

	/**
	 * 获取配件利润数量
	 * 
	 * @param startTime
	 * @param endTime
	 * @param shopId
	 * @return
	 */
	public int getAccessoryProfitCount(long startTime, long endTime, long shopId);

	/**
	 * 配件转移店铺
	 * 
	 * @param id
	 * @param shopId
	 * @param newShopId
	 * @param count
	 * @return
	 */
	public boolean changeAccessoryWithShop(long id, long shopId,
			long newShopId, int count);

	/**
	 * 修改购入价格
	 * 
	 * @param accessoryId
	 * @param price
	 * @return
	 */
	public boolean purchasePriceChange(long accessoryId, double price,
			long shopId);

	/**
	 * 删除配件
	 * 
	 * @param id
	 * @param userId
	 * @param shopId
	 * @return
	 */
	public boolean deleteAccessory(long id, long shopId);
}
