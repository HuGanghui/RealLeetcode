package BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 39. 组合总和 Median
 * https://leetcode-cn.com/problems/combination-sum/
 *
 * 这题求和，特殊在于元素可以无限重复取，所以需要保证不重不漏。最后方案和不可重复的类似，多叉树，但是在深度上
 * startIndex 不需要再 +1 操作，这个操作可以保证不重不漏的证明暂时还没有想清楚。
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        int sum = 0;
        backtracking(0, result, temp, candidates, target, sum);
        return result;
    }

    private void backtracking(int startIndex, List<List<Integer>> result, List<Integer> temp, int[] candidates, int target, int sum) {

        if (sum >= target) {
            if (sum == target) {
                result.add(new ArrayList<>(temp));
            }
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                continue;
            }
            temp.add(candidates[i]);
            sum += candidates[i];
            backtracking(i, result, temp, candidates, target, sum);
            temp.remove(temp.size() - 1);
            sum -= candidates[i];
        }
    }
}
