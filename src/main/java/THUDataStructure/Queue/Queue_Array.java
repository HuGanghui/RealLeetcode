package THUDataStructure.Queue;

public class Queue_Array implements Queue {
    public static final int CAPACITY = 1000;

    protected int capacity;

    protected Object[] Q;

    protected int f = 0;

    protected int r = 0;

    public Queue_Array() {
        this(CAPACITY);
    }

    public Queue_Array(int cap) {
        capacity = cap;
        Q = new Object[capacity];
    }


    @Override
    public int getSize() {
        return (capacity - f + r) % capacity;
    }

    @Override
    public boolean isEmpty() {
        return (f == r);
    }

    @Override
    public Object front() throws ExceptionQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionQueueEmpty("Queue empty");
        }
        return Q[f];
    }

    @Override
    public void enqueue(Object obj) throws ExceptionQueueFull {
        if (getSize() == capacity - 1 ) {
            throw new ExceptionQueueFull("Queue overflow");
        }
        Q[r] = obj;
        r = (r + 1) % capacity;

    }

    @Override
    public Object dequeue() throws ExceptionQueueEmpty {
        Object elem;
        if (isEmpty()) {
            throw new ExceptionQueueEmpty("Queue empty");
        }
        elem = Q[f];
        Q[f] = null;
        f = (f + 1) % capacity;
        return elem;
    }

    @Override
    public void Traversal() {
        for (int i = f; i < r; i++) {
            System.out.println(Q[i] + " ");
        }
        System.out.println();
    }
}
