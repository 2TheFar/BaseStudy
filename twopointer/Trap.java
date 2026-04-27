
public class Trap {
    /*
    再战接雨水
    先假设右侧多了一根无限高的柱子，看所有柱子接完水后的高度
    再假设左侧多了一根无限高的柱子，看所有柱子接完水后的高度
    二者重叠求最小值除去两根假设柱子的影响，得到真正接完雨水的高度
    减去柱子高度累加就是雨水量
     */
    public int trap(int[] height) {
        int len =height.length;
        int[] leftMax=new int[len];
        int[] rightMax=new int[len];
        int maxHeight=0;

        // 优化版，只记左右最高值
        for (int i=0;i<len;i++){
            leftMax[i]=maxHeight;
            maxHeight=Math.max(maxHeight,height[i]);// 更新高度给后面的柱子用，和i++联系着看

        }
        maxHeight=0;
        for (int i=len-1;i>=0;i--){
            rightMax[i]=maxHeight;
            maxHeight=Math.max(maxHeight,height[i]);// 更新高度给后面的柱子用，和i++联系着看
        }
        int sum=0;
        for (int i=0;i<len;i++){
            sum += Math.max(Math.min(leftMax[i], rightMax[i]), height[i]) - height[i];
        }
        return sum;
    }
}
