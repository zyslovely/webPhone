package com.phone.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.phone.meta.Phone;
import com.phone.meta.Profit;
import com.phone.service.PhoneService;
import com.phone.service.ProfitService;
import com.phone.service.PurchaseService;
import com.phone.service.SelledService;
import com.phone.util.ListUtils;

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

	@Resource
	private SelledService selledService;
	@Resource
	private ProfitService profitService;

	/**
	 * 添加手机入库操作
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showAddPurchase(HttpServletRequest request,
			HttpServletResponse response) {
		String phoneCode = ServletRequestUtils.getStringParameter(request,
				"phoneCode", "");
		String brand = ServletRequestUtils.getStringParameter(request, "brand",
				"");
		String phoneModel = ServletRequestUtils.getStringParameter(request,
				"phoneModel", "");
		double purchasePrice = ServletRequestUtils.getDoubleParameter(request,
				"purchasePrice", 0.00);
		double DecideSellPirce = ServletRequestUtils.getDoubleParameter(
				request, "DecideSellPrice", 0.00);
		ModelAndView mv = new ModelAndView("phoneadd");
		if (StringUtils.isEmpty(phoneCode)) {
			return mv;
		}
		if (purchaseService.addPurchase(brand, phoneCode, phoneModel,
				purchasePrice, DecideSellPirce)) {
			try {
				response.sendRedirect("/purchase/add/show/?phoneModel="
						+ phoneModel + "&phoneCode=" + phoneCode);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return mv;
		}
		return mv;
	}

	/**
	 * 显示添加手机页面
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showAddPurchaseView(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("phoneadd");
		String phoneModel = ServletRequestUtils.getStringParameter(request,
				"phoneModel", "");
		String phoneCode = ServletRequestUtils.getStringParameter(request,
				"phoneCode", "");
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 0);
		int offset = ServletRequestUtils.getIntParameter(request, "offset", 0);
		if (!StringUtils.isEmpty(phoneModel)) {
			List<Phone> phoneList = phoneService.getPhoneList(phoneModel,
					limit, offset);
			mv.addObject("phoneModel", phoneModel);
			mv.addObject("phoneModelCount", phoneList.size());
			mv.addObject("phoneList", phoneList);
		}
		mv.addObject("phoneCode", phoneCode);
		return mv;
	}

	/**
	 * 手机查询列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showPhoneList(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("phoneList");
		String phoneModel = ServletRequestUtils.getStringParameter(request,
				"phoneModel", "");
		String phoneCode = ServletRequestUtils.getStringParameter(request,
				"phoneCode", "");
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 0);
		int offset = ServletRequestUtils.getIntParameter(request, "offset", 0);
		List<Phone> phoneList = null;
		if (!StringUtils.isEmpty(phoneCode)) {
			phoneList = phoneService.getPhonesByPhoneCode(phoneCode);
		} else if (!StringUtils.isEmpty(phoneModel)) {
			phoneList = phoneService.getPhoneList(phoneModel, limit, offset);
		}
		if (!ListUtils.isEmptyList(phoneList)) {
			mv.addObject("phoneTotalCount",
					ListUtils.isEmptyList(phoneList) ? 0 : phoneList.size());
			mv.addObject("phoneModel", phoneModel);
			mv.addObject("phoneList", phoneList);
		}
		return mv;
	}

	/**
	 * 利润列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showProfitList(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("showProfit");
		long startTime = ServletRequestUtils.getLongParameter(request,
				"startTime", 0L);
		long endTime = ServletRequestUtils.getLongParameter(request, "endTime",
				0L);
		List<Profit> profitList = profitService.getProfitList(startTime,
				endTime);
		if (!ListUtils.isEmptyList(profitList)) {
			List<Long> selledIdList = new ArrayList<Long>(profitList.size());
			double saleTotal = 0, profitTotal = 0;
			for (Profit profit : profitList) {
				saleTotal += profit.getSelledPrice();
				profitTotal += profit.getProfit();
				selledIdList.add(profit.getPhoneid());
			}
			mv.addObject("selledPhoneNum", profitList.size());
			mv.addObject("saleTotal", saleTotal);
			mv.addObject("profitTotal", profitTotal);
			mv.addObject("selledList",
					selledService.getSelledList(selledIdList));
			return mv;
		}
		return mv;
	}

	/**
	 * 手机管理系统管理首页
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showPhoneIndex(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("phoneIndex");
	}

}