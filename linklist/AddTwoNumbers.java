
public class AddTwoNumbers {

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
}
