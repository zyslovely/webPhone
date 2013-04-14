package com.phone.service.impl;

import com.phone.mapper.TestMapper;
import com.phone.meta.Test;
import com.phone.service.TestService;


public class TestServiceImpl implements TestService {
	private TestMapper testMapper;

	public void addTest(long id, String name, int level) {
		Test test = new Test();
		test.setId(id);
		test.setName(name);
		test.setLevel(level);
		testMapper.addTest(test);
	}
}