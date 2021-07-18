package SimulationTopic.GreedyTopic;

/**
 * 5814. 新增的最少台阶数 Median
 * https://leetcode-cn.com/problems/add-minimum-number-of-rungs/
 * LeetcodeWeeklyMatch 250
 *
 * 这题直观上就是每个区间来进行台阶的增加即可，感觉上是道模拟题，但也可以说是贪心。
 * 不过本题值得注意的就是int直接做计算得到的都是int，因此如果需要保留小数，需要
 * 加上一个小数，比如 0.0。
 */
public class AddRungs {
    public int addRungs(int[] rungs, int dist) {
        int n = rungs.length;
        int result = 0;

        for(int i = 0; i < n; i++) {
            int temp;
            if (i == 0) {
                temp = rungs[i] - 0;
            } else {
                temp = rungs[i] - rungs[i-1];
            }
            if (temp > dist) {
                double left = temp / (dist + 0.0);
                if (left == (int) left) {
                    result += (left - 1);
                } else {
                    result += (int)(left);
                }
            }
        }
        return result;
    }
}
