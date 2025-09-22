package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 接雨水问题
 *
 * 时间复杂度：O(n)
 * - 使用双指针技术，每个元素最多被访问一次
 * - 总共需要遍历 n 个元素
 *
 * 空间复杂度：O(1)
 * - 只使用了常数级别的额外空间
 * - 没有使用与输入数组大小相关的额外存储空间
 */
public class TrapWater42 {
    public static void main(String[] args) {
        // 创建 Scanner 对象读取用户输入
        Scanner scanner = new Scanner(System.in);
        // 提示用户输入整数数组
        System.out.print("请输入柱子的高度数组（用空格分隔）：");
        // 读取用户输入的一行字符串
        String line = scanner.nextLine();
        // 通过空格分隔字符串
        String[] str = line.split(" ");
        // 获取输入的柱子数量
        int n = str.length;
        // 创建高度数组
        int[] height = new int[n];
        // 将每个字符串转换为整数并存入高度数组
        for (int i = 0; i < n; i++) {
            height[i] = Integer.parseInt(str[i]);
        }
        // 调用 trap 方法计算积水量
        int result = trap(height);
        // 输出结果
        System.out.println("可以接的雨水量为：" + result);
    }

    /**
     * 计算可以接住的雨水量
     * 使用双指针技术，从数组两端向中间遍历
     *
     * 算法思路：
     * 1. 使用两个指针分别从数组的两端开始
     * 2. 维护左侧最大高度和右侧最大高度
     * 3. 每次移动较短边的指针，因为积水高度由较短边决定
     * 4. 累加每个位置能够接住的雨水量
     *
     * 核心原理：
     * 每个位置能接的雨水量 = min(左侧最大高度, 右侧最大高度) - 当前位置高度
     * 使用双指针技巧，始终移动较短边的指针，因为我们知道该位置的积水只取决于较短边
     *
     * 示例过程（以数组 [0,1,0,2,1,0,1,3,2,1,2,1] 为例）：
     * 索引:  0 1 2 3 4 5 6 7 8 9 10 11
     * 高度:  0 1 0 2 1 0 1 3 2 1 2  1
     *
     * 初始状态: left=0, right=11, leftMax=0, rightMax=0, maxwater=0
     *
     * 步骤1: height[0]=0 < height[11]=1
     *        leftMax = max(0, 0) = 0
     *        积水 = leftMax - height[0] = 0 - 0 = 0
     *        maxwater = 0 + 0 = 0
     *        left++ → left=1
     *
     * 步骤2: height[1]=1 > height[11]=1
     *        rightMax = max(0, 1) = 1
     *        积水 = rightMax - height[11] = 1 - 1 = 0
     *        maxwater = 0 + 0 = 0
     *        right-- → right=10
     *
     * 步骤3: height[1]=1 < height[10]=2
     *        leftMax = max(0, 1) = 1
     *        积水 = leftMax - height[1] = 1 - 1 = 0
     *        maxwater = 0 + 0 = 0
     *        left++ → left=2
     *
     * 步骤4: height[2]=0 < height[10]=2
     *        leftMax = max(1, 0) = 1
     *        积水 = leftMax - height[2] = 1 - 0 = 1
     *        maxwater = 0 + 1 = 1
     *        left++ → left=3
     *
     * ...继续执行直到 left >= right
     *
     * @param height 表示柱子高度的数组
     * @return 可以接住的雨水总量
     */
    public static int trap(int[] height) {
        // 用于存储积水总量
        int maxwater = 0;
        // 初始化左右指针，分别指向数组的两端
        int left = 0, right = height.length - 1;
        // 左侧和右侧的最大高度初始化为0
        int leftMax = 0, rightMax = 0;

        // 当左右指针没有重合时继续循环
        while (left < right) {
            // 更新左侧和右侧最大高度
            leftMax = Math.max(leftMax, height[left]); // 更新左侧最大高度
            rightMax = Math.max(rightMax, height[right]); // 更新右侧最大高度

            // 如果左侧柱子的高度小于右侧柱子的高度
            if (height[left] < height[right]) {
                // 计算左侧当前柱子可以接住的雨水量，等于leftMax - height[left]
                maxwater += leftMax - height[left];
                // 左指针右移，处理下一个柱子
                left++;
            } else {
                // 右侧柱子更高或相等，计算右侧当前柱子可以接住的雨水量，等于rightMax - height[right]
                maxwater += rightMax - height[right];
                // 右指针左移，处理下一个柱子
                right--;
            }
        }

        // 返回最终的积水总量
        return maxwater;
    }
}
