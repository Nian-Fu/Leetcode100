package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * N皇后（LeetCode 51）
 *
 * 时间复杂度：O(N!)
 * - 第一行有N种选择，第二行最多N-1种选择，以此类推
 *
 * 空间复杂度：O(N)
 * - 递归调用栈深度为N
 * - 需要额外的数组存储皇后位置和棋盘状态
 */
public class SolveNQueues51 {

    /**
     * 解决N皇后问题
     *
     * 算法思路：
     * 使用回溯算法，逐行放置皇后
     * 1. 使用三个布尔数组记录列、主对角线、副对角线的占用情况
     * 2. 递归在每一行尝试放置皇后
     * 3. 检查当前位置是否与已放置的皇后冲突
     * 4. 找到解后构造棋盘并添加到结果中
     *
     * 执行过程分析（以`n=4`为例）：
     *
     * 递归树（部分）：
     *                    []
     *              /   /   \   \
     *           [0]  [1]   [2]  [3]  (第0行)
     *           /     |     |     \
     *        [0,2]  [1,3] [2,0] [3,1] (第1行)
     *         /       |     |      \
     *      [0,2,1]  [1,3,0][2,0,3] [3,1,2] (第2行)
     *         |       |     |       |
     *      [0,2,1,3][1,3,0,2][2,0,3,1][3,1,2,0] (第3行)
     *
     * 详细执行过程（以解[1,3,0,2]为例）：
     *
     * 1. row=0: 尝试col=0,1,2,3
     *    - col=0: 放置皇后(0,0)，标记列0、主对角线3、副对角线0为占用
     *    - row=1: 尝试col=0,1,2,3
     *      - col=0,1,2冲突，col=3可行
     *      - 放置皇后(1,3)，标记列3、主对角线4、副对角线4为占用
     *      - row=2: 尝试col=0,1,2,3
     *        - col=0可行
     *        - 放置皇后(2,0)，标记列0、主对角线2、副对角线2为占用
     *        - row=3: 尝试col=0,1,2,3
     *          - col=0,1,3冲突，col=2可行
     *          - 放置皇后(3,2)，标记列2、主对角线5、副对角线5为占用
     *          - row=4: 达到边界，找到解[0,3,0,2]
     *          - 构造棋盘并添加到结果
     *
     * 最终结果（n=4时）：
     * [[".Q..", "...Q", "Q...", "..Q."],
     *  ["..Q.", "Q...", "...Q", ".Q.."]]
     *
     * 时间复杂度分析：
     * - 回溯算法：O(N!)
     * - 每次放置皇后检查冲突：O(1)
     * - 构造棋盘：O(N²)
     * - 总时间复杂度：O(N!)
     *
     * 空间复杂度分析：
     * - 递归调用栈：O(N)
     * - queens数组：O(N)
     * - columns数组：O(N)
     * - diagonals1数组：O(N)
     * - diagonals2数组：O(N)
     * - result列表：O(N!)
     * - 总空间复杂度：O(N)
     *
     * @param n 皇后数量和棋盘大小
     * @return 所有解决方案的棋盘表示
     */
    public List<List<String>> solveNQueens(int n) {
        // List<List<String>> result = new ArrayList<>() 创建结果列表
        List<List<String>> result = new ArrayList<>();
        // 记录每行皇后的列位置
        // int[] queens = new int[n] 创建记录皇后列位置的数组
        int[] queens = new int[n];
        // 记录列的占用情况
        // boolean[] columns = new boolean[n] 创建列占用情况数组
        boolean[] columns = new boolean[n];
        // 记录主对角线的占用情况（row - col + n - 1）
        // boolean[] diagonals1 = new boolean[2 * n - 1] 创建主对角线占用情况数组
        boolean[] diagonals1 = new boolean[2 * n - 1];
        // 记录副对角线的占用情况（row + col）
        // boolean[] diagonals2 = new boolean[2 * n - 1] 创建副对角线占用情况数组
        boolean[] diagonals2 = new boolean[2 * n - 1];

        // 开始回溯
        // backtrack(0, n, queens, columns, diagonals1, diagonals2, result) 调用回溯方法
        backtrack(0, n, queens, columns, diagonals1, diagonals2, result);

        // return result 返回结果
        return result;
    }

