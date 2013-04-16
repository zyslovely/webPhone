package com.phone.web.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.phone.meta.Phone;
import com.phone.service.PhoneService;
import com.phone.service.PurchaseService;

@Controller("phoneController")
public class phoneController extends MultiActionController {
	@Resource(name = "paramResolver")
	private MethodNameResolver methodNameResolver;

	@Resource
	private PurchaseService purchaseService;

	@Resource
	private PhoneService phoneService;

	/**
	 * 
	 */
	@PostConstruct
	public void baseInit() {
		super.setMethodNameResolver(methodNameResolver);
	}

	/**
	 * 添加手机入库
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView addPurchase(HttpServletRequest request,
			HttpServletResponse response) {
		String phoneCode = ServletRequestUtils.getStringParameter(request,
				"phoneCode", "");
		String phoneModel = ServletRequestUtils.getStringParameter(request,
				"phoneModel", "");
		double purchasePrice = ServletRequestUtils.getDoubleParameter(request,
				"purchasePrice", 0.00);
		double DecideSellPirce = ServletRequestUtils.getDoubleParameter(
				request, "DecideSellPrice", 0.00);
		ModelAndView mv = new ModelAndView("return");
		if (purchaseService.addPurchase(phoneCode, phoneModel, purchasePrice,
				DecideSellPirce)) {
			List<Phone> phoneList = phoneService.getPhoneList(phoneModel);
			mv.addObject("phoneList", phoneList.toString());
			return mv;
		}
		return mv;
	}

	public ModelAndView queryPhone(HttpServletRequest request,
			HttpServletResponse response) {
		String phoneModel = ServletRequestUtils.getStringParameter(request,
				"phoneModel", "");
		ModelAndView mv = new ModelAndView("return");
		List<Phone> phoneList = phoneService.getPhoneList(phoneModel);
		if (phoneList != null) {
			mv.addObject("phoneList", phoneList.toString());
			return mv;
		}
		return mv;
	}
}