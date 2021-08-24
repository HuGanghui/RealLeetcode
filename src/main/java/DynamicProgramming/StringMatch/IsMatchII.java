package DynamicProgramming.StringMatch;

import java.util.*;

/**
 * 通配符匹配 虾皮2021笔试题 Hard
 *
 * 自底向上的DP相对更加简单
 *
 * 判断一个包含通配符的字符串能否完全匹配另一个字符串
 * "?"表示匹配任意一个字符一次
 * "#"表示匹配任意一个字符0次或一次
 * "*"表示匹配任意一个字符0次或任意次
 *
 * 这题和力扣上的区别在于，特殊字符的功能有所区别，力扣上
 * "*"表示匹配前一个字符 0次或任意次，这就需要提前考虑：
 *  j <= p.length - 2 && p[j+1] == '*'
 *
 * 而本题没有这种，反而更简单些。
 *
 * 但是相同的问题是，需要主要 为了避免
 * a
 * a****
 * 这种情况，需要优先考虑'*'和'#'，最后考虑直接匹配和"?"
 */
public class IsMatchII {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String p = sc.nextLine();
        char[] p_chars = p.toCharArray();
        String s = sc.nextLine();
        char[] s_chars = s.toCharArray();
        boolean result = isMatch(s_chars, p_chars, 0, 0);
        if (result) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }

    }

    // 应该还要加上一个memo才能算DP，但是测试样例太弱了，直接递归就过了。。。
    private static boolean isMatch(char[] s, char[] p, int s_index, int p_index) {

        // 因为p还有可能包含可以匹配零次的特殊字符，因此要放在外面。
        if (p_index == p.length) {
            return s_index == s.length;
        }

        boolean curMatch = (s_index < s.length) &&
                (s[s_index] == p[p_index] || p[p_index] == '?');

        boolean result;

        if (p[p_index] == '*') {
            // 先跳过这个顺序还必须放前面，不会出现栈溢出
            result = isMatch(s, p, s_index, p_index + 1) ||
                    isMatch(s, p, s_index + 1, p_index);
        } else if (p[p_index] == '#') {
            result = isMatch(s, p, s_index, p_index + 1) ||
                    isMatch(s, p, s_index + 1, p_index + 1);
        } else {
            result = curMatch && isMatch(s, p, s_index + 1, p_index + 1);
        }

        return result;
    }

    public boolean isMatchDP(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        for (int j = 1; j <= n; j++) {
            if (p.charAt(j-1) == '*' || p.charAt(j-1) == '#') {
                dp[0][j] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j-1) == '*') {
                    // 匹配多个 || 匹配一个 || 匹配零个
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-1] || dp[i][j-1];
                } else if (p.charAt(j-1) == '#') {
                    // 匹配一个 || 匹配零个
                    dp[i][j] = dp[i-1][j-1] || dp[i][j-1];
                } else {
                    if (matches(s, p, i, j)) {
                        dp[i][j] = dp[i-1][j-1];
                    }
                }
            }
        }

        return dp[m][n];
    }

    private boolean matches(String s, String p, int i, int j) {
        if (p.charAt(j-1) == '?') {
            return true;
        }
        return s.charAt(i-1) == p.charAt(j-1);
    }
}
