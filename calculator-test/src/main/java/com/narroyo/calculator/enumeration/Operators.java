package com.narroyo.calculator.enumeration;

import java.util.HashMap;
import java.util.Map;
/***
 * Enum to indicate de type of math operations<br>
 * <strong>A</strong> for addition<br>
 * <strong>B</strong> for subtraction
 */
public enum Operators {

    ADDITION("A"),
    SUBTRACTION("S");

    private String code;
    private static Map<String, Operators> map = new HashMap<>();

    private Operators(String code) {
      this.code = code;
    }

    static {
      for (Operators item : Operators.values()) {
        map.put(item.code, item);
      }
    }

    public static Operators codeOf(String item) {
      return map.get(item);
    }

    public String getCode() {
      return code;
    }

    public static int getEnumSize() {
      return map.size();
    }
  
}
