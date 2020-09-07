package THUDataStructure.List;

import THUDataStructure.Iterator.Iterator;
import THUDataStructure.Position;
import THUDataStructure.Vector.ExceptionBoundaryViolation;


public interface List {
    int getSize();

    boolean isEmpty();

    Position first();

    Position last();

    Position getNext(Position p) throws ExceptionPositionInvalid, ExceptionBoundaryViolation;

    Position getPrev(Position p) throws ExceptionPositionInvalid, ExceptionBoundaryViolation;

    Position insertFirst(Object e);

    Position insertLast(Object e);

    Position insertAfter(Position p, Object e) throws ExceptionPositionInvalid;

    Position insertBefore(Position p, Object e) throws ExceptionPositionInvalid;

    Object remove(Position p) throws ExceptionPositionInvalid;

    Object removeFirst();

    Object removeLast();

    Object replace(Position p, Object e) throws ExceptionPositionInvalid;

    Iterator positions();

    Iterator elements();

}
