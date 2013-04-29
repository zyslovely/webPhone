package com.phone.mapper;

import java.util.List;
import java.util.Map;

import com.phone.meta.AccessoryProfit;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-27 下午09:00:38
 * @see Class Description
 */
public interface AccessoryProfitMapper {
	/**
	 * 添加配件利润
	 * 
	 * @auther zyslovely@gmail.com
	 * @param accessoryProfit
	 * @return
	 */
	public int addAccessoryProfit(AccessoryProfit accessoryProfit);

	/**
	 * 通过时间查找Profit列表
	 * 
	 * @param hashMap
	 * @return
	 */
	public List<AccessoryProfit> getAccessoryProfitList(Map<String, Object> hashMap);
}
