
public class MaxDepth {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftHigh = maxDepth(root.left);
        int rightHigh = maxDepth(root.right);
        return (leftHigh > rightHigh) ? leftHigh + 1 : rightHigh + 1;
    }
}
