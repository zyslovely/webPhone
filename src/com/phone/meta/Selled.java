package com.phone.meta;

import java.io.Serializable;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime：2013-4-16 上午12:21:33 Class Description
 */
public class Selled implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2L;

	/**
	 * id
	 */
	private long phoneid;

	/**
	 * 卖出价格
	 */
	private double SelledPrice;

	/**
	 * 记录创建时间
	 */
	private long CreateTime;

	/**
	 * 操作人id
	 */
	private long operatorId;

	/**
	 * 店铺id
	 */
	private long shopId;

	public long getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(long phoneid) {
		this.phoneid = phoneid;
	}

	public double getSelledPrice() {
		return SelledPrice;
	}

	public void setSelledPrice(double selledPrice) {
		SelledPrice = selledPrice;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(long operatorId) {
		this.operatorId = operatorId;
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

}
