package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 单词搜索（LeetCode 79）
 *
 * 时间复杂度：O(M×N×4^L)
 * - M和N是网格的行数和列数
 * - L是单词长度
 * - 最坏情况下需要从每个单元格开始进行深度为L的搜索
 *
 * 空间复杂度：O(L)
 * - 递归调用栈深度最大为L（单词长度）
 * - 使用原地修改标记已访问单元格，不需要额外空间
 */
public class SearchExist79 {

    private char[][] board;
    private String word;
    private int rows, cols;

    public static void main(String[] args) {
        // 创建 Scanner 对象用于输入
        // Scanner scanner = new Scanner(System.in) 创建Scanner对象用于读取输入
        Scanner scanner = new Scanner(System.in);

        // 输入二维字符网格的行数和列数
        // System.out.print("请输入网格的行数 m 和列数 n（用空格分隔）：") 打印输入提示
        System.out.print("请输入网格的行数 m 和列数 n（用空格分隔）：");
        // int m = scanner.nextInt() 读取行数
        int m = scanner.nextInt();
        // int n = scanner.nextInt() 读取列数
        int n = scanner.nextInt();
        // scanner.nextLine() 处理换行符
        scanner.nextLine(); // 处理换行符

        // 输入二维字符网格
        // char[][] board = new char[m][n] 创建字符网格
        char[][] board = new char[m][n];
        // System.out.println("请输入网格的字符（每行输入一个字符串）：") 打印输入提示
        System.out.println("请输入网格的字符（每行输入一个字符串）：");
        // for (int i = 0; i < m; i++) 循环读取每行字符
        for (int i = 0; i < m; i++) {
            // String line = scanner.nextLine() 读取一行输入
            String line = scanner.nextLine();
            // board[i] = line.toCharArray() 将字符串转换为字符数组
            board[i] = line.toCharArray();
        }

        // 输入要查找的单词
        // System.out.print("请输入要查找的单词：") 打印输入提示
        System.out.print("请输入要查找的单词：");
        // String word = scanner.nextLine() 读取要查找的单词
        String word = scanner.nextLine();

        // 创建 Solution 对象并检查单词是否存在
        // SearchExist79 solution = new SearchExist79() 创建SearchExist79对象
        SearchExist79 solution = new SearchExist79();
        // boolean exists = solution.exist(board, word) 调用exist方法检查单词是否存在
        boolean exists = solution.exist(board, word);

        // 输出结果
        // System.out.println("单词 " + word + (exists ? " 存在于网格中。" : " 不存在于网格中。")) 打印结果
        System.out.println("单词 " + word + (exists ? " 存在于网格中。" : " 不存在于网格中。"));
    }

