package BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 842. 将数组拆分成斐波那契序列 Median
 * https://leetcode-cn.com/problems/split-array-into-fibonacci-sequence/
 *
 * 分割问题，使用回溯方法
 * 本题有坑，在字符串转整型时，如果不注意会出现越界
 */
public class SplitArrayIntoFibonacciSequence {
    public List<Integer> splitIntoFibonacci(String num) {
        List<List<Integer>> result = new ArrayList<>();
        backtracking(num, result, new ArrayList<>(), 0);
        if (result.size() == 0) {
            return new ArrayList<>();
        }
        return result.get(0);
    }

    private void backtracking(String num, List<List<Integer>> result, List<Integer> temp, int startIndex) {

        if (startIndex >= num.length() && temp.size() > 2) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = startIndex; i < num.length(); i++) {
            String sub = num.substring(startIndex, i+1);
            if (sub.length() > 1 && sub.charAt(0) == '0') {
                break;
            }
            long value = Long.parseLong(num.substring(startIndex, i+1));
            if (value > Integer.MAX_VALUE) {
                break;
            }
            if (temp.size() < 2) {
                temp.add((int)value);
                backtracking(num, result, temp, i+1);
                temp.remove(temp.size() - 1);
            } else {
                int a = temp.get(temp.size() - 1);
                int b = temp.get(temp.size() - 2);
                if (a + b == value) {
                    temp.add((int)value);
                    backtracking(num, result, temp, i+1);
                    temp.remove(temp.size() - 1);
                }
            }
        }
    }
}
