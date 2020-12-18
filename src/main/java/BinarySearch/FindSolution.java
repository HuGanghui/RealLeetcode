package BinarySearch;

import java.util.ArrayList;
import java.util.List;

/**
 * 1237. 找出给定方程的正整数解 Easy
 * https://leetcode-cn.com/problems/find-positive-integer-solution-for-a-given-equation/
 *
 * 单调==有序，因此可以使用二分，但是对于类似这种二维的有序，二分不一定是最优，因为只能利用到其中一个维度，
 * （CountNegatives 也是二维的）这时候双指针法可能更好，后面学习吧。
 */
public class FindSolution {
    public List<List<Integer>> findSolution(CustomFunction customfunction, int z) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        int index = -1;
        for (int y = 1; y <= 1000; y++) {
            int left = 1;
            int right = 1000;
            if (index != -1) { // 优化，考虑x同样是单调的，可以缩小范围
                right = index -1;
            }
            while (left <= right) {
                int middle = left + (right - left) / 2;
                if (customfunction.f(middle, y) > z) {
                    right = middle -1;
                } else if (customfunction.f(middle, y) < z) {
                    left = middle + 1;
                } else {
                    index = middle;
                    temp.add(middle);
                    temp.add(y);
                    result.add(new ArrayList<>(temp));
                    temp.clear();
                    break;
                }
            }
        }
        return result;
    }
}

interface CustomFunction {
    // Returns positive integer f(x, y) for any given positive integer x and y.
    int f(int x, int y);
}
