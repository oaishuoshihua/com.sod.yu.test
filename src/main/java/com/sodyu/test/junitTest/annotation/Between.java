package com.sodyu.test.junitTest.annotation;

import com.sodyu.test.junitTest.suppliers.BetweenSupplier;
import org.junit.experimental.theories.ParametersSuppliedBy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created on 2018/7/3
 **/
@Retention(RetentionPolicy.RUNTIME)
@ParametersSuppliedBy(BetweenSupplier.class)
public @interface Between {
    int start() default  0;
    int end();
}
