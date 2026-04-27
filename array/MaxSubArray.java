
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        /*
        两步走：
        1.求出每一个位置的最大前缀和，前缀和至少为0
        2.知道了每一个位置的最大前缀和之后，就知道了每一个位置为结尾的最大子数组和，逐个加起来比大小
         */

        // 前缀和最小也得是0，不能是负数
        int[] preMax=new int[nums.length];
        preMax[0]=0;
        // 计算每个位置的最大前缀和
        // 从第二个数开始啊，如果第一个数大于0，那么就更新最大前缀和为nums[0]，如果不是那拉倒了
        for (int i=1;i<nums.length;i++){
            preMax[i]=Math.max(0,preMax[i-1]+nums[i-1]);
        }
        int max=nums[0];//max应该初始化为第一个数（以第一个位置为结尾的子数组）让max至少是一个子数组的值
        // 和之后每一个位置为结尾的最大子数组和进行比较
        for (int i=0;i<nums.length;i++){
            max=Math.max(max,preMax[i]+nums[i]);
        }
        return max;
        // 我操我好牛逼一遍过，我已经变牛逼了知道不
        // ...反转了，nums=[-1]败了，不应该啊我应该大概也许...
        // 我操，之前想的时候说max大哥它是没办法，就算nums[0]是-100000也得把nums[0]也考虑上，保个底
        // 然后转头就忘了喵
    }
}
