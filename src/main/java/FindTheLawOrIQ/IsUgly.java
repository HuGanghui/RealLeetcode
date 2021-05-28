package FindTheLawOrIQ;

/**
 * 263. 丑数 Easy
 * https://leetcode-cn.com/problems/ugly-number/
 *
 * 根据质因数的定义，小于等于0的必然不是丑数，否则就一直除2，3，5，如果最后剩下1，说明就是丑数。
 */
public class IsUgly {
    public boolean isUgly(int n) {
        if (n == 0) {
            return false;
        }
        boolean result = false;
        while (n % 2 == 0) {
            n /= 2;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }
        if (n == 1) {
            result = true;
        }
        return result;
    }
}
