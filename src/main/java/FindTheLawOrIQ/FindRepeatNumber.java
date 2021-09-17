package FindTheLawOrIQ;

/**
 * 剑指 Offer 03. 数组中重复的数字 Easy
 * https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 *
 * 简单做法就是用一个HashSet存储即可，但是如果需要O(1)空间复杂度，就需要利用下标进行交换，这样当遇到两个数相同时，就
 * 找到重复的`nums[nums[i]] == nums[i]`，否则就需要进行交换。
 */
public class FindRepeatNumber {
    public int findRepeatNumber(int[] nums) {
        int i = 0;
        int res = -1;
        while (i < nums.length) {
            if (nums[i] == i) {
                i++;
                continue;
            }
            // 遇到重复元素，返回
            if (nums[nums[i]] == nums[i]) {
                res = nums[i];
                break;
            }
            // 交换
            int tmp1 = nums[i];
            int tmp2 = nums[nums[i]];
            nums[i] = tmp2;
            nums[tmp1] = tmp1;
        }
        return res;
    }
}
