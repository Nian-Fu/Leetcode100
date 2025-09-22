package com.funian.algorithm.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 最小覆盖子串
 *
 * 时间复杂度：O(|s| + |t|)
 * - |s| 是字符串 s 的长度，|t| 是字符串 t 的长度
 * - 需要遍历字符串 s 一次：O(|s|)
 * - 需要遍历字符串 t 一次：O(|t|)
 * - 每个字符最多被访问两次（一次被右指针，一次被左指针）
 *
 * 空间复杂度：O(|s| + |t|)
 * - HashMap 存储字符串 t 中字符的频率：O(|t|)
 * - HashMap 存储滑动窗口中字符的频率：最坏情况 O(|s|)
 */
public class MinWindow76 {
    public static void main(String[] args) {
        // 创建 Scanner 对象读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入字符串 s
        System.out.print("请输入字符串 s：");
        // 读取字符串 s
        String s = scanner.nextLine();

        // 提示用户输入字符串 t
        System.out.print("请输入字符串 t：");
        // 读取字符串 t
        String t = scanner.nextLine();

        // 调用 minWindow 方法返回结果
        String result = minWindow(s, t);

        // 输出结果
        System.out.println("最小覆盖子串为：" + result);
    }

    /**
     * 找到字符串 s 中涵盖字符串 t 所有字符的最小子串
     *
     * 算法思路：
     * 使用滑动窗口技术，通过双指针维护一个窗口
     * 1. 右指针不断扩展窗口，直到窗口包含 t 中的所有字符
     * 2. 当窗口满足条件时，左指针尝试收缩窗口以找到最小解
     * 3. 维护一个有效字符计数器，记录窗口中满足字符数量要求的字符种类数
     *
     * 示例过程（以 s="ADOBECODEBANC", t="ABC" 为例）：
     *
     * 步骤  窗口           包含字符  有效字符数  是否满足  最小窗口
     * 1    [A)            A:1       1          否       ""
     * 2    [AD)           A:1,D:1   1          否       ""
     * 3    [ADO)          A:1,D:1,O:1 1        否       ""
     * 4    [ADOB)         A:1,B:1,D:1,O:1 2    否       ""
     * 5    [ADOBE)        A:1,B:1,D:1,E:1,O:1 2 否      ""
     * 6    [ADOBEC)       A:1,B:1,C:1,...     3 是      "ADOBEC"
     * 7    A[D OBEC)      A:0,B:1,C:1,...     2 否      "ADOBEC"
     * ...继续滑动直到找到更优解...
     * 最终结果: "BANC"
     *
     * @param s 主字符串
     * @param t 需要覆盖的字符集合
     * @return 最小覆盖子串，如果不存在则返回空字符串
     */
    public static String minWindow(String s, String t) {
        // 边界条件检查：如果任一字符串为空，返回空字符串
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }

        // 记录 t 中每个字符的需求数量
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        // 滑动窗口所需的变量
        Map<Character, Integer> windowMap = new HashMap<>(); // 记录窗口中每个字符的实际数量
        int left = 0, right = 0; // 左右指针，表示窗口的范围 [left, right)
        int valid = 0; // 符合条件的字符种类数（窗口中数量满足需求的字符种类）
        int minLen = Integer.MAX_VALUE; // 最小窗口长度
        int start = 0; // 最小窗口的起始位置

        // 右指针遍历字符串 s
        while (right < s.length()) {
            // 获取当前右边界字符
            char c = s.charAt(right);
            // 扩展右边界
            right++;

            // 如果该字符在 t 中，则加入窗口进行处理
            if (targetMap.containsKey(c)) {
                // 更新窗口中该字符的数量
                windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
                // 如果窗口中的该字符数量和 t 中需求一致，则有效字符数 +1
                if (windowMap.get(c).equals(targetMap.get(c))) {
                    valid++;
                }
            }

            // 当窗口内的字符已经满足 t 中所有字符时，尝试收缩左边界
            while (valid == targetMap.size()) {
                // 更新最小窗口长度和起始位置
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }

                // 获取左边界字符
                char d = s.charAt(left);
                // 收缩左边界
                left++;

                // 如果该字符在 t 中，收缩窗口时需要进行处理
                if (targetMap.containsKey(d)) {
                    // 如果该字符数量减少到不再满足需求，则有效字符数 -1
                    if (windowMap.get(d).equals(targetMap.get(d))) {
                        valid--;
                    }
                    // 更新窗口中的字符数量
                    windowMap.put(d, windowMap.get(d) - 1);
                }
            }
        }

        // 如果 minLen 没有被更新过，说明没有找到符合条件的子串，返回空字符串
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
