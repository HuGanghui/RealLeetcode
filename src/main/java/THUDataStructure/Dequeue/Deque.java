package THUDataStructure.Dequeue;

import THUDataStructure.Queue.ExceptionQueueEmpty;

public interface Deque {
    int getSize();

    boolean isEmpty();

    Object first() throws ExceptionQueueEmpty;

    Object last() throws ExceptionQueueEmpty;

    void insertFirst(Object obj);

    void insertLast(Object obj);

    Object removeFirst() throws ExceptionQueueEmpty;

    Object removeLast() throws ExceptionQueueEmpty;

    void Traversal();

}
