package com.phone.bean.dwr;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.phone.meta.Purchase;
import com.phone.meta.Selled;
import com.phone.service.PurchaseService;
import com.phone.service.SelledService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-14 下午11:49:52
 * @see Class Description
 */
@Service("dwrWebPhoneBean")
public class DwrWebPhoneBean {

	@Resource
	private PurchaseService purchaseService;

	@Resource
	private SelledService selledService;

	/**
	 * 删除手机
	 * 
	 * @auther zyslovely@gmail.com
	 * @param phoneId
	 * @return
	 */
	public boolean deletePhoneById(long id) {
		Purchase purchase = purchaseService.getPurchase(id);
		if (purchase != null) {
			Selled selled = selledService.getSelled(id);
			if (selled != null) {
				return false;
			}
			return purchaseService.deletePurchase(id);
		}
		return false;
	}

}
