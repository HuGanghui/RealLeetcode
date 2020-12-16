package BackTracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1291. 顺次数 Median
 * https://leetcode-cn.com/problems/sequential-digits/
 *
 * 这题大意了呀，很明显的枚举法，但是问题在于如何枚举，给定范围10 <= low <= high <= 10^9，
 * 非常大了，如果依次判断low-high中每个数是否符合，数量太大，相反，通过枚举顺次数，然后判断是否在范围内更
 * 合适。
 */
public class SequentialDigits {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i<9; i++) {
            int num = i;
            for (int j = i + 1; j <= 9; j++) {
                num = num * 10 + j;
                if (low <= num && num <= high) {
                    result.add(num);
                }
            }
        }
        Collections.sort(result);
        return result;
    }
}
