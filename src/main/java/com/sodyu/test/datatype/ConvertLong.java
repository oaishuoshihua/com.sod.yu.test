package com.sodyu.test.datatype;

/**
 * ClassName：
 * @Date 2017/1/10 10:52
 * @since JRE 1.6.0_22  or higher
 */
public class ConvertLong {
    public static void main(String[] args) {
        String value="445456b3b8ca4d71";
        long result = Long.valueOf(value,16);
        System.out.println(result);
    }
}
