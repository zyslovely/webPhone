package com.phone.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.phone.meta.Accessory;
import com.phone.meta.AccessoryInfo;
import com.phone.meta.AccessoryProfit;
import com.phone.meta.DayProfit;
import com.phone.meta.Operation;
import com.phone.meta.Phone;
import com.phone.meta.ProfitVo;
import com.phone.security.MySecurityDelegatingFilter;
import com.phone.security.MyUser;
import com.phone.service.AccessoryService;
import com.phone.service.PhoneService;
import com.phone.service.ProfitService;
import com.phone.service.PurchaseService;
import com.phone.util.ListUtils;
import com.phone.util.StringUtil;
import com.phone.util.TimeUtil;

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
		Long userId = MyUser.getMyUser(request);
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		if (myUser == null) {
			logger.error("myUser不存在，没有经过验证");
		}

		String phoneCode = StringUtil.ToDBC(ServletRequestUtils
				.getStringParameter(request, "phoneCode", "").trim()
				.toLowerCase());
		String brand = StringUtil.ToDBC(ServletRequestUtils
				.getStringParameter(request, "brand", "").trim().toLowerCase());
		String phoneModel = StringUtil.ToDBC(ServletRequestUtils
				.getStringParameter(request, "phoneModel", "").trim()
				.toLowerCase());
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
				String urlString = "/purchase/add/show/?phoneModel="
						+ phoneModel + "&phoneCode=" + phoneCode
						+ "&shopId=0&brand=" + brand;
				response.sendRedirect(urlString);
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
		Long userId = MyUser.getMyUser(request);
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		if (myUser == null) {
			logger.error("myUser不存在，没有经过验证");
		}
		String phoneModel = StringUtil.ToDBC(ServletRequestUtils
				.getStringParameter(request, "phoneModel", "").trim()
				.toLowerCase());
		String phoneCode = StringUtil.ToDBC(ServletRequestUtils
				.getStringParameter(request, "phoneCode", "").trim()
				.toLowerCase());
		String brand = ServletRequestUtils.getStringParameter(request, "brand",
				"");
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 50);
		int offset = -1;
		if (!StringUtils.isEmpty(phoneModel)) {
			List<Phone> phoneList = phoneService.getPhoneList(phoneModel,
					myUser.getShopId(), limit, offset, -1);
			mv.addObject("phoneModel", phoneModel);
			mv.addObject("phoneModelCount",
					ListUtils.isEmptyList(phoneList) ? 0 : phoneList.size());
			mv.addObject("phoneList", phoneList);
		}
		mv.addObject("phoneCode", phoneCode);
		mv.addObject("brand", brand);
		return mv;
	}

	/**
	 * 手机查询列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public ModelAndView showPhoneList(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		response.setCharacterEncoding("UTF-8");
		Long userId = MyUser.getMyUser(request);
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		if (myUser == null) {
			logger.error("myUser不存在，没有经过验证");
		}
		ModelAndView mv = new ModelAndView("phoneList");
		this.setUD(mv, request);
		String phoneModel = ServletRequestUtils.getStringParameter(request,
				"phoneModel", "");
		phoneModel = new String(phoneModel.getBytes("iso-8859-1"), "UTF-8")
				.trim().toLowerCase();
		String phoneCode = ServletRequestUtils
				.getStringParameter(request, "phoneCode", "").trim()
				.toLowerCase();
		String brandName = ServletRequestUtils.getStringParameter(request,
				"brandName", "");
		brandName = new String(brandName.getBytes("iso-8859-1"), "UTF-8")
				.trim().toLowerCase();
		int status = ServletRequestUtils.getIntParameter(request, "status", -1);
		mv.addObject("status", status);
		int inventory = ServletRequestUtils.getIntParameter(request,
				"inventory", -1);
		mv.addObject("inventory", 0);
		logger.info("showPhoneList where phoneModel =" + phoneModel
				+ " phoneCode=" + phoneCode);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 20);
		int toPage = ServletRequestUtils.getIntParameter(request, "toPage", 0);
		if (toPage == 0) {
			toPage = 1;
		}
		int offset = (toPage - 1) * limit;
		List<Phone> phoneList = null;
		int totalPage = 0;
		if (!StringUtils.isEmpty(phoneCode)) {
			phoneList = phoneService.getPhonesByPhoneCode(phoneCode,
					myUser.getShopId(), status);
			totalPage = 1;
		} else if (!StringUtils.isEmpty(phoneModel)) {
			phoneList = phoneService.getPhoneList(phoneModel,
					myUser.getShopId(), limit, offset, status);

			int totalCount = purchaseService.getPurchaseCountByPhoneModel(
					myUser.getShopId(), phoneModel, status);
			if (totalCount % limit == 0) {
				totalPage = totalCount / limit;
			} else {
				totalPage = totalCount / limit + 1;
			}

			mv.addObject("searchPhonetotalCount", totalCount);
		} else if (!StringUtils.isEmpty(brandName)) {
			phoneList = phoneService.getPhoneListByBrandName(brandName, limit,
					offset, myUser.getShopId());

			int totalCount = phoneService.getPhoneCountByBrandName(brandName,
					myUser.getShopId());
			if (totalCount % limit == 0) {
				totalPage = totalCount / limit;
			} else {
				totalPage = totalCount / limit + 1;
			}

			mv.addObject("searchPhonetotalCount", totalCount);
			mv.addObject("brandName", brandName);
		} else if (inventory == 1) {
			phoneList = phoneService.getPhoneListNoInventory(
					myUser.getShopId(), limit, offset);

			int totalCount = purchaseService
					.getPurchaseCountNotInventory(myUser.getShopId());
			if (totalCount % limit == 0) {
				totalPage = totalCount / limit;
			} else {
				totalPage = totalCount / limit + 1;
			}

			mv.addObject("searchPhonetotalCount", totalCount);
			mv.addObject("inventory", inventory);
		} else {

			phoneList = phoneService.getPhoneList(phoneModel,
					myUser.getShopId(), limit, offset, status);

			int totalCount = purchaseService.getPurchaseCountByPhoneModel(
					myUser.getShopId(), phoneModel, status);
			if (totalCount % limit == 0) {
				totalPage = totalCount / limit;
			} else {
				totalPage = totalCount / limit + 1;
			}

			mv.addObject("searchPhonetotalCount", totalCount);
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
		} else if (ListUtils.isEmptyList(phoneList)
				&& (!StringUtils.isEmpty(phoneModel) || !StringUtils
						.isEmpty(phoneCode))) {
			mv.addObject("noFound", 1);
			mv.addObject("phoneModel", phoneModel);
		}
		int totalPhoneCount = purchaseService.getPurchaseCountByPhoneModel(
				myUser.getShopId(), null, 0);
		mv.addObject("totalPhoneCount", totalPhoneCount);
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

		Long userId = MyUser.getMyUser(request);
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		if (myUser == null) {
			logger.error("myUser不存在，没有经过验证");
		}
		ModelAndView mv = new ModelAndView("profitList");
		int date = ServletRequestUtils
				.getIntParameter(request, "profitDate", 0);

		long startTime = 0;
		long endTime = 0;
		if (date == 0) {
			date = 1;
		}
		switch (date) {
		case 1:
			startTime = TimeUtil.getDaybreakTime();
			endTime = TimeUtil.getDayBefore(TimeUtil.getDaybreakTime(), -1);
			break;
		case 2:
			startTime = TimeUtil.getDayBefore(TimeUtil.getDaybreakTime(), 1);
			endTime = TimeUtil.getDaybreakTime();
			break;
		case 3:
			startTime = TimeUtil.firstDayInMonth();
			endTime = TimeUtil.getDayBefore(TimeUtil.getDaybreakTime(), -1);
			break;
		default:
			String beginDate = ServletRequestUtils.getStringParameter(request,
					"beginDate", "");
			String endDate = ServletRequestUtils.getStringParameter(request,
					"endDate", "");
			startTime = TimeUtil.getDateFromStringYYYYMMdd(beginDate);
			endTime = TimeUtil.getDateFromStringYYYYMMdd(endDate);
			mv.addObject("beginDate", beginDate);
			mv.addObject("endDate", endDate);
		}
		int toPage = ServletRequestUtils.getIntParameter(request, "toPage", 0);
		int limit = 20;
		if (toPage == 0) {
			toPage = 1;
		}

		int offset = (toPage - 1) * limit;
		mv.addObject("nowPage", toPage);
		mv.addObject("extPage", toPage - 1);
		mv.addObject("nextPage", toPage + 1);
		mv.addObject("profitDate", date);
		List<ProfitVo> profitVoList = profitService.getProfitList(startTime,
				endTime, myUser.getShopId(), limit, offset);
		int totalCount = profitService.getProfitCount(startTime, endTime,
				myUser.getShopId());
		mv.addObject("profitVoList", profitVoList);

		List<DayProfit> dayProfits = profitService.getDayProfitListByTime(
				TimeUtil.getFormatTime(startTime),
				TimeUtil.getFormatTime(endTime), myUser.getShopId());
		if (!ListUtils.isEmptyList(dayProfits)) {
			double saleTotal = 0, profitTotal = 0;
			for (DayProfit profit : dayProfits) {
				saleTotal += profit.getTotalSell();
				profitTotal += profit.getTotalProfit();
			}
			mv.addObject("saleTotal", saleTotal);
			mv.addObject("profitTotal", profitTotal);
		}
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		mv.addObject("totalPage", totalPage);
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
		ModelAndView mv = new ModelAndView("phoneIndex");
		this.setUD(mv, request);
		return mv;
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
		String accessoryName = StringUtil
				.ToDBC(ServletRequestUtils.getStringParameter(request,
						"accessoryName", "").toLowerCase());
		long accessoryInfoId = ServletRequestUtils.getLongParameter(request,
				"accessoryInfoId", -1L);
		int toPage = ServletRequestUtils.getIntParameter(request, "toPage", 0);
		Long userId = MyUser.getMyUser(request);
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		if (myUser == null) {
			logger.error("myUser不存在，没有经过验证");
		}
		int limit = 10;
		if (toPage == 0) {
			toPage = 1;
		}
		int offset = (toPage - 1) * limit;
		List<Accessory> accessoryList = accessoryService.getAccessoryList(
				accessoryName, myUser.getShopId(), limit, offset,
				accessoryInfoId);
		int totalCount = accessoryService.getAccessoryCount(accessoryName,
				accessoryInfoId, myUser.getShopId());

		if (!ListUtils.isEmptyList(accessoryList)) {
			mv.addObject("totalPage", totalCount / 10 + 1);
			mv.addObject("accessoryName", accessoryName);
			mv.addObject("accessorysList", accessoryList);
		}
		mv.addObject("nowPage", toPage);
		mv.addObject("extPage", toPage - 1);
		mv.addObject("nextPage", toPage + 1);
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
		Long userId = MyUser.getMyUser(request);
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		String name = ServletRequestUtils
				.getStringParameter(request, "name", null).trim().toLowerCase();
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
				accessoryInfoId, unitPrice, myUser.getUserId(),
				myUser.getShopId());
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

	/**
	 * 退出
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) {
		Long userId = MyUser.getMyUser(request);
		MySecurityDelegatingFilter.userMap.remove(userId);
		HttpSession session = request.getSession();
		session.removeAttribute("login");
		session.removeAttribute("userId");
		try {
			response.sendRedirect("/");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 操作列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView operationList(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("operation");

		String beginDate = ServletRequestUtils.getStringParameter(request,
				"beginDate", "");
		String endDate = ServletRequestUtils.getStringParameter(request,
				"endDate", "");
		mv.addObject("beginDate", beginDate);
		mv.addObject("endDate", endDate);
		int type = ServletRequestUtils.getIntParameter(request, "type", 0);
		if (!StringUtils.isEmpty(beginDate) && !StringUtils.isEmpty(endDate)) {
			long startTime = TimeUtil.getDateFromStringYYYYMMdd(beginDate);
			long endTime = TimeUtil.getDateFromStringYYYYMMdd(endDate);

			List<Operation> operationList = purchaseService.getOperationByType(
					startTime, endTime, type);
			if (!ListUtils.isEmptyList(operationList)) {
				for (Operation operation : operationList) {
					operation.setCreateTimeStr(TimeUtil
							.getFormatTimeInMinute(operation.getCreateTime()));
				}
				mv.addObject("operationList", operationList);
			}
		}

		mv.addObject("type", type);
		return mv;

	}

	/**
	 * 获取配件利润列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView showAccessoryProfitList(HttpServletRequest request,
			HttpServletResponse response) {
		Long userId = MyUser.getMyUser(request);
		MyUser myUser = MySecurityDelegatingFilter.userMap.get(userId);
		if (myUser == null) {
			logger.error("myUser不存在，没有经过验证");
		}
		ModelAndView mv = new ModelAndView("accessoryprofitList");
		int date = ServletRequestUtils.getIntParameter(request,
				"accessoryprofitDate", 0);
		long startTime = 0;
		long endTime = 0;
		if (date == 0) {
			date = 1;
		}
		switch (date) {
		case 1:
			startTime = TimeUtil.getDaybreakTime();
			endTime = new Date().getTime();
			break;
		case 2:
			startTime = TimeUtil.getDayBefore(TimeUtil.getDaybreakTime(), 1);
			endTime = TimeUtil.getDaybreakTime();
			break;
		case 3:
			startTime = TimeUtil.firstDayInMonth();
			endTime = new Date().getTime();
			break;
		default:
			logger.error("错误 showAccessoryProfitList where date=" + date);
		}
		int toPage = ServletRequestUtils.getIntParameter(request, "toPage", 0);
		int limit = 10;
		if (toPage == 0) {
			toPage = 1;
		}

		int offset = (toPage - 1) * limit;
		mv.addObject("nowPage", toPage);
		mv.addObject("extPage", toPage - 1);
		mv.addObject("nextPage", toPage + 1);
		mv.addObject("profitDate", date);
		List<AccessoryProfit> accessoryProfitList = accessoryService
				.getProfitList(startTime, endTime, myUser.getShopId(), limit,
						offset);
		int totalCount = accessoryService.getAccessoryProfitCount(startTime,
				endTime, myUser.getShopId());

		if (!ListUtils.isEmptyList(accessoryProfitList)) {
			double saleTotal = 0, profitTotal = 0;
			for (AccessoryProfit accessoryProfit : accessoryProfitList) {
				saleTotal += accessoryProfit.getSoldPrice();
				profitTotal += accessoryProfit.getProfit();
			}
			mv.addObject("saleTotal", saleTotal);
			mv.addObject("profitTotal", profitTotal);
			mv.addObject("accessoryProfitList", accessoryProfitList);
		}
		mv.addObject("totalPage", totalCount / 10 + 1);
		return mv;
	}
}