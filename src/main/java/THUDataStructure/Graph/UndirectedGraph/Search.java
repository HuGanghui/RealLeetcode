package THUDataStructure.Graph.UndirectedGraph;

/**
 * 图处理算法之一：图搜索
 */
public abstract class Search {
    Search(){}

    Search(Graph G, int s){}

    abstract boolean marked(int v); // v 和 s是连通的吗

    abstract int count(); // 与s连通的顶点总数
}
