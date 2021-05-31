package GraphTopic.MatrixSearch;

/**
 * 200. 岛屿数量 Median
 * https://leetcode-cn.com/problems/number-of-islands/
 *
 * 相当于考察图的连通性，通过枚举矩阵的每个位置，进行深度搜索，
 * 连通数量就相当于进行的DFS的次数。当然身为矩阵搜索，基本上
 * 还是符合之前总结的套路的，四个方向+是否范围的标记（这里
 * 1就相当于的未访问。）
 */
public class NumIslands {
    private int[] dx = new int[]{0, 1, 0, -1};
    private int[] dy = new int[]{1, 0, -1, 0};
    private int result = 0;

    public int numIslands(char[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    backtracking(grid, i, j);
                    result++;
                }
            }
        }
        return result;
    }

    private void backtracking(char[][] grid, int x, int y) {
        if ( x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '0') {
            return;
        }

        grid[x][y] = '0';
        for (int i = 0; i < 4; i++) {
            backtracking(grid, x + dx[i] , y + dy[i]);
        }
    }
}
