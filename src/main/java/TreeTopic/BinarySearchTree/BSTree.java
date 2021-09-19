package TreeTopic.BinarySearchTree;

/**
 * 二叉搜索树实现
 *
 * 插入、删除、查询操作 以及 **查询第k个数（优化）**
 *
 * 这里的相关操作 最核心的是利用二叉搜索树的特有性质，可以进行二分查找类似
 * 的操作，使得时间复杂度都在O(logN)，其中
 *
 * 删除操作找到目标节点后，根据其不同的子树情况，有几种情况，需要分别考虑
 *
 * 查询第k个数（优化），为了降低时间复杂度，需要在Node中额外维护左子树大小
 */
public class BSTree {
    private BSTNode root;

    public BSTree() {}

    public BSTree(BSTNode root) {
        this.root = root;
    }

    public void insert(int val) {
        insert(root, val);
    }

    private BSTNode insert(BSTNode root, int val) {
        if (root == null) {
            return new BSTNode(val);
        }

        if (root.val > val) {
            // 维护 leftSize
            root.leftSize += 1;
            root.left = insert(root.left, val);
        } else if (root.val < val){
            root.right = insert(root.right, val);
        }
        return root;
    }

    public void delete(int val) {
        delete(root, val);
    }

    private BSTNode delete(BSTNode root, int val) {
        if (root == null) {
            return root;
        }

        if (root.val > val) {
            // 维护 leftSize
            root.leftSize -= 1;
            root.left = delete(root.left, val);
        } else if (root.val < val) {
            root.right = delete(root.right, val);
        } else {
            // 考虑三种情况
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                root = root.right;
            } else if (root.right == null) {
                root = root.left;
            } else {
                // 要么找直接后继替换，要么找直接前继替换，这里实现第一种
                BSTNode successor = getSucc(root.right);
                root.val = successor.val;
                root.right = delete(root.right, successor.val);
            }
        }
        return root;
    }

    private BSTNode getSucc(BSTNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public BSTNode search(int val) {
        return search(root, val);
    }

    private BSTNode search(BSTNode root, int val) {
        if (root == null) {
            return null;
        }

        if (root.val == val) {
            return root;
        } else if (root.val > val) {
            return search(root.left, val);
        } else {
            return search(root.right, val);
        }

    }

    public int findKth(int k) {
        return findKth(root, k);
    }

    // 使用leftSize，将时间复杂度降低到O(logN)
    private int findKth(BSTNode root, int k) {
        if (root == null) {
            return -1;
        }
        int curIndex = root.leftSize + 1;
        int res = -1;
        if (curIndex == k) {
            res = root.val;
        } else if (curIndex > k) {
            res = findKth(root.left, k);
        } else {
            res = findKth(root.right, k);
        }
        return res;
    }
}

class BSTNode {
    public int val;
    public BSTNode left;
    public BSTNode right;
    // 维护左子树大小，优化查询第k个数时间复杂度
    public int leftSize;

    public BSTNode(int val) {
        this.val = val;
    }
}