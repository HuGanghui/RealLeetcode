package BackTracking;

import java.util.*;

/**
 * 401. 二进制手表 Easy
 * https://leetcode-cn.com/problems/binary-watch/
 *
 * 本质上是最基础的n不重选k个组合问题，使用n叉树遍历，然后只是要想到这个本质，然后只是多个映射。
 */
public class Submissions {
    public List<String> readBinaryWatch(int num) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        backtracking(0, result, temp, num);
        return num2time(result);
    }

    private void backtracking(int startIndex, List<List<Integer>> result, List<Integer> temp, int num) {
        if (temp.size() == num) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = startIndex; i < 10; i++) {
            temp.add(i);
            backtracking(i+1, result, temp, num);
            temp.remove(temp.size() - 1);
        }
    }

    private List<String> num2time(List<List<Integer>> result) {
        List<String> timeResult = new ArrayList<>();
        for (List<Integer> list : result) {
            int hour = 0;
            int min = 0;
            for (Integer ele : list) {
                if (ele <= 3) {
                    hour += Math.pow(2, ele);
                } else {
                    min += Math.pow(2, ele - 4);
                }
            }
            if (hour > 11 || min > 59) {
                continue;
            } else {
                if (min < 10) {
                    timeResult.add("" + hour + ":0" + min);
                } else {
                    timeResult.add("" + hour + ":" + min);
                }
            }
        }
        return timeResult;
    }
}
