package TreeTopic;

/**
 * 208. 实现 Trie (前缀树) Median
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 *
 * 前缀树是典型的空间换时间的数据结构，通过字符串们的总长度 * 字符集的大小的空间，
 * 换取O(n)的时间插入和查询，n为字符串的长度。
 *
 * 实现起来其实没有很复杂，就是需要两个实例变量，一个是字符集大小的Trie数组，另一个是
 * 判断是否是该节点是某个在树中的单词的结尾。 insert，search都是迭代遍历树就行了。
 */
public class Trie {
    private Trie[] children;
    boolean isEnd;

    /** Initialize your data structure here. */
    public Trie() {
        this.children = new Trie[26];
        this.isEnd = isEnd;

    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        char[] chars = word.toCharArray();
        int n = chars.length;
        Trie node = this;
        for (int i = 0; i < n; i++) {
            int charIndex = chars[i] - 'a';
            if (node.children[charIndex] == null) {
                node.children[charIndex] = new Trie();
            }
            node = node.children[charIndex];
            if (i == n - 1) {
                node.isEnd = true;
            }
        }
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        char[] chars = word.toCharArray();
        int n = chars.length;
        Trie node = this;
        boolean result = false;
        for (int i = 0; i < n; i++) {
            int charIndex = chars[i] - 'a';
            if (node.children[charIndex] == null) {
                result = false;
                break;
            } else {
                node = node.children[charIndex];
                if (i == n - 1 && node.isEnd) {
                    result = true;
                }
            }
        }
        return result;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        char[] chars = prefix.toCharArray();
        int n = chars.length;
        Trie node = this;
        boolean result = false;
        for (int i = 0; i < n; i++) {
            int charIndex = chars[i] - 'a';
            if (node.children[charIndex] == null) {
                result = false;
                break;
            } else {
                node = node.children[charIndex];
                if (i == n - 1) {
                    result = true;
                }
            }
        }
        return result;
    }
}
