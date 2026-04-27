
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        // 以每个结点为操作对象，从第一个结点开始，每次curr指向pre进行反转操作，然后因为记了next所以跳到next就行，直到每一个结点都反转了就行
        ListNode pre = null;
        ListNode curr = head;
        ListNode next;
        while (curr != null) {
            // 在指针反转前要把next记下来
            next = curr.next;
            curr.next = pre;
            // 在curr跳到下一个之前，pre要先指向curr（pre要么是null，要么已经被反转，不可以pre=pre.next）
            pre = curr;
            curr = next;
        }
        return pre;
    }
}
