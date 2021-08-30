package ProbabilityTopic;

import java.util.*;

/**
 * 528. 按权重随机选择 Median
 * https://leetcode-cn.com/problems/random-pick-with-weight/
 *
 * 前缀和的和，刚好作为那个index的右边界，然后随机均匀生成一个数，进行
 * 二分查找找右边界即可。
 */
public class WeightRandomSelect {
    int[] pre;
    int total;

    public WeightRandomSelect(int[] w) {
        int n = w.length;
        pre = new int[n];
        // 前缀和，每个点内的前缀和就是每个index的右边界
        pre[0] = w[0];
        for (int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] + w[i];
        }
        total = pre[n-1];
    }

    public int pickIndex() {
        // 均匀随机生成一个数
        int x = new Random().nextInt(total) + 1;
        return binarySearch(x);
    }

    // 找到第一个大于等于x的右边界
    private int binarySearch(int x) {
        int left = 0;
        int right = pre.length - 1;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (pre[mid] >= x) {
                if (mid == 0 || pre[mid - 1] < x) {
                    result = mid;
                    break;
                }
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }
}
