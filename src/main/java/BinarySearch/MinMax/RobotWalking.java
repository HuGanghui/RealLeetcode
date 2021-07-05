package BinarySearch.MinMax;

/**
 * 机器人漫步 2021.07.05 虾皮笔试
 *
 * 求最小值，解空间明确且可遍历，显然是二分的套路，二分+dfs。
 * 不过这里dfs中是否需要回溯，要看测试来判断。
 */
public class RobotWalking {
    public int minimumInitHealth(int[][] rooms, int[] startPoint, int[] endPoint) {
        int m = rooms.length;
        int n = rooms[0].length;
        int left = 1;
        int right = m * n * 1000;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(rooms, startPoint, endPoint, mid)) {
                if (mid == 1 || !check(rooms, startPoint, endPoint, mid - 1)) {
                    result = mid;
                    break;
                } else {
                    right = mid - 1;
                }
            } else {
                left  = mid + 1;
            }
        }
        return result;
    }

    private int[] dx = new int[] {0, 1, 0, -1};
    private int[] dy = new int[] {1, 0, -1, 0};
    private int m;
    private int n;

    private boolean check(int[][] rooms, int[] startPoint, int[] endPoint, int H) {
        m = rooms.length;
        n = rooms[0].length;
        boolean[][] visited = new boolean[m][n];
        return dfs(rooms, startPoint[0], startPoint[1], endPoint, H, visited);

    }

    private boolean dfs(int[][] rooms, int x, int y, int[] endPoint,
                        int H, boolean[][] visited) {

        if (x >= m || y >= n || x < 0 || y < 0 || visited[x][y]) {
            return false;
        }

        if (H + rooms[x][y] <= 0) {
            return false;
        }

        if (x == endPoint[0] && y == endPoint[1]) {
            return true;
        }

        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            boolean result = dfs(rooms, x + dx[i], y + dy[i], endPoint, H + rooms[x][y], visited);
            if (result) {
                visited[x][y] = false;
                return true;
            }
        }
        visited[x][y] = false;
        return false;
    }

    public static void main(String[] args) {
        int[][] rooms = {{-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}};
        int[] startPoint = {0, 0};
        int[] endPoint = {2, 2};
        RobotWalking robotWalking = new RobotWalking();
        int result = robotWalking.minimumInitHealth(rooms, startPoint, endPoint);
        System.out.println(result);
    }

}
