package DynamicProgramming.Linear.MaxProfit;

/**
 * 剑指 Offer 63. 股票的最大利润 Median
 * https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof/
 *
 * 由于只交易一次，从暴力法出发，做法就是外循环一遍以每个点为end，然后内循环遍历当前end
 * 之前的寻找其最小值，然后相减来获取最大利润，然后与全局最大比较。
 *
 * 但是这里有个trick，就是和前缀和类似，数组到当前最小值是可以一次遍历获取到的并记录下来的，
 * 因此就无需暴力了，而是可以将时间复杂度降低到O(N)。
 */
public class MaxProfit {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }
        int[] min = new int[n];
        min[0] = prices[0];
        for (int i = 1; i < n; i++) {
            min[i] = Math.min(min[i-1], prices[i]);
        }
        int result = 0;
        for (int i = 1; i < n; i++) {
            int temp = prices[i] - min[i-1];
            if (temp > result) {
                result = temp;
            }
        }
        return result;
    }
}
