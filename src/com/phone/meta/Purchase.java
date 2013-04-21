package com.phone.meta;

import java.io.Serializable;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime锛�013-4-14 涓嬪崍11:48:54
 * @see Class Description
 */
public class Purchase implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private long id;
	/**
	 * 品牌id
	 */
	private long brandId;

	/**
	 * 手机编码（唯一）
	 */
	private String phoneCode;

	/**
	 * 手机型号
	 */
	private String phoneModel;

	/**
	 * 进货价格
	 */
	private double purchasePrice;

	/**
	 * 计划卖出价格
	 */
	private double DecideSellPrice;

	/**
	 * 记录创建时间
	 */
	private long CreateTime;

	/**
	 * 当前状态
	 */
	private int Status;

	public enum PurchaseStatus {
		/**
		 * 未卖出
		 */
		NotSold {
			@Override
			public int getValue() {
				return 0;
			}
		},
		/**
		 * 已卖出
		 */
		Sold {
			@Override
			public int getValue() {
				return 1;
			}
		};
		public abstract int getValue();

		public static PurchaseStatus genPurchaseStatus(int t) {
			for (PurchaseStatus status : PurchaseStatus.values()) {
				if (status.getValue() == t)
					return status;
			}
			return null;
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public long getBrandId() {
		return brandId;
	}

	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}

}
