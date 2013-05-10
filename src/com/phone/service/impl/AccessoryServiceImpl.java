package com.phone.service.impl;

import java.util.ArrayList;
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
import com.phone.mapper.DayProfitMapper;
import com.phone.meta.Accessory;
import com.phone.meta.AccessoryInfo;
import com.phone.meta.AccessoryProfit;
import com.phone.meta.AccessoryProfitVO;
import com.phone.meta.AccessorySold;
import com.phone.meta.DayProfit;
import com.phone.service.AccessoryService;
import com.phone.util.HashMapMaker;
import com.phone.util.ListUtils;
import com.phone.util.TimeUtil;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-27 下午07:43:39
 * @see Class Description
 */
@Service("accessoryService")
public class AccessoryServiceImpl implements AccessoryService {

	private static final Logger logger = Logger
			.getLogger(AccessoryServiceImpl.class);
	@Resource
	private AccessoryInfoMapper accessoryInfoMapper;

	@Resource
	private AccessoryMapper accessoryMapper;
	@Resource
	private AccessorySoldMapper accessorySoldMapper;

	@Resource
	private AccessoryProfitMapper accessoryProfitMapper;
	@Resource
	private DayProfitMapper dayProfitMapper;

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
	public boolean addAccessory(String name, int count, long accessoryInfoId,
			double unitPrice, long userId, long shopId) {
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
	public boolean descCountAccessoryById(long id, int count, double soldPrice,
			long shopId, long userId) {
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
		if (accessoryMapper.updateAccessoryByid(accessory.getCount() - count,
				new Date().getTime(), id, shopId) > 0) {
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

			String dayTime = TimeUtil.getFormatTime(nowTime);
			DayProfit dayProfit = dayProfitMapper.getDayProfit(dayTime,
					DayProfit.ACCESSORY, shopId);
			if (dayProfit == null) {
				dayProfit = new DayProfit();
				dayProfit.setDaytime(dayTime);
				dayProfit.setTotalProfit(accessoryProfit.getProfit());
				dayProfit.setTotalSell(accessoryProfit.getSoldPrice());
				dayProfit.setShopId(shopId);
				dayProfitMapper.addDayProfit(dayProfit);
			} else {
				dayProfitMapper.updateDayProfit(
						dayProfit.getTotalSell()
								+ accessoryProfit.getSoldPrice(),
						dayProfit.getTotalProfit()
								+ accessoryProfit.getProfit(), dayTime,
						DayProfit.ACCESSORY, shopId);
			}
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
	public List<Accessory> getAccessoryList(String name, long shopId,
			int limit, int offset, long accessoryInfoId) {
		List<Accessory> accessories = accessoryMapper.getAccessoryList(name,
				accessoryInfoId, shopId, limit, offset);
		if (ListUtils.isEmptyList(accessories)) {
			return null;
		}
		for (Accessory accessory : accessories) {
			AccessoryInfo accessoryInfo = accessoryInfoMapper
					.getAccessoryInfoById(accessory.getAccessoryInfoId());
			if (accessoryInfo != null) {
				accessory.setAccessoryInfoName(accessoryInfo
						.getAccessoryInfoName());
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
	public List<AccessoryProfitVO> getProfitList(long startTime, long endTime,
			long shopId, int limit, int offset) {
		if (startTime < 0 || endTime < 0 || endTime > new Date().getTime()) {
			return null;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("startTime", startTime);
		hashMap.put("endTime", endTime);
		hashMap.put("shopId", shopId);
		hashMap.put("limit", limit);
		hashMap.put("offset", offset);
		List<AccessoryProfit> accessoryProfitList = accessoryProfitMapper
				.getAccessoryProfitList(hashMap);
		if (ListUtils.isEmptyList(accessoryProfitList)) {
			return null;
		}
		List<Long> accessoryIdList = new ArrayList<Long>(
				accessoryProfitList.size());
		for (AccessoryProfit accessoryProfit : accessoryProfitList) {
			accessoryIdList.add(accessoryProfit.getAccessoryid());
		}
		List<AccessorySold> accessorySoldList = accessorySoldMapper
				.getAccessorySoldByIds(accessoryIdList);
		List<Accessory> accessoryList = accessoryMapper
				.getAccessoryListByIds(accessoryIdList);
		List<Long> accessoryInfoIdList = new ArrayList<Long>();
		for (Accessory accessory : accessoryList) {
			accessoryInfoIdList.add(accessory.getAccessoryInfoId());
		}
		List<AccessoryInfo> accessoryInfoList = accessoryInfoMapper
				.getAccessoryInfoByIds(accessoryInfoIdList);
		Map<Long, AccessoryInfo> accessoryInfoMap = HashMapMaker.listToMap(
				accessoryInfoList, "getId", AccessoryInfo.class);
		Map<Long, AccessorySold> accessorySoldMap = HashMapMaker.listToMap(
				accessorySoldList, "getAccessoryid", AccessorySold.class);
		Map<Long, AccessoryProfit> accessoryProfitMap = HashMapMaker.listToMap(
				accessoryProfitList, "getAccessoryid", AccessoryProfit.class);
		List<AccessoryProfitVO> accessoryProfitVOList = new ArrayList<AccessoryProfitVO>();
		for (Accessory accessory : accessoryList) {
			AccessoryProfitVO accessoryProfitVO = new AccessoryProfitVO();
			AccessoryProfit accessoryProfit = accessoryProfitMap.get(accessory
					.getId());
			AccessoryInfo accessoryInfo = accessoryInfoMap.get(accessory
					.getAccessoryInfoId());
			AccessorySold accessorySold = accessorySoldMap.get(accessory
					.getId());
			accessoryProfitVO.setName(accessory.getName());
			accessoryProfitVO.setAccessoryInfoName(accessoryInfo
					.getAccessoryInfoName());
			accessoryProfitVO.setUnitPrice(accessory.getUnitPrice());
			accessoryProfitVO.setSoldPrice(accessorySold.getSoldPrice());
			accessoryProfitVO.setProfit(accessoryProfit.getProfit());
			accessoryProfitVO.setLastUpdateTime(TimeUtil
					.getFormatTimeInMinute(accessoryProfit.getCreateTime()));
			accessoryProfitVOList.add(accessoryProfitVO);
		}
		return accessoryProfitVOList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.AccessoryService#getAccessoryProfitCount(long,
	 * long, java.lang.Long)
	 */
	@Override
	public int getAccessoryProfitCount(long startTime, long endTime, long shopId) {
		return accessoryProfitMapper.getAccessoryProfitCount(shopId, startTime,
				endTime);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.AccessoryService#changeAccessoryWithShop(long,
	 * long, long, int)
	 */
	@Override
	public boolean changeAccessoryWithShop(long id, long shopId,
			long newShopId, int changeCount) {
		Accessory accessory = accessoryMapper.getAccessoryById(id, shopId);
		if (changeCount > accessory.getCount()) {
			return false;
		}
		if (accessoryMapper.updateAccessoryByid(accessory.getCount()
				- changeCount, new Date().getTime(), id, shopId) < 0) {
			return false;
		}
		accessory.setCount(changeCount);
		accessory.setCreateTime(new Date().getTime());
		accessory.setLastUpdateTime(new Date().getTime());
		accessory.setShopId(newShopId);
		if (accessoryMapper.addAccessory(accessory) > 0) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.AccessoryService#purchasePriceChange(long, double,
	 * long)
	 */
	@Override
	public boolean purchasePriceChange(long accessoryId, double price,
			long shopId) {
		if (accessoryMapper.updateAccessoryUnitPriceByid(accessoryId, price,
				shopId) > 0
				&& accessoryProfitMapper.updatePurchasePriceByid(accessoryId,
						price, shopId) > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteAccessory(long id, long shopId) {
		if (accessorySoldMapper.getAccessorySold(id, shopId) != null) {
			return false;
		}
		if (accessoryMapper.deleteAccessory(id, shopId) > 0) {
			return true;
		}
		return false;
	}
}
