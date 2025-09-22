package com.funian.algorithm.algorithm;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 最长连续序列
 *
 * 时间复杂度：O(n)
 * - 虽然看起来有嵌套循环，但每个元素最多被访问两次
 * - 外层循环遍历所有唯一元素：O(n)
 * - 内层while循环只对序列的起始元素执行，总共O(n)
 * - 总体时间复杂度为O(n)
 *
 * 空间复杂度：O(n)
 * - 使用HashSet存储所有数组元素：O(n)
 */
public class LongestConsecutive128 {
    public static void main(String[] args) {
        // 创建 Scanner 对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入数组
        System.out.println("输入数组：");
        // 读取一行输入
        String line = scanner.nextLine();
        // 按空格分割字符串得到字符串数组
        String[] strs = line.split(" ");
        // 创建整型数组
        int[] nums = new int[strs.length];
        // 将字符串数组转换为整型数组
        for (int i = 0; i < strs.length; i++) {
            nums[i] = Integer.parseInt(strs[i]);
        }

        // 调用 longestConsect 方法查找最长连续序列长度
        int result = longestConsect(nums);
        // 输出结果
        System.out.println("最长连续序列长度为：" + result);
    }

    /**
     * 最长连续子序列
     * 在给定整数数组中找到数字连续的最长序列的长度
     *
     * 算法思路：
     * 1. 使用HashSet存储所有数字，实现O(1)查找和去重
     * 2. 对于每个数字，只有当它是序列起点时（num-1不存在）才开始计算序列长度
     * 3. 从起点开始向上查找连续数字，计算序列长度
     * 4. 记录并更新最长序列长度
     *
     * 示例过程（以数组 [100,4,200,1,3,2] 为例）：
     *
     * numSet = {100, 4, 200, 1, 3, 2}
     *
     * 遍历过程：
     * num=100: num-1=99不存在，是起点
     *          序列: 100，长度: 1
     * num=4:   num-1=3存在，不是起点，跳过
     * num=200: num-1=199不存在，是起点
     *          序列: 200，长度: 1
     * num=1:   num-1=0不存在，是起点
     *          序列: 1->2->3->4，长度: 4
     * num=3:   num-1=2存在，不是起点，跳过
     * num=2:   num-1=1存在，不是起点，跳过
     *
     * 最长序列: [1,2,3,4]，长度: 4
     *
     * @param nums 输入的整数数组
     * @return 最长连续序列的长度
     */
    public static int longestConsect(int[] nums) {
        // 使用HashSet存储所有数字，用于O(1)时间复杂度的查找，并去重
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        // 记录最长连续序列的长度
        int longestCount = 0;

        // 遍历HashSet中的每个数字
        for (int num : numSet) {
            // 只有当num-1不存在时，num才是一个序列的起点
            // 这样避免了重复计算序列的一部分
            if (!numSet.contains(num - 1)) {
                // 当前序列的起始数字
                int currentNum = num;
                // 当前序列的长度，初始为1（包含起始数字）
                int currentCount = 1;

                // 向上查找连续的数字
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentCount++;
                }

                // 更新最长序列长度
                longestCount = Math.max(longestCount, currentCount);
            }
        }

        return longestCount;
    }
}
