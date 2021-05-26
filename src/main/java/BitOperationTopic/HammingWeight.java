package BitOperationTopic;

/**
 * 剑指 Offer 15. 二进制中1的个数 Easy
 * https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/
 *
 * 最直观到思想就是每位依次判断即可。
 */
public class HammingWeight {
    public int hammingWeight(int n) {
        int bit = 1;
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & bit) != 0) {
                result++;
            }
            bit <<= 1;
        }
        return result;
    }

    // 相反的让n变化，这样当n为0就不用比较了，稍微快些
    public int hammingWeightII(int n) {
        int res = 0;
        while(n != 0) {
            res += n & 1;
            // 无符号右移
            n >>>= 1;
        }
        return res;
    }

    // 方法三 n&(n-1) 每次将最右边的1变为0
    public int hammingWeightIII(int n) {
        int res = 0;
        while(n != 0) {
            res++;
            n &= n - 1;
        }
        return res;
    }
}
