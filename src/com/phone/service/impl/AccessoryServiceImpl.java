package com.phone.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.phone.mapper.AccessoryInfoMapper;
import com.phone.mapper.AccessoryMapper;
import com.phone.mapper.AccessoryProfitMapper;
import com.phone.mapper.AccessorySoldMapper;
import com.phone.meta.Accessory;
import com.phone.meta.AccessoryInfo;
import com.phone.meta.AccessoryProfit;
import com.phone.meta.AccessorySold;
import com.phone.service.AccessoryService;
import com.phone.util.ListUtils;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-27 下午07:43:39
 * @see Class Description
 */
@Service("accessoryService")
public class AccessoryServiceImpl implements AccessoryService {

	private static final Logger logger = Logger.getLogger(AccessoryServiceImpl.class);
	@Resource
	private AccessoryInfoMapper accessoryInfoMapper;

	@Resource
	private AccessoryMapper accessoryMapper;
	@Resource
	private AccessorySoldMapper accessorySoldMapper;

	@Resource
	private AccessoryProfitMapper accessoryProfitMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.phone.service.AccessoryService#addAccessoryInfo(java.lang.String)
	 */
	@Override
	public boolean addAccessoryInfo(String name) {
		List<AccessoryInfo> list = accessoryInfoMapper.getAccessoryInfo(name);
		if (!ListUtils.isEmptyList(list)) {
			logger.error("添加配件类型失败，原因是已经存在了=" + name);
			return false;
		}
		AccessoryInfo accessoryInfo = new AccessoryInfo();
		accessoryInfo.setAccessoryInfoName(name.trim());
		accessoryInfo.setTypeId(0);
		return accessoryInfoMapper.addAccessoryInfo(accessoryInfo) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.AccessoryService#addAccessory(java.lang.String,
	 * int, long)
	 */
	@Override
	public boolean addAccessory(String name, int count, long accessoryInfoId, double unitPrice, long userId, long shopId) {
		long nowTime = new Date().getTime();
		Accessory accessory = new Accessory();
		accessory = new Accessory();
		accessory.setName(name.trim());
		accessory.setAccessoryInfoId(accessoryInfoId);
		accessory.setCount(count);
		accessory.setCreateTime(nowTime);
		accessory.setLastUpdateTime(nowTime);
		accessory.setUnitPrice(unitPrice);
		accessory.setAccessoryTypeId(0);
		accessory.setShopId(shopId);
		accessory.setOperatorId(userId);
		return accessoryMapper.addAccessory(accessory) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.AccessoryService#deleteAccessoryById(long, int)
	 */
	@Override
	public boolean descCountAccessoryById(long id, int count, double soldPrice, long shopId, long userId) {
		Accessory accessory = accessoryMapper.getAccessoryById(id, shopId);
		if (accessory == null) {
			logger.error("deleteAccessoryById accessory 不存在 id=" + id);
			return false;
		}
		if (accessory.getCount() - count < 0) {
			logger.error("deleteAccessoryById accessory 数量不够 id=" + id);
			return false;
		}
		long nowTime = new Date().getTime();
		if (accessoryMapper.updateAccessoryByid(accessory.getCount() - count, new Date().getTime(), id, shopId) > 0) {
			AccessorySold accessorySold = new AccessorySold();
			accessorySold.setAccessoryid(accessory.getId());
			accessorySold.setCreateTime(nowTime);
			accessorySold.setOperatorId(userId);
			accessorySold.setShopId(shopId);
			accessorySold.setSoldPrice(soldPrice);
			accessorySoldMapper.addAccessorySold(accessorySold);

			AccessoryProfit accessoryProfit = new AccessoryProfit();
			accessoryProfit.setAccessoryid(accessory.getId());
			accessoryProfit.setCreateTime(nowTime);
			accessoryProfit.setOperatorId(userId);
			accessoryProfit.setProfit(soldPrice - accessory.getUnitPrice());
			accessoryProfit.setPurchasePrice(accessory.getUnitPrice());
			accessoryProfit.setSoldPrice(soldPrice);
			accessoryProfit.setShopId(shopId);
			accessoryProfitMapper.addAccessoryProfit(accessoryProfit);
			return true;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.AccessoryService#getAccessoryById(long)
	 */
	@Override
	public Accessory getAccessoryById(long id, long shopId) {
		return accessoryMapper.getAccessoryById(id, shopId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.AccessoryService#getAllAccessoryInfo()
	 */
	@Override
	public List<AccessoryInfo> getAllAccessoryInfo() {
		return accessoryInfoMapper.getAllAccessoryInfo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.phone.service.AccessoryService#getAccessoryList(java.lang.String,
	 * int, int, long)
	 */
	@Override
	public List<Accessory> getAccessoryList(String name, long shopId, int limit, int offset, long accessoryInfoId) {
		List<Accessory> accessories = accessoryMapper.getAccessoryList(name, accessoryInfoId, shopId, limit, offset);
		if (ListUtils.isEmptyList(accessories)) {
			return null;
		}
		for (Accessory accessory : accessories) {
			AccessoryInfo accessoryInfo = accessoryInfoMapper.getAccessoryInfoById(accessory.getAccessoryInfoId());
			if (accessoryInfo != null) {
				accessory.setAccessoryInfoName(accessoryInfo.getAccessoryInfoName());
			}
		}
		return accessories;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.phone.service.AccessoryService#getAccessoryCount(java.lang.String,
	 * long)
	 */
	@Override
	public int getAccessoryCount(String name, long accessoryInfoId, long shopId) {
		return accessoryMapper.getAccessoryCount(name, accessoryInfoId, shopId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.AccessoryService#getProfitList(long, long, long,
	 * int, int)
	 */
	@Override
	public List<AccessoryProfit> getProfitList(long startTime, long endTime, long shopId, int limit, int offset) {
		if (startTime < 0 || endTime < 0 || endTime > new Date().getTime()) {
			return null;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("startTime", startTime);
		hashMap.put("endTime", endTime);
		hashMap.put("shopId", shopId);
		hashMap.put("limit", shopId);
		hashMap.put("offset", shopId);
		return accessoryProfitMapper.getAccessoryProfitList(hashMap);
	}
}
