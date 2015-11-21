package com.phone.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.phone.mapper.BrandMapper;
import com.phone.mapper.DayProfitMapper;
import com.phone.mapper.OperationMapper;
import com.phone.mapper.ProfileMapper;
import com.phone.mapper.ProfitMapper;
import com.phone.mapper.PurchaseMapper;
import com.phone.mapper.SelledMapper;
import com.phone.meta.Brand;
import com.phone.meta.DayProfit;
import com.phone.meta.Operation;
import com.phone.meta.Phone;
import com.phone.meta.Profile;
import com.phone.meta.Profit;
import com.phone.meta.Purchase;
import com.phone.meta.Purchase.PurchaseStatus;
import com.phone.meta.Selled;
import com.phone.meta.Shop;
import com.phone.service.PhoneService;
import com.phone.util.HashMapMaker;
import com.phone.util.ListUtils;
import com.phone.util.TimeUtil;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime：2013-4-16 下午03:31:46 Class Description
 */
@Service("phoneService")
public class PhoneServiceImpl implements PhoneService {

	private static final Logger logger = Logger
			.getLogger(PhoneServiceImpl.class);
	@Resource
	private PurchaseMapper purchaseMapper;

	@Resource
	private SelledMapper selledMapper;

	@Resource
	private ProfitMapper profitMapper;

	@Resource
	private BrandMapper brandMapper;

	@Resource
	private DayProfitMapper dayProfitMapper;

	@Resource
	private OperationMapper operationMapper;

