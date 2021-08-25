package StringTopic;

/**
 * 43. 字符串相乘 Median
 * https://leetcode-cn.com/problems/multiply-strings/
 *
 * 乘法和加法的区别就在于乘法需要和另一个数的每一个进行乘法，
 * 然后其它倒是类似的，这里用了一个数组来填装res，最后再进行
 * 字符串的转换。
 */
public class Multiply {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int n = num1.length();
        int m = num2.length();
        int[] res = new int[m + n];
        for (int i = n - 1; i >= 0; i--) {
            int a = num1.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int b = num2.charAt(j) - '0';
                int sum = res[i + j + 1] + a * b;
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) {
                continue;
            }
            result.append(res[i]);
        }
        return result.toString();
    }
}
