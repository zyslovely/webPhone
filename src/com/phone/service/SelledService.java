package com.phone.service;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime：2013-4-20 上午01:51:37 Class Description
 */
public interface SelledService {

	/**
	 * 添加Selled
	 * 
	 * @param phoneid
	 * @param selledPrice
	 * @param operatorId
	 * @return
	 */
	public boolean addSelled(long phoneid, double selledPrice, int operatorId);
}
