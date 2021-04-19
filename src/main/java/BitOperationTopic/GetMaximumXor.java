package BitOperationTopic;

/**
 * 1829. 每个查询的最大异或值 Median
 * https://leetcode-cn.com/problems/maximum-xor-for-each-query/
 *
 * 这题第一个技巧很明显就是前缀和，这是可以利用的，然后另外一个点就是如何直接
 * 获得k值，使得结果最大，这里使用了一个知识就是题目说所有元素都小于等于2^maxbit - 1，
 * 那么所有前缀和和k（k<=2^maxbit - 1)的异或结果也是小于等于2^maxbit - 1，那xorsum
 * ^ k 怎么就争取最大呢，和自身异或等于0，0和任意数异或等于其数，所有令 k = xorsum ^ (2^maxbit - 1)
 * 不就行了吗。
 */
public class GetMaximumXor {
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int n = nums.length;
        int[] preXorArray = new int[n];
        preXorArray[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preXorArray[i] = preXorArray[i-1] ^ nums[i];
        }
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = (preXorArray[n - 1 - i] ^ (int)(Math.pow(2, maximumBit) - 1));
        }
        return result;
    }
}
