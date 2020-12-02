package StackTopic;

/**
 * 880. 索引处的解码字符串 Median
 * https://leetcode-cn.com/problems/decoded-string-at-index/
 *
 * 没搞懂，当前版本内存溢出。
 */
public class DecodedStringAtIndex {
    public String decodeAtIndex(String S, int K) {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            if (!S.substring(i, i+1).matches("\\d")) {
                temp.append(S, i, i+1);
                if (temp.length() >= K) {
                    return temp.toString().substring(K-1, K);
                }
            } else {
                String tempString = temp.toString();
                for (int k = 0; k < Integer.parseInt(S.substring(i, i+1)) - 1; k++) {
                    temp.append(tempString);
                    if (temp.length() >= K) {
                        return temp.toString().substring(K-1, K);
                    }
                }
            }
        }
        return "";
    }

    public static void main(String[] args) {
        DecodedStringAtIndex decodedStringAtIndex = new DecodedStringAtIndex();
        decodedStringAtIndex.decodeAtIndex("leet2code3",10);
    }
}
