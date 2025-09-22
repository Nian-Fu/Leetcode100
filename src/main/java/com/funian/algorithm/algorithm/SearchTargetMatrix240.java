package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 搜索二维矩阵 II
 *
 * 时间复杂度：O(m + n)
 * - 最多遍历 m 行和 n 列
 * - 每次比较后要么行增加，要么列减少
 * - 总的移动步数最多为 m + n
 *
 * 空间复杂度：O(1)
 * - 只使用了常数级别的额外空间
 * - 没有使用与输入矩阵大小相关的额外存储空间
 */
public class SearchTargetMatrix240 {
    public static void main(String[] args) {
        // 创建 Scanner 对象读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 输入矩阵的大小
        System.out.print("请输入矩阵的行数 m：");
        // 读取行数
        int m = scanner.nextInt();
        System.out.print("请输入矩阵的列数 n：");
        // 读取列数
        int n = scanner.nextInt();

        // 初始化矩阵
        int[][] matrix = new int[m][n];
        // 提示用户输入矩阵元素
        System.out.println("请输入矩阵的元素：");
        // 读取矩阵元素
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // 输入目标值
        System.out.print("请输入目标值 target：");
        // 读取目标值
        int target = scanner.nextInt();

        // 调用 searchMatrix 方法查找目标值
        boolean result = searchMatrix(matrix, target);

        // 输出结果
        if (result) {
            System.out.println("目标值存在于矩阵中。");
        } else {
            System.out.println("目标值不存在于矩阵中。");
        }
    }

    /**
     * 在有序二维矩阵中搜索目标值
     * 矩阵满足以下条件：
     * 1. 每行的元素从左到右升序排列
     * 2. 每列的元素从上到下升序排列
     *
     * 算法思路：
     * 从矩阵的右上角开始搜索，利用矩阵的有序性质进行剪枝
     * 1. 从右上角开始，该位置是当前行的最大值，当前列的最小值
     * 2. 如果当前元素等于目标值，找到目标
     * 3. 如果当前元素大于目标值，说明目标值不可能在当前列，向左移动
     * 4. 如果当前元素小于目标值，说明目标值不可能在当前行，向下移动
     *
     * 示例过程（以矩阵 [[1,4,7,11],[2,5,8,12],[3,6,9,16],[10,13,14,17]]，target=5 为例）：
     *
     * 矩阵:
     * [ 1  4  7 11]
     * [ 2  5  8 12]
     * [ 3  6  9 16]
     * [10 13 14 17]
     *
     * 初始位置: row=0, col=3, matrix[0][3]=11
     *
     * 步骤1: matrix[0][3]=11 > target=5，向左移动
     *        row=0, col=2, matrix[0][2]=7
     *
     * 步骤2: matrix[0][2]=7 > target=5，向左移动
     *        row=0, col=1, matrix[0][1]=4
     *
     * 步骤3: matrix[0][1]=4 < target=5，向下移动
     *        row=1, col=1, matrix[1][1]=5
     *
     * 步骤4: matrix[1][1]=5 = target=5，找到目标
     *
     * 最终结果: true
     *
     * @param matrix 输入的二维整数矩阵
     * @param target 要搜索的目标值
     * @return 如果目标值存在于矩阵中返回true，否则返回false
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        // 获取矩阵的行数和列数
        int m = matrix.length;
        int n = matrix[0].length;

        // 从矩阵的右上角开始搜索
        int row = 0;
        int col = n - 1;

        // 当行和列都在合理范围内时，进行搜索
        while (row < m && col >= 0) {
            if (matrix[row][col] == target) {
                // 找到目标值
                return true;
            } else if (matrix[row][col] > target) {
                // 当前元素大于目标值，向左移动一列
                col--;
            } else {
                // 当前元素小于目标值，向下移动一行
                row++;
            }
        }

        // 如果遍历完整个矩阵仍未找到目标值，返回 false
        return false;
    }
}
