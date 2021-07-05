package BinarySearch.MinMax;

/**
 * 1011. 在 D 天内送达包裹的能力 Median
 * https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/
 *
 * 这题由于一定要按顺序分组，因此其实就是分割数组的套皮题，对于这种求最值的题目，并且是最值
 * 可以不断尝试的那种，二分查找不失为为一个非常不错的方法，并且时间复杂度一般是二分 O(logN)
 * + check 一般O(N)为主，那整体就是O(NlogN)。
 *
 * 不过值得注意的是，对于取值范围的选取还是挺重要的，比如这题最小值应当是包裹重量的最大值，因为
 * 包裹不可能分开运送，最大值应该是所有包裹的总和。
 */
public class ShipWithinDays {
    public int shipWithinDays(int[] weights, int D) {
        int n = weights.length;
        int max = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(weights[i], max);
            sum += weights[i];
        }
        int left = max;
        int right = sum;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(weights, mid, D)) {
                if (mid == max || !check(weights, mid - 1, D)) {
                    result = mid;
                    break;
                } else {
                    right = mid - 1;
                }
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    private boolean check(int[] weights, int cap, int D) {
        int n = weights.length;
        int days = 1;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            if (temp + weights[i] <= cap) {
                temp += weights[i];
            } else {
                days +=1;
                temp = weights[i];
            }
        }
        return days <= D;
    }
}
