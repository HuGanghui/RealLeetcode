package BFSearch;

import java.util.*;

/**
 * 剑指 Offer II 109. 开密码锁 Median
 * https://leetcode-cn.com/problems/zlDJc7/
 *
 * 求最少步数这种，基本上一种方式就是BFS来解决。
 *
 * 这题需要注意的就是加减获得下一个String还有点麻烦，其它没什么。
 */
public class OpenLock {
    public int openLock(String[] deadends, String target) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < deadends.length; i++) {
            set.add(deadends[i]);
        }
        int step = 0;
        int res = -1;
        Deque<String> deque = new LinkedList<>();
        if (!set.contains("0000")) {
            deque.add("0000");
        }
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                String cur = deque.removeFirst();
                boolean result = cur.equals(target);
                if (result) {
                    res = step;
                    return step;
                }
                for (String str : get(cur)) {
                    if (!set.contains(str)) {
                        deque.add(str);
                        set.add(str);
                    }
                }
            }
            step++;
        }
        return res;
    }

    private char addOne(char cur) {
        return cur == '9' ? '0' : (char) (cur + 1);
    }

    private char minusOne(char cur) {
        return cur == '0' ? '9' : (char) (cur - 1);
    }

    private List<String> get(String cur) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            char[] chars = cur.toCharArray();
            char temp = chars[i];
            chars[i] = addOne(temp);
            list.add(String.valueOf(chars));
            chars[i] = minusOne(temp);
            list.add(String.valueOf(chars));
        }
        return list;
    }
}
