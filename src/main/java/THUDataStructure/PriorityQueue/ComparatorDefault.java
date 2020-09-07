package THUDataStructure.PriorityQueue;

public class ComparatorDefault implements Comparator {

    public int compare(Object a, Object b) {
        return ((Comparable) a).compareTo(b);
    }
}
