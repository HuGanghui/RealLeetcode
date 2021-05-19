package BackTracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 剑指 Offer 38. 字符串的排列 Median
 * https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/
 *
 * 字符串排列和之前的数组数字排列都是类似的，核心在于不重不漏，重复涉及到树层
 * 和树间两个方法，一个是纵向，一个是横向。
 * 横向的利用每层递归中的一个哈希set来解决，纵向则利用全局的used数组来解决。
 */
public class StringPermutation {
    public String[] permutation(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        List<String> result = new ArrayList<>();
        char[] temp = new char[n];
        boolean[] used = new boolean[n];
        backtracking(chars, temp, 0, n, result, used);
        return result.toArray(new String[result.size()]);
    }

    private void backtracking(char[] chars, char[] temp, int index, int n, List<String> result, boolean[] usedArray) {

        if (index == n) {
            result.add(String.valueOf(temp));
            return;
        }

        Set<Character> used = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (usedArray[i]) {
                continue;
            }
            if (used.contains(chars[i])) {
                continue;
            }
            temp[index] = chars[i];
            used.add(chars[i]);
            usedArray[i] = true;
            backtracking(chars, temp, index+1, n, result, usedArray);
            usedArray[i] = false;
        }
    }
}
