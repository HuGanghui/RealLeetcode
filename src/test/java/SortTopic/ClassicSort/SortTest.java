package SortTopic.ClassicSort;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SortTest {
    private int [] expected = new int[] {4, 5, 10, 12, 32};

    @Test
    public void SelectSortTest() throws Exception {
        int [] iDatas = new int[] {10, 32, 12, 5, 4};
        SelectSort selectSort = new SelectSort();
        selectSort.sort(iDatas);
        for (int i = 0; i< expected.length; i++) {
            assertEquals(expected[i], iDatas[i]);
        }
    }

    @Test
    public void BubbleSortTest() throws Exception {
        int [] iDatas = new int[] {10, 32, 12, 5, 4};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(iDatas);
        for (int i = 0; i< expected.length; i++) {
            assertEquals(expected[i], iDatas[i]);
        }
    }

    @Test
    public void InsertSortTest() throws Exception {
        int [] iDatas = new int[] {10, 32, 12, 5, 4};
        InsertSort insertSort = new InsertSort();
        insertSort.sort(iDatas);
        for (int i = 0; i< expected.length; i++) {
            assertEquals(expected[i], iDatas[i]);
        }
    }

    @Test
    public void QuickSortTest() throws Exception {
        int [] iDatas = new int[] {10, 32, 12, 5, 4};
        QuickSort quickSort = new QuickSort();
        quickSort.sort(iDatas);
        for (int i = 0; i< expected.length; i++) {
            assertEquals(expected[i], iDatas[i]);
        }
    }

    @Test
    public void MergeSortTest() throws Exception {
        int [] iDatas = new int[] {10, 32, 12, 5, 4};
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(iDatas);
        for (int i = 0; i< expected.length; i++) {
            assertEquals(expected[i], iDatas[i]);
        }
    }

    @Test
    public void HeapSortTest() throws Exception {
        int [] iDatas = new int[] {10, 32, 12, 5, 4};
        HeapSort heapSort = new HeapSort();
        heapSort.sort(iDatas);
        for (int i = 0; i< expected.length; i++) {
            assertEquals(expected[i], iDatas[i]);
        }
    }

    @Test
    public void CountingSortTest() throws Exception {
        int [] iDatas = new int[] {10, 32, 12, 5, 4};
        CountingSort countingSort = new CountingSort();
        countingSort.sort(iDatas);
        for (int i = 0; i< expected.length; i++) {
            assertEquals(expected[i], iDatas[i]);
        }
    }

    @Test
    public void RadixSortTest() throws Exception {
        int [] iDatas = new int[] {10, 32, 12, 5, 4};
        RadixSort radixSort = new RadixSort();
        radixSort.sort(iDatas);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], iDatas[i]);
        }
    }
}
