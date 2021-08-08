package DynamicProgramming;

/**
 * 最小操作次数 美团笔试 2021.08.08
 *
 * 长度为n（n为偶数）的序列A，需要将序列变为前一半和后一半是
 * 相等的，也就是 A[i] == A[i + n / 2]
 *
 * 可以进行任意次操作：
 * 将序列A中的所有为x的值替换为y
 * 每次的操作都会在上次的操作基础上进行，求最小操作次数
 *
 * 核心在于从前往后依次判断就行，如果相等就不变，不等则操作一次，
 * 然后将对应的序列也做相应的修改。然后不断进行，关于换哪个是无所谓的。
 *
 * 输入：
 * 10
 * 4 2 1 5 2 10 2 1 5 8
 *
 * 输出：
 * 2
 *
 * 这题的核心在于想到之前的变完了之后，是不影响后面的结果的。
 */
public class MinOperationNum {
    private static int[] array;

    public static void main(String[] args) {
        int n = 10;
        array = new int[] {4, 2, 1, 5, 2, 10, 2, 1, 5, 8};
        int[] dp = new int[n];

        if (array[0] != array[n/2]) {
            dp[0] = 1;
            replace(array[0], array[n/2], 0);
        }

        for (int i = 1; i < n / 2; i++) {
            if (array[i] == array[i + n / 2]) {
                dp[i] = dp[i-1];
            } else {
                dp[i] = dp[i-1] + 1;
                replace(array[i], array[i+n/2], i);
            }
        }
        System.out.println(dp[n/2 - 1]);
    }

    private static void replace(int x, int y, int index) {
        for (int i = index; i < array.length; i++) {
            if (array[i] == x) {
                array[i] = y;
            }
        }
    }
}
