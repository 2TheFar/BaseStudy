public class SortList {
    /*
     * 用快慢指针找中点，然后分而治之，用归并排序
     * 要有一个merge方法在递归回去的时候合并链表
     * 因为合并的时候需要遍历到链尾，所以拆分的时候不仅是逻辑上拆分，结构上也要拆分
     * 让左链尾节点的next=null
     * 所以我们要找的不是中间节点的指针，而是左链尾节点的指针
     * 奇数个节点时，找到中间节点或者中间节点前面一个节点都一样
     * 偶数个节点时，应该找到中间偏左的哪个节点
     * 要做到这一点，让slow=head，fast=head.next，
     * 判断条件为fast != null && fast.next != null就可以实现
     * 判断条件越简单越好，所以主要改的就是起始节点，
     * 这样偶数个时还能让fast少走一步，所以slow停在中间偏左，
     * 奇数个时从fast.next=null变成了fast=null，slow依然停在中间节点，但是右链表从slow.next开始
     * 
     * 优化：
     * 在合并时，cur.next = new ListNode(val);会创建大量的新节点
     * 这会带来：对象分配成本 + GC 垃圾回收器压力 + 额外内存占用
     * 会增加很多时间成本和空间成本
     * 标准写法一般是复用原链表节点，只改 next 指针
     * cur.next = left;
     * 被接到结果链表里的节点，后续 next 会随着合并过程被重新连接；最后剩余的一段可以整体接上。
     * 
     * 另外，为了使排序是稳定的，合并的时候如果遇到相等的值，优先选左链表的
     * if (left.val <= right.val) {选left}
     * 这样才能保证一开始就在左边的值，最后还是在左边
     * 原来没有写=，1A->1B排序完变成1B->1A
     */
    public ListNode sortList(ListNode head) {
        // 只有一个节点时直接返回，所以拆分不会拆出来一个节点和一个null，merge不会收到空链
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode rightHead = slow.next;
        slow.next = null;
        return merge(sortList(head), sortList(rightHead));
    }

    private ListNode merge(ListNode leftHead, ListNode rightHead) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        ListNode left = leftHead;
        ListNode right = rightHead;

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
        // 更简单常见的写法
        cur.next = left != null ? left : right;
        return dummy.next;
    }
}
