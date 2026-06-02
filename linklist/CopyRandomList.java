public class CopyRandomList {
    // 这题还真要一个全新的Node
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * 这道题的最优解法果然还是原地
     * 一开始想着用栈全部存起来，但是空间复杂度就大了
     * 然后如果说不用栈，那时间肯定多了，比如先走一趟构建链表摸清楚，然后再走一趟把random接上
     * 但其实这个没有卵用，因为摸清楚的这个信息一样要用哈希表来存，不仅时间O(2n)，空间复杂度哈斯O(n)
     * 于是在AI透露下知道还是原地插入思路，这个是我最怕的，所以一开始就没去想
     * 果然最怕的往往就是最对的
     * 
     * 方案全名叫：原地穿插复制节点
     * 这个原地跟我想的原地还不一样，我想的原地是直接新构造一个节点，创建next和random，然后原地再往上继续插入
     * 但是这有个问题就是不知道接下来应该往哪个方向插入新节点了，因为一旦到了下一个节点就失去当前记忆，原来创建了也不知道，走不通
     * 这个方案的原地思想是指在老链表的基础上原地创建一个新链表，然后再把这个新链表拆出来
     * A -> B -> C
     * A -> A' -> B -> B' -> C -> C'
     * 就像那个DNA复制一样，根据DNA模板链生成一条内容对应、但完全独立的新链。
     * 
     * 第一步，沿着模板链原地穿插复制一模一样的节点，这个时候next要找到对应的就很容易了
     * 第二步，对于这些新节点，复制老节点的random也很简单，比如 A -> B，让 A' -> B'(A -> B -> B')即可
     * 第三步，random已经复制完了，只需要把新节点拆出来组成新链表就可以了
     * 
     * 牛逼的点在于，不管是直接复制一个独立的新链表再逐个设置 random 指针，还是每插入一个新节点就设置 random 指针
     * 都会苦于找不到对应的 random 节点位置，不得已用哈希表来存所有 val 对应的节点位置
     * 但这种原地“穿插”的方法，依附于模板链，将新节点的位置绑定到老节点上，只要知道 A -> B，就能建立起
     * A' -> A -> B -> B'的关系，不是通过 B 的值去找 B'，而是通过 B.next 来找到 B'
     * 是把模板链做成一个HashMap，老节点为key，新节点为value
     * 恰好对于每一个random，虽然找其他的key很难，但是找到对应的key很简单
     * 
     * 唯一的条件就是 Cur -> X，唯一的要求就是 Cur' -> X'，Cur' 易得而 X' 不易得
     * 那就把新旧一起绑定成一个整体，那么只要知道 Cur -> X，就能很轻松地让 Cur' -> X'
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        // 第一步，原地穿插复制
        Node p = head;
        while (p != null) {
            Node node = new Node(p.val);
            node.next = p.next;
            p.next = node;
            p = node.next;
        }
        // 第二步，逐个设置random，别写成next了
        // 诶我操，random还能是null啊不早说！
        // 我chovy啊！怎么random还能指向自己的，脑残啊，但是这个好像没事
        p = head;
        while (p != null) {
            // 核心公式A' -> (A -> B -> B')
            if (p.random != null) {
                p.next.random = p.random.next;
            } else {
                p.next.random = null;
            }
            
            p = p.next.next;
        }
        // 第三步，拆出新链
        // 题目没有明说不能拆或改原链表，但深拷贝语义要求函数结束后不能破坏原对象
        // 复制不能把“被复制的对象”给破坏了，应该保证原样
        // 写好看一点，多套几个next就套吧，好过看不清楚
        p = head;
        Node ans = p.next;
        Node q = ans;
        while (p.next.next != null) {
            p.next = p.next.next;
            p = p.next;
            q.next = q.next.next;
            q = q.next;
        }
        // 最后一组Z->Z'之间的指针要解开，我就说怎么老是破坏原链表
        p.next = null;
        // 写在最后：这力扣的内存评分真傻逼吧，同一个结果，能测出来5%也能测出来86%
        return ans;
    }
}
