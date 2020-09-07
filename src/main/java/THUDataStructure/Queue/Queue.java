package THUDataStructure.Queue;

public interface Queue {
    int getSize();

    boolean isEmpty();

    Object front() throws ExceptionQueueEmpty;

    void enqueue(Object obj) throws ExceptionQueueFull;

    Object dequeue() throws ExceptionQueueEmpty;

    void Traversal();

}
