package com.funian.algorithm.algorithm;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 无重复字符的最长子串
 *
 * 时间复杂度：O(n)
 * - 虽然有嵌套结构，但每个字符最多被访问两次（一次被右指针访问，一次被左指针访问）
 * - 左右指针都只向前移动，不会回退
 *
 * 空间复杂度：O(min(m,n))
 * - m 是字符集大小（如ASCII字符集大小为128）
 * - HashSet 最多存储 min(m,n) 个字符
 * - n 是字符串长度
 */
public class LongestSubstring3 {
    public static void main(String[] args) {
        // 创建 Scanner 对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);
        // 提示用户输入字符串
        System.out.println("输入字符串：");
        // 读取用户输入的字符串
        String s = scanner.nextLine();
        // 调用 longestSubstring 方法计算最长无重复子串长度
        int result = longestSubstring(s);
        // 输出结果
        System.out.println("不重复的字符串长度为：" + result);
    }

    /**
     * 计算字符串中无重复字符的最长子串长度
     * 使用滑动窗口 + HashSet 的方法
     *
     * 算法思路：
     * 1. 使用双指针维护一个滑动窗口 [left, right)
     * 2. 使用 HashSet 记录当前窗口中包含的字符
     * 3. 右指针不断向右扩展窗口
     * 4. 当遇到重复字符时，左指针向右收缩窗口直到没有重复字符
     * 5. 记录过程中窗口的最大长度
     *
     * 示例过程（以字符串 "abcabcbb" 为例）：
     * 步骤  窗口       HashSet          maxLen
     * 1    [a)        {a}              1
     * 2    [ab)       {a,b}            2
     * 3    [abc)      {a,b,c}          3
     * 4    [abca)     发现重复a        -
     * 5    [bca)      {b,c,a}          3
     * 6    [bcab)     发现重复b        -
     * 7    [cab)      {c,a,b}          3
     * 8    [cabc)     发现重复c        -
     * 9    [abc)      {a,b,c}          3
     * 10   [abcb)     发现重复b        -
     * 11   [bcb)      发现重复b        -
     * 12   [cb)       {c,b}            3
     * 13   [cbb)      发现重复b        -
     * 14   [bb)       发现重复b        -
     * 15   [b)        {b}              3
     * 16   [)         {}               3
     * 结果：最长无重复子串长度为3
     *
     * @param s 输入的字符串
     * @return 无重复字符的最长子串长度
     */
    public static int longestSubstring(String s) {
        // 使用 HashSet 存储当前窗口中的字符，用于快速判断是否有重复
        Set<Character> set = new HashSet<>();
        // 滑动窗口的左边界（包含）
        int left = 0;
        // 滑动窗口的右边界（不包含）
        int right = 0;
        // 获取字符串长度
        int n = s.length();
        // 记录最长子串长度
        int maxLength = 0;

        // 右指针遍历整个字符串
        while (right < n) {
            // 如果右指针指向的字符不在当前窗口中
            if (!set.contains(s.charAt(right))) {
                // 将该字符加入 HashSet
                set.add(s.charAt(right));
                // 右指针右移，扩展窗口
                right++;
                // 更新最大长度（窗口大小为 right - left）
                maxLength = Math.max(maxLength, right - left);
            } else {
                // 如果右指针指向的字符在当前窗口中（出现重复）
                // 从 HashSet 中移除左指针指向的字符
                set.remove(s.charAt(left));
                // 左指针右移，收缩窗口
                left++;
            }
        }

        // 返回最长无重复子串的长度
        return maxLength;
    }
}
