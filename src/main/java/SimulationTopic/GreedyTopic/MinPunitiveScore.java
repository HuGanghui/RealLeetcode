package SimulationTopic.GreedyTopic;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 2021.4.18 腾讯笔试
 * <p>
 * 1 // 测试组数
 * 3 // 游戏个数
 * 3 1 1 // 需要在t时刻前完成的游戏的t时刻，每个游戏完成只需要1个单位的时间，假设从时间从0开始
 * 5 6 9 // 未完成的对应惩罚
 * <p>
 * 看到题目就知道是典型的贪心算法，并且要利用优先队列，但是这题和5736. 单线程 CPU Median 虽然内核是相似的，
 * 但是表现还是有所区别，导致我贪心策略使用错误，当然另外一个原因就是样例太少，没有考虑全面所有情况，因此之后
 * 需要自己多写几个样例思考一下。
 * <p>
 * 首先贪心肯定要保证惩罚最大的被玩掉，但是这里还有一个维度是时间time，不同于单线程CPU，时间从前往后，选择逐渐增多，
 * 这里最开始的时间time最值钱，可以选择任何游戏，这就很难考虑，因此有一个技巧就是反过来思考，先将数组按时间顺序排列，
 * 然后时间从后往前放入优先队列，这样选择就和单线程CPU一样，选择逐渐增多了。可能使用贪心，这也算是一个标志吧。
 */
public class MinPunitiveScore {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int gamesize = sc.nextInt();
            int[][] timeAndDian = new int[gamesize][2];
            int sumDian = 0;
            for (int j = 0; j < gamesize; j++) {
                timeAndDian[j][0] = sc.nextInt();
            }
            for (int j = 0; j < gamesize; j++) {
                timeAndDian[j][1] = sc.nextInt();
                sumDian += timeAndDian[j][1];
            }
            getMinDian(timeAndDian, sumDian);
        }

    }

    private static void getMinDian(int[][] timeAndDian, int sumDian) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1]) {
                return -o1[0] + o2[0];
            }
            return o1[1] - o2[1];
        });
        Arrays.sort(timeAndDian, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return -o1[1] + o2[1];
            }
            return -o1[0] + o2[0]; // 从大到小
        });
        int curtime = timeAndDian[0][0];
        int i = 0;
        // 和CPU非常的相似，两层循环，内部是offer，外部是poll
        while (curtime >= 1) {
            while (i < timeAndDian.length && timeAndDian[i][0] >= curtime) {
                pq.offer(timeAndDian[i]);
                i++;
            }
            if (!pq.isEmpty()) {
                sumDian -= pq.poll()[1];
            }
            curtime--;
        }
        System.out.println(sumDian);
    }

    // 错误解答
//    private static void getMinDian(int[] time, int[] dian) {
//        PriorityQueue<Pair2> pq = new PriorityQueue<>((o1, o2) -> {
//            if (o1.time == o2.time) {
//                return -o1.dian + o2.dian;
//            }
//            return o1.time - o2.time;
//        });
//        for (int i = 0; i < time.length; i++) {
//            pq.offer(new Pair2(time[i], dian[i]));
//        }
//
//        int curtime = 0;
//        int result = 0;
//        while (!pq.isEmpty()) {
//            Pair2 game = pq.poll();
//            curtime = game.time;
//            while (!pq.isEmpty() && pq.peek().time <= curtime) {
//                result += pq.poll().dian;
//            }
//        }
//        System.out.println(result);
//    }

}