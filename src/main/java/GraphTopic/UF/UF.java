package GraphTopic.UF;

/**
 * UF算法
 */
class UF {
    // 记录连通分量
    private int count;
    // 记录所有节点的父节点
    private int[] parent;

    public UF(int n) {
        // 一开始互不连通
        this.count = n;
        // 父节点指针初始指向自己
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        // 合并两棵树
        parent[rootP] = rootQ;
        count--;
    }

    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }

    // 返回某个节点的根节点，根节点的标志就是父节点就是自己
    private int find(int x) {
        while(parent[x] != x) {
            x = parent[x];
        }
        return x;
    }

    public int count() {
        return count;
    }
}
