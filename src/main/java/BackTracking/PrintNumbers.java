package BackTracking;

/**
 * 剑指 Offer 17. 打印从1到最大的n位数 Easy
 * https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/
 *
 * 根据题解：https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/solution/mian-shi-ti-17-da-yin-cong-1-dao-zui-da-de-n-wei-2/
 *
 * 这题的核心考点其实在于大数越界，当超出整型范围时，是无法存储的，因此其实应用字符串String类型来表示大数，
 * 而利用String类型，通过+1的方式就无法生成下一个数字，String的进位方式效率太低，
 * 其实观察可知，生成的列表其实是0-9的全排列，因此通过全排列的方式就可以生成String的数字列表。
 */
public class PrintNumbers {
    private char[] loop = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private int n;
    private StringBuilder stringBuilder;

    public String printNumbers(int n) {
        this.n = n;
        stringBuilder = new StringBuilder();
        dfs(0, new char[n]);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    private void dfs(int x, char[] temp) {
        if (x == n) {
            stringBuilder.append(String.valueOf(temp) + ",");
            return;
        }

        for (char i : loop) {
            temp[x] = i;
            dfs(x + 1, temp);
        }
    }

    public static void main(String[] args) {
        PrintNumbers printNumbers = new PrintNumbers();
        System.out.println(printNumbers.printNumbers(2));
    }
}
