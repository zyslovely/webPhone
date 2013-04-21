package com.phone.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.phone.mapper.BrandMapper;
import com.phone.mapper.PurchaseMapper;
import com.phone.meta.Brand;
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
	@Resource
	private BrandMapper brandMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.PurchaseService#addPurchase(java.lang.String,
	 * java.lang.String, double, double)
	 */
	@Override
	public boolean addPurchase(String brand, String phoneCode,
			String phoneModel, double purchasePrice, double DecideSellPrice) {
		Brand brand2 = new Brand();
		brand2.setBrand(brand);
		brandMapper.addBrand(brand2);

		Purchase purchase = new Purchase();
		purchase.setPhoneCode(phoneCode);

		if (purchaseMapper.getPurchaseByPhoneCode(phoneCode) != null) {
			return false;
		}

		purchase.setBrandId(brand2.getId());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.PurchaseService#getPurchase(long)
	 */
	public Purchase getPurchase(long phoneid) {
		return purchaseMapper.getPurchase(phoneid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.PurchaseService#deletePurchase(long, int)
	 */
	public boolean deletePurchase(long phoneid) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("phoneid", phoneid);
		hashMap.put("Status", Purchase.PurchaseStatus.Deleted.getValue());
		if (purchaseMapper.updatePurchase(hashMap) > 0) {
			return true;
		}
		return false;
	}
}
