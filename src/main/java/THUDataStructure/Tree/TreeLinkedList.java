package THUDataStructure.Tree;

public class TreeLinkedList implements Tree {
    private Object element; // 树根节点

    private TreeLinkedList parent, firstChild, nextSibing; // 父亲、长子和最大的弟弟

    //（单节点树）构造方法
    public TreeLinkedList() {
        this(null, null, null, null);
    }

    public TreeLinkedList(Object e, TreeLinkedList p, TreeLinkedList c, TreeLinkedList s) {
        element = e;
        parent = p;
        firstChild = c;
        nextSibing = s;
    }

    @Override
    public Object getElem() {
        return element;
    }

    @Override
    public Object setElem(Object obj) {
        Object bak = element;
        element = obj;
        return bak;
    }

    @Override
    public TreeLinkedList getParent() {
        return parent;
    }

    @Override
    public TreeLinkedList getFirstChild() {
        return firstChild;
    }

    @Override
    public TreeLinkedList getNextSibling() {
        return nextSibing;
    }

    // 返回当前节点后代元素的数目，也就是以当前节点为根的子树的规模
    @Override
    public int getSize() {
        int size = 1;
        TreeLinkedList subtree = firstChild;
        while (null != subtree) {
            size += subtree.getSize();
            subtree = subtree.getNextSibling();
        }
        return size;
    }

    @Override
    public int getHeight() {
        int height = -1;
        TreeLinkedList subtree = firstChild;
        while (null != subtree) {
            height = Math.max(height, subtree.getHeight());
            subtree = subtree.getNextSibling();
        }
        return height + 1;
    }

    @Override
    public int getDepth() {
        int depth = 0;
        TreeLinkedList p = parent;
        while (null != p) {
            depth++;
            p = p.getParent();
        }
        return depth;
    }
}
