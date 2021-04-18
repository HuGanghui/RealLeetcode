package SimulationTopic;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 5736. 单线程 CPU Median
 * https://leetcode-cn.com/problems/single-threaded-cpu/
 * LeetcodeWeeklyMatch 238
 *
 * 典型的模拟题，但是这题稍微有些复杂，不是一起入队，而是需要根据cpu时间来确定入队的时机，
 * 有几种情况需要考虑清楚，然后值得一提的是对于二维乃至多维数组默认排序的方法，Arrays.sort
 * 通过自定义比较规则可以方便使用。
 */
public class SingleThreadedCpu {
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[][] newTasks = new int[n][3];
        for (int i = 0; i < n; i++) {
            newTasks[i][0] = tasks[i][0];
            newTasks[i][1] = tasks[i][1];
            newTasks[i][2] = i;
        }

        Arrays.sort(newTasks, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[2] - o2[2];
            }
            return o1[0] - o2[0];
        });

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] < o2[1]) {
                return -1;
            } else if (o1[1] == o2[1]) {
                return o1[2] - o2[2];
            } else {
                return 1;
            }
        });


        // 模拟CPU时间
        int time = -1;
        int i = 0;
        int[] result = new int[n];
        int j = 0;
        while(j < n) {
            while (i < n && (newTasks[i][0] <= time || pq.isEmpty())) {
                time = Math.max(newTasks[i][0], time);
                pq.offer(newTasks[i]);
                i++;
            }
            if (!pq.isEmpty()) {
                int[] task = pq.poll();
                time = time + task[1];
                result[j] = task[2];
                j++;
            }
        }
        return result;
    }
}
