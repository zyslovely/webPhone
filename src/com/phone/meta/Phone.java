package com.phone.meta;

import java.io.Serializable;

import com.phone.util.TimeUtil;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime：2013-4-16 下午02:53:30 Class Description
 */
public class Phone implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4L;

	/**
	 * 手机型号
	 */
	private String phoneModel;

	/**
	 * 手机编码（唯一）
	 */
	private String phoneCode;

	/**
	 * 进货价格
	 */
	private double purchasePrice;

	/**
	 * 计划卖出价格
	 */
	private double DecideSellPrice;

	/**
	 * 进货日期
	 */
	private long PurchaseTime;

	/**
	 * 当前状态
	 */
	private int Status;

	/**
	 * 实际卖出价格
	 */
	private double SelledPrice;

	/**
	 * 卖出时间
	 */
	private long SelledTime;

	/**
	 * 利润
	 */
	private double profit;
	/**
	 * 进货日期
	 */
	private String purchaseTimeStr;
	/**
	 * 出售日期
	 */
	private String selledTimeStr;
	
	
	

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public String getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getDecideSellPrice() {
		return DecideSellPrice;
	}

	public void setDecideSellPrice(double decideSellPrice) {
		DecideSellPrice = decideSellPrice;
	}

	public long getPurchaseTime() {
		return PurchaseTime;
	}

	public void setPurchaseTime(long purchaseTime) {
		purchaseTimeStr = TimeUtil.getFormatTime(purchaseTime);
		PurchaseTime = purchaseTime;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public double getSelledPrice() {
		return SelledPrice;
	}

	public void setSelledPrice(double selledPrice) {
		SelledPrice = selledPrice;
	}

	public long getSelledTime() {

		return SelledTime;
	}

	public void setSelledTime(long selledTime) {
		selledTimeStr = TimeUtil.getFormatTime(selledTime);
		SelledTime = selledTime;
	}

	public String getPurchaseTimeStr() {
		return purchaseTimeStr;
	}

	public void setPurchaseTimeStr(String purchaseTimeStr) {
		this.purchaseTimeStr = purchaseTimeStr;
	}

	public String getSelledTimeStr() {
		return selledTimeStr;
	}

	public void setSelledTimeStr(String selledTimeStr) {
		this.selledTimeStr = selledTimeStr;
	}

}
