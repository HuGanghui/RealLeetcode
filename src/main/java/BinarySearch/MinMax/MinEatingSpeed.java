package BinarySearch.MinMax;

/**
 * 875. 爱吃香蕉的珂珂 Median
 * https://leetcode-cn.com/problems/koko-eating-bananas/
 *
 * 仍然是相同的套路，区别只是check函数的实现方式的不同。
 */
public class MinEatingSpeed {
    public int minEatingSpeed(int[] piles, int H) {
        int n = piles.length;
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, piles[i]);
        }
        int left = 1;
        int right = max;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(piles, mid, H)) {
                if (mid == 1 || !check(piles, mid -1 , H)) {
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

    private boolean check(int[] piles, int speed, int H) {
        int h = 0;
        int n = piles.length;
        for (int i = 0; i < n; i++) {
            if (piles[i] <= speed) {
                h++;
            } else {
                int need = (int) Math.ceil(piles[i] / (speed +0.0));
                h += need;
            }
        }
        return h <= H;
    }
}
