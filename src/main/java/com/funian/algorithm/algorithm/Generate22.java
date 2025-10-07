package com.funian.algorithm.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 括号生成（LeetCode 22）
 *
 * 时间复杂度：O(4^n / √n)
 * - 第n个卡塔兰数约为4^n / (n^(3/2) * √π)
 * - 每个有效序列需要O(n)时间构造
 *
 * 空间复杂度：O(4^n / √n)
 * - 递归调用栈深度为2n
 * - 需要存储所有有效括号序列
 */
public class Generate22 {

    public static void main(String[] args) {
        // 创建 Scanner 对象用于输入
        // Scanner scanner = new Scanner(System.in) 创建Scanner对象用于读取输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入括号对数 n
        // System.out.print("请输入括号对数 n：") 打印输入提示
        System.out.print("请输入括号对数 n：");
        // int n = scanner.nextInt() 读取括号对数
        int n = scanner.nextInt();

        // 调用 generateParenthesis 方法生成所有有效的括号组合
        // List<String> result = generateParenthesis(n) 调用generateParenthesis方法生成括号组合
        List<String> result = generateParenthesis(n);

        // 输出结果
        // System.out.println("所有有效的括号组合：") 打印输出提示
        System.out.println("所有有效的括号组合：");
        // for (String s : result) 遍历结果列表
        for (String s : result) {
            // System.out.println(s) 打印每个括号组合
            System.out.println(s);
        }
    }

    /**
     * 生成有效括号组合的方法
     *
     * 算法思路：
     * 使用回溯算法，通过递归生成所有有效的括号组合
     * 1. 维护已使用的左括号(`open`)和右括号(`close`)数量
     * 2. 只有当左括号数量大于右括号数量时才能添加右括号
     * 3. 当当前字符串长度达到`2*n`时得到一个有效组合
     * 4. 剪枝：左括号不能超过n个，右括号不能超过左括号数量
     *
     * 执行过程分析（以`n=3`为例）：
     *
     * 递归树：
     *                    ""
     *                    |
     *                   "("
     *              /         \
     *           "(("        "()"
     *          /    \      /    \
     *       "((("  "(()" "()("  "())"
     *         |     / |    |      |
     *      "((()))" "(()(" "(())" "()()" (部分路径)
     *               /  |    |      |
     *           "(()()" "(())" "()()" (继续展开)
     *              |     |      |
     *          "(()())" "(())" "()())" (最终结果)
     *
     * 详细执行过程：
     *
     * 1. `open=0`, `close=0`, `max=3`
     *    - 可以添加左括号：`open=1`, `close=0`, `current="("`
     *      - 添加左括号：`open=2`, `close=0`, `current="(("`
     *        - 添加左括号：`open=3`, `close=0`, `current="((("`
     *          - 不能添加左括号(`open=max`)
     *          - 可以添加右括号：`open=3`, `close=1`, `current="((())"`
     *            - 可以添加右括号：`open=3`, `close=2`, `current="((()))"`
     *              - 可以添加右括号：`open=3`, `close=3`, `current="((()))"`
     *                - `current.length()=6=2*max`，添加`"((()))"`到结果
     *        - 添加右括号：`open=2`, `close=1`, `current="(()"`
     *          - 添加左括号：`open=3`, `close=1`, `current="(()("`
     *            - 添加右括号：`open=3`, `close=2`, `current="(()())"`
     *              - 添加右括号：`open=3`, `close=3`, `current="(()())"`
     *                - `current.length()=6=2*max`，添加`"(()())"`到结果
     *          - 添加右括号：`open=2`, `close=2`, `current="(())"`
     *            - 添加左括号：`open=3`, `close=2`, `current="(())("`
     *              - 添加右括号：`open=3`, `close=3`, `current="(())()"`
     *                - `current.length()=6=2*max`，添加`"(())()"`到结果
     *      - 添加右括号：`open=1`, `close=1`, `current="()"`
     *        - 添加左括号：`open=2`, `close=1`, `current="()("`
     *          - 同样过程得到`"()(())"`和`"()()()"`
     *
     * 最终结果（n=3）：["((()))", "(()())", "(())()", "()(())", "()()()"]
     *
     * 时间复杂度分析：
     * - 递归生成所有有效括号组合：O(4^n / √n)
     * - 每个组合需要O(n)时间构造：O(n)
     * - 总时间复杂度：O(4^n / √n)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(2n)
     * - 存储所有有效括号序列：O(4^n / √n)
     * - 总空间复杂度：O(4^n / √n)
     *
     * @param n 括号对数
     * @return 所有可能的有效括号组合
     */
    public static List<String> generateParenthesis(int n) {
        // List<String> result = new ArrayList<>() 创建存储结果的列表
        List<String> result = new ArrayList<>(); // 存储结果的列表
        // backtrack(result, "", 0, 0, n) 调用回溯方法生成括号组合
        backtrack(result, "", 0, 0, n); // 开始回溯
        // return result 返回结果列表
        return result; // 返回结果
    }

