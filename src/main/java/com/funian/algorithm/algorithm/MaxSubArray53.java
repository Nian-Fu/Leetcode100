package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 最大子数组和
 *
 * 时间复杂度：O(n)
 * - 只需要遍历数组一次
 * - 每次迭代都进行常数时间的操作
 *
 * 空间复杂度：O(1)
 * - 只使用了常数级别的额外空间
 * - 没有使用与输入数组大小相关的额外存储空间
 */
public class MaxSubArray53 {
    public static void main(String[] args) {
        // 创建 Scanner 对象读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入数组
        System.out.println("请输入整数数组（用空格分隔）：");
        // 读取输入的数组
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

        // 调用 maxSubArray 方法计算最大子数组和
        int result = maxSubArray(nums);

        // 输出结果
        System.out.println("最大子数组和为：" + result);
    }

    /**
     * 使用动态规划方法求解最大子数组和（Kadane算法）
     *
     * 算法思路：
     * 1. 对于每个元素，我们决定是将其加入到之前的子数组中，还是从当前元素重新开始
     * 2. 状态转移方程：dp[i] = max(nums[i], dp[i-1] + nums[i])
     * 3. 其中 dp[i] 表示以第 i 个元素结尾的最大子数组和
     * 4. 由于只需要前一个状态，可以使用一个变量代替整个 dp 数组
     *
     * 示例过程（以数组 [-2,1,-3,4,-1,2,1,-5,4] 为例）：
     * 索引:  0   1  2   3  4  5  6   7  8
     * 元素: -2   1 -3   4 -1  2  1  -5  4
     *
     * i=0: currentSum=-2, maxSum=-2
     * i=1: currentSum=max(1, -2+1)=1, maxSum=max(-2,1)=1
     * i=2: currentSum=max(-3, 1-3)=-2, maxSum=max(1,-2)=1
     * i=3: currentSum=max(4, -2+4)=4, maxSum=max(1,4)=4
     * i=4: currentSum=max(-1, 4-1)=3, maxSum=max(4,3)=4
     * i=5: currentSum=max(2, 3+2)=5, maxSum=max(4,5)=5
     * i=6: currentSum=max(1, 5+1)=6, maxSum=max(5,6)=6
     * i=7: currentSum=max(-5, 6-5)=1, maxSum=max(6,1)=6
     * i=8: currentSum=max(4, 1+4)=5, maxSum=max(6,5)=6
     *
     * 结果：最大子数组和为6，对应子数组[4,-1,2,1]
     *
     * @param nums 输入的整数数组
     * @return 最大子数组和
     */
    public static int maxSubArray(int[] nums) {
        // 初始化最大和为第一个元素
        int maxSum = nums[0];
        // 当前子数组的最大和初始化为第一个元素
        int currentSum = nums[0];

        // 从第二个元素开始遍历数组
        for (int i = 1; i < nums.length; i++) {
            // 当前子数组和为当前元素或加上前一个子数组和的较大值
            // 这表示我们决定是继续之前的子数组还是从当前元素重新开始
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            // 更新全局最大和
            maxSum = Math.max(maxSum, currentSum);
        }

        // 返回最大子数组和
        return maxSum;
    }
}
