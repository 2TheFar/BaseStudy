package com.zhiyuan.basestudy.function;

public class BaseFunction {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public static void quikSoft(int arr[], int left, int rignt) {
        if (left >= rignt)
            return;
        int l = left;
        int r = rignt;
        int pivot = arr[left];

        while (l < r) {
            while (l < r && arr[r] >= pivot) {
                r--;
            }
            /*
             * 两种情况：
             * 1.没有和l相遇->找到一个比pivot小的数->把r丢到l那边
             * 2.和l相遇了->指向同一个，如蜜传如蜜也无妨
             */
            arr[l] = arr[r];
            while (l < r && arr[l] < pivot) {
                l++;
            }
            arr[r] = arr[l];
        }
        /*
         * l遍历经过的数一定都小于pivot
         * r遍历经过的数一定都大于等于pivot
         * 当二者相遇时，左边都小于pivot，右边都大于等于pivot，并且这个位置在相遇之前是空位
         * 这就是pivot的位置
         */
        arr[l] = pivot;
        quikSoft(arr, left, l - 1);
        quikSoft(arr, l + 1, rignt);
    }
}