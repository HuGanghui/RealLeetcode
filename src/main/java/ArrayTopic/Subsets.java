package ArrayTopic;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集 Easy 回溯 子集、组合基本题
 * https://leetcode-cn.com/problems/subsets/
 *
 * 本题可以有多种解法，涉及不同的思路，我会全部列出来，算是多种不同的方法的一个总结。
 * 参考题解 https://leetcode-cn.com/problems/subsets/solution/c-zong-jie-liao-hui-su-wen-ti-lei-xing-dai-ni-gao-/
 */
public class Subsets {

    /**
     *  方法一 迭代枚举 使用位运算的知识，nums的子集个数为2^n个，每个元素只有两种可能 0 不选 1 选
     *  因此所有情况刚好每个对应 0 - 2^n 的所有情况，因此使用二进制作为遍历
     *  时间复杂度O(n * 2^n)
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        int size = nums.length;
        // 1 << size 子集总数 相当于 2^size
        for (int mask = 0; mask < (1 << size); mask++) {
            for (int i = 0; i < size; i++) {
                // 1 << i 对应位置为1, 其它位置都0, 结合&运算，只有mask对应位置也是1, 才能不为0
                if ((mask & (1 << i)) != 0) {
                    temp.add(nums[i]);
                }
            }
            lists.add(new ArrayList<>(temp));
            temp.clear();
        }
        return lists;
    }

    /**
     * 方法二 非递归依此加入，就是将现有的子集都加上当前元素构成新的子集，然后加入其中，这样也不重不漏。
     * 时间复杂度小于O(n * 2^n)，但是每步做的还比较多，所以提交后反而比较慢，当然位运算最快就是了。
     */
    public List<List<Integer>> subsetsTwo(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        lists.add(new ArrayList<>(temp));

        for (int ele : nums) {
            for (int i = 0, j = lists.size(); i < j; i++) {
                temp.addAll(lists.get(i));
                temp.add(ele);
                lists.add(new ArrayList<>(temp));
                temp.clear();
            }
        }
        return lists;
    }
}

class SubsetsRecursiveMethod {

    /**
     * 方法三 递归回溯遍历，这里视为一颗二叉树。
     * 时间复杂度O(n * 2^n) 递归的时间复杂度有专门的算法，要找时间专门复习一下。
     */
    public List<List<Integer>> subsetsThree(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        dfsForThree(lists, temp,0, nums);
        return lists;
    }

    private void dfsForThree(List<List<Integer>> lists, List<Integer> temp, int cur, int[] nums) {
        if (cur == nums.length) {
            lists.add(new ArrayList<>(temp));
            return;
        }

        temp.add(nums[cur]);
        dfsForThree(lists, temp,cur + 1, nums);
        temp.remove(temp.size() -1); // 回溯
        dfsForThree(lists, temp,cur + 1, nums);
    }

    /**
     * 方法四 递归回溯遍历，这里视为一颗n叉树
     * 时间复杂度
     */
    public List<List<Integer>> subsetsFour(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        dfsForFour(lists, new ArrayList<>(), nums,0);
        return lists;
    }

    private void dfsForFour(List<List<Integer>> lists, List<Integer> temp,
                            int[] nums, int start) {
        lists.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            dfsForFour(lists, temp, nums, i+1);
            temp.remove(temp.size() -1); // 回溯
        }
    }

    public static void main(String[] args) {
        SubsetsRecursiveMethod subsetsRecursiveMethod = new SubsetsRecursiveMethod();
        subsetsRecursiveMethod.subsetsFour(new int[]{1, 2, 3});
    }
}
