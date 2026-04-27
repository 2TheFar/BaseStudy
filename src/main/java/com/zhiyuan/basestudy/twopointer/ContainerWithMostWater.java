package com.zhiyuan.basestudy.twopointer;

public class ContainerWithMostWater {

    //为森莫不行啊！！！
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        // 按初始的最大
        int max_area = getRainArea(height, left, right);
        // 不要留恋于过去，记录之前的指针，直接大大方方地往里挪就完事了！
        // 所以指针没必要最后在挪，一开始挪就完事了，一开始的状态已经被max_area记录了
        while (left < right) {
            if (height[left] <= height[right]) {
                int base = left;
                // 优化了如果left往里挪结果是更小或者相同的值，不用算，但*继续往里挪*：
                // 一开始，肯定是相等的，left会右移一次
                // 初次移动之后，如果值小于等于一开始的值，那就继续右移
                // 直到遇到比一开始的值要大的才停下来

                // left++;有这个更容易理解，但是可以被优化掉
                while (left < right && height[base] >= height[left]) {
                    left++;
                }
                max_area = Math.max(max_area, getRainArea(height, left, right));
            } else {
                int base = right;
                while (left < right && height[base] >= height[right]) {
                    right--;
                }
                max_area = Math.max(max_area, getRainArea(height, left, right));
            }
        }
        return max_area;
    }


    public int getRainArea(int[] height, int left, int right) {
        return (right - left) * Math.min(height[left], height[right]);
    }

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
