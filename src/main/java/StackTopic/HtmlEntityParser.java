package StackTopic;

import java.util.HashMap;
import java.util.Map;

/**
 * 1410. HTML 实体解析器
 * https://leetcode-cn.com/problems/html-entity-parser/
 *
 * 这题和栈的关系其实不大，使用了反而小题大作，类似需要字符串拼接的，使用StringBuilder效率更高。
 */
public class HtmlEntityParser {
    private Map<String, String> map = new HashMap<>(6);
    StringBuilder result = new StringBuilder();

    public HtmlEntityParser() {
        map.put("&quot;", "\"");
        map.put("&apos;", "\'");
        map.put("&amp;", "&");
        map.put("&gt;", ">");
        map.put("&lt;", "<");
        map.put("&frasl;", "/");
    }

    public String entityParser(String text) {
        int i = 0;
        char[] arr = text.toCharArray();
        while (i < arr.length){
            if (arr[i] == '&') {
                String sub4;
                String sub5;
                String sub6;
                String sub7;
                if ((sub4 = map.get(text.substring(i, i+4))) != null) {
                    result.append(sub4);
                    i = i + 4;
                } else if ((sub5 = map.get(text.substring(i, i+5))) != null) {
                    result.append(sub5);
                    i = i + 5;
                } else if ((sub6 = map.get(text.substring(i, i+6))) != null) {
                    result.append(sub6);
                    i = i + 6;
                } else if ((sub7 = map.get(text.substring(i, i+7))) != null) {
                    result.append(sub7);
                    i = i + 7;
                } else {
                    result.append(text, i, i+1);
                    i++;
                }
            } else {
                result.append(text, i, i+1);
                i++;
            }
        }

        return result.toString();
    }
}
