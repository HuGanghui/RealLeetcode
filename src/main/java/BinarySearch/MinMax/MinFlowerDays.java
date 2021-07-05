package BinarySearch.MinMax;

/**
 * 1482. 制作 m 束花所需的最少天数 Median
 * https://leetcode-cn.com/problems/minimum-number-of-days-to-make-m-bouquets/
 *
 * 依旧是求最值，只不过check条件更复杂些，但是本质没有改变，使用二分即可。
 */
public class MinFlowerDays {
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, bloomDay[i]);
        }
        int left = 1;
        int right = max;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(bloomDay, mid, m, k)) {
                if (mid == 1 || !check(bloomDay, mid - 1, m, k)) {
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

    private boolean check(int[] bloomDay, int days, int m, int k) {
        int n = bloomDay.length;
        int _m = 0;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            if (bloomDay[i] <= days) {
                temp++;
                if (temp == k) {
                    _m++;
                    temp = 0;
                }
            } else {
                temp = 0;
            }
        }
        return _m >= m;
    }
}
