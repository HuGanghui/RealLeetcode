package BinarySearch.RotatedSortedArray;

/**
 * 33. 搜索旋转排序数组 Median
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 *
 * 这题是要求在旋转数组中寻找给定的target值，这题的思路是通过每次二分后有序的那部分来
 * 进行比较，通过mid 和 left 来的大小来判断哪left-mid mid-right 哪部分是有序的，然后
 * 在有序的那部分进行判断，如果在有序那部分的范围内则进一步缩小范围，否则就转到另外一部分去。
 *
 * 利用有序部分的思想还是值得借鉴的。
 */
public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length -1;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                result = mid;
                break;
            }
            // 这个等于号还挺重要的，因为当mid == left时，左边也是有序的
            if (nums[mid] >= nums[left]) {
                if (nums[mid] > target && nums[left] <= target) {
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && nums[right] >= target) {
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
        }
        return result;
    }
}
