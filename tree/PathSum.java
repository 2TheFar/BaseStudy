import java.util.HashMap;
import java.util.Map;

public class PathSum {
    /**
     * 终极路径组合
     * 没招了，这个是真的难
     * 
     * curSum 表示从根节点到当前节点的路径和。
     * 
     * 如果之前出现过 curSum - targetSum，
     * 说明从那个位置之后到当前节点的路径和正好等于 targetSum。
     * 
     * 递归结束后要撤销当前 curSum，
     * 因为它只能影响当前分支，不能影响兄弟分支。
     * 
     * 只要返回数量啊，我去不早说！
     * 
     * 前缀和 = 从根节点到当前节点的路径和
     * 前缀 = 从开头开始的一段内容
     * 
     * 需要注意prefixMap.get(curSUm) == null不是说数量为0，而是压根没有存这个键，需要先存键
     */

    // long curSum = 0;// 前缀和用long存防止节点值累加后溢出，这个倒不用全局，这个是局部路径的，每一条各不相同还要回溯
    private Map<Long, Integer> prefixMap = new HashMap<>();// 前缀映射表，也可以说是之前每个节点的前缀和
    private int result = 0;// 结果数
    private int target = 0;// 这个是全局的，就不用在方法中传递了

    public static void main(String[] args) {
        Integer[] arr = { 10, 5, -3, 3, 2, null, 11, 3, -2, null, 1 };
        TreeNode root = buildTree(arr);
        PathSum ps = new PathSum();
        System.out.println(ps.pathSum(root, 8));
    }

    private static TreeNode buildTree(Integer[] arr) {
        if (arr == null || arr.length == 0 || arr[0] == null)
            return null;
        TreeNode[] nodes = new TreeNode[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                nodes[i] = new TreeNode(arr[i]);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (nodes[i] == null)
                continue;
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < arr.length)
                nodes[i].left = nodes[left];
            if (right < arr.length)
                nodes[i].right = nodes[right];
        }
        return nodes[0];
    }

    public int pathSum(TreeNode root, int targetSum) {
        // 记一个空路径的路径和为0，用来处理从根节点开始的路径，
        // 当curSum=targetSum时，curSum-targetSum=0能够有结果，需要哈希表里面有一条长度为0的空路径
        prefixMap.put(0L, 1);
        this.target = targetSum;
        DFS(root, 0);
        return this.result;
    }

    private void DFS(TreeNode root, long curSUm) {
        // 结构性校验，无关业务
        if (root == null) {
            return;
        }
        // 更新curSum
        curSUm += root.val;
        /**
         * target：从中间某个节点到当前节点的路径和
         * prefix：从根节点到那个节点之前的前缀和
         * curSum：从根节点到当前节点的前缀和
         * prefix+target=curSum，两段相加就是总和
         * 有多少个前半段前缀和为prefix，就有多少个后半段路径和为target，每个后半段都是不同的完整路径
         */
        long prefix = curSUm - target;
        if (prefixMap.get(prefix) != null)
            this.result += prefixMap.get(prefix);
        // 把当前前缀和加到前缀映射表里面提供给下一个节点使用
        if (prefixMap.get(curSUm) == null) {
            prefixMap.put(curSUm, 1);
        } else {
            prefixMap.put(curSUm, prefixMap.get(curSUm) + 1);
        }
        DFS(root.left, curSUm);
        DFS(root.right, curSUm);
        // 撤销当前curSum，回到上一级就没有这个前缀和了
        prefixMap.put(curSUm, prefixMap.get(curSUm) - 1);
        return;
    }
}
