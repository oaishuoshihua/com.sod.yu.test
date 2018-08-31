package com.sodyu.test.algorithm.sort;

import java.util.Arrays;

/**
 * Created on 2018/4/21.
 */
public class Sort {
    /**
     * 插入排序
     *
     * @param a
     * @return
     */
    public static int[] insertSort(int[] a) {
        if (a == null || a.length == 0) {
            return a;
        }
        for (int i = 1; i < a.length; i++) {
            int j = i;
            int temp = a[i];
            while (j > 0 && temp < a[j - 1]) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = temp;
        }
        return a;
    }

    //二分查找（1）
    public static int[] binarySort1(int[] a) {
        if (a == null || a.length == 0) {
            return a;
        }
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            int start = 0;
            int end = i - 1;
            int middle = (start + end) / 2;
            while (start >= 0 && start <= end) {
                if (a[middle] > temp) {
                    end = middle - 1;
                }
                if (a[middle] < temp) {
                    start = middle + 1;
                }
                middle = (start + end) / 2;
            }
            if (a[middle] > temp) {
                middle += -1;
            }

            for (int j = i - 1; j > middle; j--) {
                a[j + 1] = a[j];
            }
            a[middle + 1] = temp;
        }
        return a;
    }

    //二分插入（与上面只是临界值有区别））
    public static int[] binarySort2(int[] a) {
        if (a == null || a.length == 0) {
            return a;
        }
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            int start = 0;
            int end = i - 1;
            int middle;
            while (start >= 0 && start <= end) {
                middle = (start + end) / 2;
                if (a[middle] > temp) {
                    end = middle - 1;
                } else {
                    start = middle + 1;
                }

            }
            for (int j = i - 1; j >= start; j--) {
                a[j + 1] = a[j];
            }
            a[start] = temp;
        }
        return a;
    }


    //冒泡排序
    public static int[] bubbleSort(int[] a) {
        if (a == null || a.length == 0) {
            return a;
        }
        for (int i = 1; i < a.length; i++) {
            boolean flag = false;

            for (int j = a.length - i - 1; j >= 0; j--) {
                int temp;
                if (a[j + 1] < a[j]) {
                    temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                System.out.println("已经排好序" + i);
                break;
            }
        }

        return a;
    }

    //快速排序
    public static int[] quickSort(int[] a, int low, int high) {
        if (a == null || a.length == 0 || low > high) {
            return a;
        }
        int i = low;
        int j = high;
        int temp = a[low];
        while (i < j) {
            while (a[j] > temp && i < j) {
                j--;
            }
            if (i < j) {
                a[i++] = a[j];
            }

            while (a[i] < temp && i < j) {
                i++;
            }
            if (i < j) {
                a[j--] = a[i];
            }
        }
        a[i] = temp;
        quickSort(a, low, i - 1);
        quickSort(a, j + 1, high);
        return a;
    }


    //堆排序
    public static int[] heapSort(int a[]) {
        if (a == null || a.length <= 0) {
            return a;
        }
        for (int k = a.length / 2 - 1; k >= 0; k--) //创建最大堆
        {
            heap(a, k, a.length);
        }
        for (int i = a.length; i > 1; i--)//每次调整堆
        {
            int temp = a[i - 1];
            a[i - 1] = a[0];
            a[0] = temp;
            heap(a, 0, i - 1);
        }
        return a;
    }

    private static void heap(int a[], int position, int size) {
        while (position < a.length) {
            int j = 2 * position + 1;
            if (j < size) {
                if (j + 1 < size && a[j] < a[j + 1]) {
                    j = j + 1;
                }
                if (a[position] < a[j]) {
                    int temp = a[position];
                    a[position] = a[j];
                    a[j] = temp;
                }
            }
            position = j;
        }
    }


    private static void merge(int[] a, int start, int middle, int end, int[] temp) {
        if (a == null || a.length <= 0 || start < 0 || end < 0 || start > middle || middle > end) {
            return ;
        }
        int i = start;
        int j = middle + 1;
        int k = 0;
        while (i <= middle && j <= end) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        if (i <= middle) {
            for (int l = i; l <= middle; l++) {
                temp[k++] = a[l];
            }

        }

        if (j <= end) {
            for (int l = j; l <= end; l++) {
                temp[k++] = a[l];
            }
        }

        for (int l = 0; l < k ; l++) {
            a[start + l] = temp[l];
        }
    }

    private static int[] secondMergeSort(int[] a, int start, int end, int[] temp) {
        if (a == null || a.length <= 0 || start > end || start < 0 || end < 0) {
            return a;
        }
        if (start < end) {
            int middle = (start + end) / 2;
            secondMergeSort(a, start, middle, temp);
            secondMergeSort(a, middle + 1, end, temp);
            merge(a, start, middle, end, temp);
        }
        return a;
    }

    public static void main(String[] args) {
        int[] datas = {6, 9, 14, 5, 7, 8, 24, 16, 87};
//        int[] result = insertSort(datas);//直接插入排序
//        int[] result = binarySort2(datas);//二分插入排序
//        int[] result = bubbleSort(datas);//冒泡排序
//        int[] result = quickSort(datas, 0, datas.length - 1);//快速排序
//        int[] result = heapSort(datas);//堆排序
          int[] temp = new int[datas.length];
          int[] result = secondMergeSort(datas, 0, datas.length-1, temp);
        System.out.println(Arrays.toString(result));

    }
}
