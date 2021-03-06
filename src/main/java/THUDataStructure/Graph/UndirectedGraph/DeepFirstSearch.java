package THUDataStructure.Graph.UndirectedGraph;

/**
 * 深度遍历算法完成某个顶点s的连通量以及和各个顶点的连通性的判断。
 */
public class DeepFirstSearch extends Search {
    private boolean[] marked;
    private int count;

    public DeepFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);

    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    private void bfs(Graph G, int s) {
        marked[s] = true;

    }

    @Override
    boolean marked(int v) {
        return marked[v];
    }

    @Override
    int count() {
        return count;
    }
}
