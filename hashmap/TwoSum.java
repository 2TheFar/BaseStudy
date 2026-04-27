
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        // 创建哈希表：key=数值，value=下标
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; // 计算补数

            // 补数存在 → 找到答案
            if (map.containsKey(complement)) {
                // map.get (key) = 拿 key 对应的 value
                return new int[]{map.get(complement), i};
            }

            // 补数不存在 → 存入当前值和下标
            // 注解：
            // 不是往后找补数而是往前找补数，一开始map里没有补数就先存起来，作为可能的后续数的补数
            // 往后继续计算，看看有没有补数跟之前存的对的上
            map.put(nums[i], i);
        }

        // 题目保证有解，这里不会执行
        return new int[0];
    }
}
