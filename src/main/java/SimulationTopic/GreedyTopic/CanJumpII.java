package SimulationTopic.GreedyTopic;

/**
 * 45. 跳跃游戏 II Median
 * https://leetcode-cn.com/problems/jump-game-ii/
 *
 * 与跳跃游戏一样，贪心同样是最优解法，时间复杂度O(n),
 * 通过维护一个最右边界，以及目前可达的最远距离，每次选择可选的最远距离，
 * 也就是当i == end的时候，去更新一下最远距离，以及步数。
 *
 * 值得注意的是，i < n-1，因为当i == n-1 就无需再更新了。
 *
 * 同样DP可以，最差时间复杂度O(n^2)，不过需要遍历顺序需要倒着。
 */
public class CanJumpII {
    public int jump(int[] nums) {
        int n = nums.length;
        int end = 0;
        int rightMost = 0;
        int steps = 0;
        for (int i = 0; i < n - 1; i++) {
            rightMost = Math.max(rightMost, i + nums[i]);
            if (i == end) {
                end = rightMost;
                steps++;
            }
        }
        return steps;
    }

    public int jumpDP(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[n-1] = 0;
        for (int i = n-2; i >= 0; i--) {
            dp[i] = n;
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j < n) {
                    dp[i] = Math.min(dp[i+j] + 1, dp[i]);
                }
            }
        }
        return dp[0];
    }
}
