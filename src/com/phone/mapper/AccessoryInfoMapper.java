package com.phone.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.phone.meta.AccessoryInfo;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-27 下午07:27:16
 * @see Class Description
 */
public interface AccessoryInfoMapper {
	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param accessoryInfo
	 * @return
	 */
	public int addAccessoryInfo(AccessoryInfo accessoryInfo);

	/**
	 * 获取配件类型信息列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param name
	 * @return
	 */
	public List<AccessoryInfo> getAccessoryInfo(
			@Param(value = "name") String name);

	/**
	 * 获取所有配件类型
	 * 
	 * @auther zyslovely@gmail.com
	 * @return
	 */
	public List<AccessoryInfo> getAllAccessoryInfo();

	/**
	 * 通过id取配件类型
	 * 
	 * @auther zyslovely@gmail.com
	 * @param id
	 * @return
	 */
	public AccessoryInfo getAccessoryInfoById(@Param(value = "id") long id);

	/**
	 * 
	 * 
	 * @param accessoryIdList
	 * @return
	 */
	public List<AccessoryInfo> getAccessoryInfoByIds(
			@Param(value = "accessoryInfoIdList") List<Long> accessoryInfoIdList);
}
