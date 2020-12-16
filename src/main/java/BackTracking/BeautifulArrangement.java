package BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 526. 优美的排列 Median
 * https://leetcode-cn.com/problems/beautiful-arrangement/
 *
 * 相比普通排列就多了个剪枝条件
 */
public class BeautifulArrangement {
    private int result = 0;

    public int countArrangement(int N) {
        List<Integer> temp = new ArrayList<>();
        int[] used = new int[N];
        backtracking(N, used, temp);
        return result;
    }

    private void backtracking(int N, int[] used, List<Integer> temp) {

        if (temp.size() == N) {
            result++;
        }

        for(int i = 1; i <= N; i++) {
            int index = temp.size() + 1;
            if (used[i-1] == 0 && (i % index == 0 || index % i == 0)) {
                temp.add(i);
                used[i-1] = 1;
                backtracking(N, used, temp);
                temp.remove(temp.size() - 1);
                used[i-1] = 0;
            }
        }
    }
}
