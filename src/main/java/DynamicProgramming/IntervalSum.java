package DynamicProgramming;

public class IntervalSum {

    /**
     * 给你一个整数类型的数组，要求你每次取一个数字，但你取完这个数字后，这个数字左边和右边的数字不允许去取，
     * 输出你能取到的数字累加起来总数最大的值。
     *
     * 如：
     * 输入：[2,5,3,4]
     * 输出：9
     * 解释：先拿5，2和3不允许拿，然后拿4，累加起来最大的总数为5 + 4 = 9。
     */
    public static int getResult(int[] array)  {
        int n = array.length;
        if (n < 3) {
            int max = array[0];
            for (int i = 1; i < n; i++) {
                max = Math.max(array[i], max);
            }
            return max;
        }

        int[] dp = new int[n];
        dp[0] = array[0];
        dp[1] = Math.max(array[0], array[1]);
        dp[2] = Math.max(array[0] + array[2], array[1]);
        for (int i = 3; i < n; i++) {
            dp[i] = Math.max(dp[i-2] + array[i], dp[i-3] + array[i]);
        }

        return Math.max(dp[n-1], dp[n-2]);
    }

    public static void main(String[] args) {
        System.out.println(getResult(new int[]{2,5,3,4}));
    }
}
