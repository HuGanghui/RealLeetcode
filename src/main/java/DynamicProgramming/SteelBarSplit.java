package DynamicProgramming;

/**
 * 算法导论 第15章 动态规划问题 15.1 钢条切割问题
 */
public class SteelBarSplit {

    private int[] prices = new int[] {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
    private int[] rewards = new int[100000];

    public SteelBarSplit() {
        rewards[0] = 0;
        rewards[1] = 1;
        rewards[2] = 5;
        rewards[3] = 8;
    }

    /**
     * 动态规划解法，自底向上，时间复杂度O(n2)
     *
     * @param n 需要计算的钢管长度
     * @return 长度为n的钢管的最大收益
     */
    public int reward(int n) {
        if (n < 4) {
            return rewards[n];
        }
        for (int i = 4; i<=n; i++) {
            int ans = 0;
            for (int j = 1; j < ((i/2) + 1); j++) {
                int temp = rewards[j] + rewards[i-j];
                if (temp > ans) {
                    ans = temp;
                }
            }
            if (i <= 10 && ans < prices[i]) {
                rewards[i] = prices[i];
            } else {
                rewards[i] = ans;
            }
        }
        return rewards[n];
    }

    /**
     * 递归算法，时间复杂度O(2^n), 可以思考一下证明
     *
     * @param n 需要计算的钢管长度
     * @return 长度为n的钢管的最大收益
     */
    public int recusive_reward(int n) {
        if (n < 4) {
            return rewards[n];
        }

        int ans = 0;
        for (int i = 1; i < ((n / 2) + 1); i++) {
            int temp = recusive_reward(i) + recusive_reward(n-i);
            if (temp > ans) {
                ans = temp;
            }
        }
        if (n <= 10 && ans < prices[n]) {
            ans = prices[n];
        }
        return ans;
    }

    public static void main(String[] args) {
        SteelBarSplit steelBarSplit = new SteelBarSplit();
        for (int i = 0; i< 10000; i++) {
            System.out.println(i + "'s max rewards is " + steelBarSplit.reward(i));
            System.out.println(i + "'s max rewards is " + steelBarSplit.recusive_reward(i));
        }
    }
}
