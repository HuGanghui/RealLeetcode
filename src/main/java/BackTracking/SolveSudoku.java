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
    private boolean flag = false;

    public void solveSudoku(char[][] board) {
        List<int[]> lists = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    lists.add(new int[]{i, j});
                }
            }
        }
        backtracking(lists, lists.size(), 0, board);
    }

    private void backtracking(List<int[]> lists, int n, int index, char[][] board) {
        if (index == n) {
            flag = true;
            return;
        }

        if (flag == true) {
            return;
        }

        for (int i = 1; i <= 9; i++) {
            int[] temp = lists.get(index);
            board[temp[0]][temp[1]] = (char) ('0' + i);
            if (isValid(board, temp, (char) ('0' + i))) {
                backtracking(lists, n, index + 1, board);
            }
            // 回溯后的flag判断
            if (flag == true) {
                return;
            }
            board[temp[0]][temp[1]] = '.';
        }
    }

    private boolean isValid(char[][] board, int[] index, char i) {
        for (int row = 0; row < board.length; row++) {
            if (row == index[0]) {
                continue;
            }
            if (board[row][index[1]] == i) {
                return false;
            }
        }

        for (int column = 0; column < board[0].length; column++) {
            if (column == index[1]) {
                continue;
            }
            if (board[index[0]][column] == i) {
                return false;
            }
        }

        int row_part = index[0] / 3;
        int column_part = index[1] / 3;

        for (int row = row_part * 3; row < row_part * 3 + 3; row ++) {
            for (int column = column_part * 3; column < column_part * 3 + 3; column++) {
                if (row == index[0] && column == index[1]) {
                    continue;
                }
                if (board[row][column] == i) {
                    return false;
                }
            }
        }
        return true;
    }
}
