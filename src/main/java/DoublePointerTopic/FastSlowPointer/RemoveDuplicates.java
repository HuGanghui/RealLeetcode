package DoublePointerTopic.FastSlowPointer;

/**
 * 26. 删除有序数组中的重复项 Easy
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 *
 * slow-fast，fast探路来遍历整个数组，发现不重复的元素就让slow走一步，这样最后slow就是
 * 指向不重复数组的结尾。
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int slow = 0; int fast = 0;
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }
}
