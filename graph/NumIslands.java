
public class NumIslands {
    /**
     * 岛屿数量
     * 这道题的思路好像是，遍历整个网格，遇到一个未处理过的 '1'，岛屿数量 +1，然后从这个点开始用 DFS 或 BFS
     * 把它上下左右相连的所有陆地都标记掉，比如改成 '0'。这样后面再遍历到这些位置时就不会重复计数。
     * 就想象成一个病毒式的传播，与之相连的陆地全都标记为0，直至这个岛屿一块陆地也不剩
     * 那么之后再踏足这个岛时，全部都变海了，亚特兰蒂斯了，也就不会重复计算这个岛屿了
     * 哦我知道了，当遇到一个岛时，数量加一，然后把这个岛沉了，也就不会重复计算了！
     * 
     * char[][]里的一个数组是一行还是一列都无所谓，行和列只是一个名字
     * 
     * 对于处理一格的操作优化：
     * 递归调用之前就判断，确保不会越界，比递归后再判断越界的条件数更少（4->1），更重要的是少一次递归
     * 可惜的是并不能进化执行速度，有点没啥用
     * 但是从代码简洁度来说，经典写法通常会把越界判断放在 dfs 开头
     * 也就是所有判断都放到开头处
     * 这个写法更偏“提前剪掉无效递归”，性能上有一点点好处；
     * 经典写法更短、更不容易漏方向或漏边界。两者都可以。
     * 
     * @param grid
     * @return 
     */
    public int numIslands(char[][] grid) {
        int num = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == '1') {
                    num++;
                    dfs(grid, row, col);
                }
            }
        }
        return num;
    }

    private void dfs(char[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            return;
        }
        if (grid[row][col] == '0') {
            return;
        }
        // 先置0再遍历上下左右，不然会进死循环
        grid[row][col] = '0';
        dfs(grid, row + 1, col);
        dfs(grid, row - 1, col);
        dfs(grid, row, col + 1);
        dfs(grid, row, col - 1);
    }
}
