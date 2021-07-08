package BackTracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 17. 打印从1到最大的n位数 Easy
 * https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/
 *
 * 根据题解：https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/solution/mian-shi-ti-17-da-yin-cong-1-dao-zui-da-de-n-wei-2/
 *
 * 这题的核心考点其实在于大数越界，当超出整型范围时，是无法存储的，因此其实应用字符串String类型来表示大数，
 * 而利用String类型，通过+1的方式就无法生成下一个数字，String的进位方式效率太低，
 * 其实观察可知，生成的列表其实是0-9的全排列，因此通过全排列的方式就可以生成String的数字列表。
 *
 * 然后一个值得主要的地方就是开头不能使用0开头，因此对于第一个数字，取值是1-9，后面则是0-9，并且根据不同digit
 * 的数量，遍历的去进行，这样可以转换为之前的见过的长度固定的全排列类型。
 */
public class PrintNumbers {
    private char[] chars = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public int[] printNumbers(int n) {
        List<Integer> result = new ArrayList<>();
        for (int d = 1; d <= n; d++) {
            for (int i = 1; i <= 9; i++) {
                StringBuilder builder = new StringBuilder();
                builder.append(chars[i]);
                backtracking(d, result, builder);
            }
        }
        int[] res = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;

    }

    private void backtracking(int digit, List<Integer> result, StringBuilder builder) {

        if (builder.length() == digit) {
            result.add(Integer.parseInt(builder.toString()));
            return;
        }

        for (int i = 0; i <= 9; i++) {
            builder.append(chars[i]);
            backtracking(digit, result, builder);
            builder.deleteCharAt(builder.length() - 1);
        }
    }
}
