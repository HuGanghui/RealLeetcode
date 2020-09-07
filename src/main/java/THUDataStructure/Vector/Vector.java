package THUDataStructure.Vector;

public interface Vector {
    int getSize();

    boolean isEmpty();

    Object getAtRank(int r) throws ExceptionBoundaryViolation;

    Object insertAtRank(int r, Object obj) throws ExceptionBoundaryViolation;

    Object replaceAtRank(int r, Object obj) throws ExceptionBoundaryViolation;

    Object removeAtRank(int r) throws ExceptionBoundaryViolation;
}
