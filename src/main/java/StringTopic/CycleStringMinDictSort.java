package StringTopic;

/**
 * BZOJ 2882 工艺-最小表示法
 * https://acm.taifua.com/bzoj/p/2882.html
 *
 * 题目就是给出一个字符串，求与它循环同构的串中字典序最小的串。
 *
 * 2021.4.18 腾讯笔试 第一题是对链表求循环同构的中字典序最小的链表，本质就是
 * 这一题，可以把链表的val和对应index存到list中，这样方便求最小头，在确定最小头节点之后，
 * 链表需要额外处理的就是尾部连接头部，然后到了size，断链的操作。
 *
 * // 额外处理
 * Pair best = getMin(deque, list, size);
 *
 *         // 找到当前min节点
 *         ListNode result = S;
 *         for (int i = 0; i < best.index; i++) {
 *             result = result.next;
 *         }
 *
 *         // 进行断链和接链操作
 *         ListNode tail = result;
 *         for (int i = 0; i < size; i++) {
 *             if (tail.next == null) {
 *                 tail.next = S;
 *             }
 *             if (i == size - 1) {
 *                 tail.next = null;
 *             } else {
 *                 tail = tail.next;
 *             }
 *         }
 *
 * 参考博客：https://www.cnblogs.com/suika/p/9109334.html
 * 这题有一个专门解决这类问题的方法：最小表示法。
 *
 * 首先把串复制一遍贴在原串后面，这样每个循环同构的串可以用S[i]~S[i+n-1]来表示，设为w(i)。
 * 也就是说我们把所有的串拿出来了，比较就行了。
 * 在比较w(i)和w(j)时的最坏时间复杂度是O(n)的，也就是说这只是一个暴力的做法。
 * 实际上我们不需要对所有的w(i)都进行一次比较。
 * 假设比较w(i)和w(j)时比较了k个字符，直到k+1个字符不同。
 * 那么我们将字典序大的那边指针向后跳k+1即可，因为已经知道有比这些串小的串了(就在另一个指针的后面)
 * 相当于每个指针最多向后跳n次，复杂度就变成O(n)的了，非常好写。
 */
public class CycleStringMinDictSort {
    public static String getMin(String s) {
        int n = s.length();
        char[] charsOne = s.toCharArray();
        char[] chars = new char[n*2];
        // 拷贝到尾部，方便比较
        for (int i = 0; i < n; i++) {
            chars[i] = charsOne[i];
            chars[i+n] = charsOne[i];
        }
        int i, j, k;
        for (i = 0, j = 1, k = 0; i <= n && j <= n && k < n;) {
            if (chars[i+k] == chars[j+k]) {
                k++;
            } else {
                if (chars[i+k] > chars[j+k]) {
                    i += k + 1;
                } else {
                    j += k + 1;
                }
                k = 0;
                if (i == j) { // special case，可能出现 i刚好跳到j，然后两者相等，需要过一个
                    j++;
                }
            }
        }
        int result = Math.min(i, j);
        char[] resultChar = new char[n];
        for (int m = 0; m < n; m++) {
            resultChar[m] = chars[m + result];
        }
        return String.valueOf(resultChar);
    }

    public static void main(String[] args) {
        System.out.println(getMin("22522"));
    }
}
