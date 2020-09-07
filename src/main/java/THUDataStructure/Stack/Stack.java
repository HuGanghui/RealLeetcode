package THUDataStructure.Stack;

public interface Stack {
    int getSize();

    boolean isEmpty();

    Object top() throws ExceptionStackEmpty;

    void push(Object ele);

    Object pop() throws ExceptionStackEmpty;
}
