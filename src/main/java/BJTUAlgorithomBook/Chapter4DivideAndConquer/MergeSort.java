package BJTUAlgorithomBook.Chapter4DivideAndConquer;

import java.util.Scanner;

public class MergeSort implements Runnable {
    private int[] iDatas;
    private int[] iBuffers;
    private int start;
    private int end;

    public MergeSort(int[] iDatas, int[] iBuffers, int start, int end) {
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
        MergeSort mergeSort = new MergeSort(iDatas, iBuffers, 0, size-1);
        mergeSort.run();
        for (int i : iDatas) {
            System.out.print(i + " ");
        }
    }
}
