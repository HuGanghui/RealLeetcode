package DynamicProgramming.Knapsack.Complete.WithGraph;

/**
 * LCP 07. 传递信息 Easy
 * https://leetcode-cn.com/problems/chuan-di-xin-xi/
 *
 * 一看就知道这是一个图相关的题目，然后它需要求的是经过k轮到编号为n-1
 * 的小伙伴那的方案数，那很直观的一个想法就是使用深度/广度搜索来进行处理，
 * 这样的话，首先一般的做法就是将边数组先转换为出度的邻接表，然后使用DFS/BFS
 * 进行遍历即可，明确递归结束条件。时间复杂度O(n^k)。
 *
 * 这题是个计数问题，因此可以考虑一下是否可以使用动态规划来解决。动态规划最重要的两个东西，
 * 状态和选择，这道题需要考虑的状态有step以及目前所在的顶点，然后选择就是有边相连的两个顶点。
 * 因此我们要求 dp[k][n-1]，递推方程便是 dp[i][des] = sum(dp[i-1][src]) ((src,des)有边相连）
 * base case 就是 dp[0][j] = 1, j=0; 0, j不等于0。（这个因为开始只能从编号0开始，算是一个小技巧了）。
 * 时间复杂度为O(km)，m为边条数，空间复杂度为O(n)（因为dp[i][]只与dp[i-1][]有关，可以使用滚动数组）。
 */
public class TransmitInfo {
    public int numWays(int n, int[][] relation, int k) {
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i <= k; i++) {
            int[] next = new int[n];
            for (int[] edge : relation) {
                int src = edge[0];
                int des = edge[1];
                next[des] += dp[src];
            }
            dp = next;
        }
        return dp[n-1];
    }
}
