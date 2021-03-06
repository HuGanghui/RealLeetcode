package THUDataStructure.Graph.UndirectedGraph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class DeepFirstPaths extends Paths {
    private boolean[] marked; // 是否和s连通
    private int[] edgeTo; // 保存从起点到一个顶点的已知路径上的最后一个顶点，这个其实就是构建一个父指针的树
    private final int s; // 起点

    public DeepFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    /**
     * dfs获取的路径是随机的，与图的表示和递归调用的性质有关，
     * 如果需要获取从起始节点到目标节点的最短路径, 就需要使用bfs
     *
     * @param G Graph
     * @param s source node
     */
    private void dfs(Graph G, int s) {
        marked[s] = true;
        for (int w : G.adj(s)) {
            if (!marked[w]) {
                edgeTo[w] = s; // 相比 DeepFirstSearch 多了步这个记录路径而已
                dfs(G, w);
            }
        }
    }

    /**
     * bfs获取路径同样是edgeTo[]来表示，只不过能够获取最短路径。
     *
     * @param G Graph
     * @param s source node
     */
    private void bfs(Graph G, int s) {
        Deque<Integer> deque = new LinkedList<>();
        deque.addLast(s);
        while (!deque.isEmpty()) {
            int temp = deque.removeFirst();
            if (!marked[temp]) {
                marked[temp] = true;
                for (int w : G.adj(temp)) {
                    deque.addLast(w);
                    edgeTo[w] = temp;
                }
            }
        }
    }


    @Override
    boolean hasPathsTo(int v) {
        return marked[v];
    }

    @Override
    Iterable<Integer> pathTo(int v) {
        if (!hasPathsTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
