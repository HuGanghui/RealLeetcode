package THUDataStructure.Sequence;

import THUDataStructure.DLNode;
import THUDataStructure.List.ExceptionPositionInvalid;
import THUDataStructure.List.List_DLNode;
import THUDataStructure.Position;
import THUDataStructure.Vector.ExceptionBoundaryViolation;

/*通过双向链表来实现序列是最自然的方式， 这样列表ADT中的所有方法都保持原来O(1)的复杂度，
  而向量里的方法自然效率会有所损失*/
public class Sequence_DLNode extends List_DLNode implements Sequence {

    protected void checkRank(int r, int n) throws ExceptionBoundaryViolation {
        if (r < 0 || r >= n) {
            throw new ExceptionBoundaryViolation("error");
        }
    }

    @Override
    public Position rank2Pos(int r) throws ExceptionBoundaryViolation {
        DLNode node;
        checkRank(r, getSize());
        if (r <= getSize() / 2) {
            node = header.getNext(); // 若秩比较小， 则从前端开始
            for (int i = 0; i < r; i++) {
                node = node.getNext();
            }
        } else {
            node = trailer.getPrev();
            for (int i = 1; i < getSize() - r; i++) {
                node = node.getPrev();
            }
        }
        return node;
    }

    @Override
    public int pos2Rank(Position p) throws ExceptionPositionInvalid {
        DLNode node = header.getNext();
        int r = 0;
        while (node != trailer) {
            if (node == p) {
                return r;
            }
            node = node.getNext();
            r++;
        }
        throw new ExceptionPositionInvalid("error");
    }

    @Override
    public Object getAtRank(int r) throws ExceptionBoundaryViolation {
        checkRank(r, getSize());
        return rank2Pos(r).getElem();
    }

    @Override
    public Object insertAtRank(int r, Object obj) throws ExceptionBoundaryViolation {
        checkRank(r, getSize()+1);
        if (getSize() == r) {
            insertLast(obj);
        } else {
            insertBefore(rank2Pos(r), obj);
        }
        return obj;
    }

    @Override
    public Object replaceAtRank(int r, Object obj) throws ExceptionBoundaryViolation {
        checkRank(r, getSize());
        return replace(rank2Pos(r), obj);
    }

    @Override
    public Object removeAtRank(int r) throws ExceptionBoundaryViolation {
        checkRank(r, getSize());
        return remove(rank2Pos(r));
    }
}
