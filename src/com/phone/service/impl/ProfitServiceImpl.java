package com.phone.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.phone.mapper.BrandMapper;
import com.phone.mapper.ProfitMapper;
import com.phone.mapper.PurchaseMapper;
import com.phone.meta.Brand;
import com.phone.meta.Profit;
import com.phone.meta.ProfitVo;
import com.phone.meta.Purchase;
import com.phone.service.ProfitService;
import com.phone.util.HashMapMaker;
import com.phone.util.ListUtils;
import com.phone.util.TimeUtil;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime：2013-4-23 上午02:57:16 Class Description
 */
@Service("profitService")
public class ProfitServiceImpl implements ProfitService {
	@Resource
	private ProfitMapper profitMapper;
	@Resource
	private PurchaseMapper purchaseMapper;
	@Resource
	private BrandMapper brandMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.ProfitService#getProfitList(long, long)
	 */
	@Override
	public List<ProfitVo> getProfitList(long startTime, long endTime, long shopId, int limit, int offset) {
		if (startTime < 0 || endTime < 0 || endTime > new Date().getTime()) {
			return null;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("startTime", startTime);
		hashMap.put("endTime", endTime);
		hashMap.put("shopId", shopId);
		hashMap.put("limit", limit);
		hashMap.put("offset", offset);
		List<Profit> profitList = profitMapper.getProfitList(hashMap);
		if (ListUtils.isEmptyList(profitList)) {
			return null;
		}
		List<Long> purchaseIdList = new ArrayList<Long>(profitList.size());

		for (Profit profit : profitList) {
			purchaseIdList.add(profit.getPhoneid());
		}
		List<Purchase> purchases = purchaseMapper.getPurchaseListByIds(purchaseIdList);
		List<Long> brandIdList = new ArrayList<Long>(purchases.size());
		for (Purchase purchase : purchases) {
			brandIdList.add(purchase.getBrandId());
		}
		List<Brand> brandList = brandMapper.getBrandListByIds(brandIdList);
		Map<Long, Brand> brandMap = HashMapMaker.listToMap(brandList, "getId", Brand.class);
		Map<Long, Purchase> purchaseMap = HashMapMaker.listToMap(purchases, "getId", Purchase.class);
		List<ProfitVo> profitVos = new ArrayList<ProfitVo>();
		for (Profit profit : profitList) {
			ProfitVo profitVo = new ProfitVo();
			Purchase purchase = purchaseMap.get(profit.getPhoneid());
			if (purchase == null) {
				continue;
			}
			Brand brand = brandMap.get(purchase.getBrandId());
			profitVo.setBrandName(brand.getBrand());
			profitVo.setPhoneCode(purchase.getPhoneCode());
			profitVo.setPhoneModel(purchase.getPhoneModel());
			profitVo.setProfit(profit.getProfit());
			profitVo.setPurchasePrice(purchase.getPurchasePrice());
			profitVo.setPurchaseTimeStr(TimeUtil.getFormatTimeInMinute(purchase.getCreateTime()));
			profitVo.setSelledPrice(profit.getSelledPrice());
			profitVo.setSelledTimeStr(TimeUtil.getFormatTimeInMinute(profit.getCreateTime()));
			profitVos.add(profitVo);
		}
		return profitVos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.ProfitService#getProfitCount(long, long, long)
	 */
	@Override
	public int getProfitCount(long startTime, long endTime, long shopId) {
		return profitMapper.getProfitCountByTime(shopId, startTime, endTime);
	}
}
