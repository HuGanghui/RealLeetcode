package BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. 组合总和 II
 * https://leetcode-cn.com/problems/combination-sum-ii/
 *
 * 本题核心在于给的数组有重复的数字，需要进行去重处理，方法就是先排序，然后使用used数组标记，
 * 其实这题本质上和可以无限取的类似，只是并非无限取，而是每个数字有固定限的取。
 */
public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(candidates);
        int[] used = new int[candidates.length];
        int sum = 0;
        backtracking(0, result, temp, sum, candidates, target, used);
        return result;

    }

    private void backtracking(int startIndex, List<List<Integer>> result,
                              List<Integer> temp, int sum, int[] candidates, int target, int[] used) {
        if (sum >= target) {
            if (sum == target) {
                result.add(new ArrayList<>(temp));
            }
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            if (i > 0 && candidates[i-1] == candidates[i] && used[i-1] == 0) {
                continue;
            }
            if (sum > target) {
                continue;
            }
            temp.add(candidates[i]);
            sum += candidates[i];
            used[i] = 1;
            backtracking(i + 1, result, temp, sum, candidates, target, used);
            temp.remove(temp.size() - 1);
            sum -= candidates[i];
            used[i] = 0;
        }
    }

    public static void main(String[] args) {
        CombinationSumII combinationSumII = new CombinationSumII();
        combinationSumII.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
    }
}
