package GraphTopic.MatrixSearch;

/**
 * 剑指 Offer 12. 矩阵中的路径 Median
 * https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/
 *
 * 回溯体现在当当前节点四个方向访问完毕后，需要将visited重新设置回false，这样不影响后续
 * 的递归求解答案。
 * 本题的特殊结束条件/确定树的纵向树枝的深度在于words的长度，达到了就说明找到答案了。
 * 这题有一个特殊点在于起点是不确定的，因此需要遍历一下。
 */
public class PathInMatrix {
    private boolean result = false;
    private int[] dx = {1, 0, -1, 0};
    private int[] dy = {0, 1, 0, -1};
    private int m;
    private int n;
    private int size;

    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        m = board.length;
        n = board[0].length;
        size = words.length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                backtracking(board, i, j, words, 0, visited);
            }
        }
        return result;
    }

    private void backtracking(char[][] board, int x, int y, char[] words, int index, boolean[][] visited) {
        if (x >= m || y >= n || x < 0 || y < 0 || visited[x][y]) {
            return;
        }

        if (board[x][y] != words[index]) {
            return;
        }

        if (index == size-1) {
            result = true;
        }

        if (result) {
            return;
        }

        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            backtracking(board, x + dx[i], y + dy[i], words, index+1, visited);
        }
        visited[x][y] = false;
    }
}
