package BFSearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 5793. 迷宫中离入口最近的出口 Median
 * https://leetcode-cn.com/problems/nearest-exit-from-entrance-in-maze/
 * LeetcodeDoubleWeeklyMatch 56
 *
 * 对于求最短步数这种题目，常用的套路就是BFS。
 */
public class NearestExit {
    public int nearestExit(char[][] maze, int[] entrance) {
        int[] dx = new int[] {0, 1, 0, -1};
        int[] dy = new int[] {-1, 0, 1, 0};

        int m = maze.length;
        int n = maze[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(entrance);
        boolean[][] visited = new boolean[m][n];
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] temp = queue.poll();
                int x = temp[0];
                int y = temp[1];
                visited[x][y] = true;

                if ( x == 0 || x == m-1 || y == 0 || y == n -1) {
                    if (!(x == entrance[0] && y == entrance[1])) {
                        return result;
                    }
                }

                for (int j = 0; j < 4; j++) {
                    int next_x = x+dx[j];
                    int next_y = y+dy[j];
                    if (next_x < 0 || next_y < 0 || next_x >= m || next_y >= n ||
                            visited[next_x][next_y] || maze[next_x][next_y] == '+') {
                        continue;
                    } else {
                        queue.offer(new int[] {next_x, next_y});
                    }
                }
            }
            result++;
        }
        return -1;
    }
}
