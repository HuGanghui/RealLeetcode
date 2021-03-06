package THUDataStructure.Graph.UndirectedGraph;

/**
 * 无向图Graph的基本API，这个API可以使用邻接矩阵、边的数组以及邻接表数组来
 * 完成实现，但是邻接矩阵的空间利用率太低，边的数组如果需要获取某个点的相邻节点需要
 * 遍历整个数组，效率低了，邻接表数组比较适合。
 */
public interface Graph {
    int V(); // 顶点数
    int E(); // 边数
    void addEdge(int v, int w); // 增加一条边v-w
    Iterable<Integer> adj(int v); // 和v相邻的所有顶点
    String toString(); // 对象的字符串表示
}
