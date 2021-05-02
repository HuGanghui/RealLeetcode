package BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 5747. 将字符串拆分为递减的连续值 Median
 * https://leetcode-cn.com/problems/splitting-a-string-into-descending-consecutive-values/
 *
 * 很典型的需要使用回溯来进行遍历，但是trick值得注意，对于字符串转换整数的，
 * 非常有可能出现越界的情况，异常处理也是一个不错的选择，尤其这题存在多个0的可能性，
 * 导致无法用位数直接跳过，异常处理了就表明越界了，那必然后面无法保证差值为1了。
 */
public class SplitStringIntoDesConsValues {
    private boolean result = false;

    public boolean splitString(String s) {
        backTracking(s, 0, new ArrayList<>());
        return result;
    }

    private void backTracking(String s, int startIndex, List<Long> list) {

        if (startIndex >= s.length()) {
            if (list.size() > 1) {
                result = true;
            }
        }

        for (int i = startIndex; i < s.length(); i++) {
            if (result) {
                break;
            }
            String str = s.substring(startIndex, i + 1);
            long temp = 0;
            // 对于字符串转换整数的，非常有可能出现越界的情况，异常处理也是一个不错的选择
            // 尤其这题存在多个0的可能性，导致无法用位数直接跳过，异常处理了就表明越界了，那
            // 必然后面无法保证差值为1了。
            try {
                temp = Long.parseLong(str);
            } catch (Exception e) {
                continue;
            }

            if (list.size() > 0) {
                if (temp - list.get(list.size() - 1) == -1) {
                    list.add(temp);
                } else {
                    continue;
                }
            } else {
                list.add(temp);
            }
            backTracking(s, i + 1, list);
            list.remove(list.size() - 1);
        }
    }
}
