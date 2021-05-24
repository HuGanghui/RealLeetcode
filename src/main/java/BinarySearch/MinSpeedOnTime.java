package BinarySearch;

/**
 * 5764. 准时到达的列车最小时速 Median
 * https://leetcode-cn.com/problems/minimum-speed-to-arrive-on-time/
 * LeetcodeWeeklyMatch 242
 *
 * 这题是要求最小正整数时速，然后题目感觉就是非常典型的二分查找的类型，依次二分判断
 * 直到获得最小。话不多说，直接二分框架，然后check根据题目进行编写即可。
 *
 */
public class MinSpeedOnTime {
    public int minSpeedOnTime(int[] dist, double hour) {
        return binarySearch(dist, hour);
    }

    private int binarySearch(int[] dist, double hour) {
        int left = 1;
        int right = (int) Math.pow(10, 7);
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(mid, dist, hour)) {
                if (mid == 1 || !check(mid - 1, dist, hour)) {
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

    private boolean check(int mid, int[] dist, double hour) {
        double real_hour = 0;
        int n = dist.length;
        for (int i = 0; i < n; i++) {
            double temp = dist[i] / (mid + 0.0);
            int temp_int = (int) temp;
            if (i == n - 1) {
                real_hour += temp;
            } else {
                if ((temp - temp_int) == 0) {
                    real_hour += temp_int;
                } else {
                    real_hour += (temp_int + 1);
                }
            }
        }
        return real_hour <= hour;
    }
}
