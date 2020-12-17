package BinarySearch;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 349. 两个数组的交集 Easy
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/
 *
 * 排序+二分是其中一种做法，还有其它做法后面可以总结
 */
public class Intersection {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        // Arrays.sort(nums2);
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums2.length; i++) {
            if(binarySearch(nums1, nums2[i])) {
                set.add(nums2[i]);
            }
        }

        int[] resultArray = new int[set.size()];
        int i = 0;
        for (int ele : set) {
            resultArray[i++] = ele;
        }
        return resultArray;
    }

    private boolean binarySearch(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] > target) {
                right = middle -1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Intersection intersection = new Intersection();
        intersection.intersection(new int[]{4,9,5}, new int[]{9,4,9,8,4});
    }
}
