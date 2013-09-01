package com.phone.bean.dwr;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.springframework.stereotype.Service;

import com.phone.meta.Purchase;
import com.phone.meta.Profile.ProfileLevel;
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
	private static final Logger logger = Logger
			.getLogger(DwrWebPhoneBean.class);

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
		if (purchase == null
				|| purchase.getStatus() == PurchaseStatus.Sold.getValue()) {
			return false;
		}
<<<<<<< HEAD
=======
		logger.info("删除手机操作，id=" + id + " 操作人id=" + userId);
>>>>>>> master1
		return purchaseService.deletePurchase(id, myUser.getUserId(),
				myUser.getShopId());
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
<<<<<<< HEAD
=======
		logger.info("卖出手机操作，手机id=" + phoneId + " 价格=" + selledPrice + " 操作人id="
				+ myUser.getUserId());
>>>>>>> master1
		return selledService.addSelled(phoneId, selledPrice,
				myUser.getUserId(), myUser.getShopId());
	}

	/**
	 * 添加配件信息
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @return
	 */
	public boolean addAccessoryInfo(String name) {
		return accessoryService.addAccessoryInfo(StringUtil.ToDBC(name.trim()
				.toLowerCase()));
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
<<<<<<< HEAD
=======
		logger.info("卖出配件操作，配件id=" + id + " 价格=" + soldPrice + " 操作人id="
				+ myUser.getUserId());
>>>>>>> master1
		return accessoryService.descCountAccessoryById(id, 1, soldPrice,
				myUser.getShopId(), myUser.getUserId());
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
<<<<<<< HEAD
=======
		logger.info("删除配件操作，配件id=" + id + " 操作人id+" + userId);
>>>>>>> master1
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
	public boolean changePhoneWithShop(long phoneId, long newShopId) {
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
<<<<<<< HEAD
		return phoneService.changeShop(phoneCode.toLowerCase(),
				myUser.getShopId(), newShopId);
=======
		if (myUser.getLevel() != ProfileLevel.SuperManager.getValue()) {
			return false;
		}
		logger.info("转移手机操作，手机id=" + phoneId + " 操作人id+" + userId
				+ "  转移到新店id=" + newShopId);
		return phoneService.changeShop(phoneId, newShopId, myUser.getShopId(),
				userId);
>>>>>>> master1
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
	public boolean changeAccessoryWithShop(long id, long newShopId,
			int changeCount) {
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		return accessoryService.changeAccessoryWithShop(id, myUser.getShopId(),
				newShopId, changeCount);
	}

	/**
	 * 添加品牌
	 * 
	 * @auther zyslovely@gmail.com
	 * @param brand
	 * @return
	 */
	public boolean addBrand(String brand) {
		return purchaseService.addNewBrand(StringUtil.ToDBC(brand.trim()
				.toLowerCase()));
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
		logger.info("手机退货操作，手机id=" + phoneId + " 操作人id=" + userId);
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
<<<<<<< HEAD
=======
		logger.info("购买价格改变，手机id=" + phoneId + " 操作人id=" + userId);
>>>>>>> master1
		return phoneService.purchasePriceChange(phoneId, price,
				myUser.getShopId());
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
		logger.info("卖出价格改变，手机id=" + phoneId + " 操作人id=" + userId);
		return phoneService.sellPriceChange(phoneId, price, myUser.getShopId());
	}

	/**
<<<<<<< HEAD
	 * 改变配件购入价格
	 * 
	 * @param phoneId
	 * @param price
	 * @return
	 */
	public boolean accessoryPurchasePriceChange(long accessoryId, double price) {
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		return accessoryService.purchasePriceChange(accessoryId, price,
				myUser.getShopId());
=======
	 * 添加盘点
	 * 
	 * @auther zyslovely@gmail.com
	 * @param phoneId
	 * @return
	 */
	public boolean inventoryPhone(long phoneId) {
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		return purchaseService.addInventoryPhone(phoneId, myUser.getShopId());
	}

	/**
	 * 重置盘点
	 * 
	 * @auther zyslovely@gmail.com
	 * @return
	 */
	public boolean resetAllInventory() {
		WebContext ctx = WebContextFactory.get();
		Long userId = MyUser.getMyUser(ctx.getHttpServletRequest());
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		return purchaseService.resetAllInventory(myUser.getShopId());
>>>>>>> master1
	}
}
