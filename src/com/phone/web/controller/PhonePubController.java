package com.phone.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.phone.meta.Phone;
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

	/**
	 * 手机查询接口
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView queryPhone(HttpServletRequest request, HttpServletResponse response) {
		String phoneModel = ServletRequestUtils.getStringParameter(request, "phoneModel", "");
		ModelAndView mv = new ModelAndView("phoneList");
		//purchaseService.addPurchase("123", "iphone4s", 3333, 4444);
		List<Phone> phoneList = phoneService.getPhoneList(phoneModel);
		mv.addObject("phoneList", phoneList);
		return mv;
	}
}
