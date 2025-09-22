package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 盛最多水的容器
 *
 * 时间复杂度：O(n)
 * - 使用双指针技术，两个指针总共最多移动 n 次
 * - 每次移动只进行常数时间的操作
 *
 * 空间复杂度：O(1)
 * - 只使用了常数级别的额外空间
 * - 没有使用与输入数组大小相关的额外存储空间
 */
public class MaxWater11 {
    public static void main(String[] args) {
        // 创建 Scanner 对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入高度数组
        System.out.println("输入高度数组：");
        // 读取一行输入
        String line = scanner.nextLine();
        // 按空格分割字符串得到字符串数组
        String[] strs = line.split(" ");
        // 获取数组长度
        int n = strs.length;
        // 创建整型数组存储高度
        int[] height = new int[n];
        // 将字符串数组转换为整型数组
        for (int i = 0; i < n; i++) {
            height[i] = Integer.parseInt(strs[i]);
        }

        // 调用 maxWater 方法计算最大容量
        int result = maxWater(height);
        // 输出结果
        System.out.println("最大容量为：" + result);
    }

    /**
     * 计算盛最多水的容器容量
     * 给定一个长度为 n 的整数数组 height，有 n 条垂线，
     * 第 i 条线的两个端点是 (i, 0) 和 (i, height[i])。
     * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *
     * @param height 输入的高度数组
     * @return 容器能盛的最大水量
     */
    public static int maxWater(int[] height) {
        // 获取数组长度
        int n = height.length;
        // 左指针，指向数组开始位置
        int left = 0;
        // 右指针，指向数组结束位置
        int right = n - 1;
        // 记录最大面积
        int maxArea = 0;

        // 双指针向中间移动，直到相遇
        while (left < right) {
            // 计算当前宽度（两个指针之间的距离）
            int width = right - left;
            // 计算当前高度（两个指针指向高度的较小值）
            int minHeight = Math.min(height[left], height[right]);
            // 计算当前面积
            int currentArea = width * minHeight;
            // 更新最大面积
            maxArea = Math.max(maxArea, currentArea);

            // 移动较短边的指针，因为这样才能可能找到更大的面积
            // 如果移动较长边，面积只会变小或不变
            if (height[left] < height[right]) {
                left++;  // 移动左指针
            } else {
                right--; // 移动右指针
            }
        }

        return maxArea;
    }
}
