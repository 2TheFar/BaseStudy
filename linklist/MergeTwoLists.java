
public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode start = new ListNode(0);
        ListNode p = start;
        ListNode current = null;
        ListNode another = null;

        if (list1.val <= list2.val) {
            current = list1;
            another = list2;
        } else {
            current = list2;
            another = list1;
        }
        // 综合来看，循环前current不可能是空的，循环后current也不可能是空的
        while (another != null) {
            p.next = current;
            // 连接上之后，继续往下找下一个，直到下一个是大于另一边的就停下，当前就是最后一个小于等于另一边的
            // 等于的也要算上，不然遇到一长串相等的结点时，就只能一轮处理一个结点，效率大大降低
            // 最小时间复杂度是O(n+m)，每一个值之间的比较是不可避免的，每一个值都至少需要比较一次，每一次并入一个值。
            // 但是一轮循环并入一段的话，就可以大大减少外层循环的次数
            while (current.next != null && current.next.val <= another.val) {
                current = current.next;
            }
            // p指向当前结果的尾巴
            p = current;
            // 此时此刻，另一条链表的起点小于当前链表的起点（是current.next，因为current已经并入结果了）
            // 所以结果要从另一条链表开始重复上述过程
            // 所以将指针进行反转，current指向另一条链表，another指向当前链表，要注意反转顺序，刚好p可以作为交换用的存储容器
            // 说真的，画图有助于理解大于一步的复杂过程
            current = another;
            another = p.next;
        }
        // 最后把一条链所有都并入结果了，此时another一定是null，current一定不是null
        // 就像一个一个并入时，肯定一条链先遍历完，另一条链还没遍历完
        // 所以直接把剩下的接上就好了
        p.next = current;

        return start.next;
    }
}
