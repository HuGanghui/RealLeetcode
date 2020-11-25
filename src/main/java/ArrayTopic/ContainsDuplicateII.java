package ArrayTopic;

import java.util.HashMap;
import java.util.Map;

/**
 * 219. 存在重复元素 II Easy --
 * https://leetcode-cn.com/problems/contains-duplicate-ii/
 *
 * 类似重复的题目，感觉哈希表是万能的，但是这题注意需要一个更新操作。
 */
public class ContainsDuplicateII {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        boolean result = false;
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) != null) {
                int temp = Math.abs(i - map.get(nums[i]));
                if (temp <= k) {
                    result = true;
                    break;
                } else {
                    map.put(nums[i], i); // 需要一个更新的操作
                }
            } else {
                map.put(nums[i], i);
            }
        }
        return result;
    }
}
