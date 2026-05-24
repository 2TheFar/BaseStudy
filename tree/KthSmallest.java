import java.util.Stack;

public class KthSmallest {
    /*
     * 对BST使用层序遍历吗？哦不对，层序是按水平顺序去遍历，忽大忽小这不是我们要的，我们要的是从小到大，水平投影的从左到右，这是中序遍历
     * 因为需要带全局信息k，所以用循环压栈的遍历方法更合适，但循环的思路和递归的思路是不同的，递归可以想象成一轮一轮的下探然后倒回来，但是循环不行
     * 栈就是把要遍历还没遍历的节点存起来，所以指向根节点时，应该一直往左走走到最左边，把沿路的节点都存起来
     * 然后一个一个弹栈左节点，在访问右边的父节点之前，应该先进入右节点，把右子树也同样地遍历了，然后以相同的方式把所有左子节点压入栈
     * 相当于把所有的节点都按水平方向上从左到右的顺序进行压栈，并且一个一个弹栈遍历
     */
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            k--;
            if (k == 0) {
                return root.val;
            }
            // 在访问完节点之后进入右子节点，如果右子节点为空，那就会判断栈是否为空，栈为空就结束，不为空就访问右边的第一个节点
            // 如果右子节点不为空，就会把右子树压栈，并且会第一个访问右子树的最左边节点，在遍历完右子树之后才会去访问右边第一个节点
            root = root.right;
        }
        return 0;
    }
}
