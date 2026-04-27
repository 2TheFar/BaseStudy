package com.zhiyuan.basestudy.tree;

import java.util.*;

public class NormalTreeProblems {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        // 这只是一个类型，不代表List真有List
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        int high = 0;
        int cur = 1;
        int next = 0;
        queue.add(root);
        // 这个模式是错的！会导致子树先出栈而不是兄弟先出栈！所以要用的不是栈，是队列啊！要先进先出！！！！
        while (queue.isEmpty() == false) {
            root = queue.poll();
            result.get(high).add(root.val);
            cur--;

            // 先进先出
            if (root.left != null) {
                queue.add(root.left);
                next++;
            }
            if (root.right != null) {
                queue.add(root.right);
                next++;
            }

            if (cur == 0) {
                high++;
                // 如果下一层没东西了，那么就算到下一层也没东西了，不用再建个新的了
                // 但是加了限制之后，又会发生空访问？
                // 铸币吧！是不等于0的时候就要加组，等于0的时候不加，怎么写成等于0的时候加组了，完全错了
                if (next != 0) result.add(new ArrayList<>());
                cur = next;
                next = 0;
            }
        }
        return result;
    }

    public boolean isValidBST(TreeNode root) {
        return isValid(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }

    private boolean isValid(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return isValid(node.left, min, node.val) && isValid(node.right, node.val, max);
    }


}
