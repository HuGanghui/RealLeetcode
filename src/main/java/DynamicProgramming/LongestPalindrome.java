package DynamicProgramming;

/**
 * 5. 最长回文子串
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 * 穷举方法也是不错的，不过动态规划应该也要掌握, 这题的动态规划的思想其实是根据递推
 * 判断所有子串是否是回文串，然后在是的里面选择最长的，其实和枚举差不多，因为枚举也就
 * O(n^2)的级别，差距不大。这题重复子问题没有太多。
 */
public class LongestPalindrome {
    // 以每个字母为中心，向两边扩张，判断是否是回文串，本质是穷举，但是写法上可以改进
    // 回文串存在的两种边界情况是相似的，可以用函数复用
//    public String longestPalindrome(String s) {
//        char[] chars = s.toCharArray();
//        int result = 0;
//        String resultString = "";
//        for (int i = 0; i < chars.length; i++) {
//            int j = 1;
//            int temp = 1;
//            while(i - j >=0 && i + j < chars.length) {
//                if (chars[i - j] == chars[i + j]) {
//                    temp += 2;
//                    j++;
//                } else {
//                    break;
//                }
//            }
//            if (temp > result) {
//                result = temp;
//                resultString = s.substring(i-j+1, i+j);
//            }
//            j = 1;
//            temp = 0;
//            if (i < chars.length - 1 && chars[i] == chars[i+1]) {
//                temp += 2;
//                while (i - j >=0 && i + j + 1 < chars.length) {
//                    if (chars[i - j] == chars[i + j + 1]) {
//                        temp += 2;
//                        j++;
//                    } else {
//                        break;
//                    }
//                }
//            }
//            if (temp > result) {
//                result = temp;
//                resultString = s.substring(i-j+1, i+j+1);
//            }
//        }
//        return resultString;
//    }

    // DP
    public String longestPalindromeDP(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";

        for (int l = 0; l < n; l++) {
            for (int i = 0; i + l < n; i++) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = true;
                } else if (l == 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = dp[i+1][j-1] && s.charAt(i) == s.charAt(j);
                }
                if (dp[i][j] && l + 1 > ans.length()) {
                    ans = s.substring(i, i+l+1);
                }
            }
        }
        return ans;
    }

    // 双指针
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0; int end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i+1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        while (left >=0 && right < s.length()
                && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return right - left - 1;
    }

}
