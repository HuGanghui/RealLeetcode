package LeetcodeWeeklyMatch.TwoTwoOneWeekly;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 5638. 吃苹果的最大数目 Median
 * https://leetcode-cn.com/problems/maximum-number-of-eaten-apples/
 *
 * 周赛第二题，没做出来，当时想到了要优先吃最早会过期的（贪心的思想），但是没想到如何表示过期，
 * 参考其他人有解答是使用优先队列，每天先去除过期的，然后加入当天新产生的，最后
 * 看看有没有可以进行吃的苹果。
 */
public class EatenApples {
    public int eatenApples(int[] apples, int[] days) {
        int sum = 0;
        // o[0] 苹果数量 o[1] 过期时间
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] < o2[1]) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        for (int i = 0; i < apples.length || !queue.isEmpty(); i++) {
            while (!queue.isEmpty()) {  // 去除过期
                if (queue.peek()[1] <= i) {
                    queue.poll();
                } else {
                    break;
                }
            }

            if (i < apples.length) {  // 当日新增
                queue.add(new int[] {apples[i], days[i] + i});
            }

            if (!queue.isEmpty()) {  // 每日吃一个
                int[] temp = queue.poll();
                temp[0] -= 1;
                sum++;
                if (temp[0] > 0) {
                    queue.add(temp);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        EatenApples eatenApples = new EatenApples();
        int sum = eatenApples.eatenApples(new int[]{1,2,3,5,2},
new int[]{3,2,1,4,2});
        System.out.println();
    }
}
