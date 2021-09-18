package GraphTopic.UF;

/**
 * 加权quick-union算法
 *
 * 额外使用一个size数组，记录每棵树包含的节点数
 */
public class QuickUF {
    private int count;
    private int[] parent;
    private int[] size;

    public QuickUF(int n) {
        this.count = n;
        parent = new int[n];
        // 初始每棵树只有一个节点
        // 重量都是1
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    /* 其他函数 */

    // 返回某个节点的根节点，根节点的标志就是父节点就是自己
    private int find(int x) {
        while(parent[x] != x) {
            x = parent[x];
        }
        return x;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }

        // 小树接到大树下面，较平衡
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }

        count--;
    }
}
