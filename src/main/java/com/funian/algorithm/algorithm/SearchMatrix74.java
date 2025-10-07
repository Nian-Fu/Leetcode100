package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 搜索二维矩阵（LeetCode 74）
 *
 * 时间复杂度：O(log(m*n))
 * - 将二维矩阵视为一维数组进行二分查找
 * - m是行数，n是列数
 *
 * 空间复杂度：O(1)
 * - 只使用了常数个额外变量
 */
public class SearchMatrix74 {

    /**
     * 主函数：处理用户输入并判断目标值是否在矩阵中
     *
     * 算法流程：
     * 1. 读取用户输入的矩阵和目标值
     * 2. 调用[searchMatrix](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/SearchMatrix74.java#L115-L153)方法判断目标值是否在矩阵中
     * 3. 输出结果
     */
    public static void main(String[] args) {
        // 创建 Scanner 对象用于获取用户输入
        // Scanner scanner = new Scanner(System.in) 创建Scanner对象用于读取输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入矩阵的行数和列数
        // System.out.print("请输入矩阵的行数 m 和列数 n（用空格分隔）：") 打印输入提示
        System.out.print("请输入矩阵的行数 m 和列数 n（用空格分隔）：");
        // int m = scanner.nextInt() 读取行数
        int m = scanner.nextInt();
        // int n = scanner.nextInt() 读取列数
        int n = scanner.nextInt();

        // 创建矩阵
        // int[][] matrix = new int[m][n] 创建二维矩阵
        int[][] matrix = new int[m][n];
        // System.out.println("请输入矩阵元素（每行元素用空格分隔）：") 打印矩阵元素输入提示
        System.out.println("请输入矩阵元素（每行元素用空格分隔）：");
        // for (int i = 0; i < m; i++) 外层循环遍历行
        for (int i = 0; i < m; i++) {
            // for (int j = 0; j < n; j++) 内层循环遍历列
            for (int j = 0; j < n; j++) {
                // matrix[i][j] = scanner.nextInt() 读取矩阵元素
                matrix[i][j] = scanner.nextInt(); // 读取矩阵元素
            }
        }

        // 提示用户输入目标值
        // System.out.print("请输入目标值：") 打印目标值输入提示
        System.out.print("请输入目标值：");
        // int target = scanner.nextInt() 读取目标值
        int target = scanner.nextInt();

        // 调用 searchMatrix 方法判断目标值是否在矩阵中
        // boolean result = searchMatrix(matrix, target) 调用searchMatrix方法计算结果
        boolean result = searchMatrix(matrix, target);

        // 输出结果
        // System.out.println("目标值 " + target + " 在矩阵中：" + result) 打印结果
        System.out.println("目标值 " + target + " 在矩阵中：" + result);
    }

