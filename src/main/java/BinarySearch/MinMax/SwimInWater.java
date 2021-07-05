package BinarySearch.MinMax;

/**
 * 778. 水位上升的泳池中游泳 Hard
 * https://leetcode-cn.com/problems/swim-in-rising-water/
 *
 * 这题的难点在于在check部分，需要进行dfs，相当于一道复合题，因此
 * 难点上升，check部分时间复杂度O(N^2)，总复杂度为O(N^2logN)。
 *
 * 这里有个剪枝操作，就是对于一个check，visited中经过，是不需要回溯的，
 * 因为后面必然的答案必然不会通过这里。
 */
public class SwimInWater {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, grid[i][j]);
            }
        }
        int left = 0;
        int right = max;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(grid, mid)) {
                if (mid == 0 || !check(grid, mid - 1)) {
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

    private int[] dx = {0, 1, 0, -1};
    private int[] dy = {1, 0, -1, 0};

    private boolean check(int[][] grid, int t) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        return dfs(grid, n, t, 0, 0, visited);
    }

    private boolean dfs(int[][] grid, int n, int t, int x, int y, boolean[][] visited) {

        if (x >= n || y >= n || x < 0 || y < 0 || visited[x][y]) {
            return false;
        }

        if (grid[x][y] > t) {
            return false;
        }

        if (x == n-1 && y == n-1) {
            return true;
        }

        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            boolean result = dfs(grid, n, t, x+dx[i], y+dy[i], visited);
            if (result) {
//                visited[x][y] = false;
                return true;
            }
        }
//        visited[x][y] = false;
        return false;
    }

    public static void main(String[] args) {
        SwimInWater swimInWater = new SwimInWater();
        swimInWater.swimInWater(new int[][]{new int[] {0,2}, new int[] {1,3}});
    }
}
