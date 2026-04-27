
public class HasCycle {
    public boolean hasCycle(ListNode head) {
        // 天才想法，快慢指针秒杀环：如果有环，快慢指针终将进到环里面，然后因为指针速度不同，相对速度为1，所以终将会相遇
        // 边界最好两个都写上，保证至少会进一次循环
        if (head == null || head.next == null) return false;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            // 先移动后比较，第一次不需要比
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }
}
