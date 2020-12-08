package BackTracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合 Median
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 *
 * 本质就是组合问题，但是区别在于，每个变量有自己的取值条件，因此不需要startIndex；
 * 这道题麻烦点就是有个映射，还有使用StringBuilder来处理动态变化的字符串。
 */
public class NumToLetter {

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        Map<String, String[]> num2letter = new HashMap<>();
        num2letter.put("2", new String[] {"a", "b", "c"});
        num2letter.put("3", new String[] {"d", "e", "f"});
        num2letter.put("4", new String[] {"g", "h", "i"});
        num2letter.put("5", new String[] {"j", "k", "l"});
        num2letter.put("6", new String[] {"m", "n", "o"});
        num2letter.put("7", new String[] {"p", "q", "r", "s"});
        num2letter.put("8", new String[] {"t", "u", "v"});
        num2letter.put("9", new String[] {"w", "x", "y", "z"});
        StringBuilder stringBuilder = new StringBuilder();
        backtracking(0, result, num2letter, digits, stringBuilder);
        return result;
    }

    private void backtracking(int numIndex, List<String> result,
                              Map<String, String[]> num2letter, String digits,
                              StringBuilder stringBuilder) {
        // 特殊例子 空字符串
        if (digits.equals("")) {
            return;
        }

        // 递归结束条件
        if (numIndex == digits.length()) {
            result.add(stringBuilder.toString());
            return;
        }

        String current = digits.substring(numIndex, numIndex + 1);
        String[] letters;

        // 获取映射
        if (null != num2letter.get(current)) {
            letters = num2letter.get(current);
        } else {
            letters = new String[]{};
        }

        for (int i = 0; i < letters.length; i++) {
            stringBuilder.append(current);
            backtracking(numIndex + 1, result, num2letter, digits, stringBuilder);
            stringBuilder.delete(stringBuilder.length() -1, stringBuilder.length());
        }

    }

    public static void main(String[] args) {
        NumToLetter numToLetter = new NumToLetter();
        numToLetter.letterCombinations("");
    }
}
