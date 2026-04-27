
public class SwapPairs {
    public ListNode swapPairs(ListNode head) {
        if(head==null||head.next==null)return head;
        ListNode start=head.next;
        ListNode left=head;
        ListNode right=head.next;
        ListNode next=null;
        ListNode last=null;
        // 不是说一组两个交换顺序并接着下一个就行了，一组之前的指针也要指对位置
        // 第一组之前没有结点，单独拎出来处理
        next=right.next;
        right.next=left;
        left.next=next;
        last=left;
        left=next;
        if (left!=null){
            right=left.next;
        }
        while (left!=null&&right!=null){
            next=right.next;
            // 交换
            // 笑了怎么写了个right.next=left.next
            right.next=left;
            // 处理头
            last.next=right;
            // 处理尾
            left.next=next;
            // 更新尾
            last=left;
            // 前进
            left=next;
            if (left!=null){
                right=left.next;
            }
        }
        return start;
    }
}
