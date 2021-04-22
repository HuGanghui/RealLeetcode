package DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * 1823. 找出游戏的获胜者 Median
 * https://leetcode-cn.com/problems/find-the-winner-of-the-circular-game/
 *
 * 这题是典型的约瑟夫环问题，所谓约瑟夫环就是n个人/物围成一圈，然后数到m的人/物删除，
 * 不断进行下去，直到剩下最后一个人。
 * 可以通过循环列表模拟过程，时间复杂度有O(n^2)。然后这题有递推方程，可以在O(n)时间复杂度内
 * 完成，具体的是，定义递归函数 f(n，m) 的返回结果是剩下小朋友的编号，显然当 n = 1 时，f(n, m) = 1。
 * 我们能够找出 f(n，m) 和 f(n-1，m) 之间的关系的话，我们就可以用递归的方式来解决了，
 * 删除前 --- 删除后
 * … --- …
 * m - 2 --- n - 2
 * m - 1 --- n - 1
 * m --- 无(因为编号被删除了)
 * m + 1 --- 1(因为下次就从这里报数了)
 * m + 2 ---- 2
 * … ---- …
 * 所以f(n,m) = (f(n-1,m) + m) % n
 * 最后再加上1，因为 %n 的范围只有 0-n-1。
 *
 */
public class JosephRing {
    // 递归方程
    public int findTheWinner(int n, int k) {
        return f(n, k) + 1;
    }

    private int f(int n, int k) {
        if (n == 1) return 0;
        int result = (f(n - 1, k) + k) % n;
        return result;
    }

    // list模拟
    public int findTheWinnerMock(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i<=n; i++) {
            list.add(i);
        }
        int index = 0;
        while (n > 1) {
            index = (index + k - 1) % n;
            // O(n^2)复杂度来源于每次删除需要O(n)
            list.remove(index);
            n--;
        }
        return list.get(0);
    }
}
