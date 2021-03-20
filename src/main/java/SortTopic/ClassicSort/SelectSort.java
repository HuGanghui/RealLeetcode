package SortTopic.ClassicSort;

/**
 * 选择排序
 *
 * 时间复杂度O(n^2) 空间复杂度O(1)，这个算法与输入无关，无法利用
 * 输入数组的特点进行优化。
 */
public class SelectSort implements ArraySort {
    @Override
    public void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }
}
