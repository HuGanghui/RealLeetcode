package BinarySearch.MinMax;

import java.util.Arrays;

/**
 * 1552. 两球之间的磁力 Median
 * https://leetcode-cn.com/problems/magnetic-force-between-two-balls/
 *
 * 这题反过来，需要求的是最大化最小距离，本质上看上去仍然是一个数组切分的形式，只不过
 * 具体的要求变了，做对应的调整即可。
 */
public class MaxDistance {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int n = position.length;
        int left = 1;
        int max = position[n-1] - position[0];
        int right = max;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(position, mid, m)) {
                if (mid == max || !check(position, mid + 1, m)) {
                    result = mid;
                    break;
                } else {
                    left = mid + 1;
                }
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    private boolean check(int[] position, int dis, int m) {
        int _m = 1;
        int temp = position[0];
        int n = position.length;
        for (int i = 1; i < n; i++) {
            if (position[i] - temp >= dis) {
                _m++;
                temp = position[i];
            }
        }
        return _m >= m;
    }
}
