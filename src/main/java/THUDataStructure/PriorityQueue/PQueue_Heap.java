package THUDataStructure.PriorityQueue;

import THUDataStructure.PriorityQueue.Exception.ExceptionKeyInvaild;
import THUDataStructure.PriorityQueue.Exception.ExceptionPQueueEmpty;
import THUDataStructure.Tree.ComplBinTree;

public class PQueue_Heap implements PQueue {
    private ComplBinTree H;
    private Comparator comp;

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Entry getMin() throws ExceptionPQueueEmpty {
        return null;
    }

    @Override
    public Entry insert(Object key, Object obj) throws ExceptionKeyInvaild {
        return null;
    }

    @Override
    public Entry delMin() throws ExceptionPQueueEmpty {
        return null;
    }
}
