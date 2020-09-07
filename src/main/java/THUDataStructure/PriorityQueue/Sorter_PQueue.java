package THUDataStructure.PriorityQueue;

import THUDataStructure.Sequence.Sequence;

public class Sorter_PQueue implements Sorter {
    private Comparator C;

    public Sorter_PQueue() {
        this(new ComparatorDefault());
    }

    public Sorter_PQueue(Comparator comp) {
        C = comp;
    }

    public void sort(Sequence s) {

    }
}
