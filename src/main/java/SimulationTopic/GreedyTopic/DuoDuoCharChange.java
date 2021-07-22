package SimulationTopic.GreedyTopic;

import java.util.*;

/**
 * 拼多多2021笔试题-多多的字符变换
 * https://www.nowcoder.com/questionTerminal/78255f37c7dc4f749ceafc8c58206a43
 *
 * 关键语句理解：**可以交换任意两个相邻的字符，交换次数不限**
 * 对于这句话，其实可以理解为 ——> 可以任意对字符进行排序
 *
 * 在理解上述语句的基础上，可以对两个字符串进行排序，随后就有点贪心的思想，依次的去比较相减然后相加就行。
 */
public class DuoDuoCharChange {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String a = sc.nextLine();
        String b = sc.nextLine();
        char[] char_a = a.toCharArray();
        char[] char_b = b.toCharArray();
        Arrays.sort(char_a);
        Arrays.sort(char_b);
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += Math.abs(char_a[i] - char_b[i]);
        }
        System.out.println(result);
    }
}
