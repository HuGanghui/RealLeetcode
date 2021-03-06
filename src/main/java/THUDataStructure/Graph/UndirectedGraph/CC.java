package THUDataStructure.Graph.UndirectedGraph;

/**
 * 关于连通性问题，深度优先和union-find（UF）算法相比，UF算法不需要完整构造一幅图，
 * 并且UF算法是一种动态算法（可以在任何时刻修改图，比如增加一条边等），而深度优先则需要
 * 对图进行预处理。因此我们在完成只需要判断连通下或者需要完成大量两头小查询和插入操作混合时，
 * 更倾向UF算法，而深度优先更适合实现图的抽象数据额类型。
 */
public abstract class CC {
    CC(){}
    CC(Graph G){}
    abstract boolean connected(int v, int w); // 节点v和w连通吗
    abstract int count();
    abstract int id(int v);
}