    /**
     * 回溯辅助方法
     *
     * 算法思路：
     * 递归地在每一行尝试放置皇后，并检查冲突
     *
     * @param row 当前处理的行
     * @param n 棋盘大小
     * @param queens 记录每行皇后的列位置
     * @param columns 列占用情况
     * @param diagonals1 主对角线占用情况
     * @param diagonals2 副对角线占用情况
     * @param result 存储所有解决方案的结果列表
     */
    private void backtrack(int row, int n, int[] queens, boolean[] columns,
                          boolean[] diagonals1, boolean[] diagonals2,
                          List<List<String>> result) {
        // 递归终止条件：所有行都已放置皇后
        // if (row == n) 检查是否已处理完所有行
        if (row == n) {
            // 构造棋盘并添加到结果中
            // result.add(generateBoard(queens, n)) 生成棋盘并添加到结果列表
            result.add(generateBoard(queens, n));
            // return 返回
            return;
        }

        // 在当前行尝试每个列位置
        // for (int col = 0; col < n; col++) 遍历当前行的所有列
        for (int col = 0; col < n; col++) {
            // 检查当前位置是否与已放置的皇后冲突
            // 列冲突检查
            // if (columns[col]) continue 检查列是否已被占用
            if (columns[col]) continue;
            // 主对角线冲突检查（row - col为常数）
            // if (diagonals1[row - col + n - 1]) continue 检查主对角线是否已被占用
            if (diagonals1[row - col + n - 1]) continue;
            // 副对角线冲突检查（row + col为常数）
            // if (diagonals2[row + col]) continue 检查副对角线是否已被占用
            if (diagonals2[row + col]) continue;

            // 做选择：在当前位置放置皇后
            // queens[row] = col 记录当前行皇后的列位置
            queens[row] = col;
            // columns[col] = true 标记列已被占用
            columns[col] = true;
            // diagonals1[row - col + n - 1] = true 标记主对角线已被占用
            diagonals1[row - col + n - 1] = true;
            // diagonals2[row + col] = true 标记副对角线已被占用
            diagonals2[row + col] = true;

            // 递归：处理下一行
            // backtrack(row + 1, n, queens, columns, diagonals1, diagonals2, result) 递归处理下一行
            backtrack(row + 1, n, queens, columns, diagonals1, diagonals2, result);

            // 撤销选择：回溯，重置状态
            // columns[col] = false 取消列的占用标记
            columns[col] = false;
            // diagonals1[row - col + n - 1] = false 取消主对角线的占用标记
            diagonals1[row - col + n - 1] = false;
            // diagonals2[row + col] = false 取消副对角线的占用标记
            diagonals2[row + col] = false;
        }
    }

    /**
     * 根据皇后位置构造棋盘
     *
     * 算法思路：
     * 根据记录的皇后位置生成棋盘的字符串表示
     *
     * @param queens 每行皇后的列位置
     * @param n 棋盘大小
     * @return 棋盘的字符串表示
     */
    private List<String> generateBoard(int[] queens, int n) {
        // List<String> board = new ArrayList<>() 创建棋盘列表
        List<String> board = new ArrayList<>();

        // for (int i = 0; i < n; i++) 遍历每一行
        for (int i = 0; i < n; i++) {
            // char[] row = new char[n] 创建行字符数组
            char[] row = new char[n];
            // Arrays.fill(row, '.') 填充行数组为'.'
            Arrays.fill(row, '.');
            // row[queens[i]] = 'Q' 在皇后位置放置'Q'
            row[queens[i]] = 'Q';
            // board.add(new String(row)) 将行添加到棋盘列表
            board.add(new String(row));
        }

        // return board 返回棋盘
        return board;
    }

    /**
     * 方法2：简化版（不使用额外数组记录位置）
     *
     * 算法思路：
     * 直接在棋盘上操作，每次放置皇后时检查冲突
     *
     * 时间复杂度分析：
     * - 回溯算法：O(N!)
     * - 每次放置皇后检查冲突：O(N)
     * - 构造棋盘：O(N²)
     * - 总时间复杂度：O(N!)
     *
     * 空间复杂度分析：
     * - 递归调用栈：O(N)
     * - board数组：O(N²)
     * - result列表：O(N!)
     * - 总空间复杂度：O(N²)
     *
     * @param n 皇后数量和棋盘大小
     * @return 所有解决方案的棋盘表示
     */
    public List<List<String>> solveNQueensSimple(int n) {
        // List<List<String>> result = new ArrayList<>() 创建结果列表
        List<List<String>> result = new ArrayList<>();
        // char[][] board = new char[n][n] 创建棋盘数组
        char[][] board = new char[n][n];

        // 初始化棋盘
        // for (int i = 0; i < n; i++) 遍历每一行
        for (int i = 0; i < n; i++) {
            // Arrays.fill(board[i], '.') 填充行数组为'.'
            Arrays.fill(board[i], '.');
        }

        // 开始回溯
        // backtrackSimple(0, n, board, result) 调用简化版回溯方法
        backtrackSimple(0, n, board, result);

        // return result 返回结果
        return result;
    }

