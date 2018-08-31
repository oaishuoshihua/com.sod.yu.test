package com.sod.yu.unittest.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created by yuhp on 2018/7/13
 **/
public class OperaterString {

    /**
     * 将每个字符记录其位置，放到map中
     * abdadc  ————————》  a 作为key 存储 其位置： 0,3,
     *                     b 作为key 存储 其位置： 1
     *                     d 作为key 存储 其位置： 2,4
     *                     c 作为key 存储 其位置： 5
     * 比较两个字符串生成的map 中每个字符中对应的位置是否相同
     * @param str1
     * @param str2
     * @return
     */
    public static  boolean judgeStr(String str1, String str2){
        Map<String,List<Integer>> hashMapOne = Maps.newHashMap();
        Map<String,List<Integer>> hashMapTwo = Maps.newHashMap();
        boolean flag = true;
        int size = str1.length();
        for (int i = 0; i < size; i++){
            String s1 = String.valueOf(str1.charAt(i));
            String s2 = String.valueOf(str2.charAt(i));
            List<Integer> positionListOne = hashMapOne.get(s1);
            List<Integer> positionListTwo = hashMapTwo.get(s2);
            if(positionListOne == null){
                positionListOne = Lists.newArrayList();
            }
            if(positionListTwo == null){
                positionListTwo = Lists.newArrayList();
            }
            positionListOne.add(i);
            positionListTwo.add(i);
            hashMapOne.put(s1,positionListOne);
            hashMapTwo.put(s2,positionListTwo);
        }

        for (int i = 0; i < size; i++){
            String s1 = String.valueOf(str1.charAt(i));
            String s2 = String.valueOf(str2.charAt(i));
            List<Integer> positionListOne = hashMapOne.get(s1);
            List<Integer> positionListTwo = hashMapTwo.get(s2);
            if(positionListOne.size() != positionListTwo.size()){
                flag = false;
                return flag;
            }
            positionListOne.stream().forEach(p->{
                if(positionListTwo.contains(p)){
                    positionListTwo.remove(p);
                }
            });

            if(positionListTwo.size() > 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        String str1 = "aba";
        String str2 = "bad";
        boolean result = judgeStr(str1, str2);
        System.out.println(result);
    }


    /**
     * 方法二， 思想：将每个字符对应一个数字
     *
     * ahdetdd  ---》 0123422
     * aba ---> 010
     * dcd ---> 010  所以aba与dcd为同类型字符串
     * @param str
     * @return
     */
    private static String findMark(String str) {
        StringBuilder mark = new StringBuilder();
        Map<Character, Integer> sMap = Maps.newHashMap();
        int c = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(sMap.containsKey(ch)) {
                mark.append(sMap.get(ch));
            } else {
                sMap.put(ch, c);
                mark.append(c);
                c++;
            }
        }
        return mark.toString();
    }


    public static boolean isSameType(String a, String b) {
        if(a.length() != b.length()) {
            return false;
        }
        String aMark = findMark(a);
        String bMark = findMark(b);
        return aMark.equals(bMark);
    }


}
