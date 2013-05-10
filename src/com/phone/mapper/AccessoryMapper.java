package com.phone.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.phone.meta.Accessory;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-27 下午07:27:09
 * @see Class Description
 */
public interface AccessoryMapper {
	/**
	 * 添加配件
	 * 
	 * @auther zyslovely@gmail.com
	 * @param accessory
	 * @return
	 */
	public int addAccessory(Accessory accessory);

	/**
	 * 获得配件
	 * 
	 * @auther zyslovely@gmail.com
	 * @param accessoryinfoId
	 * @param name
	 * @return
	 */
	public Accessory getAccessoryByInfoId(
			@Param(value = "accessoryInfoId") long accessoryinfoId,
			@Param(value = "name") String name,
			@Param(value = "shopId") long shopId);

	/**
	 * 获取配件
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public Accessory getAccessoryById(@Param(value = "id") long id,
			@Param(value = "shopId") long shopId);

	/**
	 * 对accessory做更新
	 * 
	 * @auther zyslovely@gmail.com
	 * @param count
	 * @param lastUpdateTime
	 * @param id
	 * @return
	 */
	public int updateAccessoryByid(@Param(value = "count") int count,
			@Param(value = "lastUpdateTime") long lastUpdateTime,
			@Param(value = "id") long id, @Param(value = "shopId") long shopId);

	/**
	 * 获取配件列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @param accessoryInfoId
	 * @param limit
	 * @param offset
	 * @return
	 */
	public List<Accessory> getAccessoryList(@Param(value = "name") String name,
			@Param(value = "accessoryInfoId") long accessoryInfoId,
			@Param(value = "shopId") long shopId,
			@Param(value = "limit") int limit,
			@Param(value = "offset") int offset);

	/**
	 * 获取配件数量
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @param accessoryInfoId
	 * @return
	 */
	public int getAccessoryCount(@Param(value = "name") String name,
			@Param(value = "accessoryInfoId") long accessoryInfoId,
			@Param(value = "shopId") long shopId);

	/**
	 * 改变配件购入价格
	 * 
	 * @param unitPrice
	 * @param id
	 * @param shopId
	 * @return
	 */
	public int updateAccessoryUnitPriceByid(@Param(value = "id") long id,
			@Param(value = "unitPrice") double unitPrice,
			@Param(value = "shopId") long shopId);

	/**
	 * 删除配件
	 * 
	 * @param id
	 * @param shopId
	 * @return
	 */
	public int deleteAccessory(@Param(value = "id") long id,
			@Param(value = "shopId") long shopId);
}
