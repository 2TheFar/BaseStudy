public class LowestCommonAncestor {
    /**
     * 核心思路：在一棵树里找 p 和 q，谁先同时“接住”它们，谁就是最近公共祖先。
     * 
     * 先熟悉以下几个DFS过程：
     * 找到一个关键值，并返回
     * 没有找到关键值，并返回
     * 左右递归都找到了关键值，二合一之后返回
     * 这样就能理解找到、接住、发现LCA的过程
     * 好脑子不如烂纸笔了，千言万语不如画两个图
     * 
     * 在以 root 为根的子树中，是否能找到 p 或 q。
     * 如果能找到，就返回找到的那个节点（p 或 q）；
     * 如果左右子树分别找到了 p 和 q，那么 root 就是最近公共祖先。
     * （只有最近公共祖先能在左右两边分别找到 p 和 q，比它高的 p 和 q 都在一边，比它低的只能找到其中之一）
     * 
     * 三种情况
     * 站在当前节点 root：
     * 情况 1：root 本身就是 p 或 q
     * 说明找到了一个，直接往上返回
     * 情况 2：左子树找到了一个，右子树也找到了一个
     * 说明当前节点就是LCA，两个目标节点合成一个LCA，直接返回
     * 情况 3：只有一边找到了
     * 要么就是 p 和 q 其中一个，要么就是LCA，直接返回就行了
     * 情况 4：啥也没有
     * 子树没有找到，自己也不是，或者这就是个空节点，说明这棵子树里没有目标节点，直接返回null
     * 
     * 验证可行性用的树能小就小
     */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return DFS(root, p, q);
    }

    private TreeNode DFS(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        // 尽情地下探吧，遍历整棵子树找目标，并能够传递回来
        // 这也是DFS的特性，儿子给爸爸，爸爸给爷爷
        // 找到了就不为空，没找到就为空
        // 用最简单的根左右模型来初步验证方法是否可行，有效且快速
        TreeNode left = DFS(root.left, p, q);
        TreeNode right = DFS(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }

}