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

	/**
	 * 操作员ID
	 */
	private long operatorId;

	/**
	 * 店铺id
	 */
	private long shopId;
	/**
	 * 是否盘点
	 */
	private int Inventory;

	public static final int DONE = 1;

	public static final int UNDO = 1;

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
		},
		/**
		 * 删除
		 */
		Deleted {
			@Override
			public int getValue() {
				return 2;
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

	public int getInventory() {
		return Inventory;
	}

	public void setInventory(int inventory) {
		Inventory = inventory;
	}

}
