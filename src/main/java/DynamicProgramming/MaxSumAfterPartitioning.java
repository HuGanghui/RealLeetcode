package DynamicProgramming;

import java.util.Arrays;

/**
 * 1043. 分隔数组以得到最大和 Median
 * https://leetcode-cn.com/problems/partition-array-for-maximum-sum/
 *
 * 这题和983. 最低票价是一样的思路，通过倒着DP（要记住这个思路），从头开始，思路上更加直观，也更方便处理
 * base和边界情况，dp[0]代表了0-最后获取的分割数组的最大值，因此递推关心就是k种情况选
 * 最大，然后大于arr.length的base就是0，因为都没有了，并且求和也不影响结果。然后倒着DP
 * 貌似自顶向下+memo的方法更好写。这题题解有自底向上的，但是我没整明白。。。回头再看。
 */
public class MaxSumAfterPartitioning {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int[] memo = new int[arr.length];
        Arrays.fill(memo, -1);
        return dfs(0, memo, arr, k);
    }

    private int dfs(int startIndex, int[] memo, int[] arr, int k) {

        if (startIndex >= arr.length) {
            return 0;
        }

        if (memo[startIndex] != -1) {
            return memo[startIndex];
        }

        int sum = 0;
        for (int i= startIndex; i< startIndex + k; i++) {
            if (i >= arr.length) {
                continue;
            }
            sum = Math.max(localMaxSum(startIndex, i, arr) + dfs(i+1, memo, arr, k), sum);
        }

        memo[startIndex] = sum;
        return sum;
    }

    private int localMaxSum(int start, int end, int[] arr) {
        int max = 0;
        for (int i = start; i <=end; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max * (end - start + 1);
    }
}
