package com.phone.meta;

import java.io.Serializable;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-27 下午07:22:27
 * @see Class Description
 */
public class AccessoryInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	private String accessoryInfoName;

	private long typeId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccessoryInfoName() {
		return accessoryInfoName;
	}

	public void setAccessoryInfoName(String accessoryInfoName) {
		this.accessoryInfoName = accessoryInfoName;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

}
