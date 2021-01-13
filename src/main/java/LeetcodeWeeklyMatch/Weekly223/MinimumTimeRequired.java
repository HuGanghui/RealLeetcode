package LeetcodeWeeklyMatch.Weekly223;

import java.util.HashSet;
import java.util.Set;

/**
 * 1723. 完成所有工作的最短时间 Hard
 * https://leetcode-cn.com/problems/find-minimum-time-to-finish-all-jobs/
 *
 * 看数据范围就知道可以尝试DFS这种遍历的，但是如何确定遍历的参数，还是需要一点经验的，这题和之前组合/排列/切割问题
 * 有点不太一样的就是，你每个工作可以分配给k个人，所以是k叉树，然后递归一直到任务分配完。之前组合都是我选择有多种，
 * 而这题则是相反的是分配有多种选择方式，但基本上如果你能想到遍历，稍微调整一下，明确树枝和树层的参数就OK了。
 *
 * 更高级的做法应该是用状压DP以及从最大最小的字眼就可以联想到的二分+状压DP，参考题解：
 * https://leetcode-cn.com/problems/find-minimum-time-to-finish-all-jobs/solution/zhuang-ya-dp-jing-dian-tao-lu-xin-shou-j-3w7r/
 *
 * 后续在补充二分的方法以及类似的一道题：https://leetcode-cn.com/problems/split-array-largest-sum/
 */

public class MinimumTimeRequired {
    public int minimumTimeRequired(int[] jobs, int k) {
        int n = jobs.length;
        int subSize = 1 << n;
        int[] subTotalTime = new int[subSize];
        for (int i = 1; i < subSize; i++) {
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) == 0) {
                    continue;
                } else {
                    int left = (i - (1 << j));
                    subTotalTime[i] = subTotalTime[left] + jobs[j]; // 有递推关系在里面，降低复杂度
                }
                break; // 知道最低位为1的就行，因为用了递推关系，而不是一个一个相加
            }
        }

        int[][] dp = new int[k+1][subSize];
        for (int i = 1; i < subSize; i++) {
            dp[1][i] = subTotalTime[i];
        }

        for (int i = 2; i <=k; i++) {
            for (int j = 0; j < subSize; j++) {
                int min = Integer.MAX_VALUE;
                for (int s = j; s != 0; s = (s-1) & j) { // trick，如何获取某个数的子集
                    int left = j - s;
                    int val = Math.max(dp[i-1][left], subTotalTime[s]);
                    min = Math.min(min, val);
                }
                dp[i][j] = Math.min(min, dp[i-1][j]);
            }
        }
        return dp[k][subSize-1];
    }
}

class MinimumTimeRequiredDFS {
    private int res = Integer.MAX_VALUE;

    public int minimumTimeRequired(int[] jobs, int k) {
        backtracking(0, new int[k], jobs, k);
        return res;
    }

    private void backtracking(int startIndex, int[] workerTime, int[] jobs, int k) {

        if (startIndex == jobs.length) {
            int max = maxTime(workerTime);
            if (max < res) {
                res = max;
            }
            return;
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < k; i++) {
            // 关键剪枝，我们只需要最大工作时间的最小值，
            // 具体是哪个人我们并不关系，因此遍历的时候，如果已经分配过的
            // 相同的时间就无需在尝试递归了
            if (set.contains(workerTime[i])) {
                continue;
            } else {
                set.add(workerTime[i]);
            }

            if (maxTime(workerTime) > res) {
                continue;
            }
            workerTime[i] += jobs[startIndex];
            backtracking(startIndex+1, workerTime, jobs, k);
            workerTime[i] -= jobs[startIndex];

        }
    }

    private int maxTime(int[] workerTime) {
        int max = workerTime[0];
        for (int i = 1; i < workerTime.length; i++) {
            max = Math.max(max, workerTime[i]);
        }
        return max;
    }
}
