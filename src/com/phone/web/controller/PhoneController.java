package com.phone.web.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
		String brand = ServletRequestUtils.getStringParameter(request, "brand", "");
		String phoneModel = ServletRequestUtils.getStringParameter(request, "phoneModel", "");
		double purchasePrice = ServletRequestUtils.getDoubleParameter(request, "purchasePrice", 0.00);
		double DecideSellPirce = ServletRequestUtils.getDoubleParameter(request, "DecideSellPrice", 0.00);
		ModelAndView mv = new ModelAndView("phoneadd");
		if (purchaseService.addPurchase(brand, phoneCode, phoneModel, purchasePrice, DecideSellPirce)) {
			try {
				response.sendRedirect("phone.do?action=showAddPurchase&phoneModel=" + phoneModel);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return mv;
		}
		return mv;
	}

	/**
	 * 添加手机入库
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showAddPurchase(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("phoneadd");
		String phoneModel = ServletRequestUtils.getStringParameter(request, "phoneModel", "");
		if (!StringUtils.isEmpty(phoneModel)) {
			List<Phone> phoneList = phoneService.getPhoneList(phoneModel);
			mv.addObject("phoneModel", phoneModel);
			mv.addObject("phoneModelCount", phoneList.size());
			mv.addObject("phoneList", phoneList);
		}
		return mv;
	}

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
		List<Phone> phoneList = phoneService.getPhoneList(phoneModel);
		mv.addObject("phoneList", phoneList);
		return mv;
	}
}