package com.funian.algorithm.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 螺旋矩阵
 *
 * 时间复杂度：O(m × n)
 * - 需要遍历矩阵中的每个元素一次
 * - 总共m×n个元素，时间复杂度为O(m × n)
 *
 * 空间复杂度：O(1)
 * - 不考虑返回结果列表，只使用了常数级别的额外空间
 * - 使用四个边界变量控制遍历过程
 */
public class SpiralOrderMatrix54 {
    public static void main(String[] args) {
        // 创建 Scanner 对象读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 输入矩阵的行和列
        System.out.print("请输入矩阵的行数 m 和列数 n：");
        // 读取行数
        int m = scanner.nextInt();
        // 读取列数
        int n = scanner.nextInt();

        // 初始化矩阵
        int[][] matrix = new int[m][n];
        // 提示用户输入矩阵元素
        System.out.println("请输入矩阵的元素，每行用空格分隔：");
        // 读取矩阵元素
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // 调用 spiralOrder 方法按螺旋顺序遍历矩阵
        List<Integer> result = spiralOrder(matrix);

        // 输出结果
        System.out.println("螺旋顺序遍历的结果是：");
        // 遍历并打印结果
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println(); // 换行
    }

    /**
     * 螺旋顺序遍历矩阵
     * 按照顺时针螺旋顺序返回矩阵中的所有元素
     *
     * 算法思路：
     * 使用四个边界变量(top, bottom, left, right)控制遍历过程
     * 1. 从左到右遍历上边界
     * 2. 从上到下遍历右边界
     * 3. 从右到左遍历下边界
     * 4. 从下到上遍历左边界
     * 5. 每遍历完一边，相应的边界向内收缩
     * 6. 重复直到所有元素都被遍历
     *
     * 示例过程（以矩阵 [[1,2,3],[4,5,6],[7,8,9]] 为例）：
     *
     * 原矩阵:
     * [1 2 3]
     * [4 5 6]
     * [7 8 9]
     *
     * 初始边界: top=0, bottom=2, left=0, right=2
     *
     * 第1轮:
     * 1. 从左到右遍历上边界(top=0): 1 2 3
     *    top++ → top=1
     * 2. 从上到下遍历右边界(right=2): 6 9
     *    right-- → right=1
     * 3. 从右到左遍历下边界(bottom=2): 8 7
     *    bottom-- → bottom=1
     * 4. 从下到上遍历左边界(left=0): 4
     *    left++ → left=1
     *
     * 第2轮:
     * 1. 从左到右遍历上边界(top=1): 5
     *    top++ → top=2
     *
     * 边界条件: top(2) > bottom(1)，结束遍历
     *
     * 最终结果: [1, 2, 3, 6, 9, 8, 7, 4, 5]
     *
     * @param matrix 输入的二维整数矩阵
     * @return 按螺旋顺序排列的元素列表
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        // 存储螺旋遍历结果的列表
        List<Integer> result = new ArrayList<>();

        // 边界条件检查
        if (matrix == null || matrix.length == 0) {
            return result;
        }

        // 获取矩阵的行数和列数
        int m = matrix.length;
        int n = matrix[0].length;
        // 定义四个边界：上、下、左、右
        int top = 0, bottom = m - 1;
        int left = 0, right = n - 1;

        // 模拟螺旋顺序遍历
        while (top <= bottom && left <= right) {
            // 从左到右遍历上边界
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;  // 上边界下移

            // 从上到下遍历右边界
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;  // 右边界左移

            // 从右到左遍历下边界（检查是否还有行需要遍历）
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;  // 下边界上移
            }

            // 从下到上遍历左边界（检查是否还有列需要遍历）
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;  // 左边界右移
            }
        }

        return result;
    }
}
