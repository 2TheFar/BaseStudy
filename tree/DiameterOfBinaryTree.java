
public class DiameterOfBinaryTree {
    public int dfs(TreeNode root,int[] max) {
        if (root == null) return 0;
        // 递归的返回值肯定要用一个变量来保存，不然每次都要递归一次才能得到值夜太慢了！
        int left = dfs(root.left,max);
        int right = dfs(root.right,max);
        max[0]=Math.max(max[0],left+right);
        return Math.max(left,right)+1;
    }
}
