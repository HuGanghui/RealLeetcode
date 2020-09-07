package THUDataStructure.List;


import THUDataStructure.DLNode;
import THUDataStructure.Iterator.Iterator;
import THUDataStructure.Position;
import THUDataStructure.Vector.ExceptionBoundaryViolation;


public class List_DLNode implements List {

    protected int numElem;

    protected DLNode header, trailer; // 哨兵：首节点+末节点

    public List_DLNode() {
        numElem = 0;
        header = new DLNode(null, null, null);
        trailer = new DLNode(null, null, null);
        header.setNext(trailer); // 首，末节点互相链接
    }

    /****************************************************/
    // 辅助方法，检查给定位置是否在列表中合法，若是，则转换为DLNode
    protected DLNode checkPosition(Position p) throws ExceptionPositionInvalid {
        if (null == p) {
            throw new ExceptionPositionInvalid("error");
        }
        if (header == p) {
            throw new ExceptionPositionInvalid("error");
        }
        if (trailer == p) {
            throw new ExceptionPositionInvalid("error");
        }
        DLNode temp = (DLNode) p;
        return temp;

    }

    /****************************************************/

    @Override
    public int getSize() {
        return numElem;
    }

    @Override
    public boolean isEmpty() {
        return (numElem == 0);
    }

    @Override
    public Position first() {
        if (isEmpty()) {
            throw new ExceptionListEmpty("error");
        }
        return header.getNext();
    }

    @Override
    public Position last() {
        if (isEmpty()) {
            throw new ExceptionListEmpty("error");
        }
        return trailer.getPrev();
    }

    @Override
    public Position getPrev(Position p) throws ExceptionPositionInvalid, ExceptionBoundaryViolation {
        DLNode v = checkPosition(p);
        DLNode prev = v.getPrev();
        if (prev == header) {
            throw new ExceptionBoundaryViolation("error");
        }
        return prev;
    }

    @Override
    public Position getNext(Position p) throws ExceptionPositionInvalid, ExceptionBoundaryViolation {
        DLNode v = checkPosition(p);
        DLNode next = v.getNext();
        if (next == trailer) {
            throw new ExceptionBoundaryViolation("error");
        }
        return next;
    }

    @Override
    public Position insertFirst(Object e) {
        numElem++;
        DLNode newNode = new DLNode(e, header, header.getNext());
        header.getNext().setPrev(newNode);
        header.setNext(newNode);
        return newNode;
    }

    @Override
    public Position insertLast(Object e) {
        numElem++;
        DLNode newNode = new DLNode(e, trailer, trailer.getNext());
        if (trailer.getPrev() == null) {
            System.out.println("!!! Prev of trailer is NULL !!!");
        }
        trailer.getPrev().setNext(newNode);
        trailer.setPrev(newNode);
        return newNode;
    }

    @Override
    public Position insertBefore(Position p, Object e) throws ExceptionPositionInvalid {
        DLNode v = checkPosition(p);
        numElem++;
        DLNode newNode = new DLNode(e, v.getPrev(), v);
        v.getPrev().setNext(newNode);
        v.setPrev(newNode);
        return newNode;
    }

    @Override
    public Position insertAfter(Position p, Object e) throws ExceptionPositionInvalid {
        DLNode v = checkPosition(p);
        numElem++;
        DLNode newNode = new DLNode(e, v, v.getNext());
        v.getNext().setPrev(newNode);
        v.setNext(newNode);
        return newNode;
    }

    @Override
    public Object remove(Position p) throws ExceptionPositionInvalid {
        DLNode v = checkPosition(p);
        numElem--;
        v.getPrev().setNext(v.getNext());
        v.getNext().setPrev(v.getPrev());
        v.setPrev(null);
        v.setNext(null);
        return v.getElem();
    }

    @Override
    public Object removeFirst() {
        return (remove(header.getNext()));
    }

    @Override
    public Object removeLast() {
        return (remove(trailer.getPrev()));
    }

    @Override
    public Object replace(Position p, Object e) throws ExceptionPositionInvalid {
        DLNode v = checkPosition(p);
        Object oldElem = v.getElem();
        v.setElem(e);
        return oldElem;
    }

    @Override
    public Iterator positions() {
        return null;
    }

    @Override
    public Iterator elements() {
        return null;
    }
}
