package com.zi.dian.unitl;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

/**
 * 快速排序
 * <p/>
 * 1.找基准数
 * <p/>
 * 2.声明两个变量i= 0 , j = N - 1
 * <p/>
 * 3.
 */
public class QuickSort {
    private static String TAG = "QuickSort";

    /**
     * 快速排序，由小到大
     */
    public static List<String> sortBy(String[] list) {
        Log.d(TAG, "排序前数组：" + Arrays.asList(list));
        sortBy(list, 0, list.length - 1);
        return Arrays.asList(list);
    }

    private static void sortBy(String[] list, int low, int high) {
        if (low >= high) {
            return;
        }
        String baseElement = list[low];//基数元素
        int left = low, right = high;
        //基数元素左边元素不大于基数，基数右边元素不小于基数
        Log.d(TAG, "基数元素：" + baseElement + " left = " + left + " right = " + right);
        while (left < right) {
            //从后向前扫描，找到小于基数的元素
            while (right > left && !compareTheSize(list[right], baseElement)) {
                right--;
            }
            if (right > left) {
                Log.d(TAG, "right元素：" + list[right] + " < " + baseElement);
                list[left++] = list[right];
            } else {
                Log.d(TAG, "right元素没有大于基数元素");
            }
            //从前向后扫描，找到大于基数的元素
            while (left < right && !compareTheSize2(list[left], baseElement)) {
                left++;
            }
            if (left < right) {
                Log.d(TAG, "left元素：" + list[left] + " > " + baseElement);
                list[right--] = list[left];
            } else {
                Log.d(TAG, "left元素没有小于基数元素");
            }
        }
        list[left] = baseElement;
        sortBy(list, low, left - 1);
        sortBy(list, left + 1, high);
    }

    /**
     * 比较字符串的大小
     *
     * @param paramString1
     * @param paramString2
     * @return true paramString1 < paramString2；
     * <p/>
     * false  paramString1 >= paramString2
     */
    private static boolean compareTheSize(String paramString1, String paramString2) {
        boolean flag = false;
        char[] str1Chars = paramString1.toCharArray();
        char[] str2Chars = paramString2.toCharArray();
        int num = str1Chars.length < str2Chars.length ? str1Chars.length : str2Chars.length;
        for (int i = 0; i < num; i++) {
            if (str1Chars[i] > str2Chars[i]) {
                break;
            } else if (str1Chars[i] < str2Chars[i]) {
                flag = true;
                break;
            } else if (i == num - 1) {
                flag = str1Chars.length < str2Chars.length;
            }
        }
        return flag;
    }

    /**
     * 比较字符串的大小
     *
     * @param paramString1
     * @param paramString2
     * @return true paramString1大于paramString2； false  paramString1不大于paramString2
     */
    private static boolean compareTheSize2(String paramString1, String paramString2) {
        boolean flag = false;
        char[] str1Chars = paramString1.toCharArray();
        char[] str2Chars = paramString2.toCharArray();
        int num = str1Chars.length < str2Chars.length ? str1Chars.length : str2Chars.length;
        for (int i = 0; i < num; i++) {
            if (str1Chars[i] > str2Chars[i]) {
                flag = true;
                break;
            } else if (str1Chars[i] < str2Chars[i]) {
                break;
            } else if (i == num - 1) {
                flag = str1Chars.length > str2Chars.length;
            }
        }
        return flag;
    }


    /**
     * 快速排序，由小到大
     *
     * @param ints
     */
    public static void sortQuick(Integer[] ints) {
        sortQuick(ints, 0, 0, ints.length - 1);
    }

    /**
     * 快速排序辅助方法
     * <p/>
     * 一趟快速排序方法
     */
    private static void sortQuick(Integer[] ints, int keyIndex, int i, int j) {
        while (j >= i) {// 从右边依次查找小于基准数的元素
            int key = ints[keyIndex];
            if (ints[j] < key) {// 找到小于基准数的元素
                while (i <= j) {// 从左边依次查找大于基准数的元素
                    if (ints[i] > key) {// 找到大于基准数的元素
                        // i j交换元素
                        swap(ints, i, j);
                        sortQuick(ints, keyIndex, i, j);
                    } else if (i == j) {// 不存在大于基准数的元素
                        // i与基准数交换
                        swap(ints, i, keyIndex);
                        // 左右子序列递归排序
                        // 基准数左边递归调用
                        sortQuick(ints, keyIndex, keyIndex, i - 1);
                        // 基准数右边递归调用
                        sortQuick(ints, i + 1, i + 1, ints.length - 1);
                    }
                    i++;
                }
            } else if (j == i) {// 基准数右边元素全部不小于基准数
                sortQuick(ints, keyIndex + 1, keyIndex + 1, ints.length - 1);
            }
            j--;
        }

    }

    private static void swap(Integer[] ints, int index1, int index2) {
        int temp = ints[index1];
        ints[index1] = ints[index2];
        ints[index2] = temp;
    }
}
