package BJTUAlgorithomBook.Chapter4DivideAndConquer;

import java.util.Scanner;

public class MultiThreadMergeSort implements Runnable {
    private int[] iDatas;
    private int[] iBuffers;
    private int start;
    private int end;

    public MultiThreadMergeSort(int[] iDatas, int[] iBuffers, int start, int end) {
        this.iDatas = iDatas;
        this.iBuffers = iBuffers;
        this.start = start;
        this.end = end;
    }

    public void run() {
        mergeSort(iDatas, iBuffers, start, end);
    }

    private static void mergeSort(int[] iDatas, int[] iBuffers, int iLow, int iHigh) {
        if (iHigh > iLow) {
            int iMid = (iLow + iHigh) / 2;
            mergeSort(iDatas, iBuffers, iLow, iMid);
            mergeSort(iDatas, iBuffers, iMid + 1, iHigh);
            merge(iDatas, iBuffers, iLow, iMid, iHigh);
            for (int i = iLow; i<= iHigh; i++) {
                iDatas[i] = iBuffers[i];
            }
        }
    }

    private static void merge(int[] iDatas, int[] iBuffers, int iLow, int iMid, int iHigh) {
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

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        int size = s.nextInt();
        int [] iBuffers = new int[size];
        int [] iDatas = new int[size];
        for (int i = 0; i< size; i++) {
            iDatas[i] = s.nextInt();
        }
        int split = size / 2;
        // 添加多线程
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
        for (int i : iDatas) {
            System.out.print(i + " ");
        }
    }
}
