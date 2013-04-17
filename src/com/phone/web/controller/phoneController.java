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
@Controller("phoneController")
public class PhoneController extends AbstractBaseController {

	@Resource
	private PhoneService phoneService;
	@Resource
	private PurchaseService purchaseService;

	/**
	 * 添加手机入库
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView addPurchase(HttpServletRequest request, HttpServletResponse response) {
		String phoneCode = ServletRequestUtils.getStringParameter(request, "phoneCode", "");
		String phoneModel = ServletRequestUtils.getStringParameter(request, "phoneModel", "");
		double purchasePrice = ServletRequestUtils.getDoubleParameter(request, "purchasePrice", 0.00);
		double DecideSellPirce = ServletRequestUtils.getDoubleParameter(request, "DecideSellPrice", 0.00);
		ModelAndView mv = new ModelAndView("phoneList");
		if (purchaseService.addPurchase(phoneCode, phoneModel, purchasePrice, DecideSellPirce)) {
			List<Phone> phoneList = phoneService.getPhoneList(phoneModel);
			mv.addObject("phoneList", phoneList.toString());
			return mv;
		}
		return mv;
	}

}