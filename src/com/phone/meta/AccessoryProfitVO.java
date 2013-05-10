package com.phone.meta;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime：2013-5-11 上午02:26:21 Class Description
 */
public class AccessoryProfitVO {
	private String name;
	private String accessoryInfoName;
	private double unitPrice;
	private double soldPrice;
	private double profit;
	private String lastUpdateTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccessoryInfoName() {
		return accessoryInfoName;
	}

	public void setAccessoryInfoName(String accessoryInfoName) {
		this.accessoryInfoName = accessoryInfoName;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
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

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}