    /**
     * 回溯方法
     *
     * 算法思路：
     * 递归生成所有有效的括号组合，通过控制左右括号数量保证有效性
     *
     * @param result 存储所有有效括号组合的结果列表
     * @param current 当前构建的括号序列
     * @param open 已使用的左括号数量
     * @param close 已使用的右括号数量
     * @param max 括号对数
     */
    private static void backtrack(List<String> result, String current, int open, int close, int max) {
        // 如果当前组合的长度达到最大长度（2*n），则添加到结果中
        // if (current.length() == 2 * max) 检查当前组合是否达到最大长度
        if (current.length() == 2 * max) {
            // result.add(current) 将当前组合添加到结果列表
            result.add(current);
            // return 返回，结束当前递归分支
            return;
        }

        // 如果还可以添加左括号（左括号数量小于n）
        // 剪枝：确保左括号不超过n个
        // if (open < max) 检查是否可以添加左括号
        if (open < max) {
            // backtrack(result, current + "(", open + 1, close, max) 递归添加左括号
            backtrack(result, current + "(", open + 1, close, max); // 添加左括号
        }

        // 如果可以添加右括号（右括号数量小于左括号数量）
        // 剪枝：确保右括号不超过左括号数量，避免生成无效序列
        // if (close < open) 检查是否可以添加右括号
        if (close < open) {
            // backtrack(result, current + ")", open, close + 1, max) 递归添加右括号
            backtrack(result, current + ")", open, close + 1, max); // 添加右括号
        }
    }

    /**
     * 方法2：使用StringBuilder优化字符串操作
     *
     * 算法思路：
     * 使用StringBuilder代替字符串拼接，减少内存分配和复制操作
     *
     * 时间复杂度分析：
     * - 与方法1相同：O(4^n / √n)
     *
     * 空间复杂度分析：
     * - 递归调用栈深度：O(2n)
     * - StringBuilder操作：O(n)
     * - 存储所有有效括号序列：O(4^n / √n)
     * - 总空间复杂度：O(4^n / √n)
     *
     * @param n 括号对数
     * @return 所有可能的有效括号组合
     */
    public List<String> generateParenthesisOptimized(int n) {
        // List<String> result = new ArrayList<>() 创建存储结果的列表
        List<String> result = new ArrayList<>();
        // StringBuilder current = new StringBuilder() 创建StringBuilder对象用于构建括号序列
        StringBuilder current = new StringBuilder();
        // backtrackOptimized(result, current, 0, 0, n) 调用优化版回溯方法
        backtrackOptimized(result, current, 0, 0, n);
        // return result 返回结果列表
        return result;
    }

    /**
     * 优化版回溯方法
     *
     * 算法思路：
     * 使用StringBuilder进行字符串操作，避免频繁创建字符串对象
     *
     * @param result 存储所有有效括号组合的结果列表
     * @param current 当前构建的括号序列
     * @param open 已使用的左括号数量
     * @param close 已使用的右括号数量
     * @param max 括号对数
     */
    private void backtrackOptimized(List<String> result, StringBuilder current, int open, int close, int max) {
        // if (current.length() == 2 * max) 检查当前组合是否达到最大长度
        if (current.length() == 2 * max) {
            // result.add(current.toString()) 将当前组合添加到结果列表
            result.add(current.toString());
            // return 返回，结束当前递归分支
            return;
        }

        // if (open < max) 检查是否可以添加左括号
        if (open < max) {
            // current.append('(') 添加左括号到StringBuilder
            current.append('(');
            // backtrackOptimized(result, current, open + 1, close, max) 递归添加左括号
            backtrackOptimized(result, current, open + 1, close, max);
            // current.deleteCharAt(current.length() - 1) 删除最后一个字符进行回溯
            current.deleteCharAt(current.length() - 1);
        }

        // if (close < open) 检查是否可以添加右括号
        if (close < open) {
            // current.append(')') 添加右括号到StringBuilder
            current.append(')');
            // backtrackOptimized(result, current, open, close + 1, max) 递归添加右括号
            backtrackOptimized(result, current, open, close + 1, max);
            // current.deleteCharAt(current.length() - 1) 删除最后一个字符进行回溯
            current.deleteCharAt(current.length() - 1);
        }
    }
}
