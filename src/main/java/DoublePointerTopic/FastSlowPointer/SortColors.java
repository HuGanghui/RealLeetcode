package DoublePointerTopic.FastSlowPointer;

/**
 * 75. 颜色分类 Median
 * https://leetcode-cn.com/problems/sort-colors/
 *
 * 快慢指针 原地排序
 */
public class SortColors {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int prefix = 0;
        int index = 0;
        while (index < n) {
            if (nums[index] == 0) {
                swap(nums, prefix, index);
                prefix++;
            }
            index++;
        }

        int suffix = n - 1;
        index = n - 1;
        while (index >= prefix) {
            if (nums[index] == 2) {
                swap(nums, suffix, index);
                suffix--;
            }
            index--;
        }
    }

    private void swap(int[] nums, int i , int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
