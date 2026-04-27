
import java.util.HashMap;

public class MinWindow {
    public static void main(String[] args) {
        MinWindow solution = new MinWindow();

        // 测试用例1: 基本功能测试
        String s1 = "ADOBECODEBANC";
        String t1 = "ABC";
        System.out.println("输入: s = \"" + s1 + "\", t = \"" + t1 + "\"");
        System.out.println("输出: \"" + solution.minWindow(s1, t1) + "\"");
        System.out.println("预期: \"BANC\"");
        System.out.println();

        // 测试用例2: t包含重复字符
        String s2 = "a";
        String t2 = "a";
        System.out.println("输入: s = \"" + s2 + "\", t = \"" + t2 + "\"");
        System.out.println("输出: \"" + solution.minWindow(s2, t2) + "\"");
        System.out.println("预期: \"a\"");
        System.out.println();

        // 测试用例3: s中不包含t的所有字符
        String s3 = "a";
        String t3 = "aa";
        System.out.println("输入: s = \"" + s3 + "\", t = \"" + t3 + "\"");
        System.out.println("输出: \"" + solution.minWindow(s3, t3) + "\"");
        System.out.println("预期: \"\"");
        System.out.println();

        // 测试用例4: 复杂情况
        String s4 = "cabwefgewcwaefgcf";
        String t4 = "cae";
        System.out.println("输入: s = \"" + s4 + "\", t = \"" + t4 + "\"");
        System.out.println("输出: \"" + solution.minWindow(s4, t4) + "\"");
        System.out.println("预期: \"cwae\"");

    }

    public String minWindow(String s, String t) {
        int[] need=new int[128];
        int[] now=new int[128];
        //遍历字符串t
        for (int i=0;i<t.length();i++){
            need[t.charAt(i)]++;
        }
        //用记录“满足数量要求的字符种类数”去判断当前窗口是否“满足条件”要远比遍历一遍哈希表来的快
        //当need中所有种类的字符刚好都满足条件时，这个临界点对于左指针来说就是最小窗口的右边界
        int valid=0;
        int required=0;
        for (int i=0;i<need.length;i++){
            if (need[i]>0){
                required++;
            }
        }
        int minLength=s.length()+1;
        int minLeft=0;
        //找s中每一个字符开始的满足条件的最小子串
        //左指针用来遍历s，指向的字符作为左边界，作为开始，要找的是左指针开始的满足条件的最小子串，找到就右移
        //右指针用来找到使“当前左指针作为左边界”的窗口“满足条件”的“最小右边界”，找到使得valid刚好等于need种类数的临界点，多一个太长，少一个不够
        int left=0;
        int right=0;
        //经过缜密的判断，left指针不会越界存取
        while (right<s.length()){
            char c=s.charAt(right);
            if(need[c]>0){
                now[c]++;
                //如果c加一个刚好满足需求need达到临界状态，说明c从不满足条件变成满足条件，valid应该+1
                if(need[c]==now[c]){
                    valid++;
                }
            }
            right++;
            //检查当前窗口是否满足条件，刚好满足时对左指针来说就是最小窗口，刚好满足就是每一种字符种类now>=need且一定有右边界字符是now[c]=need[c]
            //刚好右边界的c加入使得now[c]+1=need[c]，刚好使得valid+1=required
            //左指针右缩如果依然满足条件，右指针依然是左指针开始的最小窗口右边界，因为一旦左移，就会使得now[c]<need[c]破坏条件，左移不得
            //满足一定是刚好满足，因为一旦满足就会移动左指针，直到条件被破坏，所以下一次还是刚好满足
            while (valid==required){
                int length=right-left;
                if(length<minLength){
                    minLength=length;
                    minLeft=left;
                }
                //接下来左指针右移
                char d=s.charAt(left);
                if(need[d]>0){
                    //如果是临界状态，那么就要破坏valid=required
                    if(now[d]==need[d]){
                        valid--;
                    }
                    now[d]--;
                }
                //当前起点判断完毕，找下一个起点的最小窗口
                left++;
            }
        }
        return (minLength==s.length()+1)?"":s.substring(minLeft,minLeft+minLength);
    }
}
