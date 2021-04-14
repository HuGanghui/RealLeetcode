package SortTopic.ClassicSort;

/**
 * 堆排序（就地堆排序）
 * 借助完全二叉树实现，时间复杂度是O(nlog(n)) 空间复杂度O(1)
 *
 * 堆排序涉及到了下滤算法以及建堆算法：自下而上的不断下滤，就地堆排序首先建立一个大顶堆，
 * 然后依次将堆顶元素与堆尾元素交换后，再进行下滤操作，最后就完成了堆排序。
 *
 * 更进一步，可以利用完全二叉树来构建优先队列，对优先队列则需要增加在插入操作insert()，通常的实现方式
 * 就是加入到堆尾，然后迭代进行上滤操作；删除堆顶元素操作其实和堆排序很像，只不过不是交换，而是
 * 删除堆顶元素后，再将堆尾放到堆顶，然后进行下滤操作。多说一句，这里的相关操作其实根据自己的应用需求
 * 进行更改。比如多路归并，需要持续不断的插入元素，那就没必要delMin()后，再insert()，而是delMin()
 * 后直接进行堆顶插入，再下滤。
 *
 */
public class HeapSort implements ArraySort {
    @Override
    public void sort(int[] array) {
        int n = array.length;

        buildMaxHeap(array, n);

        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            n--;
            heapify(array, 0, n);
        }
    }

    private void buildMaxHeap(int[] arr, int len) {
        for (int i = (int) Math.floor(len / 2); i >= 0; i--) {
            heapify(arr, i, len);
        }
    }

    private void heapify(int[] arr, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }

        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, largest, len);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
