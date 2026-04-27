
public class GetIntersectionNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        // 要么相遇，pA=pB，要么各自走完，pA=pB=null，所以最终一定会相等，不需要额外结束条件
        // 要做的只是让他俩各自从起点出发，第一次到终点的时候交叉遍历另一条
        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }
        return pA;
    }
}
