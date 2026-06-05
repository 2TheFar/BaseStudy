/**
 * 这个Trie（前缀树）它首先就是一棵树，但是它可能有26个儿子
 * 只有left和right指针肯定是不行的，得有26个指针
 * 这数量太多了，所以把26个指针整合成一个引用类型的指针数组
 * 需要一个哨兵节点作为开始，这个哨兵节点就是根节点
 * 如需判断当到当前节点是否就是个单词，需要一个isEnd来标记
 */
class Trie {
    private TrieNode root;

    private static class TrieNode {
        // 26个指针的数组
        TrieNode[] children = new TrieNode[26];
        boolean isEnd = false;
    }

    public Trie() {
        this.root = new TrieNode();
    }

    /*
     * 创建新节点的陷阱：
     * 这里一开始就犯了个大错，那就是给指针A赋予另一个指针B的值，
     * 只是让指针A和指针B指向了同一个东西，不代表指针A就是指针B本身
     * 我先让child等于当前节点的儿子指针
     * TrieNode child = cur.children[word.charAt(i) - 'a'];
     * 然后又让child等于new TrieNode()的地址
     * 这不代表我让儿子指针指向了新节点，只代表child从指向null变成了指向新节点
     */
    public void insert(String word) {
        TrieNode cur = this.root;
        for (int i = 0; i < word.length(); i++) {

            // 指向当前字符的儿子指针索引
            int index = word.charAt(i) - 'a';
            if (cur.children[index] == null) {
                cur.children[index] = new TrieNode();
            }
            cur = cur.children[index];
        }
        cur.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode cur = this.root;
        for (int i = 0; i < word.length(); i++) {
            // 指向当前字符的儿子指针
            TrieNode child = cur.children[word.charAt(i) - 'a'];
            if (child == null) {
                return false;
            }
            cur = child;
        }
        return cur.isEnd;
    }

    public boolean startsWith(String prefix) {
        TrieNode cur = this.root;
        for (int i = 0; i < prefix.length(); i++) {
            // 指向当前字符的儿子指针
            TrieNode child = cur.children[prefix.charAt(i) - 'a'];
            if (child == null) {
                return false;
            }
            cur = child;
        }
        return true;
    }
}