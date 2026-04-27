package com.zhiyuan.basestudy.tree;

import java.util.List;

public class EasyTreeProblems {
    // 方法里面不能定义方法嗷
    public void inorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) return;
        inorderTraversal(root.left, result);
        result.add(root.val);
        inorderTraversal(root.right, result);
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftHigh = maxDepth(root.left);
        int rightHigh = maxDepth(root.right);
        return (leftHigh > rightHigh) ? leftHigh + 1 : rightHigh + 1;
    }

    public void invertTree(TreeNode root) {
        if (root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
    }

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

    public int dfs(TreeNode root,int[] max) {
        if (root == null) return 0;
        // 递归的返回值肯定要用一个变量来保存，不然每次都要递归一次才能得到值夜太慢了！
        int left = dfs(root.left,max);
        int right = dfs(root.right,max);
        max[0]=Math.max(max[0],left+right);
        return Math.max(left,right)+1;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums==null||nums.length==0)return null;
        return buildToBST(nums,0,nums.length-1);
    }
    public TreeNode buildToBST(int[] nums,int left,int right ){
        if (left>right)return null;
        // 取中点中间偏左
        int mid=(left+right)/2;
        TreeNode root =new TreeNode(nums[mid]);
        root.left=buildToBST(nums,left,mid-1);
        root.right=buildToBST(nums,mid+1,right);
        return root;
    }
}
