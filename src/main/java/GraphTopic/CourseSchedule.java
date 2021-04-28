package GraphTopic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 207. 课程表 Median
 * https://leetcode-cn.com/problems/course-schedule/
 *
 * 这道题是典型的拓扑排序，通过给定一幅图，然后判断或找出其拓扑
 * 排序，拓扑排序有两个性质：1. 图一定是有向无环图，有环是无法
 * 出现拓扑的 2. 拓扑排序的可能性不止一种。
 *
 * 由于是图，其实也就逃不掉图的遍历来解决问题，这题可以通过深度和广度
 * 两个方式来解决，但是深度是逆向的比较难理解，而广度的则是正向的好理解，
 * 我们主要以广度为主。
 *
 * 广度的思想就是先找到一些入度为0的点，然后加入队列，然后广度遍历队列中的点，
 * 遍历过程中的操作就是去掉图中在队列中的点并去掉所有从该点出发的边，然后看邻
 * 接点是否有入度为0，然后依次加入队列。 这个过程中可以用一个list保存依次出
 * 队的点，就构成了一个拓扑排序。
 */
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> edges = new ArrayList<>();
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            indegree[prerequisites[i][0]] += 1;
            edges.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int visited = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited++;
            for (int i : edges.get(node)) {
                indegree[i]--;
                if (indegree[i] == 0) {
                    queue.offer(i);
                }
            }
        }

        // 仅仅需要判断是否存在拓扑排序
        return visited == numCourses;
    }
}
