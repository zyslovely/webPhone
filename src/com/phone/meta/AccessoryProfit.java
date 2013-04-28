package com.phone.meta;

import java.io.Serializable;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-27 下午08:53:08
 * @see Class Description
 */
public class AccessoryProfit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private long accessoryid;
	private double purchasePrice;
	private double soldPrice;
	private double profit;
	private long createTime;
	private long operatorId;
	private long shopId;

	public long getAccessoryid() {
		return accessoryid;
	}

	public void setAccessoryid(long accessoryid) {
		this.accessoryid = accessoryid;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getSoldPrice() {
		return soldPrice;
	}

	public void setSoldPrice(double soldPrice) {
		this.soldPrice = soldPrice;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(long operatorId) {
		this.operatorId = operatorId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

}
