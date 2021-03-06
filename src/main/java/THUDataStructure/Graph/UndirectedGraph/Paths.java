package THUDataStructure.Graph.UndirectedGraph;

/**
 * 寻找路径问题，给定一个图和一个起点s，判断从s到目的顶点v是否存在一条路径，如果有找到这一条路径
 */
public abstract class Paths {
    Paths(){}
    Paths(Graph G, int s){}
    abstract boolean hasPathsTo(int v);
    abstract Iterable<Integer> pathTo(int v);
}
