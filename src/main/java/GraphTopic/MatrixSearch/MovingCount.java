package GraphTopic.MatrixSearch;

/**
 * 剑指 Offer 13. 机器人的运动范围 Median
 * https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
 *
 * 这题需要知道的是运动范围，转换一下，就是遍历能到的节点总数，那就是利用DFS来解决，当然
 * 也可以称为回溯，这题有个关键点在于，为了避免重复访问，visited=true通过某个点后，是不会回溯
 * 变成false，就非常像图的遍历了，这样就能知道图的总节点数/范围总数。
 */
public class MovingCount {
    private int[] dx = new int[]{0, 1, 0, -1};
    private int[] dy = new int[]{1, 0, -1, 0};
    private int m;
    private int n;
    private int k;
    private boolean[][] visited;

    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        visited = new boolean[m][n];
        int result = backtracking(0, 0);
        return result;
    }

    private int backtracking(int x, int y) {
        if (x >= m || x < 0 || y >= n || y < 0 || visited[x][y]) {
            return 0;
        }
        if (get(x, y) > k) {
            return 0;
        }
        visited[x][y] = true;
        int result = 1;
        for (int i = 0; i < 4; i++) {
            result += backtracking(x + dx[i], y + dy[i]);
        }
        return result;
    }

    private int get(int x, int y) {
        int result = 0;
        while (x != 0) {
            result += x % 10;
            x /= 10;
        }
        while (y != 0) {
            result += y % 10;
            y /= 10;
        }
        return result;
    }
}
