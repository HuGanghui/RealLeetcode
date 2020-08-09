package BJTUAlgorithomBook.Chapter4DivideAndConquer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MultiThreadMergeSortTest {

    @Test
    public void test() throws Exception {
        int size = 5;
        int [] iBuffers = new int[size];
        int [] iDatas = new int[] {1, 3, 2, 5, 4};
        int [] expected = new int[] {1, 2, 3, 4, 5};
        // 添加多线程
        int split = size / 2;
        MultiThreadMergeSort mergeSort = new MultiThreadMergeSort(iDatas, iBuffers, 0, split);
        MultiThreadMergeSort mergeSort1 = new MultiThreadMergeSort(iDatas, iBuffers, split + 1, size - 1);
        Thread thread = new Thread(mergeSort);
        thread.start();
        thread.join();
        Thread thread1 = new Thread(mergeSort1);
        thread1.start();
        thread1.join();
        merge(iDatas, iBuffers, 0, split, size - 1);
        for (int i = 0; i< size; i++) {
            iDatas[i] = iBuffers[i];
        }
        for (int i = 0; i< expected.length; i++) {
            assertEquals(expected[i], iDatas[i]);
        }
    }

    private void merge(int[] iDatas, int[] iBuffers, int iLow, int iMid, int iHigh) {
        int i = iLow;
        int j = iMid+1;
        int k = iLow;
        while (i <= iMid && j <= iHigh) {
            if (iDatas[i] < iDatas[j]) {
                iBuffers[k++] = iDatas[i++];
            }
            else {
                iBuffers[k++] = iDatas[j++];
            }
        }
        while (i <= iMid) {
            iBuffers[k++] = iDatas[i++];
        }
        while (j <= iHigh) {
            iBuffers[k++] = iDatas[j++];
        }
    }
}
