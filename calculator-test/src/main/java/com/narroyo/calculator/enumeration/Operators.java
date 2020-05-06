package com.narroyo.calculator.enumeration;

/***
 * Enum to indicate type of math operations<br>
 * <strong>A</strong> for addition<br>
 * <strong>S</strong> for subtraction
 */
public enum Operators {

    ADDITION("A"),
    SUBTRACTION("S");

    private final String code;

    private Operators(String code) {
      this.code = code;
    }

    public final String getCode() {
      return code;
    }

}
