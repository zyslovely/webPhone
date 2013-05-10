package com.phone.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.phone.meta.AccessorySold;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-27 下午08:57:07
 * @see Class Description
 */
public interface AccessorySoldMapper {
	/**
	 * 添加配件卖出
	 * 
	 * @auther zyslovely@gmail.com
	 * @param accessorySold
	 * @return
	 */
	public int addAccessorySold(AccessorySold accessorySold);

	/**
	 * 查询配件卖出
	 * 
	 * @param accessoryid
	 * @param shopId
	 * @return
	 */
	public AccessorySold getAccessorySold(
			@Param(value = "accessoryid") long accessoryid,
			@Param(value = "shopId") long shopId);

	/**
	 * 
	 * 
	 * @param accessoryIdList
	 * @return
	 */
	public List<AccessorySold> getAccessorySoldByIds(
			@Param(value = "accessoryIdList") List<Long> accessoryIdList);
}
