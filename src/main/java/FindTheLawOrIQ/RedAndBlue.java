package FindTheLawOrIQ;

import java.util.*;

/**
 * 牛牛刷油漆 阿里巴巴 2021.09.24 笔试题
 * 按给定颜色进行刷蓝色或者红色油漆，求出最少需要刷几次。
 * 例子：
 * 输入：
 * 8
 * BBRBRBBR
 * 输出：
 * 4
 * 最少4次，全部刷蓝色，然后3、5、8刷红色。
 * 时间复杂度要求O(N)
 *
 * 其实就是一个找规律/智力的题，需要在O(N)内完成，
 * 找到了就容易，找不到就不会，规律其实就是每颜色交替比如BBRB，
 * 最少需要的就是2次，全部刷蓝，再挑其中刷红。因此只需要求出
 * 蓝色有多少整体块、红色有多少整体块即可，取其中最小的+1就是答案。
 */
public class RedAndBlue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String str = sc.nextLine();
        System.out.println(getSmaller(str, n) + 1);
    }

    private static int getSmaller(String str, int n) {
        int buleSize = 0;
        int redSize = 0;

        char prev = str.charAt(0);

        for (int i = 1; i < n; i++) {
            char cur = str.charAt(i);
            if (cur == prev) {
                continue;
            } else {
                if (prev == 'B') {
                    buleSize++;
                } else {
                    redSize++;
                }
                prev = cur;
            }
        }
        // 处理最后一块
        if (prev == 'B') {
            buleSize++;
        } else {
            redSize++;
        }

        return Math.min(buleSize, redSize);
    }
}
