package com.sodyu.test.junitTest.suppliers;

import com.sodyu.test.junitTest.annotation.Between;
import org.junit.experimental.theories.ParameterSignature;
import org.junit.experimental.theories.ParameterSupplier;
import org.junit.experimental.theories.PotentialAssignment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created  on 2018/7/3
 **/
public class BetweenSupplier extends ParameterSupplier {
    public List<PotentialAssignment> getValueSources(ParameterSignature parameterSignature) throws Throwable {
        // 自定义实参值列表
        List<PotentialAssignment> list = new ArrayList<PotentialAssignment>();

        // 获取注解变量
        Between between = parameterSignature.getAnnotation(Between.class);
        int start = between.start();
        int end = between.end();
        for(int i = start; i < end; i++){
            list.add(PotentialAssignment.forValue("int",i));
        }
        return list;
    }
}
