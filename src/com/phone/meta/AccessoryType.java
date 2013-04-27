package com.phone.meta;

import java.io.Serializable;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-27 下午07:22:35
 * @see Class Description
 */
public class AccessoryType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4353490906018274351L;
	private long id;
	private String accessoryTypeName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccessoryTypeName() {
		return accessoryTypeName;
	}

	public void setAccessoryTypeName(String accessoryTypeName) {
		this.accessoryTypeName = accessoryTypeName;
	}

}
