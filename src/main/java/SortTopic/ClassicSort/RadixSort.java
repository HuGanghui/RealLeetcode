package SortTopic.ClassicSort;

import java.util.Arrays;

/**
 * 基数排序
 *
 * 时间复杂度是O(n*k) 空间复杂度O(n+k)
 *
 * 这里的k指的是n个数字的最大位数
 *
 * 基数排序是一种非比较型整数排序算法，其原理是将整数按位数切割成不同的数字，然后按每个位数分别比较。
 * 由于整数也可以表达字符串（比如名字或日期）和特定格式的浮点数，所以基数排序也不是只能使用于整数。
 *
 *
 */
public class RadixSort implements ArraySort {

    @Override
    public void sort(int[] array) {
        int maxDigit = getMaxDigit(array);
        radixSort(array, maxDigit);
    }

    private int[] radixSort(int[] arr, int maxDigit) {
        int mod = 10;
        int dev = 1;

        for (int i = 0; i < maxDigit; i++, dev *= 10, mod *= 10) {
            // 考虑负数的情况，这里扩展一倍队列数，其中 [0-9]对应负数，[10-19]对应正数 (bucket + 10)
            int[][] counter = new int[mod * 2][0];

            for (int j = 0; j < arr.length; j++) {
                // 先求余，去除多余高位，然后除以去除多余低位
                int bucket = ((arr[j] % mod) / dev) + mod;
                counter[bucket] = arrayAppend(counter[bucket], arr[j]);
            }

            int pos = 0;
            for (int[] bucket : counter) {
                for (int value : bucket) {
                    arr[pos++] = value;
                }
            }
        }

        return arr;
    }

    /**
     * 自动扩容，并保存数据
     */
    private int[] arrayAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }

    /**
     * 获取最高位数
     */
    private int getMaxDigit(int[] arr) {
        int maxValue = arr[0];
        for (int value : arr) {
            maxValue = Math.max(maxValue, value);
        }

        if (maxValue == 0) {
            return 1;
        }

        int length = 0;
        while (maxValue != 0) {
            length++;
            maxValue /= 10;
        }

        return length;
    }
}
