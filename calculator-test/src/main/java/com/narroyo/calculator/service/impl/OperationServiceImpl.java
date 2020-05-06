package com.narroyo.calculator.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.narroyo.calculator.dto.ResultDto;
import com.narroyo.calculator.enumeration.Operators;
import com.narroyo.calculator.service.OperationService;

import io.corp.calculator.TracerImpl;

@Service
public class OperationServiceImpl implements OperationService {

	private static final String NULL_ERROR = "Null value is not valid";
	private static final String OPERATOR_ERROR = "Operation is not implemented";
	
	@Autowired
	TracerImpl trace;
	
	@Override
	public ResultDto calculate(BigDecimal firtsElement, BigDecimal secondElement, String operatorCode) {
		if (firtsElement == null || secondElement == null) {
			return new ResultDto(NULL_ERROR, false);
		}
		
		if (Operators.ADDITION.getCode().equals(operatorCode)) {
			return addition(firtsElement, secondElement);
		} else if (Operators.SUBTRACTION.getCode().equals(operatorCode)) {
			return subtraction(firtsElement, secondElement);
		} else {
			return new ResultDto(OPERATOR_ERROR, false);
		}
	}
	
	private ResultDto addition(BigDecimal firtsElement, BigDecimal secondElement) {
		
		BigDecimal result = firtsElement.add(secondElement);
		trace.trace(result);
		return new ResultDto(result.toString(), true);
	}
	
	private ResultDto subtraction(BigDecimal firtsElement, BigDecimal secondElement) {
		BigDecimal result = firtsElement.subtract(secondElement);
		trace.trace(result);
		return new ResultDto(result.toString(), true);
	}

}
