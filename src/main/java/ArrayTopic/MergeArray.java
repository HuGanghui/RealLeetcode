package ArrayTopic;

/**
 * 88. 合并两个有序数组 Easy
 * https://leetcode-cn.com/problems/merge-sorted-array/
 *
 * 这题看上去非常简单，但是关键在于如何原地完成合并，就一个trick，
 * 就是从大到小来合并，这样就可以利用上nums1的充足的空间。
 */
public class MergeArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1;
        int j = n-1;
        int k = m + n - 1;
        while (i >=0  && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                k--;
                i--;
            } else {
                nums1[k] = nums2[j];
                k--;
                j--;
            }
        }
        while (j >= 0) {
            nums1[k] = nums2[j];
            k--;
            j--;
        }
    }
}
