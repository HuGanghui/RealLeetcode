package THUDataStructure.Graph.UndirectedGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 该数据结构中，每条边都会出现两次，具有如下特点：
 *
 * 使用空间复杂度为O(V+E)
 * 添加一条边的时间复杂度为O(1)
 * 遍历顶点v的所有顶点的时间复杂度为O(degree(v))
 * 支持平行边和自环
 *
 * 对于这些操作，这样的特性已经是最优的了。
 */
public class GraphAdjacencyListImp implements Graph {
    private int V;
    private int E;
    // 如果不允许平行边 使用Set替换List
    private Map<Integer, List<Integer>> adj;

    public GraphAdjacencyListImp(int V) {
        this.V = V;
        this.E = 0;
        adj = new HashMap<>(V);
        for (int v = 0; v < V; v++) {
            adj.put(v, new ArrayList<>());
        }
    }

    // 这个函数用来读取边的数组的形式的图，进行邻接表的初始化
//    public GraphAdjacencyListImp() {
//    }

    @Override
    public int V() {
        return V;
    }

    @Override
    public int E() {
        return E;
    }

    @Override
    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        adj.get(w).add(v);
        E++;
    }

    @Override
    public Iterable<Integer> adj(int v) {
        return adj.get(v);
    }
}
