package THUDataStructure.Stack;

// 参考 邓俊文的数据结构java 第二章 栈和队列

/*
* 没有使用范型，因此在取出操作后需要进行强制转换
* */
public class Stack_Array implements Stack {
    public static final int CAPACITY = 1024; // default capacity or array

    protected int capacity; // practical capacity of array

    protected Object[] S; // array

    protected int top = -1; // position of ele in top of stack

    public Stack_Array() {
        this(CAPACITY);
    }

    public Stack_Array(int cap) {
        capacity = cap;
        S = new Object[capacity];
    }

    @Override
    public int getSize() {
        return (top + 1);
    }

    @Override
    public boolean isEmpty() {
        return (top < 0);
    }

    @Override
    public Object top() throws ExceptionStackEmpty {
        if (isEmpty()) {
            throw new ExceptionStackEmpty("unexpected: Stack empty");
        }
        return S[top];
    }

    @Override
    public void push(Object ele) throws ExceptionStackFull {
        if (getSize() == capacity) {
            throw new ExceptionStackFull("unexpected: Stack full");
        }
        S[++top] = ele;
    }

    @Override
    public Object pop() throws ExceptionStackEmpty {
        Object elem;

        if (isEmpty()) {
            throw new ExceptionStackEmpty("unexpected: Stack empty");
        }
        elem = S[top];
        S[top--] = null; // 考虑java的内存回收机制
        return elem;
    }
}
