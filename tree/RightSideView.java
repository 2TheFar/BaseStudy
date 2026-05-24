import java.util.*;

public class RightSideView {
    /**
     * 我认为这题的关键是层序遍历，只能看到每一层最右边的节点
     * BFS是先进先出哦，是队列不是栈
     * 我想知道每一层最右边也就是层序最后的节点，要不用一个值存当前层节点数量，再用另一个值存下一层节点数量？
     * 更简洁的做法是直接利用 queue.size() 循环处理每一层
     * 因为每轮 for 循环开始时 queue 里刚好全是同一层的节点，queue.size() 就是当前层的节点数，不需要手动维护两个计数器。可以开写了。
     * 优化方向：DFS（右→左 先序遍历）— 实际更快
     * 核心：先走右子树，每层第一个访问到的节点就是右视图看到的节点
     * （depth == result.size() 判断是否是第一次到达该层）。
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode p = null;
        // 待处理节点都放进去，如果 root == null，null 被加进了队列，后面 p.left 就崩了
        if (root != null)
            queue.add(root);
        while (!queue.isEmpty()) {
            // 第一层只有1个，第一个处理完，队列的大小就是第二层的节点数，同理，第二层处理完，队列的大小就是第三层的节点数
            // 所以把BFS分成一层一层的处理，每一层处理开始都能知道这一层有几个节点，数学归纳之法
            int size = queue.size(); // 不要放在for循环里，每层数量大小不可变
            for (int i = 0; i < size; i++) {
                p = queue.poll();
                if (p.left != null) {
                    queue.add(p.left);
                }
                if (p.right != null) {
                    queue.add(p.right);
                }
            }
            result.add(p.val);
        }
        return result;
    }
}
