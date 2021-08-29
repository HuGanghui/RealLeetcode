package DoublePointerTopic;

import java.util.Arrays;

/**
 * 881. 救生艇 Median
 * https://leetcode-cn.com/problems/boats-to-save-people/
 *
 * 直观的做法就类似贪心+双指针，尽可能的让2个人坐一个船，让最重的配最轻的，如果OK
 * 就行，不OK，那就最重的那个单独一个船。
 */
public class NumRescueBoats {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0;
        int n = people.length;
        int right = n - 1;
        int res = 0;
        while (left < right) {
            int sum = people[left] + people[right];
            if (sum > limit) {
                right--;
                res++;
            } else {
                left++;
                right--;
                res++;
            }
        }
        if (right == left) {
            res += 1;
        }
        return res;
    }
}
