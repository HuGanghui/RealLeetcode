package BinarySearch.RotatedSortedArray;

/**
 * 153. 寻找旋转排序数组中的最小值 Median
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 *
 * 相同题目：
 * 剑指 Offer 11. 旋转数组的最小数字 Easy
 * https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 *
 * 154. 寻找旋转排序数组中的最小值 II Hard
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
 *
 * 这道题目的核心在于如何根据旋转后的数组的性质，使用二分查找，题解提供了一个非常直观的解释，
 * 通过比较mid 和 right 的元素大小分为三种情况。
 *
 * 这题相比之前总结的二分查找的模板，有一定的出入，因此值得总结。
 */
public class FindMinInRotatedSortedArray {

    public int findMin(int[] numbers) {
        return binarySearch(numbers);
    }

    private int binarySearch(int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 小于 说明是右边是递增的，因此在左边找，并且mid可能是最小值，不能跳过
            if (array[mid] < array[right]) {
                right = mid;
            // 大于 说明左边是整体大的那部分，因此最小值在右边，且mid不可能是最小值
            } else if (array[mid] > array[right]) {
                left = mid + 1;
            /** 等于， 有一种情况就是类似凹字，这种：那这种情况下，我们只能排除right，因为即便它是，也有mid可以代替
            *    * * mid       * right *
            *            min *
            */
            } else {
                right = right - 1;
            }
        }
        // 最后left==right相等的情况，right会-1，因此left会是最后的答案
        return array[left];
    }
}
