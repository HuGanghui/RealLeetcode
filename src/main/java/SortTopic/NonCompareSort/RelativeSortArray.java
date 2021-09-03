package SortTopic.NonCompareSort;

import java.util.*;

/**
 * 1122. 数组的相对排序 Easy
 * https://leetcode-cn.com/problems/relative-sort-array/
 *
 * 数据范围不是很大，最多1000，可以使用计数排序来完成。
 * 题解还有利用自定义排序的。
 */
public class RelativeSortArray {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        int n = arr1.length;
        int m = arr2.length;
        for (int i = 0; i < n; i++) {
            map.put(arr1[i], map.getOrDefault(arr1[i], 0) + 1);
        }
        int[] res = new int[n];
        int k = 0;
        for (int i = 0; i < m; i++) {
            int num = map.get(arr2[i]);
            for (int j = 0; j < num; j++) {
                res[k++] = arr2[i];
            }
        }
        for (int i = 0; i < m; i++) {
            set.add(arr2[i]);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!set.contains(arr1[i])) {
                list.add(arr1[i]);
            }
        }
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            res[k++] = list.get(i);
        }
        return res;
    }
}
