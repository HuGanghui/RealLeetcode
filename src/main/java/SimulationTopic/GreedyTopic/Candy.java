package SimulationTopic.GreedyTopic;

/**
 * 135. 分发糖果 Hard
 * https://leetcode-cn.com/problems/candy/
 *
 * 根据规则要求比两侧都高的，可以转化为左右两边各自遍历，取
 * 最大值即可。
 */
public class Candy {
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i-1]) {
                left[i] = left[i-1] + 1;
            } else {
                left[i] = 1;
            }
        }

        int right = 1;
        int res = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1]) {
                right++;
            } else {
                right = 1;
            }
            res += Math.max(right, left[i]);
        }
        res += left[n-1];
        return res;
    }
}
