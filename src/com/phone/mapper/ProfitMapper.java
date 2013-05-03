package com.phone.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

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

	/**
	 * 通过phoneid查找Profit
	 * 
	 * @auther zyslovely@gmail.com
	 * @param phoneid
	 * @return
	 */
	public Profit getProfit(Map<String, Object> hashMap);

	/**
	 * 获得利润列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param phoneidList
	 * @return
	 */
	public List<Profit> getProfitListByIds(@Param("phoneidList") List<Long> phoneidList, @Param("shopId") long shopId);

	/**
	 * 通过时间查找Profit列表
	 * 
	 * @param hashMap
	 * @return
	 */
	public List<Profit> getProfitList(Map<String, Object> hashMap);

	/**
	 * 得到利润数量
	 * 
	 * @auther zyslovely@gmail.com
	 * @param shopId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int getProfitCountByTime(@Param("shopId") long shopId, @Param("startTime") long startTime, @Param("endTime") long endTime);

	/**
	 * 删除
	 * 
	 * @auther zyslovely@gmail.com
	 * @param phoneId
	 * @return
	 */
	public int deleteProfit(@Param("phoneId") long phoneId);

	/**
	 * 更新利润
	 * 
	 * @auther zyslovely@gmail.com
	 * @param profit
	 * @return
	 */
	public int updateProfit(Profit profit);
}
