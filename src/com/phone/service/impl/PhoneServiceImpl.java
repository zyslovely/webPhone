package com.phone.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.phone.mapper.PurchaseMapper;
import com.phone.mapper.SelledMapper;
import com.phone.meta.Phone;
import com.phone.meta.Purchase;
import com.phone.meta.Selled;
import com.phone.service.PhoneService;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime：2013-4-16 下午03:31:46 Class Description
 */
public class PhoneServiceImpl implements PhoneService {

	@Resource
	private PurchaseMapper purchaseMapper;

	@Resource
	private SelledMapper selledMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.PhoneService#getPhoneList(java.lang.String)
	 */
	@Override
	public List<Phone> getPhoneList(String phoneModel) {
		List<Purchase> purchaseliList = purchaseMapper
				.getPuchaseList(phoneModel);
		if (purchaseliList != null) {
			List<Long> phoneidList = new ArrayList<Long>(purchaseliList.size());
			for (Purchase purchase : purchaseliList) {
				phoneidList.add(purchase.getId());
			}
			List<Selled> selledList = selledMapper
					.getSelledListByIds(phoneidList);
			List<Phone> phoneList = new ArrayList<Phone>(purchaseliList.size());
			for (Purchase purchase : purchaseliList) {
				for (Selled selled : selledList) {
					if (purchase.getId() == selled.getPhoneid()) {
						Phone phone = new Phone();
						phone.setPhoneCode(purchase.getPhoneCode());
						phone.setPhoneModel(purchase.getPhoneModel());
						phone.setPurchasePrice(purchase.getPurchasePrice());
						phone.setDecideSellPrice(purchase.getDecideSellPrice());
						phone.setPurchaseTime(purchase.getCreateTime());
						phone.setStatus(purchase.getStatus());
						phone.setSelledPrice(selled.getSelledPrice());
						phone.setSelledTime(selled.getCreateTime());
						phone.setProfile(selled.getSelledPrice()
								- purchase.getPurchasePrice());
						phoneList.add(phone);
					}
					Phone phone = new Phone();
					phone.setPhoneCode(purchase.getPhoneCode());
					phone.setPhoneModel(purchase.getPhoneModel());
					phone.setPurchasePrice(purchase.getPurchasePrice());
					phone.setDecideSellPrice(purchase.getDecideSellPrice());
					phone.setPurchaseTime(purchase.getCreateTime());
					phone.setStatus(purchase.getStatus());
					phone.setSelledPrice(0.00);
					phone.setSelledTime(0);
					phone.setProfile(purchase.getDecideSellPrice()
							- purchase.getPurchasePrice());
					phoneList.add(phone);
				}
			}
			return phoneList;
		}
		return null;
	}
}