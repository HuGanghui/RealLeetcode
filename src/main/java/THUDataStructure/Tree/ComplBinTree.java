package THUDataStructure.Tree;

public interface ComplBinTree extends BiTree {
    public BinTreePosition addLast(Object e);

    public Object delLast();

    public BinTreePosition posOfNode(int i);
}
