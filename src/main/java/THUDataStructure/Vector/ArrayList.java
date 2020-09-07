package THUDataStructure.Vector;

public class ArrayList implements Vector {
    private final int N = 1024;

    private int n = 0;

    private Object[] A;

    @Override
    public int getSize() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return (0 == n);
    }

    @Override
    public Object getAtRank(int r) throws ExceptionBoundaryViolation {
        if (0 > r || r >= n) {
            throw new ExceptionBoundaryViolation("out of scope");
        }
        return A[r];
    }

    @Override
    public Object insertAtRank(int r, Object obj) throws ExceptionBoundaryViolation {
        if (0 > r || r > n) {
            throw new ExceptionBoundaryViolation("out of scope");
        }
        if (n >= N) {
            throw new ExceptionBoundaryViolation("overflow");
        }
        for (int i = n; i > r; i--) {
            A[i] = A[i - 1];
        }
        A[r] = obj;
        n++;
        return obj;
    }

    @Override
    public Object replaceAtRank(int r, Object obj) throws ExceptionBoundaryViolation {
        if (0 > r || r >= n) {
            throw new ExceptionBoundaryViolation("out of scope");
        }
        Object bak = A[r];
        A[r] = obj;
        return bak;
    }

    @Override
    public Object removeAtRank(int r) {
        if (0 > r || r >= n) {
            throw new ExceptionBoundaryViolation("out of scope");
        }
        Object bak = A[r];
        for (int i = r; i < n; i++) {
            A[i] = A[i + 1];
        }
        n--;
        return bak;
    }
}
