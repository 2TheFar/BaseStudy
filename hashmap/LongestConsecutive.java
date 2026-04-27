
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutive {
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
