package com.phone.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.phone.mapper.ProfitMapper;
import com.phone.mapper.PurchaseMapper;
import com.phone.mapper.SelledMapper;
import com.phone.meta.Profit;
import com.phone.meta.Purchase;
import com.phone.meta.Selled;
import com.phone.service.SelledService;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime：2013-4-20 上午01:52:59 Class Description
 */
@Service("selledService")
public class SelledServiceImpl implements SelledService {

	private static final Logger logger = Logger
			.getLogger(SelledServiceImpl.class);

	@Resource
	private SelledMapper selledMapper;

	@Resource
	private PurchaseMapper purchaseMapper;

	@Resource
	private ProfitMapper profitMapper;

	@Override
	/*
	 * 
	 */
	public boolean addSelled(long phoneid, double selledPrice, long operatorId,
			long shopId) {
		Selled selled = new Selled();
		selled.setPhoneid(phoneid);
		selled.setSelledPrice(selledPrice);
		selled.setCreateTime(new Date().getTime());
		selled.setOperatorId(operatorId);
		if (selledMapper.addSelled(selled) > 0) {
			this.updatePurchaseAndProfit(phoneid, selledPrice, operatorId,
					shopId);
			return true;
		}
		return false;
	}

	private void updatePurchaseAndProfit(long phoneid, double selledPrice,
			long operatorId, long shopId) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("phoneid", phoneid);
		hashMap.put("operatorId", operatorId);
		hashMap.put("shopId", shopId);
		Purchase purchase = purchaseMapper.getPurchase(hashMap);
		Map<String, Object> map = new HashMap<String, Object>();
		hashMap.put("phoneid", purchase.getId());
		hashMap.put("Status", Purchase.PurchaseStatus.Sold.getValue());
		if (purchaseMapper.updatePurchase(map) > 0) {
			logger.info("updatePurchase Successed!");
			Profit profit = new Profit();
			profit.setPhoneid(phoneid);
			profit.setPurchasePrice(purchase.getPurchasePrice());
			profit.setDecideSellPrice(purchase.getDecideSellPrice());
			profit.setSelledPrice(selledPrice);
			profit.setProfit(selledPrice - purchase.getPurchasePrice());
			profit.setCreateTime(new Date().getTime());
			profit.setOperatorId(operatorId);
			profit.setShopId(purchase.getShopId());
			if (profitMapper.addProfit(profit) > 0) {
				logger.info("addProfit Successed!");
			}
		}
	}

	@Override
	public Selled getSelled(long phoneid, long operatorId, long shopId) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("phoneid", phoneid);
		hashMap.put("operatorId", operatorId);
		hashMap.put("shopId", shopId);
		return selledMapper.getSelled(hashMap);
	}

	@Override
	public List<Selled> getSelledList(List<Long> selledIdList, long shopId) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		List<Map<String, Object>> hashMapList = new ArrayList<Map<String, Object>>(
				selledIdList.size());
		for (Long selledId : selledIdList) {
			hashMap.put("phoneid", selledId);
			hashMap.put("shopId", shopId);
			hashMapList.add(hashMap);
		}
		return selledMapper.getSelledListByIds(hashMapList);
	}
}
