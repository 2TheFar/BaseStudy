package com.zhiyuan.basestudy.array;

public class RotateArray {
    public void rotate(int[] nums, int k) {
        if (nums.length==0||k%nums.length==0)return;
        k=k%nums.length;
        //三次反转
        //第一次反转：让右边的k个移动到左边的n-k个的左边，整体进行左移
        reverse(nums,0,nums.length-1);
        //第二次&第三次反转：让两个局部的顺序恢复正常顺序
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
    }
    //写一个反转数组的方法
    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
