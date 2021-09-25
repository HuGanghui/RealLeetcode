package BFSearch;

import java.util.*;

/**
 * 牛牛旅行 阿里巴巴 2021.09.24 笔试
 * n座城市，有m条双向路径，第i条代表（ui,vi,ci) ui和vi之间需要ci个小时，
 * 对第i座城市，开放时间也就是0，wi，2wi，...，kwi...时刻开放。到达城市
 * 也需要等待到开放时间才能进入，进入后会花费pi个小时来进行休整。
 *
 * 现在牛牛在s城市处，在0时刻，牛牛从s城市出发，他想知道最快要在几时才能到底t城市（无需进入）？
 *
 * 这种题目，最直观的解法肯定就是遍历，但是首先需要考虑是DFS还是BFS，求最短/快这种，会更偏向于BFS。
 * 因此这题也是，笔试过程中我不假思索的写了DFS（也是因为更熟悉），但是超时了，无法通过。
 *
 * 牛客讨论有人说使用BFS通过了，当然额外加了一个优先队列的优化，这个可能有用可能无所谓。
 */
public class NiuNiuTravel {
    // 邻接矩阵 不是很友好
    private static int[][] matrix;
    private static int n;
    private static int m;
    private static int[] w;
    private static int[] p;
    private static int start;
    private static int end;

    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        w = new int[n+1];
        for (int i = 1; i <= n; i++) {
            w[i] = sc.nextInt();
        }
        p = new int[n+1];
        for (int i = 1; i <= n; i++) {
            p[i] = sc.nextInt();
        }
        matrix = new int[n+1][n+1];
        for (int i = 0; i < m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int cost = sc.nextInt();
            matrix[start][end] = cost;
            matrix[end][start] = cost;
        }
        start = sc.nextInt();
        end = sc.nextInt();
        dfs(new HashSet<>(), start, 0, 0);
        System.out.println(resTime);
    }

    private static int resTime = -1;

    // DFS 超时
    // 因为最后要的时间是无需进入，因此额外维护一个arriveTime
    private static void dfs(Set<Integer> visited, int index, int time, int arriveTime) {

        if (visited.contains(index)) {
            return;
        }

        if (index == end) {
            if (resTime == -1 || arriveTime < resTime) {
                resTime = arriveTime;
            }
            return;
        }

        int[] cur = matrix[index];
        visited.add(index);
        for (int i = 1; i <= n; i++) {
            int tempTime = time;
            int tempArriveTime = arriveTime;
            if (cur[i] != 0) {
                tempTime += cur[i];
                tempArriveTime = tempTime;
                if (tempTime > w[i]) {
                    int left = tempTime % w[i];
                    int k = tempTime / w[i];
                    if (left != 0) {
                        tempTime = (k+1) * w[i];
                    }
                } else {
                    tempTime = w[i];
                }
                tempTime += p[i];
                dfs(visited, i, tempTime, tempArriveTime);
            }
        }
        visited.remove(index);
    }
}

class BFSolution {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] w = new int[n+1];
        for (int i = 1; i <= n; i++) {
            w[i] = sc.nextInt();
        }
        int[] p = new int[n+1];
        for (int i = 1; i <= n; i++) {
            p[i] = sc.nextInt();
        }

        Map<Integer, List<Pair>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int cost = sc.nextInt();
            putElem(map, start, end, cost);
            putElem(map, end, start, cost);
        }
       int start = sc.nextInt();
       int end = sc.nextInt();

       PriorityQueue<Pair> queue = new PriorityQueue<>((o1, o2) -> {
           return o1.cost - o2.cost;
       });

       boolean[] visited = new boolean[n+1];

       queue.offer(new Pair(start, 0));

       while (!queue.isEmpty()) {
           int size = queue.size();
           for (int i = 0; i < size; i++) {
               Pair cur = queue.poll();
               int index = cur.index;
               // 判断是否达到目标
               if (index == end) {
                   System.out.println(cur.cost);
                   return;
               }
               // 判断是否以及访问过
               if (!visited[index]) {
                   visited[index] = true;
                   if (map.containsKey(index)) {
                       List<Pair> list = map.get(index);
                       for (Pair pair : list) {
                           int tempTime = cur.cost + pair.cost;
                           if (pair.index != end) {
                               if (tempTime > w[i]) {
                                   int left = tempTime % w[i];
                                   int k = tempTime / w[i];
                                   if (left != 0) {
                                       tempTime = (k+1) * w[i];
                                   }
                               } else {
                                   tempTime = w[i];
                               }
                               tempTime += p[i];
                           }
                           queue.add(new Pair(pair.index, tempTime));
                       }
                   }
               }
           }
       }
       System.out.println(-1);
    }

    private static void putElem(Map<Integer, List<Pair>> map, int index, int to, int cost) {
        if (!map.containsKey(index)) {
            List<Pair> list = new ArrayList<>();
            list.add(new Pair(to, cost));
            map.put(index, list);
        } else {
            map.get(index).add(new Pair(to, cost));
        }
    }
}

class Pair {
    public int index;
    public int cost;

    public Pair() {}

    public Pair(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }
}
