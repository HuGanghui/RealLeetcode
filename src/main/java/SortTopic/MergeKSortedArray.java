package SortTopic;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 2021.4.18 腾讯笔试
 *
 * 这题本质上就是合并多个有序数组，使用优先队列来完成k路排序是ok的，这题笔试过了60%，不清楚
 * 为啥，后面讨论，可能可以在user数量那剪枝，因为只需要输出k个，如果某个用户的最小时间倍数比
 * 全部用户最小时间倍数*k都小，就不需要考虑了。。。
 */
public class MergeKSortedArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        Pair[] t = new Pair[n+1];
        for(int i = 1; i <= n; i++){
            t[i] = new Pair(i, sc.nextInt());
        }
        getSequence(n, k, t);
    }

    private static void getSequence(int n, int k, Pair[] t) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.time == o2.time) {
                return o1.user - o2.user;
            }
            return o1.time - o2.time;});
        for (int i = 1; i <= n; i++) {
            pq.offer(t[i]);
        }
        for (int i = 0; i < k; i++) {
            Pair pair = pq.poll();
            System.out.println(pair.user);
            int cishu = pair.time / t[pair.user].time;
            pq.offer(new Pair(pair.user, t[pair.user].time * (cishu + 1)));
        }
    }
}

class Pair {
    int user;
    int time;
    public Pair(int user, int time) {
        this.user = user;
        this.time = time;
    }
}
