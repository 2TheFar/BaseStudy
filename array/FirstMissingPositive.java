public class FirstMissingPositive {
    public static void main(String[] args) {

    }

    //借助豆包提示拿下100%
    public int firstMissingPositive(int[] nums) {
        /*
         * 核心思想：如果答案是k，说明数组里面有连续的[1,k-1]
         * 核心思路：
         * 1. 缩小答案范围，答案只可能在[1,n+1]之间
         * 2. [1,n]都可以在大小为n的数组中找到自己的位置，这样可以大大减小空间
         * 3. 数组元素每一个只操作一次，就可以保证时间复杂度为O(n)
         * 大于n的数既不可能在数组中找到自己的位置，也不可能是答案，所以可以直接丢了完全不用管，n+1可能是答案，但是判断是n+1的条件是数组每个位置都有正确的数
         * 
         * 注意，可以输入负数
         * 哦对了，别忘了加判断ans是n+1的条件
         * 注意，可以输入重复的数，你咋这么阴呢你！
         */
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i + 1 || nums[i] > nums.length || nums[i] < 1) {
                continue;
            } else {
                int op = nums[i];
                int temp = 0;
                nums[i] = nums.length + 1;
                // 首先得是[1,n]才需要处理，其次如果已经对了，那就没必要取出来了，直接覆盖，不然会死循环
                while ((nums[op - 1] <= nums.length && nums[op - 1] >= 1) && nums[op - 1] != op) {
                    temp = nums[op - 1];
                    nums[op - 1] = op;
                    op = temp;// 还好有没有用上temp提醒，差点忘了temp是下一个op要更新op
                }
                nums[op - 1] = op;
            }
        }
        int j = 0;
        while (j < nums.length) {
            if (nums[j] != j + 1) {
                ans = j + 1;
                break;
            }
            j++;
        }
        if (j == nums.length) {
            ans = nums.length + 1;
        }
        return ans;
    }
}
