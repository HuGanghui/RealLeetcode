package GraphTopic.MatrixSearch;

import java.util.*;

/**
 * 部队集合 阿里巴巴 2021.08.27 笔试题
 *
 * 给定一个n*m的矩阵，1代表沼泽，0代表陆地，多个队伍集合，
 * 可以上下左右移动，每移动一次要一小时，但是不能走沼泽，求所有k支队伍集合的最少时间。
 *
 * 题目的集合地点是任何都可以，因此就是遍历每个作为集合地点，然后求所有队伍中最大
 * 时间的最小值。
 *
 * n, m <= 500，k < 10
 *
 * 直接遍历然后DFS就行，时间复杂度是粗略是O(n^2*m^2*k)
 * 可能会超时，但是也有些剪枝的操作，因此应该可以。
 */
public class ArmyMass {
    private static int[][] matrix;
    private static int n;
    private static int m;
    private static int[][] army;

    private static int res;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        // n,m 赋值后才可以用
        res = n * m + 1;
        matrix = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        int k = sc.nextInt();
        // k个部队的初始位置
        army = new int[k][2];
        for (int i = 0; i < k; i++) {
            army[i][0] = sc.nextInt();
            army[i][1] = sc.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (matrix[i][j] != 1) {
                    int temp_res = 0;
                    boolean flag = true;
                    for (int l = 0; l < k; l++) {
                        int[][] map = new int[n+1][m+1];
                        int local = dfs(new int[]{i, j}, army[l][0], army[l][1], 0, map);
                        if (local == -1) {
                            flag = false;
                            break;
                        } else {
                            temp_res = Math.max(local, temp_res);
                        }
                    }
                    if (flag) {
                        res = Math.min(temp_res, res);
                    }
                }
            }
        }

        System.out.println(res);

    }

    private static int[] dx = new int[] {0, 1, 0, -1};
    private static int[] dy = new int[] {1, 0, -1, 0};

    private static int dfs(int[] dest, int x, int y, int hour, int[][] map) {
        if (x > n || x <= 0 || y > m || y <= 0 || matrix[x][y] == 1 || map[x][y] == 1) {
            return -1;
        }

        if (x == dest[0] && y == dest[1]) {
            return hour;
        }

        int res = n * m + 1;
        map[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            // hour + 1 不能 ++
            int cur = dfs(dest, x + dx[i], y + dy[i], hour+1, map);
            if (cur == -1) {
                continue;
            }
            res = Math.min(cur, res);
        }
        map[x][y] = 0;
        return res == n * m + 1 ? -1 : res;
    }

}
