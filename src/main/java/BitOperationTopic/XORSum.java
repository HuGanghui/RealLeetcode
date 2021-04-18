package BitOperationTopic;

/**
 * 5737. 所有数对按位与结果的异或和 Hard
 * https://leetcode-cn.com/problems/find-xor-sum-of-all-pairs-bitwise-and/
 * LeetcodeWeeklyMatch 238
 *
 * 本题利用位运算的两个性质：1. 所有运算都是位运算的，每位独立，可以单独考虑；2. 连续按位与
 * 和连续按位异或的性质，都注意和1相关。
 * 这题值得注意的是：1. 一个是cnt1 cnt2如果相乘可能溢出；2. 题目给的都是非负数，所以考虑31位，如果
 * 涉及符号位，应该是更复杂的吧。。。
 */
public class XORSum {
    public int getXORSum(int[] arr1, int[] arr2) {
        int ans = 0;
        for (int k = 0; k < 30; k++) {
            int cnt1 = 0;
            for (int num : arr1) {
                if ((num & (1 << k)) > 0) {
                    cnt1++;
                }
            }

            int cnt2 = 0;
            for (int num : arr2) {
                if ((num & (1 << k)) > 0) {
                    cnt2++;
                }
            }

            if (cnt1 % 2 == 1 && cnt2 % 2 == 1) {
                ans |= (1 << k);
            }
        }
        return ans;
    }
}
