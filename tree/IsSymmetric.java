
public class IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode l, TreeNode r) {
        // 看返回值不要看两边，只要看成一边的遍历，一直返回true就是true，一旦有false就是false
        if (l == null && r == null) return true;
        if (l == null || r == null) return false;
        return l.val == r.val && isMirror(l.left, r.right) && isMirror(l.right, r.left);
    }
}
