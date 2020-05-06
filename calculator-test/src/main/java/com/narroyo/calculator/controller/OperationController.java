package com.narroyo.calculator.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.narroyo.calculator.dto.ResultDto;
import com.narroyo.calculator.service.OperationService;

@RestController
@RequestMapping("/api/calculator")
public class OperationController {

	@Autowired
	OperationService operationService;
	
	@PostMapping("/calculate/{operator}")
	public ResponseEntity<ResultDto> calculate(@PathVariable(value = "operator", required = true) String operator,
			@RequestParam(value = "firstElement", required = true) BigDecimal firstElement,
			@RequestParam(value = "secondElement", required = true) BigDecimal secondElement) {
		
		final ResultDto response = operationService.calculate(firstElement, secondElement, operator);
		if (response != null) {
			if(response.isSuccess()) {
				return ResponseEntity.ok(response);
			}
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.noContent().build();
	}
}