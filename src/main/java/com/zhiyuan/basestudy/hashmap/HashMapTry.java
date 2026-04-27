package com.zhiyuan.basestudy.hashmap;

import java.util.*;

public class HashMapTry {
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

    /**
     * 根据特征归类，创建散列表，key=特征，value=被归类的对象
     *
     * @param strs 字符串列表
     * @return arrayList 字符串集的集合
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List> map = new HashMap<>();
        int[] count = new int[26];

        for (String s : strs) {
            Arrays.fill(count, 0);
            char[] chars = s.toCharArray();
            for (char c : chars) {
                count[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList());
            }
            map.get(key).add(s);
        }
        return new ArrayList(map.values());
    }

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;

        Set<Integer> set = new HashSet<>();
        for(int num:nums){
            set.add(num);
        }
        int maxlen=0;
        int len=0;
        int x=0;
        for (int num:set){
            if(!set.contains(num-1)){
                len=1;
                x=num;
                while (set.contains(x+1)){
                    len++;
                    x=x+1;
                }
//                if(len>maxlen){
//                    maxlen=len;
//                }
                maxlen = Math.max(maxlen, len);
            }
        }
        return maxlen;
    }
}
