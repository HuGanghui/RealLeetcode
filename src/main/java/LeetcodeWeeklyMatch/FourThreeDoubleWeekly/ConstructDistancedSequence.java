package LeetcodeWeeklyMatch.FourThreeDoubleWeekly;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 1718. 构建字典序最大的可行序列 Median
 * https://leetcode-cn.com/problems/construct-the-lexicographically-largest-valid-sequence/
 *
 * 其实看到n的范围基本上就能够才到需要DFS/BFS类似的枚举方法，然后这题本质上就是排列问题的变种，
 * 只是多了一些额外的限制条件/规则而已。
 *
 * 但是这题有个关键的剪枝，就是下文提到的，在for循环里，k是不变的，然后不同的i对后续递归没有任何作用时，只要
 * 一个就行了。
 */
public class ConstructDistancedSequence {
    private int[] res;

    public int[] constructDistancedSequence(int n) {
        int lens = 2*n-1;
        int[] result = new int[lens];
        backtracking(result, 0, n, new HashSet<>(), lens);
        return res;
    }

    private void backtracking(int[] result, int k, int n, Set<Integer> set, int lens) {
        if (k > lens) {
            return;
        }

        if (res != null) {
            return;
        }

        if (set.size() == n) {
            res = Arrays.copyOf(result, result.length);
            return;
        }

        for (int i = n; i >= 1; i--) {
            if (set.contains(i)) {
                continue;
            }

            int sec = k + i;
            if (result[k] != 0) {
                backtracking(result, k+1, n, set, lens);
                return;  // 剪枝的关键，这题如果这里没有return，就会超时，这里剪枝也是合理的，因为如果
                         // result[k] != 0，那么就直接跳到k+1就行，并且不同的i没有任何不同，因此只需要
                         // 处理一个就行，后续的i就不需要了。
            } else if (i == 1 || sec < result.length && result[sec] == 0) {
                result[k] = i;
                set.add(i);
                if (i > 1) {
                    result[sec] = i;
                }
                backtracking(result, k+1, n, set, lens);
                if (res != null) {
                    return;
                }
                if (i > 1) {
                    result[sec] = 0;
                }
                result[k] = 0;
                set.remove(i);
            }
        }
    }
}
