package LeetcodeWeeklyMatch.TwoTwoTwoWeekly;

import java.util.HashMap;
import java.util.Map;

/**
 * 5642. 大餐计数 Median
 * https://leetcode-cn.com/problems/count-good-meals/
 *
 * 看到这题就想到了两数之和，使用哈希表来降低时间复杂度，并且考虑题目，
 * 哈希表同时还保存了元素出现次数，想法很简单，主要这题的问题出在
 * 1.去重，否则会有出现双倍结果 2. 关于数值的东西，一定要额外注意
 *
 */
public class CountPairs {
    public int countPairs(int[] deliciousness) {
        int result = 0;
        Map<Long, Integer> map = new HashMap<>();
        for (long ele : deliciousness) {
            if (map.containsKey(ele)) {
                map.replace(ele, map.get(ele)+1);
            } else {
                map.put(ele, 1);
            }
        }
        for (long ele : map.keySet()) {
            for (int i = 0; i < 32; i++) {
                long target = (long) Math.pow(2, i);
                if (ele >= target) {
                    continue;
                }
                long diff = target - ele;
                if (diff < ele) {
                    continue;
                }
                if (map.containsKey(diff)) {
                    long num = map.get(ele);
                    if (diff == ele) {
                        result += (num * (num -1) / 2) % (int) (Math.pow(10, 9) + 7); // 避免溢出
                    } else {
                        result += (num * map.get(diff)) % (int) (Math.pow(10, 9) + 7);
                    }
                }
            }
        }
        return result % (int) (Math.pow(10, 9) + 7); // 避免溢出

    }

    public static void main(String[] args) {
        CountPairs countPairs = new CountPairs();
        System.out.println(countPairs.countPairs(new int[] {1,1,1,3,3,3,7}));
    }
}
