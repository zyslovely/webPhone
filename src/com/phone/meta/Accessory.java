package com.phone.meta;

import java.io.Serializable;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-27 下午07:22:18
 * @see Class Description
 */
public class Accessory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private int count;
	private double unitPrice;
	private long createTime;
	private long lastUpdateTime;
	private long accessoryTypeId;
	private long accessoryInfoId;
	private long operatorId;
	private long shopId;
	/**
	 * 不存数据库
	 */
	private String accessoryInfoName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public long getAccessoryTypeId() {
		return accessoryTypeId;
	}

	public void setAccessoryTypeId(long accessoryTypeId) {
		this.accessoryTypeId = accessoryTypeId;
	}

	public long getAccessoryInfoId() {
		return accessoryInfoId;
	}

	public void setAccessoryInfoId(long accessoryInfoId) {
		this.accessoryInfoId = accessoryInfoId;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getAccessoryInfoName() {
		return accessoryInfoName;
	}

	public void setAccessoryInfoName(String accessoryInfoName) {
		this.accessoryInfoName = accessoryInfoName;
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
