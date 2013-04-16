package com.phone.mapper;

import java.util.List;

import com.phone.meta.Profit;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime：2013-4-16 上午12:36:41 Class Description
 */
public interface ProfitMapper {
	/**
	 * 添加Profit
	 * 
	 * @param profit
	 * @return
	 */
	public int addProfit(Profit profit);

	public Profit getProfit(long phoneid);
}
