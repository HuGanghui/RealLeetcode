package LeetcodeWeeklyMatch.Weekly223;

import java.util.*;

/**
 * 1722. 执行交换操作后的最小汉明距离 Median
 * https://leetcode-cn.com/problems/minimize-hamming-distance-after-swap-operations/
 *
 * 本题的按任意顺序多次交换一对特定下标指向的元素，其实就是典型的动态连通性的特征，
 * 使用并查集这个数据结构来辅助，可以高效的完成本题。通过本题熟悉了并查集这个数据结构。
 *
 * 大体框架基本就是这样，但是本题还有两个小坑：
 *
 * 1. 可能有重复元素，因此要使用list来保存target的index
 * 2. 由于source可能有多于target中相同且互相连通的元素，因此需要匹配后删除，而java迭代过程中
 *    删除操作只能使用iterator来完成
 */
public class MinimumHammingDistance {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int n = source.length;
        UF uf = new UF(n);

        for (int[] swap : allowedSwaps) {
            uf.union(swap[0], swap[1]);
        }

        Map<Integer, List<Integer>> map = new HashMap<>();

        // 需要主要的是可能有重复元素，因此需要使用list来保存
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(target[i])) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(target[i], list);
            } else {
                map.get(target[i]).add(i);
            }
        }

        int same = 0;
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(source[i])) {
                continue;
            } else {
                // 迭代时修改元素只能使用iterator
                Iterator<Integer> it = map.get(source[i]).iterator();
                while (it.hasNext()) {
                    int ele = it.next();
                    if (uf.connected(i, ele)) {
                        same++;
                        // 删除是因为source中可能包含多于target的相同元素，并且互相连通
                        // 因此需要匹配后删除
                        it.remove();
                        break;
                    }
                }
            }
        }
        return n - same;
    }
}

class UF {
    private int count;
    private int[] parent;
    private int[] size;

    public UF(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        count--;
    }

    private int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }
}
