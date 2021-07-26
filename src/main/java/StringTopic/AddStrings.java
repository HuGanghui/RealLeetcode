package StringTopic;

/**
 * 415. 字符串相加 Easy
 * https://leetcode-cn.com/problems/add-strings/
 *
 * 就是模拟算术求和，从尾部开始，然后形式其实类似归并排序的
 * merge环节。只不过注意有个进1。
 */
public class AddStrings {
    public String addStrings(String num1, String num2) {
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();
        int n1 = chars1.length;
        int n2 = chars2.length;
        int i = n1 - 1;
        int j = n2 - 1;
        int prev = 0;
        StringBuilder builder = new StringBuilder();
        while (i >= 0 || j >= 0 || prev != 0) {
            int x = i >= 0 ? (int)(chars1[i] - '0') : 0;
            int y = j >= 0 ? (int)(chars2[j] - '0') : 0;
            int sum = x + y + prev;
            builder.append(sum % 10);
            prev = sum / 10;
            i--;
            j--;
        }
        builder.reverse();
        return builder.toString();
    }
}
