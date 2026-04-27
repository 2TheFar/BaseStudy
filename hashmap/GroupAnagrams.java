
import java.util.*;

public class GroupAnagrams {
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
}
