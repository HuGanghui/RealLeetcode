package ArrayTopic;

/**
 * 面试题 10.01. 合并排序的数组 Easy --
 * https://leetcode-cn.com/problems/sorted-merge-lcci/
 *
 * 这本质上就是对数组做一次归并排序，但是不同在于对空间有限制，要求在其中一个数组内完成合并，
 * 之前我们做都会额外的开辟一个数组空间。因此这题的突破就在于某个数组后方有充足的空间，我们可以
 * 从后往前来处理合并，这样，哪怕到了前面也不用怕被覆盖。
 */
public class SortedArrayMerge {
    public void merge(int[] A, int m, int[] B, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = A.length - 1;
        while (i >= 0 && j >= 0) {
            if (A[i] >= B[j]) {
                A[k--] = A[i--];
            } else {
                A[k--] = B[j--];
            }
        }
        if (i < 0) {
            while (j >= 0) {
                A[k--] = B[j--];
            }
        }
    }
}
