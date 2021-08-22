package GraphTopic.MatrixSearch;

/**
 * 130. 被围绕的区域 Median
 * https://leetcode-cn.com/problems/surrounded-regions/
 *
 * 这题核心是先把所有的'O'换成'A'，然后四边出发，DFS，可以遇到的
 * 'A'都换成'O'，然后剩下的就是被包围的。
 */
public class TheSurroundedArea {
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'A';
                }
            }
        }

        // 四边出发
        for (int i = 0; i < n; i++) {
            dfs(0, i, m, n, board);
        }
        for (int i = 0; i < n; i++) {
            dfs(m-1, i, m, n, board);
        }
        for (int i = 0; i < m; i++) {
            dfs(i, 0,  m, n, board);
        }
        for (int i = 0; i < m; i++) {
            dfs(i, n-1,  m, n, board);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private int[] dx = new int[]{0, 1, 0, -1};
    private int[] dy = new int[]{1, 0, -1, 0};

    private void dfs(int x, int y, int m, int n, char[][] board) {
        if (x >= m || x < 0 || y < 0 || y >= n) {
            return;
        }

        if (board[x][y] == 'A') {
            board[x][y] = 'O';
            for (int i = 0; i < 4; i++) {
                dfs(x+dx[i], y+dy[i], m , n, board);
            }
        }
    }
}
