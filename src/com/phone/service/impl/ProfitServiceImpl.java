package com.phone.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.phone.mapper.ProfitMapper;
import com.phone.meta.Profit;
import com.phone.service.ProfitService;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime：2013-4-23 上午02:57:16 Class Description
 */
@Service("profitService")
public class ProfitServiceImpl implements ProfitService {
	@Resource
	private ProfitMapper profitMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.phone.service.ProfitService#getProfitList(long, long)
	 */
	@Override
	public List<Profit> getProfitList(long startTime, long endTime, long shopId) {
		if (startTime < 0 || endTime < 0 || endTime > new Date().getTime()) {
			return null;
		}
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("startTime", startTime);
		hashMap.put("endTime", endTime);
		hashMap.put("shopId", shopId);
		return profitMapper.getProfitList(hashMap);
	}
}
