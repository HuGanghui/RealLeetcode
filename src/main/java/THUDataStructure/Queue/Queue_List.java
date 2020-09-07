package THUDataStructure.Queue;

import THUDataStructure.Node;

public class Queue_List implements Queue {
    protected Node head;

    protected Node tail;

    protected int size;

    public Queue_List() {
        head = tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (0 == size);
    }

    @Override
    public Object front() throws ExceptionQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionQueueEmpty("Queue empty");
        }
        return head.getElem();
    }

    @Override
    public void enqueue(Object obj) throws ExceptionQueueFull {
        Node node = new Node();
        node.setElem(obj);
        node.setNext(null);
        if (0 == size) {
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
        size++;
    }

    @Override
    public Object dequeue() throws ExceptionQueueEmpty {
        if (isEmpty()) {
            throw new ExceptionQueueEmpty("Queue empty");
        }
        Object obj = head.getElem();
        Node v = head.getNext();
        head.setNext(null);
        head = v;
        if (0 == size) {
            tail = null;
        }
        return obj;
    }

    @Override
    public void Traversal() {
        Node p = head;
        while (null != p) {
            System.out.println(p.getElem() + " ");
            p = p.getNext();
        }
    }
}
