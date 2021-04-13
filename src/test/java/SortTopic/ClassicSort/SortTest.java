package SortTopic.ClassicSort;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SortTest {
    private int [] expected = new int[] {1, 2, 3, 4, 5};

    @Test
    public void SelectSortTest() throws Exception {
        int [] iDatas = new int[] {1, 3, 2, 5, 4};
        SelectSort selectSort = new SelectSort();
        selectSort.sort(iDatas);
        for (int i = 0; i< expected.length; i++) {
            assertEquals(expected[i], iDatas[i]);
        }
    }

    @Test
    public void BubbleSortTest() throws Exception {
        int [] iDatas = new int[] {1, 3, 2, 5, 4};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.sort(iDatas);
        for (int i = 0; i< expected.length; i++) {
            assertEquals(expected[i], iDatas[i]);
        }
    }

    @Test
    public void InsertSortTest() throws Exception {
        int [] iDatas = new int[] {1, 3, 2, 5, 4};
        InsertSort insertSort = new InsertSort();
        insertSort.sort(iDatas);
        for (int i = 0; i< expected.length; i++) {
            assertEquals(expected[i], iDatas[i]);
        }
    }

    @Test
    public void QuickSortTest() throws Exception {
        int [] iDatas = new int[] {1, 3, 2, 5, 4};
        QuickSort quickSort = new QuickSort();
        quickSort.sort(iDatas);
        for (int i = 0; i< expected.length; i++) {
            assertEquals(expected[i], iDatas[i]);
        }
    }

    @Test
    public void MergeSortTest() throws Exception {
        int [] iDatas = new int[] {1, 3, 2, 5, 4};
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(iDatas);
        for (int i = 0; i< expected.length; i++) {
            assertEquals(expected[i], iDatas[i]);
        }
    }
}
