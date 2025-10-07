package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 分割回文串（LeetCode 131）
 *
 * 时间复杂度：O(N * 2^N)
 * - 最坏情况下有2^N个分割方案，每个方案需要O(N)时间验证和构造
 *
 * 空间复杂度：O(N)
 * - 递归调用栈深度为N
 * - 需要额外的列表存储当前路径和预处理的回文信息
 */
public class Partition131 {

    /**
     * 分割字符串使得每个子串都是回文串
     *
     * 算法思路：
     * 使用回溯算法，通过递归和状态重置生成所有可能的分割方案
     * 1. 预处理：使用动态规划计算所有子串是否为回文
     * 2. 回溯：从起始位置开始，尝试每个可能的分割点
     * 3. 只有当前子串是回文时才继续递归
     * 4. 找到完整分割方案后添加到结果中
     *
     * 执行过程分析（以`s="aab"`为例）：
     *
     * 预处理回文信息：
     * dp[0][0]=T, dp[1][1]=T, dp[2][2]=T  // 单个字符
     * dp[0][1]=T (aa), dp[1][2]=F (ab)    // 长度为2的子串
     * dp[0][2]=F (aab)                    // 长度为3的子串
     *
     * 递归树：
     *                    []
     *                 /      \
     *              [a]        [aa]
     *             /  \         |
     *        [a,a]  [a,ab]  [aa,b]
     *          |      X       |
     *      [a,a,b]         [aa,b] (解)
     *
     * 详细执行过程：
     *
     * 1. start=0: 尝试分割点i=0,1,2
     *    - i=0: substring(0,1)="a"是回文
     *      - path=["a"], start=1
     *      - start=1: 尝试分割点i=1,2
     *        - i=1: substring(1,2)="a"是回文
     *          - path=["a","a"], start=2
     *          - start=2: 尝试分割点i=2
     *            - i=2: substring(2,3)="b"是回文
     *              - path=["a","a","b"], start=3
     *              - start=3 == s.length()，找到解["a","a","b"]
     *        - i=2: substring(1,3)="ab"不是回文，跳过
     *    - i=1: substring(0,2)="aa"是回文
     *      - path=["aa"], start=2
     *      - start=2: 尝试分割点i=2
     *        - i=2: substring(2,3)="b"是回文
     *          - path=["aa","b"], start=3
     *          - start=3 == s.length()，找到解["aa","b"]
     *    - i=2: substring(0,3)="aab"不是回文，跳过
     *
     * 最终结果：[["a","a","b"], ["aa","b"]]
     *
     * 时间复杂度分析：
     * - 预处理回文信息：O(N²)
     * - 回溯生成方案：O(N * 2^N)
     * - 总时间复杂度：O(N * 2^N)
     *
     * 空间复杂度分析：
     * - 递归调用栈：O(N)
     * - isPalindrome数组：O(N²)
     * - path列表：O(N)
     * - result列表：O(N * 2^N)
     * - 总空间复杂度：O(N * 2^N)
     *
     * @param s 输入字符串
     * @return 所有可能的分割方案
     */
    public List<List<String>> partition(String s) {
        // List<List<String>> result = new ArrayList<>() 创建结果列表
        List<List<String>> result = new ArrayList<>();
        // List<String> path = new ArrayList<>() 创建路径列表
        List<String> path = new ArrayList<>();

        // 预处理：计算所有子串是否为回文
        // boolean[][] isPalindrome = preprocess(s) 预处理回文信息
        boolean[][] isPalindrome = preprocess(s);

        // 开始回溯
        // backtrack(s, 0, path, isPalindrome, result) 调用回溯方法
        backtrack(s, 0, path, isPalindrome, result);

        // return result 返回结果
        return result;
    }

