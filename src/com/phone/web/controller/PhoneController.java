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
import com.phone.service.SelledService;

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

	/**
	 * 添加手机入库
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showAddPurchase(HttpServletRequest request, HttpServletResponse response) {
		String phoneCode = ServletRequestUtils.getStringParameter(request, "phoneCode", "");
		String brand = ServletRequestUtils.getStringParameter(request, "brand", "");
		String phoneModel = ServletRequestUtils.getStringParameter(request, "phoneModel", "");
		double purchasePrice = ServletRequestUtils.getDoubleParameter(request, "purchasePrice", 0.00);
		double DecideSellPirce = ServletRequestUtils.getDoubleParameter(request, "DecideSellPrice", 0.00);
		ModelAndView mv = new ModelAndView("phoneadd");
		if (purchaseService.addPurchase(brand, phoneCode, phoneModel, purchasePrice, DecideSellPirce)) {
			try {
				response.sendRedirect("/phone/list/?phoneModel=" + phoneModel + "&phoneCode=" + phoneCode);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return mv;
		}
		return mv;
	}

	/**
	 * 添加手机入库售出查询列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showPhoneList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("showPhone");
		String phoneModel = ServletRequestUtils.getStringParameter(request, "phoneModel", "");
		String phoneCode = ServletRequestUtils.getStringParameter(request, "phoneCode", "");
		if (!StringUtils.isEmpty(phoneModel)) {
			List<Phone> phoneList = phoneService.getPhoneList(phoneModel);
			int toPage = ServletRequestUtils.getIntParameter(request, "toPage", 0);
			// 返回总共有多少页,toPage返回第几页的数据，每页20条数据，toPage从1开始
			int totalPage = 5;
			mv.addObject("nowPage", toPage);
			mv.addObject("extPage", toPage - 1);
			mv.addObject("nextPage", toPage + 1);
			mv.addObject("totalPage", totalPage);

			mv.addObject("phoneModel", phoneModel);

			mv.addObject("phoneModelCount", phoneList.size());
			mv.addObject("phoneList", phoneList);
		}
		mv.addObject("phoneCode", phoneCode);
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
	public ModelAndView showProfitList(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	/**
	 * 手机管理系统管理首页
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showPhoneIndex(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("phoneIndex");
	}

	/**
	 * 添加手机销售
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView addSelled(HttpServletRequest request, HttpServletResponse response) {
		long phoneid = ServletRequestUtils.getLongParameter(request, "phoneid", 0L);
		double selledPrice = ServletRequestUtils.getDoubleParameter(request, "selledPrice", 0.00);
		int operatorId = ServletRequestUtils.getIntParameter(request, "operatorId", 0);
		ModelAndView mv = new ModelAndView("phoneselled");
		if (selledService.addSelled(phoneid, selledPrice, operatorId)) {
			try {
				// 销售列表
				response.sendRedirect("/phone/list/?phoneModel=" + phoneModel + "&phoneCode=" + phoneCode);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return mv;
		}
		return mv;
	}
}