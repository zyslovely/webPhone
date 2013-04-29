package com.phone.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.phone.meta.Accessory;
import com.phone.meta.AccessoryInfo;
import com.phone.meta.Phone;
import com.phone.meta.Profit;
import com.phone.security.MySecurityDelegatingFilter;
import com.phone.security.MyUser;
import com.phone.service.AccessoryService;
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

	@Resource
	private AccessoryService accessoryService;

	/**
	 * 添加手机入库操作
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showAddPurchase(HttpServletRequest request,
			HttpServletResponse response) {
		String sessionId = request.getSession().getId();
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(sessionId);
		if (myUser == null) {
			logger.error("myUser不存在，没有经过验证");
		}

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
				purchasePrice, DecideSellPirce, myUser.getUserId(),
				myUser.getShopId())) {
			try {
				response.sendRedirect("/purchase/add/show/?phoneModel="
						+ phoneModel + "&phoneCode=" + phoneCode + "&shopId=0");
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
		String sessionId = request.getSession().getId();
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(sessionId);
		if (myUser == null) {
			logger.error("myUser不存在，没有经过验证");
		}
		String phoneModel = ServletRequestUtils.getStringParameter(request,
				"phoneModel", "");
		String phoneCode = ServletRequestUtils.getStringParameter(request,
				"phoneCode", "");
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 10);
		int offset = ServletRequestUtils.getIntParameter(request, "offset", 0);
		if (!StringUtils.isEmpty(phoneModel)) {
			List<Phone> phoneList = phoneService.getPhoneList(phoneModel,
					myUser.getShopId(), limit, offset);
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
		String sessionId = request.getSession().getId();
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(sessionId);
		if (myUser == null) {
			logger.error("myUser不存在，没有经过验证");
		}
		ModelAndView mv = new ModelAndView("phoneList");
		String phoneModel = ServletRequestUtils.getStringParameter(request,
				"phoneModel", "");
		String phoneCode = ServletRequestUtils.getStringParameter(request,
				"phoneCode", "");
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 10);
		int toPage = ServletRequestUtils.getIntParameter(request, "toPage", 0);
		int offset = toPage * limit;
		List<Phone> phoneList = null;
		int totalPage = 0;
		if (!StringUtils.isEmpty(phoneCode)) {
			phoneList = phoneService.getPhonesByPhoneCode(phoneCode,
					myUser.getShopId());
			totalPage = 1;
		} else if (!StringUtils.isEmpty(phoneModel)) {
			phoneList = phoneService.getPhoneList(phoneModel,
					myUser.getShopId(), limit, offset);
			List<Phone> allPhoneList = phoneService.getPhoneListByPhoneModel(
					phoneModel, myUser.getShopId());
			if (allPhoneList.size() % limit > 0) {
				totalPage = allPhoneList.size() / limit + 1;
			} else {
				totalPage = allPhoneList.size() / limit;
			}
		}
		if (!ListUtils.isEmptyList(phoneList)) {
			mv.addObject("phoneTotalCount",
					ListUtils.isEmptyList(phoneList) ? 0 : phoneList.size());
			mv.addObject("phoneModel", phoneModel);
			mv.addObject("phoneList", phoneList);
			mv.addObject("nowPage", toPage);
			mv.addObject("extPage", toPage - 1);
			mv.addObject("nextPage", toPage + 1);
			mv.addObject("totalPage", totalPage);
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
		String sessionId = request.getSession().getId();
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(sessionId);
		if (myUser == null) {
			logger.error("myUser不存在，没有经过验证");
		}
		ModelAndView mv = new ModelAndView("showProfit");
		int today = ServletRequestUtils.getIntParameter(request, "today", 0);
		int yesterday = ServletRequestUtils.getIntParameter(request,
				"yesterday", 0);
		if (today > 0) {

		} else if (yesterday > 0) {

		} else {

		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date startDate, endDate;
		long startTime = 0, endTime = 0;
		try {
			startDate = sdf.parse("");
			endDate = sdf.parse("");
			startTime = startDate.getTime();
			endTime = endDate.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Profit> profitList = profitService.getProfitList(startTime,
				endTime, myUser.getShopId());
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
			mv.addObject(
					"selledList",
					selledService.getSelledList(selledIdList,
							myUser.getShopId()));
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
		logger.info(request.getSession().getId());
		return new ModelAndView("phoneIndex");
	}

	/**
	 * 显示添加配件页面
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showAddAccessory(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("accessoryadd");
		List<AccessoryInfo> accessoryInfos = accessoryService
				.getAllAccessoryInfo();
		mv.addObject("accessoryInfos", accessoryInfos);
		mv.addObject("succ", 0);
		return mv;
	}

	/**
	 * 配件列表页面
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showAccessoryList(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("accessoryList");
		String accessoryName = ServletRequestUtils.getStringParameter(request,
				"accessoryName", null);
		long accessoryInfoId = ServletRequestUtils.getLongParameter(request,
				"accessoryInfoId", -1L);
		int toPage = ServletRequestUtils.getIntParameter(request, "toPage", 0);
		String sessionId = request.getSession().getId();
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(sessionId);
		if (myUser == null) {
			logger.error("myUser不存在，没有经过验证");
		}
		int limit = 10;
		if (toPage == 0) {
			toPage = 1;
		}
		int offset = (toPage - 1) * limit;
		List<Accessory> accessoryList = null;
		if (!StringUtils.isEmpty(accessoryName)) {
			accessoryList = accessoryService.getAccessoryList(accessoryName,
					myUser.getShopId(), limit, offset, accessoryInfoId);
			int totalCount = accessoryService.getAccessoryCount(accessoryName,
					accessoryInfoId, myUser.getShopId());
			if (!ListUtils.isEmptyList(accessoryList)) {
				mv.addObject("totalPage", totalCount / 10 + 1);
				mv.addObject("accessoryName", accessoryName);
				mv.addObject("accessoryList", accessoryList);
			}
			if (toPage == 0) {

			}
			mv.addObject("nowPage", toPage);
			mv.addObject("extPage", toPage - 1);
			mv.addObject("nextPage", toPage + 1);
		}
		List<AccessoryInfo> accessoryInfos = accessoryService
				.getAllAccessoryInfo();
		mv.addObject("accessoryInfos", accessoryInfos);
		return mv;
	}

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView addAccessory(HttpServletRequest request,
			HttpServletResponse response) {
		String name = ServletRequestUtils.getStringParameter(request, "name",
				null);
		int count = ServletRequestUtils.getIntParameter(request, "count", 0);
		long accessoryInfoId = ServletRequestUtils.getLongParameter(request,
				"accessoryInfoId", -1L);
		double unitPrice = ServletRequestUtils.getDoubleParameter(request,
				"unitPrice", -1L);
		if (count == 0 || accessoryInfoId <= 0) {
			logger.error("添加配件失败，因为数据有错误。数量=" + count + " 配件类型="
					+ accessoryInfoId);
		}
		ModelAndView mv = new ModelAndView("accessoryadd");
		Boolean succ = accessoryService.addAccessory(name, count,
				accessoryInfoId, unitPrice);
		mv.addObject("succ", 0);
		if (succ) {
			mv.addObject("succ", 1);
		} else {
			mv.addObject("succ", 2);
		}
		List<AccessoryInfo> accessoryInfos = accessoryService
				.getAllAccessoryInfo();
		mv.addObject("accessoryInfos", accessoryInfos);
		return mv;

	}
}