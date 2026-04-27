
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // head不为空
        ListNode slow=head;
        ListNode fast=head;
        for (int i=0;i<n;i++){
            fast=fast.next;
        }
        // 只有一种可能会让fast=null，那就是找倒数第n个
        // 因为第一个前面没有结点，所以下面的方式是找不到slow的
        if(fast==null){
            return head.next;
        }
        // 当fast到尾结点的时候，slow在倒数第n+1个，也就是第n个的前一个
        while (fast.next!=null){
            fast=fast.next;
            slow=slow.next;
        }
        slow.next=slow.next.next;
        return head;
    }
}
