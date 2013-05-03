package com.phone.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.phone.mapper.BrandMapper;
import com.phone.mapper.PurchaseMapper;
import com.phone.meta.Brand;
import com.phone.meta.Purchase;
import com.phone.meta.Purchase.PurchaseStatus;
import com.phone.service.PurchaseService;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime 2013-4-15 03:17:06 Class Description
 */
@Service("purchaseService")
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
	public boolean addPurchase(String brand, String phoneCode, String phoneModel, double purchasePrice, double DecideSellPrice, long operatorId,
			long shopId) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("phoneCode", phoneCode);
		hashMap.put("shopId", shopId);
		if (purchaseMapper.getPurchaseByPhoneCode(hashMap) != null) {
			return false;
		}
		Brand brand2 = new Brand();
		brand2.setBrand(brand);
		brandMapper.addBrand(brand2);

		Purchase purchase = new Purchase();
		purchase.setPhoneCode(phoneCode);

		purchase.setBrandId(brand2.getId());
		purchase.setPhoneModel(phoneModel);
		purchase.setPurchasePrice(purchasePrice);
		purchase.setDecideSellPrice(DecideSellPrice);
		purchase.setCreateTime(new Date().getTime());
		purchase.setStatus(Purchase.PurchaseStatus.NotSold.getValue());
		purchase.setOperatorId(operatorId);
		purchase.setShopId(shopId);
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
	@Override
	public Purchase getPurchase(long phoneid, long shopId) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("phoneid", phoneid);
		hashMap.put("shopId", shopId);
		return purchaseMapper.getPurchase(hashMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.PurchaseService#deletePurchase(long, int)
	 */
	@Override
	public boolean deletePurchase(long phoneid, long operatorId, long shopId) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("phoneid", phoneid);
		hashMap.put("Status", PurchaseStatus.Deleted.getValue());
		if (purchaseMapper.updatePurchase(hashMap) > 0) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.PurchaseService#getPhoneCountByPhoneModel(long,
	 * java.lang.String)
	 */
	@Override
	public int getPurchaseCountByPhoneModel(long shopId, String phoneModel, int status) {
		return purchaseMapper.getPurchaseCountByPhoneModel(phoneModel, shopId, status);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.PurchaseService#getPurchaseByPhoneCode(long,
	 * java.lang.String)
	 */
	public Purchase getPurchaseByPhoneCode(long shopId, String phoneCode) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("shopId", shopId);
		hashMap.put("phoneCode", phoneCode);
		return purchaseMapper.getPurchase(hashMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.PurchaseService#getBrandList()
	 */
	@Override
	public List<String> getBrandList() {
		return brandMapper.getAllBrandList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.PurchaseService#addNewBrand(java.lang.String)
	 */
	@Override
	public boolean addNewBrand(String brandName) {
		Brand brand = new Brand();
		brand.setBrand(brandName);
		return brandMapper.addBrand(brand) > 0;
	}
}
