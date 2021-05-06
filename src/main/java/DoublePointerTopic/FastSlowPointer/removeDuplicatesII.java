package DoublePointerTopic.FastSlowPointer;

/**
 * 80. 删除有序数组中的重复项 II Median
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/
 *
 * 这题要求最多保留2两位，其实有个通解，保留k位，那就前k位其实都可以保留，然后比较的是slow-k和fast
 */
public class removeDuplicatesII {
    public int removeDuplicates(int[] nums) {
        return remove(nums, 2);
    }

    private int remove(int[] nums, int k) {
        int n = nums.length;
        if (n <= k) {
            return n;
        }
        int slow = k, fast = k;
        while (fast < n) {
            if (nums[slow - k] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }
}
