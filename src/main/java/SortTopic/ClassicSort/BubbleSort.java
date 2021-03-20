package SortTopic.ClassicSort;

/**
 * 冒泡排序
 *
 * 时间复杂度O(n^2) 空间复杂度O(1)
 */
public class BubbleSort implements ArraySort {
    @Override
    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            // 小优化，如果发现没有交换，说明已经排序完成
            boolean switched = false;
            for (int j = 0; j < array.length - i; j++) {
                if (array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    switched = true;
                }
            }
            if (!switched) {
                break;
            }
        }
    }
}
