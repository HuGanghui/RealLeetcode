package BackTracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 691. 贴纸拼词 Hard
 * https://leetcode-cn.com/problems/stickers-to-spell-word/
 *
 * 比较复杂，剪枝和停止条件比较多，还未解决
 */
public class StickersToSpellWord {
    private int result = -1;

    public int minStickers(String[] stickers, String target) {
        List<String> temp = new ArrayList<>();
        char[] chars = target.toCharArray();
        backtracking(0, stickers, chars, temp);
        return result;
    }

    private void backtracking(int startIndex, String[] strings, char[] chars, List<String> temp) {
        char absent = isCover(temp, chars);

        if (absent == '0') {
            if (result == -1 || temp.size() < result) {
                result = temp.size();
            }
            return;
        }

        if (result != -1 && temp.size() >= result) {
            return;
        }

        for (int i = startIndex; i<strings.length; i++) {
            if (!strings[i].contains(String.valueOf(absent))) {
                continue;
            }
            temp.add(strings[i]);
            backtracking(i, strings, chars, temp);
            temp.remove(temp.size() -1);
        }
    }

    private Character isCover(List<String> strings, char[] chars) {
        Map<Character, Integer> map = splitString(strings);
        for (char ele : chars) {
            if (!map.containsKey(ele)) {
                return ele;
            } else if (map.get(ele) < 1) {
                return ele;
            } else {
                map.put(ele, map.get(ele) -1);
            }
        }
        return '0';
    }

    private Map<Character, Integer> splitString(List<String> strings) {
        Map<Character, Integer> map = new HashMap<>();
        for (String s : strings) {
            map.putAll(charMap(s.toCharArray()));
        }
        return map;
    }

    private Map<Character, Integer> charMap(char[] chars) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ele : chars) {
            if (map.containsKey(ele)) {
                map.put(ele, map.get(ele) + 1);
            } else {
                map.put(ele, 1);
            }
        }
        return map;
    }

    public static void main(String[] args) {
        StickersToSpellWord stickersToSpellWord = new StickersToSpellWord();
        int result = stickersToSpellWord.minStickers(new String[] {"with", "example", "science"}, "thehat");
        System.out.println(result);
    }
}
