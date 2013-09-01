package com.phone.meta;

import java.io.Serializable;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime：2013-4-27 下午08:37:47 Class Description
 */
public class Shop implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7L;
	/**
	 * 店铺id
	 */
	private long id;
	/**
	 * 店铺名
	 */
	private String nameString;
	/**
	 * 创建时间
	 */
	private long CreateTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public static String getShopName(long id) {
		if (id == 1) {
			return "宜美手机店";
		} else if (id == 2) {
			return "天宝手机店";
		} else {
			return "正泰手机店";
		}
	}
}