	@Resource
	private ProfileMapper profileMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.PhoneService#getPhoneList(java.lang.String)
	 */
	@Override
	public List<Phone> getPhoneList(String phoneModel, long shopId, int limit,
			int offset, int status) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("phoneModel", phoneModel);
		hashMap.put("shopId", shopId);
		hashMap.put("limit", limit);
		hashMap.put("status", status);
		hashMap.put("offset", offset);
		List<Purchase> purchaseList = purchaseMapper.getPurchaseList(hashMap);
		if (ListUtils.isEmptyList(purchaseList)) {
			return null;
		}
		List<Long> phoneIdList = new ArrayList<Long>(purchaseList.size());
		for (Purchase purchase : purchaseList) {
			phoneIdList.add(purchase.getId());
		}
		List<Phone> phoneList = new ArrayList<Phone>(purchaseList.size());
		this.addProfitInfo(phoneList, phoneIdList, purchaseList, shopId);
		return phoneList;
	}

	/**
	 * 添加销售利润信息
	 * 
	 * @auther zyslovely@gmail.com
	 * @param phoneList
	 * @param phoneIdList
	 * @param purchaseliList
	 */
	private void addProfitInfo(List<Phone> phoneList, List<Long> phoneIdList,
			List<Purchase> purchaseList, long shopId) {
		if (ListUtils.isEmptyList(phoneIdList)) {
			return;
		}
		List<Selled> selledList = selledMapper.getSelledListByIds(phoneIdList,
				shopId);
		Map<Long, Selled> selledMap = HashMapMaker.listToMap(selledList,
				"getPhoneid", Selled.class);
		List<Profit> profitList = profitMapper.getProfitListByIds(phoneIdList,
				shopId);
		Map<Long, Profit> profitMap = HashMapMaker.listToMap(profitList,
				"getPhoneid", Profit.class);
		List<Long> brandIds = new ArrayList<Long>();
		for (Purchase purchase : purchaseList) {
			brandIds.add(purchase.getBrandId());
		}
		List<Brand> brandList = brandMapper.getBrandListByIds(brandIds);
		Map<Long, Brand> brandMap = HashMapMaker.listToMap(brandList, "getId",
				Brand.class);
		for (Purchase purchase : purchaseList) {
			Phone phone = new Phone();
			Selled selled = selledMap.get(purchase.getId());
			if (selled != null) {
				phone.setSelledPrice(selled.getSelledPrice());
				phone.setSelledTime(selled.getCreateTime());
			}
			Profit profit = profitMap.get(purchase.getId());
			if (profit != null) {
				phone.setProfit(profit.getProfit());
			}
			Brand brand = brandMap.get(purchase.getBrandId());
			if (brand != null) {
				phone.setBrand(brand.getBrand());
			}
			phone.setInventory(purchase.getInventory());
			phone.setShopName(Profile.getShopName(purchase.getShopId()));
			phone.setPhoneId(purchase.getId());
			phone.setPhoneCode(purchase.getPhoneCode());
			phone.setPhoneModel(purchase.getPhoneModel());
			phone.setPurchasePrice(purchase.getPurchasePrice());
			phone.setDecideSellPrice(purchase.getDecideSellPrice());
			phone.setPurchaseTime(purchase.getCreateTime());

			phone.setStatus(purchase.getStatus());
			phoneList.add(phone);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.phone.service.PhoneService#getPhonesByPhoneCode(java.lang.String)
	 */
	@Override
	public List<Phone> getPhonesByPhoneCode(String phoneCode, long shopId,
			int status) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("phoneCode", phoneCode);
		hashMap.put("shopId", shopId);
		hashMap.put("status", status);
		Purchase purchase = purchaseMapper.getPurchaseByPhoneCode(hashMap);
		List<Purchase> purchaseList = new ArrayList<Purchase>();
		List<Long> phoneIdList = new ArrayList<Long>(purchaseList.size());
		if (purchase != null) {
			purchaseList.add(purchase);
			phoneIdList.add(purchase.getId());
		}
		List<Phone> phoneList = new ArrayList<Phone>(purchaseList.size());
		this.addProfitInfo(phoneList, phoneIdList, purchaseList, shopId);
		return phoneList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.PhoneService#changeShop(java.lang.String, long,
	 * long)
	 */
	@Override
	public boolean changeShop(long phoneId, long newShopId, long shopId,
			long operatorUserId) {

		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("phoneid", phoneId);
		hashMap.put("shopId", shopId);
		Purchase purchase = purchaseMapper.getPurchase(hashMap);
		if (purchase.getStatus() != PurchaseStatus.NotSold.getValue()) {
			return false;
		}
		Map<String, Object> existHashMap = new HashMap<String, Object>();
		hashMap.put("phoneCode", purchase.getPhoneCode());
		hashMap.put("shopId", newShopId);
		Purchase existPurchase = purchaseMapper.getPurchaseByPhoneCode(existHashMap);
		if (null != existPurchase) {
			return false;
		}
		Operation operation = new Operation();
		Profile profile = profileMapper.getProfile(operatorUserId);
		Brand brand = brandMapper.getBrandById(purchase.getBrandId());
		if (profile != null && brand != null) {

			operation.setComment("  由用户" + profile.getName() + " 将手机型号为"
					+ brand.getBrand() + purchase.getPhoneModel() + " 串号为"
					+ purchase.getPhoneCode() + " 从" + Shop.getShopName(shopId)
					+ " 转入到 " + Shop.getShopName(newShopId));
			operation.setCreateTime(new Date().getTime());
			operation.setType(0);
			operationMapper.addOperation(operation);
		}

		return purchaseMapper.changeShop(phoneId, newShopId) > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.PhoneService#returnPhone(long)
	 */
	@Override
	public boolean returnPhone(long phoneId, long shopId, long operatorUserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phoneid", phoneId);
		map.put("shopId", shopId);
		Selled selled = selledMapper.getSelled(map);
		Purchase purchase = purchaseMapper.getPurchase(map);
		if (selled == null) {
			return false;
		}
		Profit profit = profitMapper.getProfit(map);
		String dayTime = TimeUtil.getFormatTime(selled.getCreateTime());
		DayProfit dayProfit = dayProfitMapper.getDayProfit(dayTime,
				DayProfit.PHONE, shopId);
		double newTotalProfit = dayProfit.getTotalProfit() - profit.getProfit();
		double newTotalSell = dayProfit.getTotalSell()
				- profit.getSelledPrice();
		if (dayProfitMapper.updateDayProfit(newTotalSell, newTotalProfit,
				dayTime, DayProfit.PHONE, shopId) > 0) {
			selledMapper.deleteSelled(phoneId);
			profitMapper.deleteProfit(phoneId);
			map.put("Status", Purchase.PurchaseStatus.NotSold.getValue());
			purchaseMapper.updatePurchase(map);
		}
		Operation operation = new Operation();
		Profile profile = profileMapper.getProfile(operatorUserId);
		Brand brand = brandMapper.getBrandById(purchase.getBrandId());
		if (profile != null && brand != null) {

			operation.setComment("  由用户" + profile.getName() + " 将手机型号为"
					+ brand.getBrand() + purchase.getPhoneModel() + " 串号为"
					+ purchase.getPhoneCode() + " 的手机退货");
			operation.setCreateTime(new Date().getTime());
			operation.setType(2);
			operationMapper.addOperation(operation);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.PhoneService#purchasePriceChange(long, double)
	 */
	@Override
	public boolean purchasePriceChange(long phoneId, double price, long shopId) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("phoneid", phoneId);
		hashMap.put("shopId", shopId);
		Purchase purchase = purchaseMapper.getPurchase(hashMap);
		if (purchase == null
				|| purchase.getStatus() == PurchaseStatus.Deleted.getValue()) {
			return false;
		}
		purchaseMapper.updatePurchasePrice(price, shopId, phoneId);
		if (purchase.getStatus() == PurchaseStatus.Sold.getValue()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("phoneid", phoneId);
			map.put("shopId", shopId);
			Selled selled = selledMapper.getSelled(map);
			Profit profit = profitMapper.getProfit(map);
			String dayTime = TimeUtil.getFormatTime(selled.getCreateTime());
			DayProfit dayProfit = dayProfitMapper.getDayProfit(dayTime,
					DayProfit.PHONE, shopId);
			double newTotalProfit = dayProfit.getTotalProfit()
					+ purchase.getPurchasePrice() - price;
			if (dayProfitMapper.updateDayProfit(dayProfit.getTotalSell(),
					newTotalProfit, dayTime, DayProfit.PHONE, shopId) > 0) {
				profit.setPurchasePrice(price);
				profit.setProfit(profit.getProfit()
						+ purchase.getPurchasePrice() - price);
				profitMapper.updateProfit(profit);
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean sellPriceChange(long phoneId, double price, long shopId) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("phoneid", phoneId);
		hashMap.put("shopId", shopId);
		Purchase purchase = purchaseMapper.getPurchase(hashMap);
		if (purchase == null
				|| purchase.getStatus() == PurchaseStatus.Deleted.getValue()) {
			return false;
		}
		if (purchase.getStatus() == PurchaseStatus.Sold.getValue()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("phoneid", phoneId);
			map.put("shopId", shopId);
			Selled selled = selledMapper.getSelled(map);
			Profit profit = profitMapper.getProfit(map);
			String dayTime = TimeUtil.getFormatTime(selled.getCreateTime());
			DayProfit dayProfit = dayProfitMapper.getDayProfit(dayTime,
					DayProfit.PHONE, shopId);
			double newTotalProfit = dayProfit.getTotalProfit() + price
					- selled.getSelledPrice();
			double newTotalSell = dayProfit.getTotalSell() + price
					- selled.getSelledPrice();
			if (dayProfitMapper.updateDayProfit(newTotalSell, newTotalProfit,
					dayTime, DayProfit.PHONE, shopId) > 0) {
				profit.setSelledPrice(price);
				profit.setProfit(profit.getProfit() + price
						- selled.getSelledPrice());
				profitMapper.updateProfit(profit);

				selled.setSelledPrice(price);
				selledMapper.updateSelled(selled);
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.PhoneService#getPhoneListNoInventory(long, int,
	 * int)
	 */
	@Override
	public List<Phone> getPhoneListNoInventory(long shopId, int limit,
			int offset) {
		List<Purchase> purchaseList = purchaseMapper.getNotInventoryList(limit,
				shopId, offset);
		if (ListUtils.isEmptyList(purchaseList)) {
			return null;
		}
		List<Long> phoneIdList = new ArrayList<Long>(purchaseList.size());
		for (Purchase purchase : purchaseList) {
			phoneIdList.add(purchase.getId());
		}
		List<Phone> phoneList = new ArrayList<Phone>(purchaseList.size());
		this.addProfitInfo(phoneList, phoneIdList, purchaseList, shopId);
		return phoneList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.phone.service.PhoneService#getPhoneListByBrandName(java.lang.String,
	 * int, int, long)
	 */
	@Override
	public List<Phone> getPhoneListByBrandName(String brandName, int limit,
			int offset, long shopId, int status) {
		List<Brand> brands = brandMapper.getBrandListByName(brandName);
		if (ListUtils.isEmptyList(brands)) {
			return null;
		}
		logger.info("showPhoneList get by brancds size = " + brands.size());
		List<Long> brandIds = new ArrayList<Long>();
		for (Brand brand : brands) {
			brandIds.add(brand.getId());
		}
		List<Purchase> purchaseList = purchaseMapper.getPurchaseListByBrandIds(
				brandIds, limit, shopId, offset, status);
		if (ListUtils.isEmptyList(purchaseList)) {
			return null;
		}
		logger.info("showPhoneList get by purchaseList size = "
				+ purchaseList.size());
		List<Long> phoneIdList = new ArrayList<Long>(purchaseList.size());
		for (Purchase purchase : purchaseList) {
			phoneIdList.add(purchase.getId());
		}
		List<Phone> phoneList = new ArrayList<Phone>(purchaseList.size());
		logger.info("showPhoneList get by phoneList size = " + phoneList.size());
		this.addProfitInfo(phoneList, phoneIdList, purchaseList, shopId);
		logger.info("showPhoneList get by phoneList size = " + phoneList.size());
		return phoneList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.phone.service.PhoneService#getPhoneCountByBrandName(java.lang.String,
	 * long)
	 */
	@Override
	public int getPhoneCountByBrandName(String brandName, long shopId) {
		List<Brand> brands = brandMapper.getBrandListByName(brandName);
		if (ListUtils.isEmptyList(brands)) {
			return 0;
		}
		List<Long> brandIds = new ArrayList<Long>();
		for (Brand brand : brands) {
			brandIds.add(brand.getId());
		}
		return purchaseMapper.getPurchaseCountByBrandIds(brandIds, shopId);
	}
}