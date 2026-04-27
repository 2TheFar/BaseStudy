
public class DetectCycle {

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head;
        ListNode fast = head;
        // 我觉得，尽量用保证指针始终不是null的写法更安全
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        // fast!=null，条件一安全，条件一不满足时才可能判断条件二，也就是说判断条件二时fast.next!=null，条件二也安全
        // 唉，还是顺着看条件更好思考，先判断p再判断p.next可以很好地避免访问空指针
        if (fast.next == null || fast.next.next == null) {
            return null;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
