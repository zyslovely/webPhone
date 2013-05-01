package com.phone.service;

import java.util.List;

import com.phone.meta.DayProfit;
import com.phone.meta.ProfitVo;

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
	public List<ProfitVo> getProfitList(long startTime, long endTime, long shopId, int limit, int offset);

	/**
	 * 得到利润数量
	 * 
	 * @auther zyslovely@gmail.com
	 * @param startTime
	 * @param endTime
	 * @param shopId
	 * @return
	 */
	public int getProfitCount(long startTime, long endTime, long shopId);

	/**
	 * 按时间获得利润
	 * 
	 * @auther zyslovely@gmail.com
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<DayProfit> getDayProfitListByTime(String startTime, String endTime, long shopId);
}
