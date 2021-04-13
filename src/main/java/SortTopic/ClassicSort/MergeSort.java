package SortTopic.ClassicSort;

/**
 * 归并排序
 * <p>
 * 时间复杂度是O(nlog(n)) 空间复杂度O(n)，需要和进行排序
 * 的算法一样长的辅助空间。
 * <p>
 * 算法4提到递归会导致小规模问题中的方法调用过于频繁，因此可以改进
 * 对小数组的处理方法，比如当数组长度小于15，使用选择/插入排序代替
 * 归并排序的操作，然后这样一般会使性能提升10%-15%。
 *
 * 并且还有除了常见的递归的方式完成归并，还有迭代的方式来完成。
 */
public class MergeSort implements ArraySort {
    private int[] aux;

    @Override
    public void sort(int[] array) {
        aux = new int[array.length];
        mergeSort(array, 0, array.length-1);
    }

    private void mergeSort(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        mergeSort(array, low, mid);
        mergeSort(array, mid + 1, high);
        merge(array, low, mid, high);
    }

    private void merge(int[] arr, int low, int mid, int high) {
        for (int k = low; k <= high; k++) {
            aux[k] = arr[k];
        }
        int k = low;
        int i = low;
        int j = mid + 1;
        while (i <= mid && j <= high) {
            if (aux[i] < aux[j]) {
                arr[k++] = aux[i++];
            } else {
                arr[k++] = aux[j++];
            }
        }
        while(i <= mid) {
            arr[k++] = aux[i++];
        }
        while(j <= high) {
            arr[k++] = aux[j++];
        }
    }

    // 自低向上的方法，迭代完成归并排序
    public void sortBU(int[] array) {
        int N = array.length;
        aux = new int[N];
        for (int sz = 1; sz < N; sz = 2 * sz) {
            for (int lo = 0; lo < N - sz; lo += 2 * sz) {
                merge(array, lo, lo + sz - 1, Math.min(lo + 2 * sz - 1, N - 1));
            }
        }
    }
}
