package DynamicProgramming;

/**
 * 303. 区域和检索 - 数组不可变 Easy
 * https://leetcode-cn.com/problems/range-sum-query-immutable/
 *
 * 简单的DP算法，但是这题值得注意的是 微不足道的计算有时候可以节省大量空间
 */
public class RangeSumQuery {
    // 使用二维dp有点奢侈了，可以压缩的
//    private int[][] dp;
//    public RangeSumQuery(int[] nums) {
//        int n = nums.length;
//        dp = new int[n][n];
//        for (int i = 0; i < n; i++) {
//            dp[i][i] = nums[i];
//        }
//        for (int i = 0; i < n; i++) {
//            for (int j = i+1; j < n; j++) {
//                dp[i][j] = dp[i][j-1] + nums[j];
//            }
//        }
//    }
//
//    public int sumRange(int i, int j) {
//        return dp[i][j];
//    }

    // 一维dp，每个维度保存0-i的元素，然后i-j = 0-j - 0-i
    private int[] dp;
    private int[] nums;
    public RangeSumQuery(int[] nums) {
        this.nums = nums;
        int n = nums.length;
        dp = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            dp[i] = sum;
        }
    }

    public int sumRange(int i, int j) {
        return dp[j] - dp[i] + nums[i];
    }

}
