package BinarySearch;

/**
 * 4. 寻找两个正序数组的中位数 Hard
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 *
 * 将两个数组merge成一个，然后返回对应位置的数字即可，时间空间复杂度都是O(m+n)，但着不是最优的方法，
 *
 * 二分查找是本题最优的方法，通过比较A[k/2 - 1] 和 B[k/2 - 1]，哪个小，前面最多有k-2个数比它小，肯定不是
 * 第k个数，所以舍弃，这样每次都可以舍弃一半。
 */
public class FindMedianSortedArrays {

    // 二分的方法 时间复杂度都是O(log(m+n)) 空间复杂度O(1)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        double median = 0;
        if ((m + n) % 2 == 1) {
            int midIndex = (m + n) / 2;
            median = getKthElement(nums1, nums2, midIndex + 1);
        } else {
            int midIndex = (m + n) / 2;
            median = (getKthElement(nums1, nums2, midIndex) +
                    getKthElement(nums1, nums2, midIndex + 1)) / 2.0;
        }
        return median;
    }

    private double getKthElement(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;
        int index1 = 0;
        int index2 = 0;

        while (true) {
            if (index1 == n) {
                return nums2[index2 + k - 1];
            }
            if (index2 == m) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }

            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, n) - 1;
            int newIndex2 = Math.min(index2 + half, m) - 1;
            int pivot1 = nums1[newIndex1];
            int pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }

    // Merge方法，时间空间复杂度都是O(m+n)
    public double findMedianSortedArraysMerge(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        boolean odd = (m + n) % 2 == 1;
        int mid = (m + n) / 2;
        int[] new_nums = new int[m+n];
        int k = 0;
        int i = 0;
        int j = 0;
        while (i < n && j < m) {
            if (nums1[i] < nums2[j]) {
                new_nums[k++] = nums1[i++];
            } else {
                new_nums[k++] = nums2[j++];
            }
        }

        while (i < n) {
            new_nums[k++] = nums1[i++];
        }

        while (j < m) {
            new_nums[k++] = nums2[j++];
        }

        if (odd) {
            return new_nums[mid];
        } else {
            return (new_nums[mid-1] + new_nums[mid]) / 2.0;
        }
    }
}
