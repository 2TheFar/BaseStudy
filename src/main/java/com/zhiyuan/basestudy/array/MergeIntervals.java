package com.zhiyuan.basestudy.array;

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.Arrays;

public class MergeIntervals {
    public static int[][] merge(int[][] intervals) {
        int width = intervals[0].length;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        ArrayList<int[]> result=new ArrayList<>();
        result.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            /*
            惊天大优化一：
            排序后根本不需要遍历所有已合并区间！
            从 O (n²) → 变成 O (n)
            result是之前所有区间合并后的集合，显然当前区间只可能和最后一个区间合并
            因为当前区间的下限一定比result中所有区间的下限都大，result中最后一个区间的下限肯定比其他区间的上限大
            也就是说当前区间的下限比其他区间的上限都大，所以不可能合并
             */
            int[] last=result.get(result.size()-1);
            int[] curr=intervals[i];
            if (curr[0] <= last[1]) {
                last[1] = Math.max(last[1], curr[1]);
            } else {
                result.add(curr);
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void quickSort(int[][] intervals, int left, int right) {
        if (left >= right) return;

        int lp = left;
        int rp = right;
        // 排下限
        int pivot = intervals[lp][0];
        // 我去，不要只想着排下限，把上限忘记了！
        int pivot_1 = intervals[lp][1];
        while (lp < rp) {
            while (lp < rp && intervals[rp][0] >= pivot) {
                rp--;
            }
            intervals[lp][0] = intervals[rp][0];
            intervals[lp][1] = intervals[rp][1];
            // lp与rp相遇的位置就是空位
            // 小发现：空位要么是pivot本身，要么就是与指针相对的值（left-大，right-小），所以停下来肯定是因为lp==rp
            // 所以要使相遇时能正常结束，必须加上条件lp<rp
            while (lp < rp && intervals[lp][0] < pivot) {
                lp++;
            }
            intervals[rp][0] = intervals[lp][0];
            intervals[rp][1] = intervals[lp][1];
        }
        intervals[lp][0] = pivot;
        intervals[lp][1] = pivot_1;
        quickSort(intervals, left, lp - 1);
        quickSort(intervals, rp + 1, right);
    }

}
