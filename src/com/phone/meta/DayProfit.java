package com.phone.meta;

import java.io.Serializable;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-1 下午11:42:42
 * @see Class Description
 */
public class DayProfit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1861495999933744367L;

	private long id;
	private String daytime;
	private double totalSell;
	private double totalProfit;
	private long shopId;
	/**
	 * 类型
	 */
	private int type;

	public static int PHONE = 0;

	public static int ACCESSORY = 1;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDaytime() {
		return daytime;
	}

	public void setDaytime(String daytime) {
		this.daytime = daytime;
	}

	public double getTotalSell() {
		return totalSell;
	}

	public void setTotalSell(double totalSell) {
		this.totalSell = totalSell;
	}

	public double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(double totalProfit) {
		this.totalProfit = totalProfit;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

}
