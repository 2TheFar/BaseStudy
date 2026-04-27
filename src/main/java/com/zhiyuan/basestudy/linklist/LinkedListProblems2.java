package com.zhiyuan.basestudy.linklist;


public class LinkedListProblems2 {

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


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1=l1;
        ListNode p2=l2;
        ListNode end=p1;
        int plus=0;
        int result=0;
        int rs_single=0;
        while (p1!=null&&p2!=null){
            result=p1.val+p2.val+plus;
            rs_single=result%10;
            plus=result/10;
            p1.val=rs_single;
            end=p1;
            p1=p1.next;
            p2=p2.next;
        }
        if (p1==null&&p2==null&&plus>0){
            end.next=new ListNode(plus);
        }
        if(p1==null&&p2!=null){
            end.next=p2;
            p2.val=p2.val+plus;
            while (p2.val==10){
                p2.val=0;
                if(p2.next==null){
                    p2.next=new ListNode(0);
                }
                p2=p2.next;
                p2.val+=1;
            }
        }
        if (p1!=null&&p2==null){
            p1.val+=plus;
            while (p1.val==10){
                p1.val=0;
                if(p1.next==null){
                    p1.next=new ListNode(0);
                }
                p1=p1.next;
                p1.val+=1;
            }
        }
        return l1;
    }

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
