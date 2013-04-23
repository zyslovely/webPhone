package com.phone.bean.dwr;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.phone.meta.Purchase;
import com.phone.meta.Purchase.PurchaseStatus;
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
		if (purchase == null || purchase.getStatus() == PurchaseStatus.Sold.getValue()) {
			return false;
		}
		return purchaseService.deletePurchase(id);
	}

	/**
	 * 添加手机销售
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean sellPhone(long phoneId, double selledPrice) {
		int operatorId = 0;
		return selledService.addSelled(phoneId, selledPrice, operatorId);
	}

}
