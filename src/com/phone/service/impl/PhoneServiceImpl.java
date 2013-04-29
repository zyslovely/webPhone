package com.phone.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.phone.mapper.BrandMapper;
import com.phone.mapper.ProfitMapper;
import com.phone.mapper.PurchaseMapper;
import com.phone.mapper.SelledMapper;
import com.phone.meta.Brand;
import com.phone.meta.Phone;
import com.phone.meta.Profit;
import com.phone.meta.Purchase;
import com.phone.meta.Selled;
import com.phone.service.PhoneService;
import com.phone.util.HashMapMaker;
import com.phone.util.ListUtils;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime：2013-4-16 下午03:31:46 Class Description
 */
@Service("phoneService")
public class PhoneServiceImpl implements PhoneService {

	@Resource
	private PurchaseMapper purchaseMapper;

	@Resource
	private SelledMapper selledMapper;

	@Resource
	private ProfitMapper profitMapper;

	@Resource
	private BrandMapper brandMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.PhoneService#getPhoneList(java.lang.String)
	 */
	@Override
	public List<Phone> getPhoneList(String phoneModel, long shopId, int limit,
			int offset) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("phoneModel", phoneModel);
		hashMap.put("shopId", shopId);
		hashMap.put("limit", limit);
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
		Map<String, Object> hashMap = new HashMap<String, Object>();
		List<Map<String, Object>> hashMapList = new ArrayList<Map<String, Object>>(
				phoneIdList.size());
		for (Long selledId : phoneIdList) {
			hashMap.put("phoneid", selledId);
			hashMap.put("shopId", shopId);
			hashMapList.add(hashMap);
		}
		List<Selled> selledList = selledMapper.getSelledListByIds(hashMapList);
		Map<Long, Selled> selledMap = HashMapMaker.listToMap(selledList,
				"getPhoneid", Selled.class);
		List<Profit> profitList = profitMapper.getProfitListByIds(hashMapList);
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
				phone.setSelledTime(phone.getSelledTime());
			}
			Profit profit = profitMap.get(purchase.getId());
			if (profit != null) {
				phone.setProfit(profit.getProfit());
			}
			Brand brand = brandMap.get(purchase.getBrandId());
			if (brand != null) {
				phone.setBrand(brand.getBrand());
			}
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
	public List<Phone> getPhonesByPhoneCode(String phoneCode, long shopId) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("phoneCode", phoneCode);
		hashMap.put("shopId", shopId);
		Purchase purchase = purchaseMapper.getPurchaseByPhoneCode(hashMap);
		List<Purchase> purchaseList = new ArrayList<Purchase>();
		purchaseList.add(purchase);
		List<Long> phoneIdList = new ArrayList<Long>(purchaseList.size());
		phoneIdList.add(purchase.getId());

		List<Phone> phoneList = new ArrayList<Phone>(purchaseList.size());
		this.addProfitInfo(phoneList, phoneIdList, purchaseList, shopId);
		return phoneList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.phone.service.PhoneService#getPhoneListByPhoneModel(java.lang.String)
	 */
	@Override
	public List<Phone> getPhoneListByPhoneModel(String phoneModel, long shopId) {
		List<Purchase> purchaseList = purchaseMapper
				.getPurchaseListByPhoneModel(phoneModel, shopId);
		List<Long> phoneIdList = new ArrayList<Long>(purchaseList.size());
		for (Purchase purchase : purchaseList) {
			phoneIdList.add(purchase.getId());
		}
		List<Phone> phoneList = new ArrayList<Phone>(purchaseList.size());
		this.addProfitInfo(phoneList, phoneIdList, purchaseList, shopId);
		return phoneList;
	}
}