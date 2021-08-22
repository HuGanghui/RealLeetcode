package BFSearch;

import java.util.*;

/**
 * 多多的心情 2021.08.22 拼多多笔试题
 *
 * 从start 到 end，需要的最小的天数
 * 有 -2 -1 +1 和 双倍四种改变，一般求最小这种，BFS是可以的尝试的。
 */
public class DuoduodeMood {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 0 -2
        // 1 -1
        // 2 +1
        // 3 double
        for (int i = 0; i < n; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            getResult(start, end);
        }
    }

    private static void getResult(int start, int end) {
        Deque<Integer> deque = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        if (start == end) {
            System.out.println(0);
            return;
        }
        deque.addLast(start);
        int day = 0;
        while(!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                int cur = deque.removeFirst();
                if (cur == end) {
                    System.out.println(day);
                    return;
                }
                int next = cur - 2;
                if (!set.contains(next)) {
                    deque.addLast(next);
                    set.add(next);
                }
                next = cur - 1;
                if (!set.contains(next)) {
                    deque.addLast(next);
                    set.add(next);
                }
                next = cur + 1;
                if (!set.contains(next)) {
                    deque.addLast(next);
                    set.add(next);
                }
                next = cur * 2;
                // **不能让它无限增大** 不加这个就超时。。。恶心了
                if (next < end && !set.contains(next)) {
                    deque.addLast(next);
                    set.add(next);
                }
            }
            day++;
        }

    }
}
