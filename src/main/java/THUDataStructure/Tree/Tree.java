package THUDataStructure.Tree;

public interface Tree {
    Object getElem();

    Object setElem(Object obj);

    TreeLinkedList getParent();

    TreeLinkedList getFirstChild();

    TreeLinkedList getNextSibling();

    int getSize();

    int getHeight();

    int getDepth();
}
