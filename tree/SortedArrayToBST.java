
public class SortedArrayToBST {
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
