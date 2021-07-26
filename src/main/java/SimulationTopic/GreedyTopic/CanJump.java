package SimulationTopic.GreedyTopic;

/**
 * 55. 跳跃游戏 Median
 * https://leetcode-cn.com/problems/jump-game/
 *
 * 贪心是最优的做法，持续维护一个目前可达的最远距离，如果可达距离
 * 达到了n-1就发挥true，否则返回false。时间复杂度O(n)
 *
 * 当然也可以用DP的思想，但是最差的时间复杂度可能会达到O(n^2)，
 * 不过需要遍历顺序需要倒着。
 */
public class CanJump {
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int rightMost = 0;
        for (int i = 0; i < n; i++) {
            if (i <= rightMost) {
                rightMost = Math.max(rightMost, i + nums[i]);
                if (rightMost >= n-1) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canJumpDP(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[n-1] = true;
        for (int i = n - 2; i >=0; i--) {
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j <= n-1) {
                    dp[i] = dp[i] || dp[i+j];
                }
            }
        }
        return dp[0];
    }
}
