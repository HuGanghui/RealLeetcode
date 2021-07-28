package DynamicProgramming.StateCompression;

import java.util.*;

/**
 * 1931. 用三种不同颜色为网格涂色 Hard
 * https://leetcode-cn.com/problems/painting-a-grid-with-three-different-colors/
 *
 * 这题是典型的状态压缩题，最核心的一些trick
 *
 * * 3进制表示所有涂色可能
 * * 行列没有本质区别，因此根据时间复杂度，选择较小的作为行
 * * 两次预处理，预处理这种方式值得学习
 */
public class ColorTheGrid {
    private final int MOD = (int) Math.pow(10, 9) + 7;

    public int colorTheGrid(int m, int n) {
        // 3进制 表示所有涂色可能
        // m比较小，看作行
        List<Integer> list = new ArrayList<>();
        int total = (int)Math.pow(3, m);
        for (int i = 0; i < total; i++) {
            int temp = i;
            List<Integer> tempList = new ArrayList<>();
            boolean result = true;
            for (int j = 0; j < m; j++) {
                tempList.add(temp % 3);
                temp /= 3;
            }
            for (int j = 1; j < m; j++) {
                if (tempList.get(j) == tempList.get(j-1)) {
                    result = false;
                    break;
                }
            }
            if (result) {
                list.add(i);
            }
        }
        int size = list.size();
        boolean[][] valid  = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int a = list.get(i);
                int b = list.get(j);
                boolean result = true;
                for (int k = 0; k < m; k++) {
                    if (a % 3 == b % 3) {
                        result = false;
                        break;
                    } else {
                        a /= 3;
                        b /= 3;
                    }
                }
                valid[i][j] = result;
            }
        }

        int[][] dp = new int[n][size];
        for (int i = 0; i < size; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    if (valid[j][k]) {
                        dp[i][j] += dp[i-1][k];
                        dp[i][j] = dp[i][j] % MOD;
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < size; i++) {
            ans += dp[n-1][i];
            ans = ans % MOD;
        }

        return ans;
    }
}
