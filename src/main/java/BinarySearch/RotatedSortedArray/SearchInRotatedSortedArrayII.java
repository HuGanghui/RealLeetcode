package BinarySearch.RotatedSortedArray;

/**
 * 81. 搜索旋转排序数组 II Median
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
 *
 * 这题和I的区别在于元素可以重复，这会导致类似凹字，这种：那这种情况下，我们就排除一个
 * left，因为mid==left，而且前面判断过mid不是target了。
 *    * * left     * mid *
 *             * *
 *
 */
public class SearchInRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length -1;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                result = mid;
                break;
            }

            if (nums[mid] == nums[left]) {
                left += 1;
            } else if (nums[mid] > nums[left]) {
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
        return result != -1;
    }
}
