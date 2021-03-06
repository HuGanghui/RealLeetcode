package THUDataStructure.Graph.UndirectedGraph;

import java.util.Deque;
import java.util.LinkedList;

public class BreathFirstSearch extends Search {
    private boolean[] marked;
    private int count;

    public BreathFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Deque<Integer> deque = new LinkedList<>();
        deque.addLast(s);
        while (!deque.isEmpty()) {
            int temp = deque.removeFirst();
            marked[temp] = true;
            count++;
            for (int w : G.adj(temp)) {
                if (!marked[w]) {
                    deque.addLast(w);
                }
            }
        }
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
