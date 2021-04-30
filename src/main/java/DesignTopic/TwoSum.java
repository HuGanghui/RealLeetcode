package DesignTopic;

import java.util.*;

/**
 * 170. 两数之和 III - 数据结构设计 Easy
 * https://leetcode-cn.com/problems/two-sum-iii-data-structure-design/
 *
 * 关于设计题，如果有多个API，必然会设计到这个数据结构是侧重哪个API，也就是
 * 那个API使用频率更高，然后就尽可能去优化那个。
 */
public class TwoSum {
    private Map<Integer, Integer> map = new HashMap<>();


    public TwoSum() {

    }

    // O(1)
    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    // O(n) 每次都需要遍历一遍map
    public boolean find(int value) {
        for (int ele : map.keySet()) {
            int diff = value - ele;
            int threshold = 0;
            if (diff == ele) {
                threshold = 1;
            }
            if (map.getOrDefault(diff, 0) > threshold) {
                return true;
            }
        }
        return false;
    }
}

class TwoSum2 {
    private List<Integer> list = new ArrayList<>();
    private Set<Integer> set = new HashSet<>();
    // O(n)
    public void add(int number) {
        for (int ele : list) {
            set.add(ele + number);
        }
        list.add(number);
    }

    // O(1)
    public boolean find(int value) {
        return set.contains(value);
    }
}
