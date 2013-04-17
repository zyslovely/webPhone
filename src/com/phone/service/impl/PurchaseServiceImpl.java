package com.phone.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.phone.mapper.PurchaseMapper;
import com.phone.meta.Purchase;
import com.phone.service.PurchaseService;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime 2013-4-15 03:17:06 Class Description
 */
@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService {
	@Resource
	private PurchaseMapper purchaseMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.PurchaseService#addPurchase(java.lang.String,
	 * java.lang.String, double, double)
	 */
	public boolean addPurchase(String phoneCode, String phoneModel, double purchasePrice, double DecideSellPrice) {
		Purchase purchase = new Purchase();
		purchase.setPhoneCode(phoneCode);
		purchase.setPhoneModel(phoneModel);
		purchase.setPurchasePrice(purchasePrice);
		purchase.setDecideSellPrice(DecideSellPrice);
		purchase.setCreateTime(new Date().getTime());
		purchase.setStatus(Purchase.PurchaseStatus.NotSold.getValue());
		if (purchaseMapper.addPurchase(purchase) > 0) {
			return true;
		}
		return false;
	}
}
