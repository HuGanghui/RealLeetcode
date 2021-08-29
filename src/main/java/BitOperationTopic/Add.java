package BitOperationTopic;

/**
 * 剑指 Offer 65. 不用加减乘除做加法 Median
 * https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/
 *
 * 没有进位就是异或运算，有进位就是与运算，因此这两者结合即可。
 */
public class Add {
    public int add(int a, int b) {
        while(b != 0) { // 当进位为 0 时跳出
            int c = (a & b) << 1;  // c = 进位
            a ^= b; // a = 非进位和
            b = c; // b = 进位
        }
        return a;
    }
}
