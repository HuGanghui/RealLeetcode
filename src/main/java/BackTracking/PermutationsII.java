package BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. 全排列 II Median
 * https://leetcode-cn.com/problems/permutations-ii/
 *
 * 排列问题主要就是要添加一个used来判断元素是否之前使用过了，这题多了重复元素，同样需要去重，
 * 依旧是老办法，树层去重，和子集/组合问题一样的判断条件。
 */
public class PermutationsII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(nums);
        int[] used = new int[nums.length];
        backtracking(nums, used, temp, result);
        return result;
    }

    private void backtracking(int[] nums, int[] used,
                              List<Integer> temp, List<List<Integer>> result) {

        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
        }

        for (int i = 0; i < nums.length; i++) {
            if (i != 0 && (nums[i-1] == nums[i] && used[i-1] == 0)) {
                continue;
            }
            if (used[i] == 0) {
                temp.add(nums[i]);
                used[i] = 1;
                backtracking(nums, used, temp, result);
                temp.remove(temp.size() - 1);
                used[i] = 0;
            }
        }
    }
}