    /**
     * 预处理：计算所有子串是否为回文
     *
     * 算法思路：
     * 使用动态规划，`dp[i][j]`表示`s.substring(i,j+1)`是否为回文
     * 状态转移方程：
     * `dp[i][j]` = `(s.charAt(i) == s.charAt(j)) && (j-i <= 2 || dp[i+1][j-1])`
     *
     * 时间复杂度分析：
     * - 填充DP表：O(N²)
     *
     * 空间复杂度分析：
     * - DP表存储空间：O(N²)
     *
     * @param s 输入字符串
     * @return 二维布尔数组，`dp[i][j]`表示`s[i..j]`是否为回文
     */
    private boolean[][] preprocess(String s) {
        // int n = s.length() 获取字符串长度
        int n = s.length();
        // boolean[][] dp = new boolean[n][n] 创建DP表
        boolean[][] dp = new boolean[n][n];

        // 从下到上，从左到右填充DP表
        // for (int i = n - 1; i >= 0; i--) 外层循环从后往前遍历
        for (int i = n - 1; i >= 0; i--) {
            // for (int j = i; j < n; j++) 内层循环从i开始遍历
            for (int j = i; j < n; j++) {
                // if (s.charAt(i) == s.charAt(j)) 检查首尾字符是否相同
                if (s.charAt(i) == s.charAt(j)) {
                    // 如果长度小于等于3，或者内部子串是回文
                    // if (j - i <= 2 || dp[i + 1][j - 1]) 检查是否为回文
                    if (j - i <= 2 || dp[i + 1][j - 1]) {
                        // dp[i][j] = true 标记为回文
                        dp[i][j] = true;
                    }
                }
            }
        }

        // return dp 返回DP表
        return dp;
    }

