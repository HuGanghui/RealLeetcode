package SortTopic.ClassicSort;

import java.util.*;

/**
 * 快速选择
 *
 * 与快速排序的区别就是适合无需对整个数组进行排序，仅仅需要
 * 其中第k个元素的场景。时间复杂度根据算法导论获得的。
 *
 * 时间复杂度是O(n) 空间复杂度O(log(n))
 */
public class QuickSelect {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return quickSelect(nums, 0, n-1, n-k);
    }

    private int quickSelect(int[] nums, int left, int right, int k) {
        int partitionIndex = partition(nums, left, right);
        if (partitionIndex == k) {
            return nums[k];
        }
        return partitionIndex > k ?
                quickSelect(nums, left, partitionIndex-1, k)
                : quickSelect(nums, partitionIndex + 1, right, k);
    }

    private int partition(int[] arr, int left, int right) {
        // 优化：随机化主元
        int random = new Random().nextInt(right - left + 1) + left;
        swap(arr, left, random);

        // 设定基准值（pivot）
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[pivot]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, pivot, index - 1);
        return index - 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
