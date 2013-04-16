package com.phone.meta;

import java.io.Serializable;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime：2013-4-16 上午12:28:11 Class Description
 */
public class Profit implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3L;

	/**
	 * id
	 */
	private long phoneid;

	/**
	 * 购入价格
	 */
	private double purchasePrice;

	/**
	 * 计划卖出价格
	 */
	private double DecideSellPrice;

	/**
	 * 实际卖出价格
	 */
	private double SelledPrice;

	/**
	 * 利润
	 */
	private double profile;

	/**
	 * 记录创建时间
	 */
	private long CreateTime;

	public long getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(long phoneid) {
		this.phoneid = phoneid;
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

	public double getSelledPrice() {
		return SelledPrice;
	}

	public void setSelledPrice(double selledPrice) {
		SelledPrice = selledPrice;
	}

	public double getProfile() {
		return profile;
	}

	public void setProfile(double profile) {
		this.profile = profile;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
}