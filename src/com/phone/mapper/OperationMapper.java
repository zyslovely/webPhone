package com.phone.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.phone.meta.Operation;

public interface OperationMapper {
	public int addOperation(Operation operation);

	public List<Operation> getOperationsByType(
			@Param(value = "beginTime") long beginTime,
			@Param(value = "endTime") long endTime,
			@Param(value = "type") int type);
}