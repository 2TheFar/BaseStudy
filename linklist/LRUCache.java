import java.util.HashMap;
import java.util.Map;

/**
 * HashMap<Integer, Node>：负责通过 key 在平均 O(1) 时间找到节点。
 * 双向链表：负责维护“最近使用顺序”。
 * 头部表示最近使用
 * 尾部表示最久未使用
 * 
 * 哈希表记录节点的<key, 节点地址>对，使用节点的时候移动到链表头部，
 * 未使用的节点自然而然就移动到尾部了，不用管。
 * 最新使用的节点移动到头部还是尾部都可以，反正头指针和尾指针都得有，一个负责插入新的，一个负责移除旧的。
 * 两个的指针操作数是一样的，所以怎么通顺怎么来就好。
 * 用双向链表是因为，把一个节点拆出来的时候，它的前后节点要连接，如果知道前一个节点的地址会更方便
 * 
 * 按标准来，最新使用节点用头插法
 * 
 * 一开始以为存入的只有一个值，然后按值查找（既然知道值了为什么还要按值查找...)
 * 后来才发现原来存的是键值对
 * 只是存和取的话，一个哈希表就能解决，中间插入一个链表层，是为了记录使用顺序
 * 
 * 为什么节点里还要存 key？
 * 因为淘汰尾部节点时，你拿到的是链表尾节点，要从 HashMap 里删掉对应项
 * 
 * 如果最新使用的节点是尾结点，移动到头部之前，
 * 一定要更新尾指针，不然就把尾指针带到头部去了。
 * 在拆出节点的时候更新尾指针指向自己的前一个节点。
 * 
 * 优化：
 * 
 * get()和put()
 * 修改：不再先判断是否包含key，再取Node，改成直接取Node，若为空就返回-1
 * 原因：少一次查哈希表
 * 心得：永远把条件执行内容少的放前面，内容多的放后面。
 * 注意！把if-else修改成if的时候，千万要在前面的if里面写返回，不然就两个都执行了！
 * 
 * moveToHead()
 * 修改：增加判断cur是否已经是头部节点，如果是就直接返回
 * 原因：已经在头部就不用再插一次了
 * 心得：之前在做一个“新老节点都能使用的头插法”时其实想到了这个，
 * 因为当时没有先拆出，如果对头节点使用头插法，逻辑上毫无意义，
 * 实现上还会导致头节点next指向自己，失去后续链表
 * 现在因为是先拆出再插入，所以不会有next指向自己的问题，但一样没有意义
 * 
 * num
 * 修改：不再用num，就用map.size()
 * 原因：一样用，还不用去管这个值的增加减少
 * 心得：尺寸大小什么的，能用现成的方法就用现成的，不容易出错
 * 
 */
public class LRUCache {
    private int capacity;
    private Node dummy = new Node();
    private Node end = dummy;
    private Map<Integer, Node> map = new HashMap<>();

    private static class Node {
        private int val;
        private int key;
        private Node prev;
        private Node next;
    }

    // 这是构造函数！不是程序入口！不是在这里调用get和put！
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    /*
     * get使用之后会移动使用的节点到最前面！
     */
    public int get(int key) {
        Node cur = map.get(key);
        if (cur == null) {
            return -1;
        }
        moveToHead(cur);
        return cur.val;

    }

    /*
     * 牢记哈希表记得是key和Node，Node里面记的才是value
     */
    public void put(int key, int value) {
        Node cur = map.get(key);
        if (cur != null) {
            cur.val = value;
            moveToHead(cur);
            // 这里别忘了返回！
            return;
        }

        cur = new Node();
        cur.key = key;
        cur.val = value;

        headInsert(cur);

        map.put(key, cur);

        if (map.size() > capacity) {
            map.remove(end.key);
            end = end.prev;
            end.next = null;
        }

    }

    private void moveToHead(Node cur) {
        // 如果本来就在头部就不用移动了
        if (dummy.next == cur) {
            return;
        }
        removeNode(cur);
        headInsert(cur);
    }

    /**
     * 独立节点才能用的头插法
     * 
     * @param cur
     */
    private void headInsert(Node cur) {
        if (dummy.next != null) {
            cur.next = dummy.next;
            dummy.next.prev = cur;
            dummy.next = cur;
            cur.prev = dummy;
        } else {
            dummy.next = cur;
            cur.prev = dummy;
            cur.next = null;
            this.end = cur;
        }
    }

    /**
     * 拆出节点
     * 
     * @param cur
     */
    private void removeNode(Node cur) {
        if (cur.next == null) {
            // 拆尾部节点一定要更新尾指针
            end = cur.prev;
        } else {
            // 后面还有就要把后续接点接到前面
            cur.next.prev = cur.prev;
        }
        cur.prev.next = cur.next;
        cur.prev = null;
        cur.next = null;
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
