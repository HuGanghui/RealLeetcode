package BitOperationTopic;

/**
 * 剑指 Offer 56 - II. 数组中数字出现的次数 II Median
 * https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/
 *
 * 这题变了一些，变成只有一个数字出现一次，其它都出现三次了，其实本质上还是去找位运算有啥规律，
 * 这题的每一位求和再除以3，就可以获得只出现一次那个，就通过这个方式去还原得到答案即可。
 */
public class SingleNumberII {
    public int singleNumber(int[] nums) {
        int n = nums.length;
        int[] counts = new int[32];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 32; j++) {
                counts[j] += (nums[i] & 1);
                nums[i] >>>= 1;
            }
        }

        for (int i = 0; i < 32; i++) {
            counts[i] %= 3;
        }

        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            result |= counts[31 - i];
        }
        return result;
    }
}
