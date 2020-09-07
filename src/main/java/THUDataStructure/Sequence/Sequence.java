package THUDataStructure.Sequence;

import THUDataStructure.List.ExceptionPositionInvalid;
import THUDataStructure.List.List;
import THUDataStructure.Position;
import THUDataStructure.Vector.ExceptionBoundaryViolation;
import THUDataStructure.Vector.Vector;

public interface Sequence extends Vector, List {

    public Position rank2Pos(int r) throws ExceptionBoundaryViolation;

    public int pos2Rank(Position p) throws ExceptionPositionInvalid;
}
