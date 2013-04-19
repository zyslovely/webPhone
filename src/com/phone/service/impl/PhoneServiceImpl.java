package com.phone.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.phone.mapper.ProfitMapper;
import com.phone.mapper.PurchaseMapper;
import com.phone.mapper.SelledMapper;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.PhoneService#getPhoneList(java.lang.String)
	 */
	@Override
	public List<Phone> getPhoneList(String phoneModel) {
		List<Purchase> purchaseliList = purchaseMapper.getPurchaseList(phoneModel);
		if (ListUtils.isEmptyList(purchaseliList)) {
			return null;
		}
		List<Long> phoneIdList = new ArrayList<Long>(purchaseliList.size());
		for (Purchase purchase : purchaseliList) {
			phoneIdList.add(purchase.getId());
		}
		List<Phone> phoneList = new ArrayList<Phone>(purchaseliList.size());
		this.addProfitInfo(phoneList, phoneIdList, purchaseliList);
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
	private void addProfitInfo(List<Phone> phoneList, List<Long> phoneIdList, List<Purchase> purchaseliList) {
		List<Selled> selledList = selledMapper.getSelledListByIds(phoneIdList);
		Map<Long, Selled> selledMap = HashMapMaker.listToMap(selledList, "getPhoneid", Selled.class);
		List<Profit> profitList = profitMapper.getProfitListByIds(phoneIdList);
		Map<Long, Profit> profitMap = HashMapMaker.listToMap(profitList, "getPhoneid", Profit.class);
		for (Purchase purchase : purchaseliList) {
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
}