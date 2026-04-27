package com.zhiyuan.basestudy.linklist;

import java.util.ArrayList;
import java.util.List;

public class LinkedListProblems {



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
