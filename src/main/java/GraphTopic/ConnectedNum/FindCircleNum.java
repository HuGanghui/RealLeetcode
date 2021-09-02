package GraphTopic.ConnectedNum;

/**
 * 547. 省份数量 Median
 * https://leetcode-cn.com/problems/number-of-provinces/
 *
 * 一看就是求图的连通分量的格式，然后DFS，BFS，并查集都OK，一般
 * 还是习惯DFS。给的图结构是邻接矩阵。
 */
public class FindCircleNum {
    // DFS
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, n, i);
                res++;
            }
        }
        return res;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int n, int index) {
        for (int i = 0; i < n; i++) {
            if (isConnected[index][i] == 1 && !visited[i]) {
                visited[i] = true;
                dfs(isConnected, visited, n, i);
            }
        }
    }
}
