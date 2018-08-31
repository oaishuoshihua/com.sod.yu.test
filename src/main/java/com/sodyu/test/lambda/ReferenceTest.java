package com.sodyu.test.lambda;

/**
 * Created   on 2018/3/4.
 */
public class ReferenceTest {

    public static void main(String[] args) {
//        Converter<String, Integer> converter = new Converter<String, Integer>() {
//            @Override
//            public Integer convert(String from) {
//                return ReferenceTest.String2Int(from);
//            }
//        };
//        converter.convert("120");

        Converter<String, Integer> converter = ReferenceTest::String2Int;
        System.out.println(converter.convert("120"));

    }


    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }

    static int String2Int(String from) {
        return Integer.valueOf(from);
    }
}
