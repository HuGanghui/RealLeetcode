package BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. 分割回文串 Median
 * https://leetcode-cn.com/problems/palindrome-partitioning/
 *
 * 分割问题，基本上就是枚举递归，我的回文判断一开始做的不好，概念不对。
 */
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        backtracking(0, s, result, temp);
        return result;
    }

    private void backtracking(int startIndex, String s,
                              List<List<String>> result, List<String> temp) {
        if (startIndex == s.length()) {
            result.add(new ArrayList<>(temp));
        }
        for (int i = startIndex; i < s.length(); i++) {
            String subString = s.substring(startIndex, i+1);
            boolean flag = checkPalindrome(subString);
            if (flag) {
                temp.add(subString);
                backtracking(i + 1, s, result, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private boolean checkPalindrome(String str) {
        // 严格小于即可
        int left = 0;
        int right = str.length();
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

//    private boolean isPalindrome(String s) {
//        if (s.length() == 1) {
//            return true;
//        }
//        Stack<Character> stack = new Stack<>();
//
//        char[] chars = s.toCharArray();
//        boolean even = chars.length % 2 == 0;
//        for (int i = 0; i < chars.length; i++) {
//            if (!even && i == chars.length / 2 ) {
//                continue;
//            }
//            if (!stack.isEmpty() && stack.peek() == chars[i]) {
//                stack.pop();
//            } else {
//                stack.push(chars[i]);
//            }
//        }
//
//        return stack.isEmpty();
//    }

}
