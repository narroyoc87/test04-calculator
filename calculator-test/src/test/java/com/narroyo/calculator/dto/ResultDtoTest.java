package com.narroyo.calculator.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ResultDtoTest {
	@Test
    public void toStringOK() {
		
		
		ResultDto resultDto = new ResultDto("5.6", true);
        String dtoAsString = resultDto.toString();
        assertFalse(dtoAsString.contains("Error"));
        assertTrue(dtoAsString.contains("5.6"));
        assertEquals(dtoAsString.toString(),
                "ResultDto(result=5.6, success=true)");
    }
    
    @Test
    public void equalsOK() {
        
    	ResultDto expected = new ResultDto();
    	expected.setResult("23");
    	expected.setSuccess(true);
    	ResultDto result = new ResultDto("23", true);
        
        assertEquals(expected, result);
    }
}
