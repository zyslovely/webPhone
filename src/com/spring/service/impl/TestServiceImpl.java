package com.spring.service.impl;

import com.spring.service.TestService;

import com.spring.mapper.TestMapper;
import com.spring.meta.Test;

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