package SortTopic.ClassicSort;

/**
 * 插入排序
 * 时间复杂度O(n^2) 空间复杂度O(1)，有个优化就是在排序好的
 * 查找过程中使用二分查找。
 */
public class InsertSort implements ArraySort {
    @Override
    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i;
            int temp = array[i];
            while (j > 0 && temp < array[j-1]) {
                array[j] = array[j-1];
                j--;
            }
            if (j != i) {
                array[j] = temp;
            }
        }
    }

    // 二分查找的优化 每次迭代过程时间复杂度O(log(n) + n)
    public void sortWithBS(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int left = 0;
            int right = i-1;
            int insertIndex = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (array[mid] <= array[i]) {
                    left = mid + 1;
                } else {
                    if (mid == 0 || array[mid-1] < array[i]) {
                        insertIndex = mid;
                        break;
                    } else {
                        right = mid - 1;
                    }
                }
            }
            if (insertIndex != -1) {
                int temp = array[i];
                for (int j = i; j > insertIndex; j++) {
                    array[j] = array[j-1];
                }
                array[insertIndex] = temp;
            }
        }
    }
}
