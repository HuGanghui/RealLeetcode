package DynamicProgramming;

/**
 * 801. 使序列递增的最小交换次数 Median
 *
 * https://leetcode-cn.com/problems/minimum-swaps-to-make-sequences-increasing/
 *
 * 首先我们明确，要最终递增，只需要每次保证 i-1 和 i 的元素满足递增就行（限制条件1），但是这题还需要考虑交换的问题，
 * 因为两个数组等长并且都是相同下标比较，所以只需要一个状态来表示下标，但是这样还有一个状态就是是否在该下标
 * 进行了交换，并且这个状态是会影响能够进行的选择的。然后选择的时候还有满足限制条件1，这样状态、限制条件下的选择
 * 就都明确了。
 */
public class MinSwap {
    public int minSwap(int[] A, int[] B) {
        int n = A.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = 1;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Integer.MAX_VALUE;
            dp[i][1] = Integer.MAX_VALUE;
            if (A[i-1] < A[i] && B[i-1] < B[i]) {
                dp[i][0] = dp[i-1][0];
                dp[i][1] = dp[i-1][1] + 1;
            }
            if (A[i-1] < B[i] && B[i-1] < A[i]) {
                dp[i][0] = Math.min(dp[i-1][1], dp[i][0]);
                dp[i][1] = Math.min(dp[i-1][0] + 1, dp[i][1]);
            }
        }
        return Math.min(dp[n-1][0], dp[n-1][1]);
    }
}
