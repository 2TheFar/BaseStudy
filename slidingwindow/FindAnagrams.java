
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

public class FindAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();

        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        //考虑ss更短的情况直接返回空
        if (ss.length < pp.length) return res;
        int len = pp.length;

        int simple = 'a';
        char[] nums = new char[26];
        for (int i = 0; i < len; i++) {
            nums[pp[i] - simple]++;
        }
        char[] com = new char[26];
        for (int i = 0; i < len; i++) {
            com[ss[i] - simple]++;
        }
        if (Arrays.equals(com, nums)) {
            res.add(0);
        }
        for (int i = 1; i <= ss.length - len; i++) {
            com[ss[i - 1] - simple]--;
            com[ss[i + len - 1] - simple]++;
            if (Arrays.equals(com, nums)) {
                res.add(i);
            }
        }
        return res;
    }
}
