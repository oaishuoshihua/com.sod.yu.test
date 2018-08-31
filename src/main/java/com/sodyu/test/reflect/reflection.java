package com.sodyu.test.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * ClassName：
 * @Date 2017/1/11 17:34
 * @since JRE 1.6.0_22  or higher
 */
public class reflection {
    //定义输出的格式。
    private static final String  fmt = "%24s: %s%n";

    // for the morbidly curious
    <E extends RuntimeException> void genericThrow() throws E {}

    public static void main(String... args) {

        try {
            Class<?> c = null;
            //根据传入的参数加载相应的类
            c = Class.forName("com.sodyu.test.other.test");
            Method[] allMethods = c.getDeclaredMethods();
            //遍历出加载的类的所有方法。
            for (Method m : allMethods) {

                //方法名与输入的参数一致，输出方法的相关信息。
                System.out.format("%s%n", m.toGenericString());//格式化成字符串输出方法相关信息
                System.out.format(fmt, "ReturnType", m.getReturnType());//返回的类型
                System.out.format(fmt, "GenericReturnType", m.getGenericReturnType());//原始的返回类型。

                //获取方法的所有的参数的类型。
                Class<?>[] pType  = m.getParameterTypes();
                //获取方法的所有的参数的原始类型。
                Type[] gpType = m.getGenericParameterTypes();
                for (int i = 0; i < pType.length; i++) {
                    System.out.format(fmt,"ParameterType", pType[i]);
                    System.out.format(fmt,"GenericParameterType", gpType[i]);
                }
                //获取方法的异常类型。
                Class<?>[] xType  = m.getExceptionTypes();
                //获取方法原始的异常类型。
                Type[] gxType = m.getGenericExceptionTypes();
                //遍历出所有的异常类型
                for (int i = 0; i < xType.length; i++) {
                    System.out.format(fmt,"ExceptionType", xType[i]);
                    System.out.format(fmt,"GenericExceptionType", gxType[i]);
                }
            }

            // production code should handle these exceptions more gracefully
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }
}
