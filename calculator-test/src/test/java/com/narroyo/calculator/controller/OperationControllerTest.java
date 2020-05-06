package com.narroyo.calculator.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.narroyo.calculator.dto.ResultDto;
import com.narroyo.calculator.enumeration.Operators;
import com.narroyo.calculator.service.OperationService;

public class OperationControllerTest {
	
	private MockMvc mvc;
	
	@Mock
	OperationService operationService;
	
	@InjectMocks
	private OperationController operationController;
	
	private static final String URI_BASE = "/api/calculator/";
	private static final String CALCULATE = "calculate/";
	private static final String ADDITION_URI = URI_BASE.concat(CALCULATE).concat(Operators.ADDITION.getCode());
	private static final String SUBTRACTION_URI = URI_BASE.concat(CALCULATE).concat(Operators.SUBTRACTION.getCode());
	private static final String FIRST_PARAM_NAME = "firstElement";
	private static final String SECOND_PARAM_NAME = "secondElement";
	private static final String FIRST_PARAM_VALID_VALUE = "2.3";
	private static final String SECOND_PARAM_VALID_VALUE = "1.5";
	private static final String PARAM_INVALID_VALUE = "A";
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(operationController).build();
	}
	
	@Test
	public void noRequiredParams() throws Exception {
		mvc.perform(post(SUBTRACTION_URI)).andExpect(status().isBadRequest());
	}
	
	@Test
	public void noRequiredFirstParam() throws Exception {
		mvc.perform(post(SUBTRACTION_URI.concat("?").concat(SECOND_PARAM_NAME).concat("=").concat(SECOND_PARAM_VALID_VALUE))).andExpect(status().isBadRequest());
	}
	
	@Test
	public void noRequiredSecondParam() throws Exception {
		mvc.perform(post(SUBTRACTION_URI.concat("?").concat(FIRST_PARAM_NAME).concat("=").concat(FIRST_PARAM_VALID_VALUE))).andExpect(status().isBadRequest());
	}
	
	@Test
	public void invalidFirstParam() throws Exception {
		mvc.perform(post(SUBTRACTION_URI.concat("?").concat(FIRST_PARAM_NAME).concat("=").concat(PARAM_INVALID_VALUE))).andExpect(status().isBadRequest());
	}
	
	@Test
	public void invalidSecondParam() throws Exception {
		mvc.perform(post(SUBTRACTION_URI.concat("?").concat(SECOND_PARAM_NAME).concat("=").concat(PARAM_INVALID_VALUE))).andExpect(status().isBadRequest());
	}
	
	@Test
	public void emptyFirstParam() throws Exception {
		mvc.perform(post(SUBTRACTION_URI.concat("?").concat(FIRST_PARAM_NAME).concat("=").concat(PARAM_INVALID_VALUE))).andExpect(status().isBadRequest());
	}
	
	@Test
	public void emptySecondParam() throws Exception {
		mvc.perform(post(SUBTRACTION_URI.concat("?").concat(SECOND_PARAM_NAME).concat("="))).andExpect(status().isBadRequest());
	}
	
	@Test
	public void calculateAddition_responseOK() throws Exception {
		/**
		 * Given
		 */
		ResultDto resultOperation = new ResultDto();
		resultOperation.setSuccess(true);
		resultOperation.setResult("0.8");
		when(operationService.calculate(new BigDecimal(FIRST_PARAM_VALID_VALUE),
				new BigDecimal(SECOND_PARAM_VALID_VALUE), Operators.SUBTRACTION.getCode())).thenReturn(resultOperation);
		
		/**
		 * When
		 */
		ResultActions result = mvc.perform(
				post(ADDITION_URI.concat("?").concat(FIRST_PARAM_NAME).concat("=").concat(FIRST_PARAM_VALID_VALUE)
						.concat("&").concat(SECOND_PARAM_NAME).concat("=").concat(SECOND_PARAM_VALID_VALUE)));
		
		/**
		 * Then
		 */
		result.andExpect(status().isOk()).andExpect(content().json("{\"result\": \"3.8\", \"success\": true}"));
	}
	
	@Test
	public void calculateSubtraction_responseOK() throws Exception {
		/**
		 * Given
		 */
		ResultDto resultOperation = new ResultDto();
		resultOperation.setSuccess(true);
		resultOperation.setResult("0.8");
		when(operationService.calculate(new BigDecimal(FIRST_PARAM_VALID_VALUE),
				new BigDecimal(SECOND_PARAM_VALID_VALUE), Operators.SUBTRACTION.getCode())).thenReturn(resultOperation);

		/**
		 * When
		 */
		ResultActions result = mvc.perform(
				post(SUBTRACTION_URI.concat("?").concat(FIRST_PARAM_NAME).concat("=").concat(FIRST_PARAM_VALID_VALUE)
						.concat("&").concat(SECOND_PARAM_NAME).concat("=").concat(SECOND_PARAM_VALID_VALUE)));

		/**
		 * Then
		 */
		result.andExpect(status().isOk()).andExpect(content().json("{\"result\": \"0.8\", \"success\": true}"));
	}
}

