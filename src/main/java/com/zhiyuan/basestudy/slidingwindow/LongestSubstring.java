package com.zhiyuan.basestudy.slidingwindow;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        if (chars.length == 0) return 0;
        Set<Character> set = new HashSet<>();
        int maxLength = 0;
        int p = 0;
        int q = 0;
        // 哎哟我，虽然当时没有具体思路，但是目前为止的初始化全对诶，我好牛福

        while (q < chars.length&&(chars.length-p)>maxLength) {
            while (set.contains(chars[q])) {
                set.remove(chars[p]);
                p++;
            }
            //当p=q时，set里已经清空了，必然会停下
            set.add(chars[q]);
            maxLength = Math.max(maxLength, q-p+1);
            q++;
        }
        return maxLength;
    }
}
