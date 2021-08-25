package StringTopic;
import java.util.*;

/**
 * 6. Z 字形变换 Median
 * https://leetcode-cn.com/problems/zigzag-conversion/
 *
 * 规律就是行数是从小到大，再从大到小，然后有0和numRows -1 两个
 * 转折点。
 */
public class ZConvert {
    public String convert(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        int i = 0;
        int flag = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            if (i == 0 || i == numRows - 1) {
                flag = -flag;
            }
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }
        return res.toString();
    }
}
