package THUDataStructure.Tree;

import THUDataStructure.Iterator.ExceptionNoSuchElement;
import THUDataStructure.Iterator.Iterator;
import THUDataStructure.List.List;
import THUDataStructure.Position;
import THUDataStructure.Queue.Queue_List;

public class IteratorTree implements Iterator {
    private List list; // 列表
    private Position nextPosition;

    public IteratorTree() {
        list = null;
    }

    public void elementsPreorderIterator(TreeLinkedList T) {
        if (null == T) {
            return;
        }
        list.insertLast(T);
        TreeLinkedList subtree = T.getFirstChild();
        while (null != subtree) {
            this.elementsPreorderIterator(subtree);
            subtree = subtree.getNextSibling();
        }
    }

    public void elementsPostorderIterator(TreeLinkedList T) {
        if (null == T) {
            return;
        }
        TreeLinkedList subtree = T.getFirstChild();
        while (null != subtree) {
            this.elementsPostorderIterator(subtree);
            subtree = subtree.getNextSibling();
        }
        list.insertLast(T);
    }

    public void levelTraversalIterator(TreeLinkedList T) {
        if (null == T) {
            return;
        }
        Queue_List Q = new Queue_List();
        Q.enqueue(T);
        while (!Q.isEmpty()) {
            TreeLinkedList tree = (TreeLinkedList) (Q.dequeue());
            list.insertLast(tree);
            TreeLinkedList subtree = tree.getFirstChild();
            while (null != subtree) {
                Q.enqueue(subtree);
                subtree = subtree.getNextSibling();
            }
        }
    }

    @Override
    public boolean hasNext() {
        return (null != nextPosition);
    }

    @Override
    public Object getNext() throws ExceptionNoSuchElement {
        if (!hasNext()) {
            throw new ExceptionNoSuchElement("No next position");
        }
        Position currentPosition = nextPosition;
        if(currentPosition == list.last()) {
            nextPosition = null;
        } else {
            nextPosition = list.getNext(currentPosition);
        }
        return  currentPosition.getElem();
    }
}