    /**
     * 在二维矩阵中搜索目标值
     *
     * 算法思路：
     * 将二维矩阵视为一维有序数组，使用二分查找
     * 关键在于坐标转换：
     * - 一维索引index对应的二维坐标为：(index / cols, index % cols)
     * - 二维坐标(row, col)对应的一维索引为：row * cols + col
     *
     * 执行过程分析（以`matrix=[[1,3,5,7],[10,11,16,20],[23,30,34,60]]`, `target=3`为例）：
     *
     * 矩阵结构：
     * [1,  3,  5,  7]
     * [10, 11, 16, 20]
     * [23, 30, 34, 60]
     *
     * 转换为一维数组：[1,3,5,7,10,11,16,20,23,30,34,60]
     * 数组长度：12，索引范围：[0,11]
     *
     * 初始状态：
     * left = 0, right = 11
     *
     * 第一次查找：
     * mid = 0 + (11-0)/2 = 5
     * 二维坐标：(5/4, 5%4) = (1,1)
     * midValue = matrix[1][1] = 11
     * 11 > target=3
     * right = mid - 1 = 4
     *
     * 第二次查找：
     * mid = 0 + (4-0)/2 = 2
     * 二维坐标：(2/4, 2%4) = (0,2)
     * midValue = matrix[0][2] = 5
     * 5 > target=3
     * right = mid - 1 = 1
     *
     * 第三次查找：
     * mid = 0 + (1-0)/2 = 0
     * 二维坐标：(0/4, 0%4) = (0,0)
     * midValue = matrix[0][0] = 1
     * 1 < target=3
     * left = mid + 1 = 1
     *
     * 第四次查找：
     * mid = 1 + (1-1)/2 = 1
     * 二维坐标：(1/4, 1%4) = (0,1)
     * midValue = matrix[0][1] = 3
     * 3 == target=3
     * 返回true
     *
     * 时间复杂度分析：
     * - 二分查找：O(log(m*n))
     * - 每次迭代操作：O(1)
     * - 总时间复杂度：O(log(m*n))
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param matrix m×n的二维矩阵，每行从左到右升序排列，下一行第一个元素大于上一行最后一个元素
     * @param target 目标值
     * @return 如果目标值在矩阵中返回true，否则返回false
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        // 确保矩阵非空
        // 这是重要的边界条件检查
        // if (matrix.length == 0 || matrix[0].length == 0) 检查矩阵是否为空
        if (matrix.length == 0 || matrix[0].length == 0) {
            // return false 如果矩阵为空，返回 false
            return false; // 如果矩阵为空，返回 false
        }

        // 获取矩阵的行数和列数
        // int rows = matrix.length 获取行数
        int rows = matrix.length;    // 行数
        // int cols = matrix[0].length 获取列数
        int cols = matrix[0].length; // 列数

        // 设置二分查找的边界
        // int left = 0 初始化左边界（一维索引）
        int left = 0;                    // 左边界（一维索引）
        // int right = rows * cols - 1 初始化右边界（一维索引）
        int right = rows * cols - 1;     // 右边界（一维索引）

        // 使用二分查找
        // while (left <= right) 当左边界小于等于右边界时循环
        while (left <= right) {
            // 计算中间索引，避免整数溢出
            // int mid = left + (right - left) / 2 计算中间索引
            int mid = left + (right - left) / 2;

            // 将一维索引转换为二维坐标
            // mid / cols：行索引
            // mid % cols：列索引
            // int midValue = matrix[mid / cols][mid % cols] 获取中间元素值
            int midValue = matrix[mid / cols][mid % cols];

            // 找到目标值，返回 true
            // if (midValue == target) 检查中间元素是否等于目标值
            if (midValue == target) {
                // return true 返回true
                return true;
            }
            // 中间值小于目标值，向右半部分查找
            // else if (midValue < target) 检查中间元素是否小于目标值
            else if (midValue < target) {
                // left = mid + 1 更新左边界
                left = mid + 1;
            }
            // 中间值大于目标值，向左半部分查找
            else {
                // right = mid - 1 更新右边界
                right = mid - 1;
            }
        }

        // 如果未找到目标值，返回 false
        // return false 返回false
        return false;
    }

    /**
     * 方法2：两次二分查找解法
     *
     * 算法思路：
     * 1. 先在第一列中二分查找确定目标值可能在哪一行
     * 2. 再在该行中二分查找目标值
     *
     * 时间复杂度分析：
     * - 第一次二分查找：O(log m)
     * - 第二次二分查找：O(log n)
     * - 总时间复杂度：O(log m + log n) = O(log(m*n))
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param matrix m×n的二维矩阵
     * @param target 目标值
     * @return 如果目标值在矩阵中返回true，否则返回false
     */
    public boolean searchMatrixTwoBinarySearch(int[][] matrix, int target) {
        // if (matrix.length == 0 || matrix[0].length == 0) 检查矩阵是否为空
        if (matrix.length == 0 || matrix[0].length == 0) {
            // return false 返回false
            return false;
        }

        // int rows = matrix.length 获取行数
        int rows = matrix.length;
        // int cols = matrix[0].length 获取列数
        int cols = matrix[0].length;

        // 第一次二分查找：确定行
        // int top = 0 初始化上边界
        int top = 0;
        // int bottom = rows - 1 初始化下边界
        int bottom = rows - 1;

        // while (top <= bottom) 当上边界小于等于下边界时循环
        while (top <= bottom) {
            // int mid = top + (bottom - top) / 2 计算中间索引
            int mid = top + (bottom - top) / 2;
            // if (matrix[mid][0] == target) 检查当前行第一个元素是否等于目标值
            if (matrix[mid][0] == target) {
                // return true 返回true
                return true;
            } else if (matrix[mid][0] < target) {
                // top = mid + 1 更新上边界
                top = mid + 1;
            } else {
                // bottom = mid - 1 更新下边界
                bottom = mid - 1;
            }
        }

        // 如果目标值小于第一行第一个元素
        // if (bottom < 0) 检查是否小于第一行第一个元素
        if (bottom < 0) {
            // return false 返回false
            return false;
        }

        // 第二次二分查找：在确定的行中查找
        // int row = bottom 确定目标行
        int row = bottom;
        // int left = 0 初始化左边界
        int left = 0;
        // int right = cols - 1 初始化右边界
        int right = cols - 1;

        // while (left <= right) 当左边界小于等于右边界时循环
        while (left <= right) {
            // int mid = left + (right - left) / 2 计算中间索引
            int mid = left + (right - left) / 2;
            // if (matrix[row][mid] == target) 检查当前元素是否等于目标值
            if (matrix[row][mid] == target) {
                // return true 返回true
                return true;
            } else if (matrix[row][mid] < target) {
                // left = mid + 1 更新左边界
                left = mid + 1;
            } else {
                // right = mid - 1 更新右边界
                right = mid - 1;
            }
        }

        // return false 返回false
        return false;
    }

    /**
     * 方法3：从右上角开始搜索
     *
     * 算法思路：
     * 从矩阵右上角开始，利用矩阵的有序性质：
     * - 如果当前值小于目标值，向下移动
     * - 如果当前值大于目标值，向左移动
     *
     * 时间复杂度分析：
     * - 最多移动m+n步：O(m+n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param matrix m×n的二维矩阵
     * @param target 目标值
     * @return 如果目标值在矩阵中返回true，否则返回false
     */
    public boolean searchMatrixFromCorner(int[][] matrix, int target) {
        // if (matrix.length == 0 || matrix[0].length == 0) 检查矩阵是否为空
        if (matrix.length == 0 || matrix[0].length == 0) {
            // return false 返回false
            return false;
        }

        // int row = 0 初始化行索引
        int row = 0;
        // int col = matrix[0].length - 1 初始化列索引为最右列
        int col = matrix[0].length - 1;

        // while (row < matrix.length && col >= 0) 当行列索引有效时循环
        while (row < matrix.length && col >= 0) {
            // if (matrix[row][col] == target) 检查当前元素是否等于目标值
            if (matrix[row][col] == target) {
                // return true 返回true
                return true;
            } else if (matrix[row][col] < target) {
                // row++ 向下移动
                row++; // 向下移动
            } else {
                // col-- 向左移动
                col--; // 向左移动
            }
        }

        // return false 返回false
        return false;
    }
}
