package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 缺失的第一个正数
 *
 * 时间复杂度：O(n)
 * - 第一个循环最多执行 n 次交换操作（每个元素最多被放置到正确位置一次）
 * - 第二个循环遍历数组：O(n)
 * - 总时间复杂度：O(n)
 *
 * 空间复杂度：O(1)
 * - 原地操作，只使用了常数级别的额外空间
 */
public class FirstMissingPositive41 {
    public static void main(String[] args) {
        // 创建 Scanner 对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 读取输入的数组
        System.out.print("请输入数组元素，以空格分隔：");
        // 读取一行输入
        String line = scanner.nextLine();
        // 按空格分割字符串得到字符串数组
        String[] str = line.split(" ");
        // 获取数组长度
        int n = str.length;
        // 创建整型数组
        int[] nums = new int[n];
        // 将字符串数组转换为整型数组
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(str[i]);
        }

        // 调用 firstMissingPositive 方法计算缺失的第一个正数
        int result = firstMissingPositive(nums);

        // 输出结果
        System.out.println("数组中缺失的最小正整数是：" + result);
    }

    /**
     * 找到数组中缺失的第一个正整数
     *
     * 算法思路：
     * 使用原地哈希的思想，将数组本身作为哈希表
     * 1. 对于长度为 n 的数组，缺失的第一个正整数一定在 [1, n+1] 范围内
     * 2. 将每个数字放到它应该在的位置上（数字 i 放到索引 i-1 的位置）
     * 3. 遍历数组找到第一个不在正确位置的数字
     *
     * 示例过程（以数组 [3,4,-1,1] 为例）：
     * 数组: [3, 4, -1, 1]
     * 索引:  0  1   2  3
     *
     * 步骤1 - 将数字放到正确位置:
     * i=0: nums[0]=3, 应该放在索引2, 交换nums[0]和nums[2]
     *      [-1, 4, 3, 1]
     * i=0: nums[0]=-1, 不在[1,4]范围内, 跳过
     * i=1: nums[1]=4, 应该放在索引3, 交换nums[1]和nums[3]
     *      [-1, 1, 3, 4]
     * i=1: nums[1]=1, 应该放在索引0, 交换nums[1]和nums[0]
     *      [1, -1, 3, 4]
     * i=1: nums[1]=-1, 不在[1,4]范围内, 跳过
     * i=2: nums[2]=3, 已经在正确位置, 跳过
     * i=3: nums[3]=4, 已经在正确位置, 跳过
     *
     * 最终数组: [1, -1, 3, 4]
     *
     * 步骤2 - 找到第一个不匹配的位置:
     * i=0: nums[0]=1, 匹配 i+1=1
     * i=1: nums[1]=-1, 不匹配 i+1=2, 返回 2
     *
     * 结果: 2
     *
     * @param nums 输入的整数数组
     * @return 缺失的第一个正整数
     */
    public static int firstMissingPositive(int[] nums) {
        // 获取数组长度
        int n = nums.length;

        // 1. 将数字放到正确的位置
        // 对于每个位置，如果该位置的数字在[1,n]范围内且不在正确位置，则交换到正确位置
        for (int i = 0; i < n; i++) {
            // 当前数字在有效范围内(1到n) 且 不在正确位置 且 正确位置上的数字不等于当前数字时进行交换
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                // 交换 nums[i] 和 nums[nums[i] - 1]
                // 将数字 nums[i] 放到它应该在的位置 nums[i]-1
                swap(nums, i, nums[i] - 1);
            }
        }

        // 2. 找到第一个不匹配的数字
        // 正确情况下，索引i位置应该存放数字i+1
        for (int i = 0; i < n; i++) {
            // 如果索引i位置存放的不是i+1，则i+1就是缺失的第一个正整数
            if (nums[i] != i + 1) {
                return i + 1; // 返回缺失的最小正整数
            }
        }

        // 3. 如果所有位置都匹配，说明数组包含[1,n]的所有正整数
        // 缺失的第一个正整数就是n+1
        return n + 1;
    }

    /**
     * 交换数组中两个位置的元素
     *
     * @param nums 数组
     * @param i 第一个位置
     * @param j 第二个位置
     */
    private static void swap(int[] nums, int i, int j) {
        // 临时变量存储第一个位置的值
        int temp = nums[i];
        // 将第二个位置的值赋给第一个位置
        nums[i] = nums[j];
        // 将临时变量的值赋给第二个位置
        nums[j] = temp;
    }
}
