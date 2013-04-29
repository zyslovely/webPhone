package com.phone.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.phone.meta.Selled;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime：2013-4-16 上午12:35:20 Class Description
 */
public interface SelledMapper {
	/**
	 * 添加Selled
	 * 
	 * @param selled
	 * @return
	 */
	public int addSelled(Selled selled);

	/**
	 * 通过phoneidList查找selledList信息
	 * 
	 * @param phoneidList
	 * @return
	 */
	public List<Selled> getSelledListByIds(
			@Param("phoneidList") List<Long> phoneidList,@Param("shopId") long shopId);

	/**
	 * 查找Selled
	 * 
	 * @param phoneid
	 * @return
	 */
	public Selled getSelled(Map<String, Object> hashMap);

}
