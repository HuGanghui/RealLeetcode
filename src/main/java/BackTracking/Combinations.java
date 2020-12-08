package BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合 Median
 * https://leetcode-cn.com/problems/combinations/
 *
 * 组合问题，典型的可以使用回溯法（比较枚举总是ok的），我的写法是参考代码随想录的典型回溯模板，
 * 另一种写法应该就是
 */
public class Combinations {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        backtracking(0, result, temp, n, k);
        return result;
    }

    private void backtracking(int startIndex, List<List<Integer>> result,
                              List<Integer> temp, int n, int k) {

        // 递归结束条件一 如果是子集问题，这个结束条件就没有
        if (temp.size() == k) {
            result.add(new ArrayList<>(temp));
            return;
        }

        // 剪枝操作 递归结束条件三
        if ((n - startIndex + 1) < (k - temp.size())) {
            return;
        }

        // 递归结束条件二
        for (int i = startIndex; i < n; i++) {
            temp.add(i+1);
            backtracking(i+1, result, temp, n, k);
            temp.remove(temp.size() - 1);
        }
    }
}
