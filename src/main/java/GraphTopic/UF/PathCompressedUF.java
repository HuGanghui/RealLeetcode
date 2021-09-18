package GraphTopic.UF;

/**
 * 路径压缩版本的UF
 *
 * 只需要在find中添加一行代码
 */
public class PathCompressedUF {
    private int count;
    private int[] parent;
    private int[] size;

    public PathCompressedUF(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p , int q) {
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

    private int find(int p) {
        while (parent[p] != p) {
            // 一行代码 进行路径压缩
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }
}
