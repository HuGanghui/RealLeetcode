package BinarySearch;

/**
 * 852. 山脉数组的峰顶索引 Easy
 * https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/
 *
 * 这道题也是有序的，虽然没有那么的典型，是一个山峰状的序列，我们要找山峰，就相当于找的最后一个
 * arr[middle] > arr[middle - 1]的下标索引，因此算二分查找的一个变种。
 */
public class PeakIndex {
    public int peakIndexInMountainArray(int[] arr) {
        int left = 1; // index = 0 必然不可能为答案，因此从1开始，也避免了 middle - 1 小于 0 的问题
        int right = arr.length - 1;
        int index = -1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (arr[middle] > arr[middle-1]) {
                if (middle == arr.length -1 || arr[middle] > arr[middle+1]) {
                    index = middle;
                    return index;
                }
                left = middle + 1;
            } else if (arr[middle] <= arr[middle -1]) {
                right = middle - 1;
            }
        }
        return index;
    }
}
