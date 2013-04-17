package com.phone.mapper;

import java.util.List;

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
	 * 通过phoneidList查找phoneList信息
	 * 
	 * @param phoneidList
	 * @return
	 */
	public List<Selled> getSelledListByIds(@Param("phoneidList") List<Long> phoneidList);

}
