
import java.util.HashMap;
import java.util.Map;

public class SubarraySum {
    //给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
    //子数组是数组中元素的连续非空序列。

    /**
     * 统计数组中和为 k 的子数组个数
     * 核心思路：前缀和 + 哈希表（最优解法，时间复杂度 O(n)）
     *
     * @param nums 输入的整数数组
     * @param k    目标和
     * @return 满足条件的子数组数量
     */
    public int subarraySum(int[] nums, int k) {
        // 哈希表：统计[0,i-1]的所有前缀的前缀和及出现的次数
        // 为什么不是[0,i]的所有前缀？当k=0时，前缀是[0,i]，那后缀不就是[空]，满足k=0，但是子数组不可以为空
        Map<Integer, Integer> preSum = new HashMap<>();

        // 初始化：前缀为空也是前缀，记前缀和=0次数=1
        // 作用：处理从数组第一个元素开始就满足和为k的情况
        preSum.put(0, 1);

        int result = 0;
        int curSum = 0;

        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];

            // 把找以i为结尾的目标子串，转化为找[0,i]中和为k的目标后缀，再转化为找[0,i]中和为curSum-k的前缀
            // 后缀至少得有一个i，前缀只能是指针i之前的所有前缀
            int key = curSum - k;

            // 有多少个这样的前缀和，就有多少个目标后缀和
            if (preSum.containsKey(key)) {
                result += preSum.get(key);
            }

            // [0,i]是i+1之前的前缀之一，但perSum只包含i之前的前缀，应该加入以供后续位置使用
            preSum.put(curSum, preSum.getOrDefault(curSum, 0) + 1);
        }

        return result;
    }
}
