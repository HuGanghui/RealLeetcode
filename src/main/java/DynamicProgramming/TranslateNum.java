package DynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 46. 把数字翻译成字符串 Median
 * https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
 *
 * 典型的类似爬楼梯的题目，有比较直观的递推公式，但是base case需要注意一下。
 */
public class TranslateNum {
    // 自底向上
    public int translateNum(int num) {
        String s = String.valueOf(num);
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int temp = Integer.parseInt(s.substring(i-2, i));
            if (10 <= temp && temp <=25) {
                dp[i] = dp[i-1] + dp[i-2];
            } else {
                dp[i] = dp[i-1];
            }
        }
        return dp[n];
    }

    // 自顶向下
    public int translateNumMemo(int num) {
        String s = String.valueOf(num);
        Map<Integer, Integer> memo = new HashMap<>();
        return dfs(s, s.length(), 0, memo);
    }

    private int dfs(String s, int n, int index, Map<Integer, Integer> memo) {
        if (memo.containsKey(index)) {
            return memo.get(index);
        }
        if (index > n) {
            return 0;
        }
        if (index == n - 1 || index == n) {
            return 1;
        }

        int result = 0;
        int first = dfs(s, n, index + 1, memo);
        int sec = 0;
        if (index <= n - 2) {
            int temp = Integer.parseInt(s.substring(index, index + 2));
            if (10 <=temp && temp <=25) {
                sec = dfs(s, n, index + 2, memo);
            }
        }
        memo.put(index, first + sec);
        return memo.get(index);
    }
}
