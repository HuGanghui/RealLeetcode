package BinarySearch.MinMax;

/**
 * 1631. 最小体力消耗路径 Median
 * https://leetcode-cn.com/problems/path-with-minimum-effort/
 *
 * 这题同样是二分+dfs的套路，只不过具体的限制条件有了变换。
 */
public class MinimumEffortPath {
    private int n;
    private int m;

    public int minimumEffortPath(int[][] heights) {
        this.n = heights.length;
        this.m = heights[0].length;
        int left = 0;
        int right = (int) Math.pow(10, 6) - 1;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(heights, mid)) {
                if (mid == 0 || !check(heights,  mid - 1)) {
                    result = mid;
                    break;
                } else {
                    right = mid - 1;
                }
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    private int[] dx = new int[]{0, 1, 0, -1};
    private int[] dy = new int[]{1, 0, -1, 0};

    private boolean check(int[][] heights, int cur_max) {
        boolean[][] visited = new boolean[n][m];
        return dfs(heights, 0, 0, cur_max, visited);
    }

    private boolean dfs(int[][] heights, int x, int y, int cur_max, boolean[][] visited) {
        if (x < 0 || x >= n || y < 0 || y >= m || visited[x][y]) {
            return false;
        }

        if (x == n - 1 && y == m - 1) {
            return true;
        }

        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int next_x = x + dx[i];
            int next_y = y + dy[i];
            if (next_x < 0 || next_x >= n || next_y < 0 || next_y >= m || visited[next_x][next_y]) {
                continue;
            } else {
                int temp = Math.abs(heights[x][y] - heights[next_x][next_y]);
                if (temp > cur_max) {
                    continue;
                } else {
                    if (dfs(heights, next_x, next_y, cur_max, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