    /**
     * 简化版回溯辅助方法
     *
     * 算法思路：
     * 在棋盘上直接操作，每次放置皇后前检查冲突
     *
     * @param row 当前处理的行
     * @param n 棋盘大小
     * @param board 棋盘状态
     * @param result 存储所有解决方案的结果列表
     */
    private void backtrackSimple(int row, int n, char[][] board, List<List<String>> result) {
        // if (row == n) 检查是否已处理完所有行
        if (row == n) {
            // 构造解决方案
            // List<String> solution = new ArrayList<>() 创建解决方案列表
            List<String> solution = new ArrayList<>();
            // for (int i = 0; i < n; i++) 遍历每一行
            for (int i = 0; i < n; i++) {
                // solution.add(new String(board[i])) 将行添加到解决方案列表
                solution.add(new String(board[i]));
            }
            // result.add(solution) 将解决方案添加到结果列表
            result.add(solution);
            // return 返回
            return;
        }

        // for (int col = 0; col < n; col++) 遍历当前行的所有列
        for (int col = 0; col < n; col++) {
            // if (isValid(board, row, col, n)) 检查当前位置是否可以放置皇后
            if (isValid(board, row, col, n)) {
                // 放置皇后
                // board[row][col] = 'Q' 在当前位置放置皇后
                board[row][col] = 'Q';

                // 递归处理下一行
                // backtrackSimple(row + 1, n, board, result) 递归处理下一行
                backtrackSimple(row + 1, n, board, result);

                // 回溯
                // board[row][col] = '.' 撤销皇后放置
                board[row][col] = '.';
            }
        }
    }

    /**
     * 检查当前位置是否可以放置皇后
     *
     * 算法思路：
     * 检查当前位置是否与已放置的皇后冲突
     *
     * @param board 棋盘状态
     * @param row 行位置
     * @param col 列位置
     * @param n 棋盘大小
     * @return 是否可以放置皇后
     */
    private boolean isValid(char[][] board, int row, int col, int n) {
        // 检查列
        // for (int i = 0; i < row; i++) 遍历当前列的前面行
        for (int i = 0; i < row; i++) {
            // if (board[i][col] == 'Q') 检查是否有皇后
            if (board[i][col] == 'Q') {
                // return false 返回false表示不能放置
                return false;
            }
        }

        // 检查主对角线（左上方向）
        // for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) 遍历左上对角线
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            // if (board[i][j] == 'Q') 检查是否有皇后
            if (board[i][j] == 'Q') {
                // return false 返回false表示不能放置
                return false;
            }
        }

        // 检查副对角线（右上方向）
        // for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) 遍历右上对角线
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            // if (board[i][j] == 'Q') 检查是否有皇后
            if (board[i][j] == 'Q') {
                // return false 返回false表示不能放置
                return false;
            }
        }

        // return true 返回true表示可以放置
        return true;
    }

    /**
     * 辅助方法：读取用户输入的N值
     *
     * 时间复杂度分析：
     * - 读取输入：O(1)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @return N值
     */
    public static int readN() {
        // Scanner scanner = new Scanner(System.in) 创建Scanner对象
        Scanner scanner = new Scanner(System.in);
        // System.out.print("请输入N值（N皇后问题）: ") 打印提示信息
        System.out.print("请输入N值（N皇后问题）: ");
        // return scanner.nextInt() 读取并返回N值
        return scanner.nextInt();
    }

    /**
     * 辅助方法：打印解决方案
     *
     * 时间复杂度分析：
     * - 遍历解决方案：O(N!)
     * - 打印每个解决方案：O(N²)
     * - 总时间复杂度：O(N! × N²)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param result 解决方案列表
     */
    public static void printSolutions(List<List<String>> result) {
        // System.out.println("共有 " + result.size() + " 个解决方案：") 打印解决方案数量
        System.out.println("共有 " + result.size() + " 个解决方案：");
        // for (int i = 0; i < result.size(); i++) 遍历所有解决方案
        for (int i = 0; i < result.size(); i++) {
            // System.out.println("解决方案 " + (i + 1) + ":") 打印解决方案编号
            System.out.println("解决方案 " + (i + 1) + ":");
            // for (String row : result.get(i)) 遍历解决方案的每一行
            for (String row : result.get(i)) {
                // System.out.println(row) 打印行
                System.out.println(row);
            }
            // System.out.println() 打印空行
            System.out.println();
        }
    }

    /**
     * 主函数：处理用户输入并解决N皇后问题
     */
    public static void main(String[] args) {
        // System.out.println("N皇后问题") 打印程序标题
        System.out.println("N皇后问题");

        // 读取用户输入的N值
        // int n = readN() 调用readN方法读取N值
        int n = readN();
        // System.out.println("解决 " + n + " 皇后问题：") 打印问题描述
        System.out.println("解决 " + n + " 皇后问题：");

        // 解决N皇后问题
        // SolveNQueues51 solution = new SolveNQueues51() 创建解决方案对象
        SolveNQueues51 solution = new SolveNQueues51();
        // List<List<String>> result1 = solution.solveNQueens(n) 调用solveNQueens方法
        List<List<String>> result1 = solution.solveNQueens(n);
        // List<List<String>> result2 = solution.solveNQueensSimple(n) 调用solveNQueensSimple方法
        List<List<String>> result2 = solution.solveNQueensSimple(n);

        // 输出结果
        // System.out.println("方法1结果：") 打印方法1结果标题
        System.out.println("方法1结果：");
        // printSolutions(result1) 调用printSolutions方法打印方法1结果
        printSolutions(result1);

        // System.out.println("方法2结果：") 打印方法2结果标题
        System.out.println("方法2结果：");
        // printSolutions(result2) 调用printSolutions方法打印方法2结果
        printSolutions(result2);
    }
}
