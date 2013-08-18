package com.phone.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.phone.meta.DayProfit;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-5-1 下午11:44:29
 * @see Class Description
 */
public interface DayProfitMapper {

	/**
	 * 添加日利润
	 * 
	 * @auther zyslovely@gmail.com
	 * @param dayProfit
	 * @return
	 */
	public int addDayProfit(DayProfit dayProfit);

	/**
	 * 更新日利润
	 * 
	 * @auther zyslovely@gmail.com
	 * @param totalSell
	 * @param totalProfit
	 * @param dayTime
	 * @return
	 */
	public int updateDayProfit(@Param(value = "totalSell") double totalSell,
			@Param(value = "totalProfit") double totalProfit,
			@Param(value = "daytime") String dayTime,
			@Param(value = "type") int type,
			@Param(value = "shopId") long shopId);

	/**
	 * 获得日利润
	 * 
	 * @auther zyslovely@gmail.com
	 * @param dayTime
	 * @return
	 */
	public DayProfit getDayProfit(@Param(value = "daytime") String dayTime,
			@Param(value = "type") int type,
			@Param(value = "shopId") long shopId);

	/**
	 * 根据时间获得利润
	 * 
	 * @auther zyslovely@gmail.com
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<DayProfit> getDayProfitsByDayTime(
			@Param(value = "beginTime") String beginTime,
			@Param(value = "endTime") String endTime,
			@Param(value = "type") int type,
			@Param(value = "shopId") long shopId);

}
