import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class OrangesRotting {
    /**
     * 994. 腐烂的橘子
     *
     * 多个源同时出发、多层扩散、最少分钟数，完全适合用BFS来解
     * 模拟橘子腐烂过程，一层一层向外扩张
     * 如果某一轮没有任何橘子腐烂，但是还剩好橘子，说明没法所有的橘子都烂完，返回-1
     * 
     * 岛屿数量：DFS，把整座岛沉掉
     * 腐烂橘子：BFS，一分钟一层往外烂
     * DFS 是钻洞，一条路走到底。
     * BFS 是涨潮，一层一层淹过去。
     * 
     * 优化一：队列建议用 ArrayDeque，比 LinkedList 更适合做队列
     * 优化二：把条件直接放进 while，更干净
     * 优化三：写成goodOrange == 0 ? ans : -1;
     * 
     * 还好有AI帮我看代码，已经确认我这已经是最优复杂度了，没拿到最优成绩是力扣的问题
     * 果然如此，一刷新就是100%了
     * 
     * @param grid
     * @return
     */
    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new ArrayDeque<>();// 优化一
        /*
         * 解决两个问题
         * 一个是一开始就没有好橘子的情况
         * 另一个是最后没有橘子可以腐烂了是否没有好橘子的判断
         * 还有一个是判断这一轮是不是已经全部腐烂后额外进行的一轮
         */
        int goodOrange = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 2) {
                    queue.offer(new int[] { row, col });
                }
                if (grid[row][col] == 1) {
                    goodOrange++;
                }
            }
        }
        int ans = 0;
        while (!queue.isEmpty() && goodOrange > 0) {
            /*
             * 优化二：这个goodOrange的判断完全可以放在while条件里！
             * 然后意思就变成了，只有在还有烂橘子可以扩散腐烂并且还有好橘子时才会发生腐烂。
             * 更精简一点，只有在还有感染源和未感染者时，才会发生感染
             * 再言简意赅一点，没有好橘子了，已经可以结束了，不用再进行下一轮了
             * 我去不早说！
             * ----------------------------------------------------------------
             * 所有橘子都腐烂的最后一轮之后，
             * 虽然已经没有好橘子了，但是因为最后一轮有烂橘子加入队列,所以会额外进行一轮
             * 如果是额外进行的一轮，只有烂橘子没有好橘子，直接退出
             * 其实这个跟一开始就没有好橘子的判断非常相似，
             * 都是图里已经没有好橘子了，但是因为队列里还有烂橘子
             * 所以会额外进行一轮
             * 所以就把前面那个判断删掉了
             */
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int row = cur[0];
                int col = cur[1];
                /*
                 * 想要优美的写法的前提是不优美地写一遍，然后提炼
                 * 四个方向对应着四组(nextRow,nextCol)，拆成(row,col)+(四组坐标)=(nextRow,nextCol)
                 * 可以写成一个for循环，每个循环腐烂一组(nextRow,nextCol)
                 */
                for (int[] direction : DIRECTIONS) {
                    int nextRow = row + direction[0];
                    int nextCol = col + direction[1];

                    if (nextRow < 0 || nextRow >= grid.length || nextCol < 0 || nextCol >= grid[0].length
                            || grid[nextRow][nextCol] != 1) {
                        continue;
                    }

                    grid[nextRow][nextCol] = 2;
                    goodOrange--;
                    queue.offer(new int[] { nextRow, nextCol });
                }
            }
            ans++;
        }

        return goodOrange == 0 ? ans : -1;// 优化三
    }

    private static final int[][] DIRECTIONS = {
            { 1, 0 },
            { -1, 0 },
            { 0, 1 },
            { 0, -1 }
    };

}
