package DesignTopic;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串出现次数的TopK 牛客热考题
 *
 * 对于力扣的：
 * 692. 前K个高频单词 Median
 * https://leetcode-cn.com/problems/top-k-frequent-words/
 * 347. 前 K 个高频元素 Median
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 *
 * 这题是要求时间复杂度小于O(nlogn)的，要达到O(nlogk)，很明显需要使用
 * 堆/优先队列来完成，因为优先队列的操作复杂度取决于树高，只要我们保证
 * 优先队列只有k个元素即可，TopK的题，确实可以舍弃前面的元素，保留住k个元素。
 *
 * 然后这题如果直接使用Java的优先队列是更轻松的一件事，否则需要自己实现一个
 * 优先队列，并且利用了Comparable接口来进行比较。
 *
 */
class Solution {
    /**
     * return topK string
     *
     * @param strings string字符串一维数组 strings
     * @param k       int整型 the k
     * @return string字符串二维数组
     */
    public String[][] topKstrings(String[] strings, int k) {
        // write code here
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < strings.length; i++) {
            map.put(strings[i], map.getOrDefault(strings[i], 0) + 1);
        }
        Pair2[] pairs = new Pair2[map.size()];
        int i = 0;
        for (String key : map.keySet()) {
            pairs[i] = new Pair2(key, map.get(key));
            i++;
        }

        DesignPriorityQueue pq = new DesignPriorityQueue(k+1);
        for (int j = 0; j < map.size(); j++) {
            pq.offer(pairs[j]);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        String[][] result = new String[k][2];
        int j = k - 1;
        while (pq.size() > 0) {
            Pair2 temp = pq.poll();
            result[j] = new String[]{temp.key, String.valueOf(temp.val)};
            j--;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.topKstrings(new String[]{"a","b","c","b"},2);
    }

}

public class DesignPriorityQueue {

    private final int CAP;

    private int size;

    private Pair2[] pairs;

    public DesignPriorityQueue(int cap) {
        this.CAP = cap;
        pairs = new Pair2[this.CAP];
        size = 0;
    }

    public void offer(Pair2 ele) {
        pairs[size] = ele;
        size++;
        up(size-1);
    }

    private void up(int i) {
        int parent = (i - 1) / 2;
        if (parent >= 0 && pairs[parent].compareTo(pairs[i]) > 0) {
            swap(parent, i);
            up(parent);
        }
    }

    public Pair2 poll() {
        Pair2 ele = pairs[0];
        swap(0, size-1);
        size--;
        down(0);
        return ele;
    }

    private void down(int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int smallest = i;

        if (left < size && pairs[left].compareTo(pairs[smallest]) < 0) {
            smallest = left;
        }
        if (right < size && pairs[right].compareTo(pairs[smallest]) < 0) {
            smallest = right;
        }
        if (smallest != i) {
            swap(i, smallest);
            down(smallest);
        }
    }

    private void swap(int i, int j) {
        Pair2 temp = pairs[i];
        pairs[i] = pairs[j];
        pairs[j] = temp;
    }

    public int size() {
        return size;
    }
}


class Pair2 implements Comparable<Pair2> {
    String key;
    int val;
    public Pair2(String key, int val) {
        this.key = key;
        this.val = val;
    }

    @Override
    public int compareTo(Pair2 o) {
        if (this.val == o.val) {
            return -this.key.compareTo(o.key);
        } else {
            return this.val - o.val;
        }
    }
}

