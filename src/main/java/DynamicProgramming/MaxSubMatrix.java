package DynamicProgramming;

/**
 * 面试题 17.24. 最大子矩阵 Hard
 * https://leetcode-cn.com/problems/max-submatrix-lcci/
 *
 * 这题有给数据范围：1 <= matrix.length, matrix[0].length <= 200
 * 因此时间复杂度可以到n^3，因此可以考虑一定程度上的枚举，并且我们熟悉
 * 最大子序列这种一维的题目，那么我们是否可以将二维转换为一维呢？显然是可行的，
 * 枚举不同的行涉及n^2的复杂度，在其中需要进行每列的求和，这里需要利用前缀和的
 * 方式，使得其复杂度是n，并且当获得压缩后的一维数组之后，最大子序列的DP算法时间
 * 复杂度也是n，因此总的时间复杂度就是O(n^3)，刚好满足题目要求。
 *
 * 这里值得学习的思想是1. 二维到一维的压缩，将没有遇到过的题如何转换为我们之前解决过
 * 的问题的思想；2. 关于DP算法需要获取最值还是要获取下标index，其实差别不大，只是需要
 * 记录一下。
 */
public class MaxSubMatrix {
    private int max = Integer.MIN_VALUE;

    public int[] getMaxMatrix(int[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        int[] partialSum = new int[c];
        int[] resultIndexs = new int[4];
        for (int i = 0; i < r; i++) {
            for (int k = 0; k < c; k++) {
                partialSum[k] = matrix[i][k];
            }
            int[] maxAndIndexes = getOneArrayMaxIndex(partialSum);
            if (maxAndIndexes[2] == max) {
                resultIndexs[0] = i;
                resultIndexs[1] = maxAndIndexes[0];
                resultIndexs[2] = i;
                resultIndexs[3] = maxAndIndexes[1];
            }
            for (int j = i+1; j < r; j++) {
                for (int k = 0; k < c; k++) {
                    partialSum[k] = partialSum[k] + matrix[j][k];
                }
                maxAndIndexes = getOneArrayMaxIndex(partialSum);
                if (maxAndIndexes[2] == max) {
                    resultIndexs[0] = i;
                    resultIndexs[1] = maxAndIndexes[0];
                    resultIndexs[2] = j;
                    resultIndexs[3] = maxAndIndexes[1];
                }
            }
        }
        return resultIndexs;
    }

    // MaxSubArray 但是不同在于需要知道Indexes，因此需要有个记录的指针
    private int[] getOneArrayMaxIndex(int[] array) {
        int n = array.length;
        int dp;
        dp = array[0];
        int begin = 0;
        int[] maxAndIndexes = new int[3];
        if (dp > max) {
            max = dp;
            maxAndIndexes[0] = 0;
            maxAndIndexes[1] = 0;
            maxAndIndexes[2] = max;
        }
        for (int i = 1; i < n; i++) {
            if (dp > 0) {
                dp = dp + array[i];
            } else {
                dp = array[i];
                begin = i;
            }
            if (dp > max) {
                max = dp;
                maxAndIndexes[0] = begin;
                maxAndIndexes[1] = i;
                maxAndIndexes[2] = max;
            }
        }
        return maxAndIndexes;
    }
}
