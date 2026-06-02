public class MergeKLists {
    /*
     * 23. 合并 K 个升序链表
     * SortList是分而治之，这个已经分好了，直接治就可以了
     * （其实不对，这道题一样要分而治之，相比于SortList只是最小元素从节点变成了升序链表，合并链表变成了合并区间内的链表）
     * 分组两两合并的话，每一轮数量减半，需要log2(k)轮，扫描全部N个节点，所以时间复杂度是O(Nlogk)
     * 滚雪球式的一个结果一直往后合并，从头合并到尾，需要k轮，最后一轮需要扫描全部N个节点
     * 假设第一条链就无敌长接近于N，那么最坏情况下的时间复杂是O(kN)
     * 
     * 不要一开始就想“第几个和第几个手动配对”，而是把 lists 当成一个数组，对下标做归并：
     * mergeRange(lists, left, right)
     * 意思是：把 lists[left...right] 这一段里的链表合并成一个。
     * 这又何尝不是一种分而治之？
     * 把一个大区间拆成很多个小区间！然后再让小区间沿路返回合并成一个大区间！
     * 
     * 这个办法的好处是：你不用操心具体哪个和哪个合并，递归区间会天然帮你配好。
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        // 用递归的写法，不用在意谁和谁合并，合并多少次，直接拆分到最小再合并就行了
        return mergeRange(lists, 0, lists.length - 1);
    }

    /**
     * 合并区间内的所有链表。
     *
     * 做法是把当前区间继续拆成左右两半：
     * 先分别把左半区间、右半区间合并成两个有序链表，
     * 再把这两个有序链表二合一。
     *
     * 递归出口：left == right 时，区间里只有 lists[left] 这一条链表，
     * 它本身就是这个区间合并的最终结果，所以直接返回它的头节点。
     * N条链的区间合并后就一条链，一条链的区间合并完后也是一条链，原地合并
     *
     * @param lists 链表数组，每个元素本身已经是升序链表
     * @param left  当前要处理的左边界下标，包含
     * @param right 当前要处理的右边界下标，包含
     * @return lists[left...right] 合并后的升序链表头节点
     */
    private ListNode mergeRange(ListNode[] lists, int left, int right) {
        if (left >= right) {
            // 只有一条链表时，不需要合并；这条链表就是当前区间合并的最终结果
            return lists[left];
        }
        int mid = (left + right) / 2;
        // 左半区间合并后的结果
        ListNode leftMerged = mergeRange(lists, left, mid);
        // 右半区间合并后的结果
        ListNode rightMerged = mergeRange(lists, mid + 1, right);
        // 左右结果二合一，就是当前区间的合并结果
        return mergeTwoLists(leftMerged, rightMerged);
    }

    private ListNode mergeTwoLists(ListNode left, ListNode right) {
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (left != null && right != null) {
            if (left.val <= right.val) {
                cur.next = left;
                cur = cur.next;
                left = left.next;
            } else {
                cur.next = right;
                cur = cur.next;
                right = right.next;
            }
        }
        cur.next = left == null ? right : left;
        return dummy.next;
    }
}
