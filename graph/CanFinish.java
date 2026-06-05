import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CanFinish {
    /**
     * 207. 课程表
     *
     * prerequisites[i] = [ai, bi] 表示想学 ai，必须先学 bi
     * 
     * 用邻接表来记录图的数据结构，然后要用一个数组记一下每个节点的前置（入度）有几个
     * 找出前置数为0的，把这个节点给删掉，逻辑上把它的出边都删掉，实际上把它指向的节点的前置都减一
     * 用一个计数器来记录删掉的节点，逻辑删除节点，而不是真把节点从图里删了
     * 等到所有节点都删完了，count==numCourses，即成功
     * 如果队列里没有节点可以删了，但是没有删完，即失败
     * 
     * 邻接表用List来做，比数组和链表好使多了，封装好就是牛逼
     * 初始化要做到List里有每个节点对应的List，空的也无妨，也就是只有节点没有边的图
     * 
     * 优化：
     * List<List<Integer>> graph邻接表用两层List来写，第一层List感觉有点鸡肋
     * 遍览garph，无非就是往graph里面加List和取List，但是这个数组也能做，用数组还更快
     * 基于此对graph进行改造成List<Integer>[] graph = new ArrayList[numCourses];
     * 虽然数组的大小是numCourses，但是元素List的大小是动态变化的
     * 所以空间复杂度仍然是 O(点+边)，内存不会爆炸
     * 因为 Java 不太喜欢直接创建泛型数组，所以不改了，但是在力扣上跑效果确实好一个档次
     * 
     * 邻接表用二维数组的方式是分数最高的，需要两个数组indegree和outdegree，但是不能是
     * int[numCourses][numCourses]内存会爆炸，所以还挺麻烦的，得是动态的
     *
     * @param numCourses    课程数量，编号为 0 到 numCourses - 1
     * @param prerequisites 先修课程数组
     * @return 是否可以完成所有课程
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int count = 0;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        int[] indegree = new int[numCourses];
        // 更好的写法
        for (int p[] : prerequisites) {
            // 取出来[1]->[0]
            graph.get(p[1]).add(p[0]);
            // 记入度
            indegree[p[0]]++;
        }
        // 快速判断有哪些入度为0的节点，需要一个队列，找节点从每轮扫描变查找队列，删除从做标记变成出队
        // 每个节点只可能一次入队，因为只有入队1->0时才入队，所以只会处理一次，不用担心重复处理
        // 下一轮的节点由上一轮的节点删除边来创造，初始化是一开始就入度为0的节点
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        // count < numCourses 可以不要，因为每个节点最多入队一次，不会超过 numCourses
        while (!queue.isEmpty()) {
            int curNum = queue.poll();
            for (int i : graph.get(curNum)) {
                indegree[i]--;
                if (indegree[i] == 0) {
                    queue.offer(i);
                }
            }
            count++;
        }
        return count == numCourses;
    }
}
