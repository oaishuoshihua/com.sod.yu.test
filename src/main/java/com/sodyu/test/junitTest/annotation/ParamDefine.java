package com.sodyu.test.junitTest.annotation;

/**
 * Created on 2018/7/3
 **/

import com.sodyu.test.junitTest.suppliers.ParamSupplier;
import org.junit.experimental.theories.ParametersSuppliedBy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
// 声明注解接口所使用的委托处理类
 @ParametersSuppliedBy(ParamSupplier.class)
public @interface ParamDefine {
   int max() default 10;
}
