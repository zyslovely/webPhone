package com.phone.web.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import com.phone.service.PhoneService;
import com.phone.service.PurchaseService;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-17 上午06:34:05
 * @see Class Description
 */
@Controller("phonePubController")
public class PhonePubController extends AbstractBaseController {
	@Resource
	private PhoneService phoneService;
	@Resource
	private PurchaseService purchaseService;


}
