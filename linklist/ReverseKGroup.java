public class ReverseKGroup {
    public static void main(String[] args) {
        // debug: [1,2,3,4,5]
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ReverseKGroup rkg = new ReverseKGroup();
        ListNode result = rkg.reverseKGroup(head, 2);
        System.out.println(result);
    }

    /**
     * k个为一组反转链表，先确定剩下的有没有k个
     * 然后将这k个单独摘出来，用两个指针记下这个子链表的前一个节点和后一个节点
     * 将这个子链表原地反转
     * 原地反转用三指针法
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 虚拟头节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;
        ListNode kth = groupPrev;
        ListNode groupNext;

        // 哎哟我还真是true啊
        while (true) {
            for (int i = 0; i < k; i++) {
                kth = kth.next;// kth指向当前子链表的第i+1个，初始时算第0个
                if (kth == null) {
                    return dummy.next;
                }
            }
            groupNext = kth.next;// 这个其实可以为空
            /*
             * groupPrev要更新为上一个子链表反转后的最后一个节点，也就是原来的groupPrev.next，这里直接作为方法返回值返回了
             * groupPrev要更新不然会无限循环
             * 比如[1,2,3,4,5]，k=2
             * 第一轮之后变成[dummy,2,1,3,4,5]
             * groupPrev仍然指向dummy，kth此时指向2，for循环后kth指向3，groupNext指向4
             * 此时反转之后变成[dummy,3,1,2,4,5]
             * kth此时指向3，for循环之后kth指向2，groupNext又是指向4，groupPrev还是指向dummy
             * 然后就是又把这个子链表反转一遍，无限循环
             */
            groupPrev = reverseList(groupPrev, groupNext);
            kth = groupPrev;
        }

    }

    // 不妨返回这一组反转后的最后一个节点作为下一组的groupPrev
    public ListNode reverseList(ListNode groupPrev, ListNode groupNext) {
        if (groupPrev == null) {
            return null;
        }
        ListNode prev;
        ListNode cur;
        ListNode next;

        prev = groupNext;// 原版是null
        cur = groupPrev.next;// 从子链表起点开始

        while (cur != null && cur != groupNext) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        ListNode oldGroupHead = groupPrev.next;
        groupPrev.next = prev;// 子链表前一个把反转后的子链表头给接上
        return oldGroupHead;
    }
}
