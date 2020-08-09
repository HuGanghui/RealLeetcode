package BJTUAlgorithomBook.Chapter4DivideAndConquer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MergeSortTest {

    @Test
    public void test() throws Exception {
        int size = 5;
        int [] iBuffers = new int[size];
        int [] iDatas = new int[] {1, 3, 2, 5, 4};
        int [] expected = new int[] {1, 2, 3, 4, 5};
        MergeSort mergeSort = new MergeSort(iDatas, iBuffers, 0, size-1);
        mergeSort.run();
        for (int i = 0; i< expected.length; i++) {
            assertEquals(expected[i], iDatas[i]);
        }
    }

}
