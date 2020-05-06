package com.narroyo.calculator.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	@PostMapping("/calculate")
	  public ResponseEntity<ResultDto> search(@RequestParam(value = "element1", required = true) BigDecimal firstElement,
	      @RequestParam(value = "element2", required = true) BigDecimal secondElement,
	      @RequestParam(value = "operator", required = true) String operator) {

	    final ResultDto response = operationService.calculate(firstElement, secondElement, operator);
	    if (response != null) {
	      return ResponseEntity.ok(response);
	    }
	    return ResponseEntity.noContent().build();

	  }
}
