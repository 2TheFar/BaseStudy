package com.zhiyuan.basestudy.dp;

import java.util.HashMap;
import java.util.Map;

public class DPExamples {
    //873. 最长的斐波那契子序列的长度
    //哈希表的键值是自由的，是离散的，唯一肯定的是，键和值一一对应，能知道某个键或值是否存在，且可以通过键去寻找值，但无法通过值找键
    public int lenLongestFibSubseq(int[] arr) {
        //对于下标0对值0，下标1对值1......这种关系使用数组更快更好，这种对应关系跟哈希表八竿子打不着
        //但是哈希表能做到数组做不到的是，哈希表能通过离散的值，去找对应的下标
        //当然，哈希表的值可不是数组的值，哈希表通过键去找值，我们把离散的、已知的量设为key，它们对应的、未知的目标量设为value
        //在这道题中，通过目标值找符合的下标是否存在，设数值为key，下标为value，如果反过来就变成数组，通过下标来找数值
        //所以一般把哈希表当做反过来的数组用
        Map<Integer,Integer> hashmap=new HashMap<>();
        int len=arr.length;
        for (int i=0;i<len;i++){
            hashmap.put(arr[i],i);
        }
        int[][] map=new int[len][len];
        int max=0;
        for (int i=0;i<len;i++){
            for (int j=0;j<i;j++){
                int prev=arr[i]-arr[j];
                if(hashmap.containsKey(prev)&&hashmap.get(prev)<j){
                    int k=hashmap.get(prev);
                    map[j][i]=map[k][j]+1;
                }else {
                    map[j][i]=2;
                }
                max=Math.max(max,map[j][i]);
            }
        }
        if (max<3){
            return 0;
        }else {
            return max;
        }
    }
}
