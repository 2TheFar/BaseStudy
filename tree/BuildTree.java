import java.util.HashMap;
import java.util.Map;

public class BuildTree {
    /**
     * 核心思路就是递归。
     * 
     * 前序遍历第一个元素是根节点，在中序遍历中找到这个根节点，左边就是左子树，右边就是右子树，然后递归处理左右部分。
     * 
     * 不过如果要追求效率，常规做法会先用一个 HashMap 缓存中序遍历中每个值的下标，避免每次递归都去遍历查找。
     * 
     * 自顶向下分治，掌握整棵树的全貌之后，自底向上连接
     * 
     * 原理性解释：
     * 因为递归构造的顺序和前序遍历顺序一致，都是“根 → 左 → 右”
     * 那么每当构造完一个根节点之后，p指向下一个前序节点，preorder[p]就是下一个要构造的子树根节点
     * 
     * 陈述性解释：
     * 比如要构造左子树根节点，p 刚好就指向左子树根节点
     * 没有左子树，要构建右子树根节点，p 也刚好就指向右子树根节点
     * 所以 p 始终指向当前要构造的子树的根节点
     * 
     * 如果一个陈述性的解释能说得通的话，就不用硬写一个原理性的解释了，没那个必要，而且太花时间了
     */
    private int p = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return build(preorder, map, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, Map<Integer, Integer> map, int left, int right) {
        if (left > right) {
            return null;
        }
        int rootVal = preorder[p];
        int rootIndex = map.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        p++;
        root.left = build(preorder, map, left, rootIndex - 1);
        root.right = build(preorder, map, rootIndex + 1, right);
        return root;
    }

}
