package LeetcodeWeeklyMatch.Weekly223;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 1655. 分配重复整数 Hard
 *
 * 第 39 场双周赛第四题
 *
 * 这题放这主要是因为和MinimumTimeRequired是比较相似的，甚至都可以通过回溯解决，
 * 回溯的状态变量是frep的不同元素的状态以及 quantity的每个元素是否能满足，这里最后的
 * 判断是通过k是否达到quantity.length，因为达到了说明之前的都满足了，只有满足才会继续
 * 往下。
 *
 * 更高级的做法应该是用状压DP
 */
public class CanDistribute {
    public boolean canDistribute(int[] nums, int[] quantity) {
        // 数量固定，因此使用数组就行，没必要一定map，并且最后取最大的50个就行
        int[] frep = new int[1001];
        for (int i = 0; i < nums.length; i++) {
            frep[nums[i]]++;
        }
        Arrays.sort(frep);
        int[] frep50 = new int[50];
        for (int i = 0; i < 50; i++) {
            frep50[i] = frep[frep.length - 50 + i];
        }
        int n = frep50.length;
        int m = quantity.length;
        int subSize = 1 << m;
        int[] sum = new int[subSize];
        for (int i = 1; i < subSize; i++) {
            for (int j = 0; j < m; j++) {
                if ((i & (1 << j)) == 0) {
                    continue;
                } else {
                    int left = i- (1<< j);
                    sum[i] = sum[left] + quantity[j];
                }
            }
        }

        boolean[][] dp = new boolean[n+1][subSize];
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < subSize; j++) {
                if (dp[i-1][j]) {
                    dp[i][j] = true;
                    continue;
                }
                for (int s = j; s != 0; s = ((s -1) & j)) {
                    int prev = j - s;
                    if (dp[i-1][prev] && frep50[i-1] >= sum[s]) {
                        dp[i][j] = true;
                        break;
                    }
                }
            }
        }
        return dp[n][subSize-1];
    }
}

class CanDistributeDFS {
    public boolean canDistribute(int[] nums, int[] quantity) {
        // 数量固定，因此使用数组就行，没必要一定map，并且最后取最大的50个就行
        int[] frep = new int[1001];
        for (int i = 0; i < nums.length; i++) {
            frep[nums[i]]++;
        }
        Arrays.sort(frep);
        return backtracking(frep, 0, quantity);
    }

    private boolean backtracking(int[] frep, int k, int[] quantity) {
        if (k == quantity.length) {
            return true;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = frep.length - 50; i < frep.length; i++) {
            // 关键剪枝，由于具体的分配哪个数字不重要，数量大小就能表示状态
            // 因此，不需要相同状态的再过一遍
            if (set.contains(frep[i])) {
                continue;
            } else {
                set.add(frep[i]);
            }
            if (frep[i] >= quantity[k]) {
                frep[i] -= quantity[k];
                if (backtracking(frep, k+1, quantity)) {
                    return true;
                }
                frep[i] += quantity[k];
            }
        }
        return false;
    }

}
