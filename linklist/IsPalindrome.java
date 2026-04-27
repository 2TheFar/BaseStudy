
public class IsPalindrome {
    public boolean isPalindrome(ListNode head) {
        // 能判断的先处理了
        if (head == null || head.next == null) return true;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            // fast一轮两步，前进速度是slow的两倍，要比slow先到终点。
            // fast都还没到终点，slow就不会是null。
            slow = slow.next;
            fast = fast.next.next;
        }

        // 开始反转
        ListNode pre = null;
        ListNode curr = slow.next;
        ListNode next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }

        ListNode left = head;
        ListNode right = pre;
        while (right != null) {
            if (left.val != right.val) return false;
            left = left.next;
            right = right.next;
        }
        return true;
    }
}
