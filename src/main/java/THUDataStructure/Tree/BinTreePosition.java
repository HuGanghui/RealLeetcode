package THUDataStructure.Tree;

import THUDataStructure.Iterator.Iterator;
import THUDataStructure.Position;

public interface BinTreePosition extends Position {
    boolean hasParent();

    BinTreePosition getParent();

    void setParent(BinTreePosition p);

    boolean isLeaf();

    boolean hasLChild();

    BinTreePosition getLChild();

    void setLChild(BinTreePosition c);

    boolean hasRChild();

    BinTreePosition getRChild();

    void setRChild(BinTreePosition c);

    int getHeight();

    void updateHeight();

    int getDepth();

    void updateDepth();

    // 按照中序遍历的次序，找到当前节点的直接前驱
    BinTreePosition getPrev();

    // 按照中序遍历的次序，找到当前节点的直接前驱
    BinTreePosition getSucc();

    // 断绝当前节点与其父亲的父子关系
    // 返回当前节点
    BinTreePosition secede();

    // 将节点c作为当前节点的左孩子
    BinTreePosition attachL(BinTreePosition c);

    // 将节点c作为当前节点的右孩子
    BinTreePosition attachR(BinTreePosition c);

    // 前序遍历
    Iterator elementsPreorder();

    // 中序遍历
    Iterator elementsInorder();

    // 后序遍历
    Iterator elementsPostorder();

    // 层次遍历
    Iterator elementsLevelorder();

}
