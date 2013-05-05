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
import com.phone.service.PhoneService;
import com.phone.service.PurchaseService;
import com.phone.service.SelledService;
import com.phone.util.StringUtil;

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

	@Resource
	private PhoneService phoneService;

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
		Purchase purchase = purchaseService.getPurchase(id, myUser.getShopId());
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
		return accessoryService.addAccessoryInfo(StringUtil.ToDBC(name.trim().toLowerCase()));
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
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		return accessoryService.deleteAccessory(id, myUser.getShopId());
	}

	/**
	 * 手机换仓
	 * 
	 * @param phoneCode
	 * @param shopId
	 * @param newShopId
	 * @return
	 */
	public boolean changePhoneWithShop(String phoneCode, long newShopId) {
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		return phoneService.changeShop(phoneCode.toLowerCase(), myUser.getShopId(), newShopId);
	}

	/**
	 * 配件换仓
	 * 
	 * @param id
	 * @param shopId
	 * @param newShopId
	 * @param count
	 * @return
	 */
	public boolean changeAccessoryWithShop(long id, long newShopId, int changeCount) {
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		return accessoryService.changeAccessoryWithShop(id, myUser.getShopId(), newShopId, changeCount);
	}

	/**
	 * 添加品牌
	 * 
	 * @auther zyslovely@gmail.com
	 * @param brand
	 * @return
	 */
	public boolean addBrand(String brand) {
		return purchaseService.addNewBrand(StringUtil.ToDBC(brand.trim().toLowerCase()));
	}

	/**
	 * 退货
	 * 
	 * @auther zyslovely@gmail.com
	 * @param phoneId
	 * @return
	 */
	public boolean returnPhone(long phoneId) {
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		return phoneService.returnPhone(phoneId, myUser.getShopId());
	}

	/**
	 * 购买价格改变
	 * 
	 * @auther zyslovely@gmail.com
	 * @param phoneId
	 * @param price
	 * @return
	 */
	public boolean purchasePriceChange(long phoneId, double price) {
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		return phoneService.purchasePriceChange(phoneId, price, myUser.getShopId());
	}

	/**
	 * 卖出价格改变
	 * 
	 * @auther zyslovely@gmail.com
	 * @param phoneId
	 * @param price
	 * @return
	 */
	public boolean sellPriceChange(long phoneId, double price) {
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		return phoneService.sellPriceChange(phoneId, price, myUser.getShopId());
	}
}
