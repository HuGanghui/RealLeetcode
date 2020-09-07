package THUDataStructure.Iterator;

import THUDataStructure.List.List;
import THUDataStructure.Position;

/*
* 基于列表实现的位置迭代器，IteratorElement 也是类似
* */
public class IteratorPosition implements Iterator {
    private List list;
    private Position nextPosition;

    public IteratorPosition() {
        list = null;
    }

    public IteratorPosition(List L) {
        list = L;
        if (list.isEmpty()) {
            nextPosition = null;
        } else {
            nextPosition = list.first();
        }
    }

    @Override
    public boolean hasNext() {
        return (nextPosition != null);
    }

    @Override
    public Object getNext() throws ExceptionNoSuchElement {
        if (!hasNext()) {
            throw new ExceptionNoSuchElement("error");
        }
        Position currentPosition = nextPosition;
        if (currentPosition == list.last()) {
            nextPosition = null;
        }
        else {
            nextPosition = list.getNext(currentPosition);
        }
        return currentPosition;
    }
}

