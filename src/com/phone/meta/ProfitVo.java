package com.phone.meta;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-29 下午10:37:21
 * @see Class Description
 */
public class ProfitVo {

	private String phoneCode;
	private String phoneModel;
	private double profit;
	private double purchasePrice;
	private double SelledPrice;
	private String selledTimeStr;
	private String purchaseTimeStr;

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getSelledPrice() {
		return SelledPrice;
	}

	public void setSelledPrice(double selledPrice) {
		SelledPrice = selledPrice;
	}

	public String getSelledTimeStr() {
		return selledTimeStr;
	}

	public void setSelledTimeStr(String selledTimeStr) {
		this.selledTimeStr = selledTimeStr;
	}

	public String getPurchaseTimeStr() {
		return purchaseTimeStr;
	}

	public void setPurchaseTimeStr(String purchaseTimeStr) {
		this.purchaseTimeStr = purchaseTimeStr;
	}

}
