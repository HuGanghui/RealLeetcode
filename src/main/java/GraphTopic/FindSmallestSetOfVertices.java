package GraphTopic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1557. 可以到达所有点的最少点数目 Median
 * https://leetcode-cn.com/problems/minimum-number-of-vertices-to-reach-all-nodes/
 *
 * 这题没想出来，一开始以为要遍历组合呢，但是其实是不太现实的，看完题解会发现其实，仔细分析后
 * 其实只需要找到入度为0的节点就行。。。哎
 */
public class FindSmallestSetOfVertices {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        Set<Integer> endSet = new HashSet<>();
        List<Integer> ans = new ArrayList<>();
        for (List<Integer> edge : edges) {
            endSet.add(edge.get(1));
        }
        for (int i = 0; i < n; i++) {
            if (!endSet.contains(i)) {
                ans.add(i);
            }
        }
        return ans;
    }
}
