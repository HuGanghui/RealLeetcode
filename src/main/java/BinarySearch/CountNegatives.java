package BinarySearch;

/**
 * 1351. 统计有序矩阵中的负数 Easy
 * https://leetcode-cn.com/problems/count-negative-numbers-in-a-sorted-matrix/
 *
 * 利用了有序的特点，也是可以使用二分的典型特点，找的第一个负数，就可以统计数量，
 * 官方题解提供了其它更好的解法，可以参考。
 *
 * 同时本题参考了https://github.com/halfrost/LeetCode-Go 中对二分查找的总结，蛮不错的：
 * 二分搜索的变种写法。有 4 个基本变种:
 * 查找第一个与 target 相等的元素，时间复杂度 O(logn)
 * 查找最后一个与 target 相等的元素，时间复杂度 O(logn)
 * 查找第一个大于等于 target 的元素，时间复杂度 O(logn)
 * 查找最后一个小于等于 target 的元素，时间复杂度 O(logn)
 */
public class CountNegatives {
    public int countNegatives(int[][] grid) {
        int result = 0;
        for (int[] array : grid) {
            result += binarySearch(array);
        }
        return result;
    }

    private int binarySearch(int[] array) {
        int n = array.length;
        int left = 0;
        int right = n -1;
        int sum = 0;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (array[middle] >= 0) {
                left = middle + 1;
            } else if (array[middle] < 0) {
                if (middle == 0 || array[middle -1] >=0) {
                    sum = n - middle;
                    break;
                }
                right = middle - 1;
            }
        }
        return sum;
    }
}
