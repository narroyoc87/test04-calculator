package com.narroyo.calculator.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4513741196180970122L;
	
	BigDecimal result;
	
	
}
