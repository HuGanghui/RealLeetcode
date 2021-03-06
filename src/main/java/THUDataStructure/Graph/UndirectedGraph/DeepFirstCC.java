package THUDataStructure.Graph.UndirectedGraph;

public class DeepFirstCC extends CC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public DeepFirstCC(Graph G) {
        marked = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    @Override
    public boolean connected(int v, int w) {
        return id[w] == id[v];
    }

    @Override
    int count() {
        return count;
    }

    @Override
    int id(int v) {
        return id[v];
    }
}
