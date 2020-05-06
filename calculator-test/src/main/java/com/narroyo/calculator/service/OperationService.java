package com.narroyo.calculator.service;

import java.math.BigDecimal;

import com.narroyo.calculator.dto.ResultDto;

public interface OperationService {

	
	/***
	 * This method calculates the result of addition or subtraction depending on firstElement, secondElement and an {@link com.narroyo.calculator.enumeration.Operators}
	 */
	
	ResultDto calculate(BigDecimal firstElement, BigDecimal secondElement, String operator);
}
