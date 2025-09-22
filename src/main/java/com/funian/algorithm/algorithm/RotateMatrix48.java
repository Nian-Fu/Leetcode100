package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 旋转图像
 *
 * 时间复杂度：O(n²)
 * - 矩阵转置需要遍历上三角矩阵：O(n²/2)
 * - 反转每一行需要遍历矩阵的一半：O(n²/2)
 * - 总时间复杂度为O(n²)
 *
 * 空间复杂度：O(1)
 * - 原地旋转，只使用了常数级别的额外空间
 * - 没有使用与输入矩阵大小相关的额外存储空间
 */
public class RotateMatrix48 {
    public static void main(String[] args) {
        // 创建 Scanner 对象读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 输入矩阵的大小 n
        System.out.print("请输入矩阵的大小 n：");
        // 读取矩阵大小
        int n = scanner.nextInt();

        // 初始化矩阵
        int[][] matrix = new int[n][n];
        // 提示用户输入矩阵元素
        System.out.println("请输入矩阵的元素：");
        // 读取矩阵元素
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // 调用 rotate 方法将矩阵顺时针旋转90度
        rotate(matrix);

        // 输出旋转后的矩阵
        System.out.println("旋转后的矩阵是：");
        // 遍历并打印旋转后的矩阵
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 将矩阵顺时针旋转90度
     *
     * 算法思路：
     * 通过两个步骤实现旋转：
     * 1. 矩阵转置（沿主对角线翻转）
     * 2. 反转每一行
     *
     * 数学原理：
     * 矩阵顺时针旋转90度的变换公式：
     * 原位置(i,j) → 新位置(j, n-1-i)
     *
     * 示例过程（以矩阵 [[1,2,3],[4,5,6],[7,8,9]] 为例）：
     *
     * 原矩阵:
     * [1 2 3]
     * [4 5 6]
     * [7 8 9]
     *
     * 步骤1 - 矩阵转置（沿主对角线翻转）:
     * [1 4 7]
     * [2 5 8]
     * [3 6 9]
     *
     * 步骤2 - 反转每一行:
     * [7 4 1]
     * [8 5 2]
     * [9 6 3]
     *
     * 最终结果（顺时针旋转90度）:
     * [7 4 1]
     * [8 5 2]
     * [9 6 3]
     *
     * @param matrix 输入的n×n二维整数矩阵
     */
    public static void rotate(int[][] matrix) {
        // 获取矩阵大小
        int n = matrix.length;

        // 第一步：矩阵转置（沿主对角线翻转）
        // 只需要处理上三角矩阵（j >= i），避免重复交换
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // 交换 matrix[i][j] 和 matrix[j][i]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 第二步：反转每一行
        for (int i = 0; i < n; i++) {
            // 只需要交换前半部分和后半部分的元素
            for (int j = 0; j < n / 2; j++) {
                // 交换 matrix[i][j] 和 matrix[i][n-1-j]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }
}
