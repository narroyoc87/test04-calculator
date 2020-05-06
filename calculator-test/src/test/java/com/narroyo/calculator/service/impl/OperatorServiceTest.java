package com.narroyo.calculator.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.narroyo.calculator.Tracer;
import com.narroyo.calculator.dto.ResultDto;
import com.narroyo.calculator.enumeration.Operators;
import com.narroyo.calculator.service.OperationService;

public class OperatorServiceTest {

	@Mock
	private Tracer trace;
	
	@InjectMocks
	private OperationServiceImpl operationService;
	
	private static final String NULL_ERROR = "Null value is not valid";
	private static final String OPERATOR_ERROR = "Operation is not implemented";
	
	private static final String FIRST_PARAM_VALID_VALUE = "2.3";
	private static final String SECOND_PARAM_VALID_VALUE = "3.5";
	private static final String RESULT_ADDITION = "5.8";
	private static final String RESULT_SUBTRACTION = "-1.2";
	@BeforeEach
    public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void calculateNullValues() {
		ResultDto expected = new ResultDto();
		expected.setSuccess(false);
		expected.setResult(NULL_ERROR);
		ResultDto result = operationService.calculate(null, null, Operators.ADDITION.getCode());
		assertEquals(expected, result);
	}
	
	@Test
	public void calculateFisrtValueIsNull() {
		ResultDto expected = new ResultDto();
		expected.setSuccess(false);
		expected.setResult(NULL_ERROR);
		ResultDto result = operationService.calculate(null, new BigDecimal(SECOND_PARAM_VALID_VALUE), Operators.ADDITION.getCode());
		assertEquals(expected, result);
	}
	
	@Test
	public void calculateSecondValueIsNull() {
		ResultDto expected = new ResultDto();
		expected.setSuccess(false);
		expected.setResult(NULL_ERROR);
		ResultDto result = operationService.calculate(new BigDecimal(FIRST_PARAM_VALID_VALUE), null, Operators.ADDITION.getCode());
		assertEquals(expected, result);
	}
	
	@Test
	public void calculateAddition() {
		ResultDto expected = new ResultDto();
		expected.setSuccess(true);
		expected.setResult(RESULT_ADDITION);
		ResultDto result = operationService.calculate(new BigDecimal(FIRST_PARAM_VALID_VALUE),
				new BigDecimal(SECOND_PARAM_VALID_VALUE), Operators.ADDITION.getCode());
		assertEquals(expected, result);
	}
	
	@Test
	public void calculateSubtraction() {
		ResultDto expected = new ResultDto();
		expected.setSuccess(true);
		expected.setResult(RESULT_SUBTRACTION);
		ResultDto result = operationService.calculate(new BigDecimal(FIRST_PARAM_VALID_VALUE),
				new BigDecimal(SECOND_PARAM_VALID_VALUE), Operators.SUBTRACTION.getCode());
		assertEquals(expected, result);
	}
	
	@Test
	public void operationNotImplemented() {
		ResultDto expected = new ResultDto();
		expected.setSuccess(false);
		expected.setResult(OPERATOR_ERROR);
		ResultDto result = operationService.calculate(new BigDecimal(FIRST_PARAM_VALID_VALUE),
				new BigDecimal(SECOND_PARAM_VALID_VALUE), "CODE");
		assertEquals(expected, result);
	}
}
