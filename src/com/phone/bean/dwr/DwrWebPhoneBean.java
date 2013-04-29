package com.phone.bean.dwr;

import javax.annotation.Resource;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Service;

import com.phone.meta.Purchase;
import com.phone.meta.Purchase.PurchaseStatus;
import com.phone.security.MySecurityDelegatingFilter;
import com.phone.security.MyUser;
import com.phone.service.AccessoryService;
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

	@Resource
	private AccessoryService accessoryService;

	/**
	 * 删除手机
	 * 
	 * @auther zyslovely@gmail.com
	 * @param phoneId
	 * @return
	 */
	public boolean deletePhoneById(long id) {
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		Purchase purchase = purchaseService.getPurchase(id, myUser.getUserId(), myUser.getShopId());
		if (purchase == null || purchase.getStatus() == PurchaseStatus.Sold.getValue()) {
			return false;
		}
		return purchaseService.deletePurchase(id, myUser.getUserId(), myUser.getShopId());
	}

	/**
	 * 添加手机销售
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean sellPhone(long phoneId, double selledPrice) {
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		return selledService.addSelled(phoneId, selledPrice, myUser.getUserId(), myUser.getShopId());
	}

	/**
	 * 添加配件信息
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @return
	 */
	public boolean addAccessoryInfo(String name) {
		return accessoryService.addAccessoryInfo(name.trim());
	}

	/**
	 * 卖出配件
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public boolean sellAccessory(long id, double soldPrice) {
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		return accessoryService.descCountAccessoryById(id, 1, soldPrice, myUser.getShopId(), myUser.getUserId());
	}

	/**
	 * 完全删除配件
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public boolean deleteAccessory(long id) {
		return false;
	}
}
