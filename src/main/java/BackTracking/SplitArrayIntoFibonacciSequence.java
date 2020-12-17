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
    public List<Integer> splitIntoFibonacci(String S) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        backtracking(0, S, temp, result);
        if (result.size() == 0) {
            return temp;
        } else {
            return  result.get(0);
        }
    }

    private void backtracking(int startIndex, String S,
                              List<Integer> temp, List<List<Integer>> result) {

        if (startIndex == S.length() && temp.size() > 2) {
            result.add(new ArrayList<>(temp));
        }

        for (int i = startIndex; i < S.length(); i++) {
            if (S.substring(startIndex, startIndex + 1).equals("0") && i > startIndex) {
                break;
            }
            long sub = subDigit(S.toCharArray(), startIndex, i + 1); // 字符串转整型，有可能越界，因为位数达到200位
            if (sub > Integer.MAX_VALUE) {
                break;
            }
            if (temp.size() < 2 || temp.get(temp.size() -2) + temp.get(temp.size()-1) == sub) {
                    temp.add((int)sub);
                    backtracking(i+1, S, temp, result);
                    temp.remove(temp.size() -1);
            }
        }
    }

    //相当于截取字符串S中的子串然后转换为十进制数字
    private long subDigit(char[] digit, int start, int end) {
        long res = 0;
        for (int i = start; i < end; i++) {
            res = res * 10 + digit[i] - '0';
        }
        return res;
    }

    public static void main(String[] args) {
        SplitArrayIntoFibonacciSequence splitArrayIntoFibonacciSequence = new SplitArrayIntoFibonacciSequence();
        splitArrayIntoFibonacciSequence.splitIntoFibonacci("123456579");
    }
}
