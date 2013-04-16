package com.phone.service;

import java.util.List;

import com.phone.meta.Phone;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime：2013-4-16 下午03:21:23 Class Description
 */
public interface PhoneService {

	public List<Phone> getPhoneList(String phoneModel);
}
