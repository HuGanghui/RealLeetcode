package SortTopic.NonCompareSort;

import java.util.*;

/**
 * KMinMax 阿里巴巴 2021.08.27 笔试题
 *
 * 给n个数，每次可以选择其中一个数执行+1操作，求K次操作后最大值的最小值。
 *
 * 基本的思想，就是计数排序，先得到n个数中每个数对应有多少个，然后用k去
 * 抵消，最后可以得到结果。
 */
public class KMinMax {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        // 获取每个数有多少个
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
        }
        // 排序
        int[] keys = new int[map.keySet().size()];
        int j = 0;
        for (int key : map.keySet()) {
            keys[j] = key;
            j++;
        }
        Arrays.sort(keys);

        int res = keys[keys.length-1];
        for (int i = 1; i < keys.length; i++) {
            int cur = map.get(keys[i-1]);
            int next = map.get(keys[i]);
            int valueDiff = keys[i] - keys[i-1];
            int gap = valueDiff * cur;
            if (k > gap) {
                k -= gap;
                map.put(keys[i], next + cur);
            } else {
                res = keys[i];
            }
        }
        System.out.println(res);
    }
}
