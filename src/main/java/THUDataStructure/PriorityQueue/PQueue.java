package THUDataStructure.PriorityQueue;

import THUDataStructure.PriorityQueue.Exception.ExceptionKeyInvaild;
import THUDataStructure.PriorityQueue.Exception.ExceptionPQueueEmpty;

public interface PQueue {
    public int getSize();

    public boolean isEmpty();

    public Entry getMin() throws ExceptionPQueueEmpty;

    public Entry insert(Object key, Object obj) throws ExceptionKeyInvaild;

    public Entry delMin() throws ExceptionPQueueEmpty;
}
