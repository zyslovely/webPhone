package com.phone.web.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.phone.meta.ProfitVo;
import com.phone.service.ProfitService;
import com.phone.util.ListUtils;
import com.phone.util.TimeUtil;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-17 上午06:34:05
 * @see Class Description
 */
@Controller("phonePubController")
public class PhonePubController extends AbstractBaseController {

	@Resource
	private ProfitService profitService;

	/**
	 * 
	 * @auther zyslovely@gmail.com
	 * @param request
	 * @param response
	 * @return app/profit/list/
	 */
	public ModelAndView showPubProfitList(HttpServletRequest request,
			HttpServletResponse response) {
		String key = ServletRequestUtils.getStringParameter(request, "key",
				null);
		if (StringUtils.isEmpty(key) || !key.equals("zystest123")) {
			return null;
		}
		long shopId = ServletRequestUtils.getLongParameter(request, "shopId",
				-1L);
		ModelAndView mv = new ModelAndView("return");
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
			logger.error("错误 showProfitList where date=" + date);
		}
		int offset = ServletRequestUtils.getIntParameter(request, "offset", 0);
		int limit = 10;

		mv.addObject("profitDate", date);
		List<ProfitVo> profitVoList = profitService.getProfitList(startTime,
				endTime, shopId, limit, offset);
		int totalCount = profitService.getProfitCount(startTime, endTime,
				shopId);
		JSONObject returnObject = new JSONObject();
		if (!ListUtils.isEmptyList(profitVoList)) {
			double saleTotal = 0, profitTotal = 0;
			for (ProfitVo profit : profitVoList) {
				saleTotal += profit.getSelledPrice();
				profitTotal += profit.getProfit();
			}
			returnObject.put("saletotal", saleTotal);
			returnObject.put("profittotal", profitTotal);
		}
		returnObject.put("totalcount", totalCount);

		JSONArray array = new JSONArray();
		for (ProfitVo profitVo : profitVoList) {
			JSONObject profitObject = new JSONObject();
			profitObject.put("phonecode", profitVo.getPhoneCode());
			profitObject.put("brandname", profitVo.getBrandName());
			profitObject.put("phonemodel", profitVo.getPhoneModel());
			profitObject.put("profit", profitVo.getProfit());
			profitObject.put("purchaseprice", profitVo.getPurchasePrice());
			profitObject.put("selledprice", profitVo.getSelledPrice());
			profitObject.put("selledtimestr", profitVo.getSelledTimeStr());
			array.add(profitObject);
		}
		returnObject.put("profitlist", array);

		return mv;
	}
}
