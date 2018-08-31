package com.sod.yu.unittest;

import com.sod.yu.test.junitTest.annotation.Between;
import com.sod.yu.test.junitTest.annotation.ParamDefine;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertTrue;

/**
 * Created  on 2018/7/3
 **/
@RunWith(Theories.class)
public class TestOne {
    @Theory
    public final void test(@TestedOn(ints = { 0, 1, 2 }) int i) {
        assertTrue(i >= 0);
        System.out.println(i);
    }

    @Theory
    public final void max(@ParamDefine(max = 3) int i) {
        assertTrue(i <= 20);
        System.out.println(i);
    }


    @Theory
    public final void between(@Between(start = -2, end = -1) int i) {
        assertTrue(i <= 0);
        System.out.println(i);
    }
}
