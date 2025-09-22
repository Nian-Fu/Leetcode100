package com.funian.algorithm.algorithm;

import java.util.*;

/**
 * 字母分组
 *
 * 时间复杂度：O(N * K * log K)
 * - N 是字符串数组的长度
 * - K 是字符串的最大长度
 * - 对每个字符串进行排序需要 O(K * log K) 时间
 * - 总共需要处理 N 个字符串
 *
 * 空间复杂度：O(N * K)
 * - HashMap 存储所有字符串需要 O(N * K) 空间
 * - 排序时创建的字符数组需要 O(K) 空间
 * - 返回结果列表需要 O(N * K) 空间
 */
public class GroupAnagrams49 {
    public static void main(String[] args) {
        // 创建 Scanner 对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);
        // 提示用户输入字符串
        System.out.println("输入字符串：");
        // 读取一行输入
        String line = scanner.nextLine();
        // 按空格分割字符串得到字符串数组
        String[] strs = line.split(" ");
        // 调用 groupAnagrams 方法对字母异位词进行分组
        List<List<String>> result = groupAnagrams(strs);
        // 输出分组结果
        System.out.println("分组后的结果为：");
        // 遍历并打印每个分组
        for (List<String> group : result) {
            System.out.println(group);
        }
    }

    /**
     * 字母异位词分组
     * 将字符串数组中互为字母异位词的字符串分到同一组
     * 字母异位词是指两个字符串包含相同的字符，但字符的顺序可能不同
     *
     * 算法思路：
     * 1. 对每个字符串的字符进行排序，异位词排序后结果相同
     * 2. 使用排序后的字符串作为键，将原字符串分组
     * 3. 返回所有分组
     *
     * 示例过程（以数组 ["eat","tea","tan","ate","nat","bat"] 为例）：
     *
     * 字符串  排序后  分组过程
     * "eat"   "aet"   map={"aet": ["eat"]}
     * "tea"   "aet"   map={"aet": ["eat","tea"]}
     * "tan"   "ant"   map={"aet": ["eat","tea"], "ant": ["tan"]}
     * "ate"   "aet"   map={"aet": ["eat","tea","ate"], "ant": ["tan"]}
     * "nat"   "ant"   map={"aet": ["eat","tea","ate"], "ant": ["tan","nat"]}
     * "bat"   "abt"   map={"aet": ["eat","tea","ate"], "ant": ["tan","nat"], "abt": ["bat"]}
     *
     * 结果：[["eat","tea","ate"], ["tan","nat"], ["bat"]]
     *
     * @param strs 输入的字符串数组
     * @return 分组后的字母异位词列表
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        // 使用哈希表存储分组结果，键为排序后的字符串，值为该组的所有原始字符串
        Map<String, List<String>> map = new HashMap<>();
        // 遍历每个字符串
        for (String str : strs) {
            // 将字符串转换为字符数组
            char[] chars = str.toCharArray();
            // 对字符数组进行排序，使得异位词具有相同的排序结果
            Arrays.sort(chars);
            // 将排序后的字符数组转换为字符串作为键
            String key = new String(chars);
            // 如果该键不存在，则创建新的列表
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            // 将原始字符串添加到对应键的列表中
            map.get(key).add(str);
        }
        // 返回所有分组的列表
        return new ArrayList<>(map.values());
    }
}
