package BitOperationTopic;

/**
 * 剑指 Offer 56 - I. 数组中数字出现的次数 Median
 * https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/
 *
 * 这题有两个数字只出现一次，而不是一个数字，因此直接全量异或是不ok的，题解提到的方法是，分组异或，如果我们能够
 * 将两个出现一次的数字分到两个组，同时其它出现两次的相同的数字也分到一个组，那不就分组异或后的结果就是
 * 答案嘛，那如何做到这一点呢？
 * 通过全量异或，得到的是`x=a^b`的结果，那我们找到`x`中最低位的1不就代表这两个数字不同嘛，然后根据这一位的1来进行
 * 分组的话，两个数字肯定可以被分到不同的组，同时相同的数字该位必然相同，因此也被分到同一组，所以这个方法OK。
 */
public class SingleNumbers {
    public int[] singleNumbers(int[] nums) {
        int x = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            x ^= nums[i];
        }
        int bit = 1;
        while ((x & bit) == 0) {
            bit <<= 1;
        }
        int a = 0;
        int b = 0;
        for (int i = 0; i < n; i++) {
            if ((nums[i] & bit) == 0) {
                a ^= nums[i];
            } else {
                b ^= nums[i];
            }
        }
        return new int[]{a, b};
    }
}
