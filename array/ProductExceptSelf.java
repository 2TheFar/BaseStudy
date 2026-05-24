
public class ProductExceptSelf {
    /*
     * 一开始想的是，循环n次，每一次都给除了当前i乘以i，但是没想到这个复杂度已经到达O(n^2)了，铸币吧
     * 最好的时间复杂度应该是O(n)，空间复杂度是O(1)
     * 要达到这个空间复杂度必须好好利用answer[]
     * 看了提示才知道，当前位置的值，就应该是左边的乘积x右边的乘积
     * 然后就想到用左右指针，但是这样的话，如果从左往右遍历，左指针累乘好解决，但是右指针不能连除啊
     * 也就是说，应该有一个东西来装右指针的“右边乘积的值”，这个值从右往左是累乘的，就像现在的左指针一样
     * 这个东西就是answer！拿到值的思路解决了，也有东西存了，存完也可以计算最终结果了，搞定。
     * 稍加改动一下，先求所有的左边乘积吧~
     */
    public int[] productExceptSelf(int[] nums) {
        int result = 0;
        int[] answer = new int[nums.length];
        answer[0] = 1;
        result=1;
        for (int i = 1; i < nums.length; i++) {
            answer[i] = answer[i-1] * nums[i - 1];
        }
        result = 1;
        for (int i = nums.length-1; i >= 0; i--) {
            answer[i] = answer[i] * result;
            result *= nums[i];
        }
        return answer;
    }
}
