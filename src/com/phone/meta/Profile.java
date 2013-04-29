package com.phone.meta;

import java.io.Serializable;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime：2013-4-24 上午01:41:37 Class Description
 */
public class Profile implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6L;
	/**
	 * 账户ID
	 */
	private long UserId;
	/**
	 * 账户名
	 */
	private String UserName;
	/**
	 * 名字
	 */
	private String name;
	/**
	 * 账户密码
	 */
	private String Password;
	/**
	 * 创建时间
	 */
	private long CreateTime;
	/**
	 * 账户等级，0为店员，1为店长
	 */
	private int level;
	/**
	 * 店铺id
	 */
	private long shopId;

	public enum ProfileLevel {
		/**
		 * 店员
		 */
		Assistant {
			@Override
			public int getValue() {
				return 0;
			}
		},
		/**
		 * 店长
		 */
		Manager {
			@Override
			public int getValue() {
				return 1;
			}
		};
		public abstract int getValue();

		public static ProfileLevel genProfileLevle(int t) {
			for (ProfileLevel level : ProfileLevel.values()) {
				if (level.getValue() == t)
					return level;
			}
			return null;
		}
	}

	public long getUserId() {
		return UserId;
	}

	public void setUserId(long userId) {
		UserId = userId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String UserName) {
		this.UserName = UserName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getShopId() {
		return shopId;
	}

	public void setShopId(long shopId) {
		this.shopId = shopId;
	}

	/**
	 * 获得店铺名称
	 * 
	 * @auther zyslovely@gmail.com
	 * @param shopId
	 * @return
	 */
	public static String getShopName(long shopId) {
		if (shopId == 1) {
			return "店铺B1";
		} else if (shopId == 2) {
			return "店铺A2";
		}
		return "";
	}
}
