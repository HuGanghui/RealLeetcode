package DynamicProgramming;

/**
 * 剑指 Offer 19. 正则表达式匹配 Hard
 * https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof/
 *
 * 参考labuladong博客：https://mp.weixin.qq.com/s/TAiIIxoDXx67MNGXea6gfQ
 * 从最简单的情况的来思考，先不考虑正则符号，递归形式就是 s[i] == p[j] && isMatch(s, p,
 * i+1, j+1)，base case是 if ( i == s.length) {return j == p.length}
 * if (j == p.length) {return i == s.length}
 *
 * 增加 "."符号，很简单就是 (s[i] == p[j] || p[j] == ".") && isMatch(s, p, i+1, j+1)，
 * 增加 "*"符号，"*"符号有两种情况，一种是"*"前一个符号出现为0，则 ans = isMatch(s, p,
 * i, j+2); 另一种是"*"前一个符号出现任意次，则 ans = s[i] == p[j] && isMatch(s, p,
 * i+1, j) 这时还有base case 需要修改一下，因为当i == s.length，可能刚好 j+1是"*"，这样
 * 还是可以匹配上，因为有出现零次的情况。
 *
 * 目前使用的方式是自顶向下带memo的备忘录形式，这种比较好理解。
 *
 */
public class IsMatch {
    private int[][] memo;

    public boolean isMatch(String s, String p) {
        char[] sChar = s.toCharArray();
        char[] pChar = p.toCharArray();
        memo = new int[sChar.length+1][pChar.length+1];
        return isMatch(sChar, pChar, 0, 0);
    }

    private boolean isMatch(char[] s, char[] p, int i, int j) {
        if (memo[i][j] == 1) {
            return true;
        } else if (memo[i][j] == -1) {
            return false;
        }

        if (j == p.length) {
            return i == s.length;
        }

        boolean firstMatch = (i < s.length) && (s[i] == p[j] || p[j] == '.');
        boolean ans = false;
        if (j <= p.length - 2 && p[j+1] == '*') {
            ans = isMatch(s, p, i, j+2) || (firstMatch && isMatch(s, p, i+1, j));
        } else {
            ans = firstMatch && isMatch(s, p, i+1, j+1);
        }

        if (ans) {
            memo[i][j] = 1;
        } else {
            memo[i][j] = -1;
        }
        return ans;
    }
}
