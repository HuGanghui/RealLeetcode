package DoublePointerTopic.FastSlowPointer;

/**
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面 Easy
 * https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
 *
 * 这题利用快慢指针来进行原地交互是最优解，这里的技巧其实在快速排序中也有体现，
 * 不过那个以某个值为区分，前半部分都是小于某个值，后半部分都是大于某个值，和这里
 * 的奇偶性其实是相似的。
 */
public class ExchangeOddAndEven {
    public int[] exchange(int[] nums) {
        int slow = 0;
        int fast = 0;
        int n = nums.length;
        while (fast < n) {
            if (nums[fast] % 2 != 0) {
                int temp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = temp;
                slow++;
            }
            fast++;
        }
        return nums;
    }
}
