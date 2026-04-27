
import java.util.List;

public class InorderTraversal {
    // 方法里面不能定义方法嗷
    public void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) return;
        inorderTraversal(root.left, result);
        result.add(root.val);
        inorderTraversal(root.right, result);
    }
}