    /**
     * 主方法，检查单词是否存在于网格中
     *
     * 算法思路：
     * 使用回溯算法（深度优先搜索）在网格中搜索单词
     * 1. 遍历网格中的每个单元格作为起始点
     * 2. 从每个起始点开始进行深度优先搜索
     * 3. 搜索过程中标记已访问的单元格避免重复使用
     * 4. 如果找到完整单词返回true，否则继续搜索
     *
     * 执行过程分析（以`board=[['A','B','C','E'],['S','F','C','S'],['A','D','E','E']]`, `word="ABCCED"`为例）：
     *
     * 网格结构：
     * A B C E
     * S F C S
     * A D E E
     *
     * 搜索过程：
     * 1. 从(0,0)开始，`board[0][0]='A'` == `word[0]='A'`
     *    - 标记(0,0)为已访问：'#' B C E
     *    - 搜索相邻位置，找到(0,1)='B' == word[1]='B'
     *      - 标记(0,1)为已访问：'#' '#' C E
     *      - 搜索相邻位置，找到(0,2)='C' == word[2]='C'
     *        - 标记(0,2)为已访问：'#' '#' '#' E
     *        - 搜索相邻位置，找到(1,2)='C' == word[3]='C'
     *          - 标记(1,2)为已访问：'#' '#' '#' E
     *                                '#' '#' '#' S
     *          - 搜索相邻位置，找到(2,2)='E' == word[4]='E'
     *            - 标记(2,2)为已访问：'#' '#' '#' E
     *                                  '#' '#' '#' S
     *                                  '#' '#' '#' E
     *            - 搜索相邻位置，找到(2,1)='D' == word[5]='D'
     *              - index=6 == word.length()=6，找到完整单词"ABCCED"
     *              - 返回true
     *
     * 时间复杂度分析：
     * - 外层双重循环：O(M×N)
     * - 每次DFS搜索：O(4^L)
     * - 总时间复杂度：O(M×N×4^L)
     *
     * 空间复杂度分析：
     * - 递归调用栈：O(L)
     * - 原地修改标记：O(1)
     * - 总空间复杂度：O(L)
     *
     * @param board 二维字符网格
     * @param word 要查找的单词
     * @return 如果单词存在于网格中返回true，否则返回false
     */
    public boolean exist(char[][] board, String word) {
        // this.board = board 初始化board成员变量
        this.board = board;
        // this.word = word 初始化word成员变量
        this.word = word;
        // this.rows = board.length 初始化rows成员变量
        this.rows = board.length;
        // this.cols = board[0].length 初始化cols成员变量
        this.cols = board[0].length;

        // 遍历每个单元格作为起始点
        // for (int i = 0; i < rows; i++) 外层循环遍历行
        for (int i = 0; i < rows; i++) {
            // for (int j = 0; j < cols; j++) 内层循环遍历列
            for (int j = 0; j < cols; j++) {
                // if (dfs(i, j, 0)) 调用dfs方法从当前位置开始搜索
                if (dfs(i, j, 0)) { // 从当前位置开始 DFS 搜索
                    // return true 找到单词返回true
                    return true; // 找到单词
                }
            }
        }
        // return false 未找到单词返回false
        return false; // 未找到单词
    }

    /**
     * 深度优先搜索方法
     *
     * 算法思路：
     * 从指定位置开始深度优先搜索，匹配单词的剩余部分
     *
     * @param i 当前行索引
     * @param j 当前列索引
     * @param index 当前匹配的单词字符索引
     * @return 如果从当前位置能匹配剩余单词返回true，否则返回false
     */
    private boolean dfs(int i, int j, int index) {
        // 如果当前索引等于单词长度，表示找到完整单词
        // 这是递归的终止条件
        // if (index == word.length()) 检查是否已匹配完整个单词
        if (index == word.length()) {
            // return true 返回true表示找到完整单词
            return true;
        }

        // 检查边界条件和字符匹配
        // 剪枝：如果越界或者字符不匹配，直接返回false
        // if (i < 0 || i >= rows || j < 0 || j >= cols || board[i][j] != word.charAt(index)) 检查边界和字符匹配
        if (i < 0 || i >= rows || j < 0 || j >= cols || board[i][j] != word.charAt(index)) {
            // return false 返回false表示不匹配
            return false;
        }

        // 保存当前字符并标记为已访问
        // 使用特殊字符'#'标记已访问单元格，避免在同一次搜索中重复使用
        // char temp = board[i][j] 保存当前字符
        char temp = board[i][j];
        // board[i][j] = '#' 使用特殊字符标记已访问单元格
        board[i][j] = '#'; // 使用特殊字符标记已访问单元格

        // 进行上下左右四个方向的搜索
        // 递归搜索相邻单元格，匹配单词的下一个字符
        // boolean found = dfs(i + 1, j, index + 1) || dfs(i - 1, j, index + 1) || dfs(i, j + 1, index + 1) || dfs(i, j - 1, index + 1) 四个方向搜索
        boolean found = dfs(i + 1, j, index + 1) || // 向下搜索
                dfs(i - 1, j, index + 1) || // 向上搜索
                dfs(i, j + 1, index + 1) || // 向右搜索
                dfs(i, j - 1, index + 1);   // 向左搜索

        // 恢复当前单元格的字符
        // 回溯：恢复单元格的原始字符，以便其他搜索路径使用
        // board[i][j] = temp 恢复访问状态
        board[i][j] = temp; // 恢复访问状态
        // return found 返回搜索结果
        return found; // 返回搜索结果
    }

