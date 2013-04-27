package com.phone.service;

import java.util.List;

import com.phone.meta.Profit;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime：2013-4-23 上午02:43:36 Class Description
 */
public interface ProfitService {
	/**
	 * 通过时间段获取Profit列表
	 * 
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<Profit> getProfitList(long startTime, long endTime, long shopId);
}
