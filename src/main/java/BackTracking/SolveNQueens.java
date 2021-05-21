package BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 51. N 皇后 Hard
 * https://leetcode-cn.com/problems/n-queens/
 *
 * 经典的回溯算法，暴力枚举，看似二维，其实也就是横向每行的不同位置，纵向为不同行，
 * 然后改进复杂的点在于 1.isValid的代码量稍微多了些， 2.Java对字符串
 * 的支持不够，需要先利用char[]数组来处理，然后最后转换为String。
 */
public class SolveNQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        List<char[]> temp = new ArrayList<>();
        String init = "";
        for (int i = 0; i < n; i++) {
            init += ".";
        }
        for (int i = 0; i < n; i++) {
            temp.add(init.toCharArray());
        }
        backtracking(n, result, temp, 0);
        return result;
    }

    private void backtracking(int n, List<List<String>> result, List<char[]> temp, int row) {

        if (row == n) {
            List<String> temp_result = new ArrayList<>();
            for (char[] ele : temp) {
                temp_result.add(String.valueOf(ele));
            }
            result.add(temp_result);
        }

        for (int col = 0; col < n; col++) {
            if (isValid(temp, row, col, n)) {
                temp.get(row)[col] = 'Q';
                backtracking(n, result, temp, row+1);
                temp.get(row)[col] = '.';
            }
        }
    }

    private boolean isValid(List<char[]> temp, int row, int col, int n) {

        for (int i = 0; i < row; i++) {
            if (temp.get(i)[col] == 'Q') {
                return false;
            }
        }

        for (int i = row - 1, j = col - 1; i >=0 && j >= 0; i--, j--) {
            if (temp.get(i)[j] == 'Q') {
                return false;
            }
        }

        for(int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (temp.get(i)[j] == 'Q') {
                return false;
            }
        }
        return true;
    }
}
