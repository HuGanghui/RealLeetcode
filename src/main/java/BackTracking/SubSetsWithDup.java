package BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. 子集 II Median
 * https://leetcode-cn.com/problems/subsets-ii/
 *
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 子集问题组合问题本质上都是一类问题，使用n叉树的策略，对于去重，基本上都是树层去重。
 */
public class SubSetsWithDup {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(nums);
        int[] used = new int[nums.length];
        backtracking(0, nums, used, temp, result);
        return result;
    }

    private void backtracking(int startIndex, int[] nums, int[] used,
                              List<Integer> temp, List<List<Integer>> result) {

        result.add(new ArrayList<>(temp));

        for (int i = startIndex; i < nums.length; i++) {
            if (!(i != 0 && (nums[i-1] == nums[i] && used[i-1] == 0))) {
                temp.add(nums[i]);
                used[i] = 1;
                backtracking(i + 1, nums, used, temp, result);
                temp.remove(temp.size() - 1);
                used[i] = 0;
            }
        }
    }
}
