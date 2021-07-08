package BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 37. 解数独 Hard
 * https://leetcode-cn.com/problems/sudoku-solver/
 *
 * 基本套路就是回溯，但是有一下几点需要注意：
 * 1. 数独是二维的，通过一个list来保存需要填充的位置，从而
 *    将二维降到一维
 * 2. 关于char类型，涉及到int类型转换，都还是用 (char) ('0' + i)
 *    这个和'0'做一个运算保险
 * 3. 由于这题直接利用给的board作为答案，因此一定要在回溯后加上一个
 *    flag判断，否则得不到答案
 */
public class SolveSudoku {
    private boolean result = false;
    private char[] chars = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public void solveSudoku(char[][] board) {
        int n = board.length;
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '.') {
                    list.add(new int[]{i, j});
                }
            }
        }
        backtracking(board, list, 0);
        System.out.println(result);
    }

    private void backtracking(char[][] board, List<int[]> list, int index) {

        if (index == list.size()) {
            result = true;
            return;
        }

        if (result) {
            return;
        }

        for (int i = 1; i <= 9; i++) {
            int[] rc = list.get(index);
            if (isValid(board, rc, chars[i])) {
                board[rc[0]][rc[1]] = chars[i];
                backtracking(board, list, index + 1);
                if (result) {
                    return;
                }
                board[rc[0]][rc[1]] = '.';
            }
        }
    }

    private boolean isValid(char[][] board, int[] rc, char num) {

        for (int col = 0; col < board.length; col++) {
            if (col == rc[1]) {
                continue;
            }
            if (board[rc[0]][col] == num) {
                return false;
            }
        }

        for (int row = 0; row < board.length; row++) {
            if (row == rc[0]) {
                continue;
            }

            if (board[row][rc[1]] == num) {
                return false;
            }
        }

        int left_up_row = (rc[0] / 3) * 3;
        int left_up_col = (rc[1] / 3) * 3;
        for (int i = left_up_row; i < left_up_row + 3; i++) {
            for (int j = left_up_col; j < left_up_col + 3; j++) {
                if (i == rc[0] && j == rc[1]) {
                    continue;
                }
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}
