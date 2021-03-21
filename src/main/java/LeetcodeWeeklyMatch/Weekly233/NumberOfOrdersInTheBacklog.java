package LeetcodeWeeklyMatch.Weekly233;

import java.util.PriorityQueue;

/**
 * 5710. 积压订单中的订单总数 Median
 * https://leetcode-cn.com/problems/number-of-orders-in-the-backlog/
 *
 * 这道题题目比较长，但是仔细看就是一个考察优先队列的使用，然后模拟整个过程就行。
 * 另外需要注意的一点就是对于结果的取模操作。
 */
public class NumberOfOrdersInTheBacklog {
    private final int MOD = (int) (Math.pow(10, 9) + 7);

    public int getNumberOfBacklogOrders(int[][] orders) {
        PriorityQueue<int[]> sellQueue = new PriorityQueue<>((o1, o2)->{return o1[0] - o2[0];});
        PriorityQueue<int[]> buyQueue = new PriorityQueue<>((o1, o2)->{return -o1[0] + o2[0];});
        for(int[] order : orders) {
            if (order[2] == 0) {
                while(!sellQueue.isEmpty() && sellQueue.peek()[0] <= order[0] && order[1] > 0) {
                    int[] topOrder = sellQueue.peek();
                    if (topOrder[1] > order[1]) {
                        topOrder[1] = topOrder[1] - order[1];
                        order[1] = 0;
                    } else if (topOrder[1] == order[1]) {
                        sellQueue.poll();
                        order[1] = 0;
                    } else {
                        sellQueue.poll();
                        order[1] = order[1] - topOrder[1];
                    }
                }
                if (order[1] > 0) {
                    buyQueue.offer(order);
                }
            } else {
                while(!buyQueue.isEmpty() && buyQueue.peek()[0] >= order[0] && order[1] > 0) {
                    int[] topOrder = buyQueue.peek();
                    if (topOrder[1] > order[1]) {
                        topOrder[1] = topOrder[1] - order[1];
                        order[1] = 0;
                    } else if (topOrder[1] == order[1]) {
                        buyQueue.poll();
                        order[1] = 0;
                    } else {
                        buyQueue.poll();
                        order[1] = order[1] - topOrder[1];
                    }
                }
                if (order[1] > 0) {
                    sellQueue.offer(order);
                }
            }
        }
        int result = 0;
        while(!sellQueue.isEmpty()) {
            result = result % MOD + sellQueue.poll()[1] % MOD;
        }
        while(!buyQueue.isEmpty()) {
            result = result % MOD + buyQueue.poll()[1] % MOD;
        }
        return result % MOD;


    }
}