    /**
     * 方法2：使用额外的visited数组记录访问状态
     *
     * 算法思路：
     * 使用额外的布尔数组记录访问状态，避免修改原数组
     *
     * 时间复杂度分析：
     * - 外层双重循环：O(M×N)
     * - 每次DFS搜索：O(4^L)
     * - 总时间复杂度：O(M×N×4^L)
     *
     * 空间复杂度分析：
     * - 递归调用栈：O(L)
     * - visited数组：O(M×N)
     * - 总空间复杂度：O(M×N)
     *
     * @param board 二维字符网格
     * @param word 要查找的单词
     * @return 如果单词存在于网格中返回true，否则返回false
     */
    public boolean existWithVisited(char[][] board, String word) {
        // if (board == null || board.length == 0 || word == null) 检查边界条件
        if (board == null || board.length == 0 || word == null) {
            // return false 返回false
            return false;
        }

        // int rows = board.length 获取行数
        int rows = board.length;
        // int cols = board[0].length 获取列数
        int cols = board[0].length;
        // boolean[][] visited = new boolean[rows][cols] 创建访问状态数组
        boolean[][] visited = new boolean[rows][cols];

        // for (int i = 0; i < rows; i++) 外层循环遍历行
        for (int i = 0; i < rows; i++) {
            // for (int j = 0; j < cols; j++) 内层循环遍历列
            for (int j = 0; j < cols; j++) {
                // if (dfsWithVisited(board, word, 0, i, j, visited)) 调用dfsWithVisited方法从当前位置开始搜索
                if (dfsWithVisited(board, word, 0, i, j, visited)) {
                    // return true 找到单词返回true
                    return true;
                }
            }
        }

        // return false 未找到单词返回false
        return false;
    }

    /**
     * 使用visited数组的DFS方法
     *
     * 算法思路：
     * 使用visited数组记录访问状态的深度优先搜索实现
     *
     * @param board 二维字符网格
     * @param word 要查找的单词
     * @param index 当前匹配的单词字符索引
     * @param i 当前行索引
     * @param j 当前列索引
     * @param visited 访问状态数组
     * @return 如果从当前位置能匹配剩余单词返回true，否则返回false
     */
    private boolean dfsWithVisited(char[][] board, String word, int index, int i, int j, boolean[][] visited) {
        // if (index == word.length()) 检查是否已匹配完整个单词
        if (index == word.length()) {
            // return true 返回true表示找到完整单词
            return true;
        }

        // if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j] || board[i][j] != word.charAt(index)) 检查边界、访问状态和字符匹配
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length ||
            visited[i][j] || board[i][j] != word.charAt(index)) {
            // return false 返回false表示不匹配
            return false;
        }

        // visited[i][j] = true 标记当前位置为已访问
        visited[i][j] = true;

        // boolean found = dfsWithVisited(board, word, index + 1, i + 1, j, visited) || dfsWithVisited(board, word, index + 1, i - 1, j, visited) || dfsWithVisited(board, word, index + 1, i, j + 1, visited) || dfsWithVisited(board, word, index + 1, i, j - 1, visited) 四个方向搜索
        boolean found = dfsWithVisited(board, word, index + 1, i + 1, j, visited) ||
                       dfsWithVisited(board, word, index + 1, i - 1, j, visited) ||
                       dfsWithVisited(board, word, index + 1, i, j + 1, visited) ||
                       dfsWithVisited(board, word, index + 1, i, j - 1, visited);

        // visited[i][j] = false 回溯，标记当前位置为未访问
        visited[i][j] = false; // 回溯

        // return found 返回搜索结果
        return found;
    }
}