    /**
     * 回溯辅助方法
     *
     * 算法思路：
     * 递归地尝试所有可能的分割点，只在子串为回文时继续递归
     *
     * @param s 输入字符串
     * @param start 当前处理的起始位置
     * @param path 当前构建的分割方案
     * @param isPalindrome 预处理的回文信息
     * @param result 存储所有分割方案的结果列表
     */
    private void backtrack(String s, int start, List<String> path,
                          boolean[][] isPalindrome, List<List<String>> result) {
        // 递归终止条件：已处理完整个字符串
        // if (start == s.length()) 检查是否已处理完整个字符串
        if (start == s.length()) {
            // 将当前路径添加到结果中（需要创建新列表避免引用问题）
            // result.add(new ArrayList<>(path)) 添加路径副本到结果列表
            result.add(new ArrayList<>(path));
            // return 返回
            return;
        }

        // 尝试每个可能的分割点
        // for (int i = start; i < s.length(); i++) 遍历所有可能的分割点
        for (int i = start; i < s.length(); i++) {
            // 只有当前子串是回文时才继续
            // if (isPalindrome[start][i]) 检查子串是否为回文
            if (isPalindrome[start][i]) {
                // 做选择：将当前子串添加到路径中
                // path.add(s.substring(start, i + 1)) 添加子串到路径
                path.add(s.substring(start, i + 1));

                // 递归：处理剩余部分
                // backtrack(s, i + 1, path, isPalindrome, result) 递归处理剩余部分
                backtrack(s, i + 1, path, isPalindrome, result);

                // 撤销选择：回溯，移除当前子串
                // path.remove(path.size() - 1) 移除路径中的最后一个元素
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * 方法2：不预处理，直接判断回文
     *
     * 算法思路：
     * 不预先计算回文信息，而是在回溯过程中直接判断子串是否为回文
     *
     * 时间复杂度分析：
     * - 回溯生成方案：O(N * 2^N)
     * - 每次判断回文：O(N)
     * - 总时间复杂度：O(N² * 2^N)
     *
     * 空间复杂度分析：
     * - 递归调用栈：O(N)
     * - path列表：O(N)
     * - result列表：O(N * 2^N)
     * - 总空间复杂度：O(N * 2^N)
     *
     * @param s 输入字符串
     * @return 所有可能的分割方案
     */
    public List<List<String>> partitionNoPreprocess(String s) {
        // List<List<String>> result = new ArrayList<>() 创建结果列表
        List<List<String>> result = new ArrayList<>();
        // List<String> path = new ArrayList<>() 创建路径列表
        List<String> path = new ArrayList<>();
        // backtrackNoPreprocess(s, 0, path, result) 调用不预处理的回溯方法
        backtrackNoPreprocess(s, 0, path, result);
        // return result 返回结果
        return result;
    }

    /**
     * 不预处理的回溯辅助方法
     *
     * 算法思路：
     * 在回溯过程中直接判断子串是否为回文
     *
     * @param s 输入字符串
     * @param start 当前处理的起始位置
     * @param path 当前构建的分割方案
     * @param result 存储所有分割方案的结果列表
     */
    private void backtrackNoPreprocess(String s, int start, List<String> path,
                                      List<List<String>> result) {
        // if (start == s.length()) 检查是否已处理完整个字符串
        if (start == s.length()) {
            // result.add(new ArrayList<>(path)) 添加路径副本到结果列表
            result.add(new ArrayList<>(path));
            // return 返回
            return;
        }

        // for (int i = start; i < s.length(); i++) 遍历所有可能的分割点
        for (int i = start; i < s.length(); i++) {
            // 直接判断子串是否为回文
            // if (isPalindrome(s, start, i)) 检查子串是否为回文
            if (isPalindrome(s, start, i)) {
                // path.add(s.substring(start, i + 1)) 添加子串到路径
                path.add(s.substring(start, i + 1));
                // backtrackNoPreprocess(s, i + 1, path, result) 递归处理剩余部分
                backtrackNoPreprocess(s, i + 1, path, result);
                // path.remove(path.size() - 1) 移除路径中的最后一个元素
                path.remove(path.size() - 1);
            }
        }
    }

    /**
     * 判断子串是否为回文
     *
     * 算法思路：
     * 使用双指针法判断子串是否为回文
     *
     * 时间复杂度分析：
     * - 双指针遍历：O(N)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param s 字符串
     * @param left 左边界
     * @param right 右边界
     * @return 是否为回文
     */
    private boolean isPalindrome(String s, int left, int right) {
        // while (left < right) 当左指针小于右指针时循环
        while (left < right) {
            // if (s.charAt(left) != s.charAt(right)) 比较左右字符
            if (s.charAt(left) != s.charAt(right)) {
                // return false 返回false
                return false;
            }
            // left++ 左指针右移
            left++;
            // right-- 右指针左移
            right--;
        }
        // return true 返回true
        return true;
    }

    /**
     * 辅助方法：读取用户输入的字符串
     *
     * 时间复杂度分析：
     * - 读取输入：O(N)
     *
     * 空间复杂度分析：
     * - 存储字符串：O(N)
     *
     * @return 用户输入的字符串
     */
    public static String readString() {
        // Scanner scanner = new Scanner(System.in) 创建Scanner对象
        Scanner scanner = new Scanner(System.in);
        // System.out.print("请输入字符串: ") 打印提示信息
        System.out.print("请输入字符串: ");
        // return scanner.nextLine() 读取并返回字符串
        return scanner.nextLine();
    }

    /**
     * 辅助方法：打印分割方案
     *
     * 时间复杂度分析：
     * - 遍历结果：O(N * 2^N)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param result 分割方案列表
     */
    public static void printPartitions(List<List<String>> result) {
        // System.out.println("所有分割方案：") 打印标题
        System.out.println("所有分割方案：");
        // for (int i = 0; i < result.size(); i++) 遍历所有分割方案
        for (int i = 0; i < result.size(); i++) {
            // System.out.println((i + 1) + ": " + result.get(i)) 打印分割方案
            System.out.println((i + 1) + ": " + result.get(i));
        }
        // System.out.println("总共 " + result.size() + " 个方案") 打印总计数量
        System.out.println("总共 " + result.size() + " 个方案");
    }

    /**
     * 主函数：处理用户输入并生成所有回文分割方案
     */
    public static void main(String[] args) {
        // System.out.println("分割回文串") 打印程序标题
        System.out.println("分割回文串");

        // 读取用户输入的字符串
        // String s = readString() 调用readString方法读取字符串
        String s = readString();
        // System.out.println("输入字符串: \"" + s + "\"") 打印输入字符串
        System.out.println("输入字符串: \"" + s + "\"");

        // 生成所有分割方案
        // Partition131 solution = new Partition131() 创建Partition131对象
        Partition131 solution = new Partition131();
        // List<List<String>> result1 = solution.partition(s) 调用partition方法
        List<List<String>> result1 = solution.partition(s);
        // List<List<String>> result2 = solution.partitionNoPreprocess(s) 调用partitionNoPreprocess方法
        List<List<String>> result2 = solution.partitionNoPreprocess(s);

        // 输出结果
        // System.out.println("预处理方法结果：") 打印预处理方法结果标题
        System.out.println("预处理方法结果：");
        // printPartitions(result1) 调用printPartitions方法打印结果
        printPartitions(result1);

        // System.out.println("\n直接判断方法结果：") 打印直接判断方法结果标题
        System.out.println("\n直接判断方法结果：");
        // printPartitions(result2) 调用printPartitions方法打印结果
        printPartitions(result2);
    }
}
