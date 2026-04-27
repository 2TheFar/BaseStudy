
import java.util.*;

public class ThreeSum {
    public static void main(String[] args) {
        threeSum(null);
    }
    //注意，输出的顺序和三元组的顺序并不重要。所以为了确保三元组不重复，得先排序再比较。
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            //"第一个数"去重
            if(i>0&&nums[i]==nums[i-1]){
                //剪枝，找过的不再找
                continue;
            }
            int left = i + 1;
            int right = n - 1;
            int prev = -(nums[i]);
            while (left < right) {
                //错误1：把下标当值加起来
                int sum = nums[left] + nums[right];
                if (sum == prev) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);

                    //"左边"去重
                    //剪枝处理，一样的就没必要再找了
                    //对于这种递归式的“多米诺骨牌”，用特定值来想更好，不然脑子转不过来
                    do {
                        left++;
                    } while (left < right && nums[left] == nums[left - 1]);
                    //优化1：其实右边也可以剪枝处理，节约时间
                    //"右边"去重
                    do {
                        right--;
                    } while (left < right && nums[right] == nums[right + 1]);
                } else if (sum < prev) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
}
