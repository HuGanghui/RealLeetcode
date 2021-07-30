package SimulationTopic.GreedyTopic;

import java.util.*;

/**
 * 1353. 最多可以参加的会议数目 Median
 * https://leetcode-cn.com/problems/maximum-number-of-events-that-can-be-attended/
 *
 * 数组排序加优先队列，数组排序按会议开始时间早-晚排序，优先队列按会议结束时间早-晚排序。
 * 两层while + if 外面控制出队，内部控制入队。这题多了一个while是过期的时间剔除，需要将结束的会议从优先队列中去除。
 *
 */
public class MaxEvents {
    public int maxEvents(int[][] events) {
        int n = events.length;
        Arrays.sort(events, (o1, o2) -> {
            return o1[0] - o2[0];
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            return o1 - o2;
        });

        int startDay = events[0][0];
        int i = 0;
        int count = 0;
        while (i < n || !pq.isEmpty()) {
            while (!pq.isEmpty() && pq.peek() < startDay) {
                pq.poll();
            }

            while (i < n && events[i][0] <= startDay) {
                pq.offer(events[i][1]);
                i++;
            }

            if (!pq.isEmpty()) {
                pq.poll();
                count++;
            }
            startDay++;
        }

        return count;

    }
}
