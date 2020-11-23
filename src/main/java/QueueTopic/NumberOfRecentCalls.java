package QueueTopic;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 933. 最近的请求次数 Easy
 * https://leetcode-cn.com/problems/number-of-recent-calls/
 *
 * 核心就是队列的使用，过去先进队的对未来如果没有在相应时间范围内就是没有用的，出队即可。
 */
public class NumberOfRecentCalls {

    Queue<Integer> queue = new ArrayDeque<>();

    public NumberOfRecentCalls() {

    }

    public int ping(int t) {
        int least = t - 3000;
        while (!queue.isEmpty() && queue.peek() < least) {
            queue.remove();
        }
        queue.add(t);
        return queue.size();
    }
}
