package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

/**
 * 腐烂的橘子（LeetCode 994）
 *
 * 时间复杂度：O(M×N)
 * - M和N是网格的行数和列数
 * - 最坏情况下需要访问每个单元格常数次
 *
 * 空间复杂度：O(M×N)
 * - 队列最多存储所有单元格
 */
public class OrangesRotting994 {

    /**
     * 计算腐烂所有橘子所需的最短时间
     *
     * 算法思路：
     * 使用多源广度优先搜索（BFS）
     * 1. 首先将所有腐烂的橘子加入队列作为初始源点
     * 2. 同时进行BFS，每一轮代表一分钟
     * 3. 将新鲜橘子腐烂并加入队列
     * 4. 当队列为空时，检查是否还有新鲜橘子
     *
     * 执行过程分析（以grid=[[2,1,1],[1,1,0],[0,1,1]]为例）：
     *
     * 初始状态（分钟0）：
     * 2 1 1
     * 1 1 0
     * 0 1 1
     *
     * 队列初始化：[(0,0)]（初始腐烂橘子位置）
     * 新鲜橘子计数：6个
     *
     * 第1分钟：
     * 队列：[(0,0)]
     * 处理(0,0)：腐烂相邻的新鲜橘子(0,1)和(1,0)
     * 新状态：
     * 2 2 1
     * 2 1 0
     * 0 1 1
     * 队列：[(0,1), (1,0)]
     * 新鲜橘子：4个
     *
     * 第2分钟：
     * 队列：[(0,1), (1,0)]
     * 处理(0,1)：腐烂相邻的新鲜橘子(0,2)
     * 处理(1,0)：无新腐烂
     * 新状态：
     * 2 2 2
     * 2 1 0
     * 0 1 1
     * 队列：[(0,2)]
     * 新鲜橘子：3个
     *
     * 第3分钟：
     * 队列：[(0,2)]
     * 处理(0,2)：无新腐烂
     * 新状态不变
     * 队列：[]
     *
     * 继续处理剩余队列元素...
     *
     * 最终结果：4分钟（所有橘子腐烂）
     *
     * @param grid 二维网格，0表示空单元格，1表示新鲜橘子，2表示腐烂橘子
     * @return 腐烂所有橘子所需的最短分钟数，如果无法腐烂所有橘子返回-1
     */
    public int orangesRotting(int[][] grid) {
        // 边界条件检查：空网格
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return -1;
        }

        // 获取网格的行数和列数
        int rows = grid.length;
        int cols = grid[0].length;

        // 创建队列用于BFS，存储腐烂橘子的位置
        Queue<int[]> queue = new LinkedList<>();

        // 统计新鲜橘子的数量
        int freshCount = 0;

        // 初始化：将所有腐烂橘子加入队列，统计新鲜橘子数量
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 如果当前单元格是腐烂橘子
                if (grid[i][j] == 2) {
                    // 将腐烂橘子的位置加入队列作为BFS的起始点
                    queue.offer(new int[]{i, j});
                }
                // 如果当前单元格是新鲜橘子
                else if (grid[i][j] == 1) {
                    // 统计新鲜橘子数量
                    freshCount++;
                }
            }
        }

        // 如果没有新鲜橘子，直接返回0（无需腐烂时间）
        if (freshCount == 0) {
            return 0;
        }

        // 定义四个方向的偏移量：下、上、右、左
        // 用于检查当前橘子的相邻单元格
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // 记录腐烂所有橘子所需的分钟数
        int minutes = 0;

        // 多源BFS：同时从所有腐烂橘子开始腐烂过程
        while (!queue.isEmpty()) {
            // 获取当前层级（当前分钟）的腐烂橘子数量
            int size = queue.size();

            // 标记本轮是否有橘子腐烂
            // 用于判断是否需要增加时间计数
            boolean rotten = false;

            // 处理当前层级的所有腐烂橘子（同一分钟内腐烂的橘子）
            for (int i = 0; i < size; i++) {
                // 从队列中取出一个腐烂橘子的位置
                int[] current = queue.poll();
                int row = current[0];  // 当前行索引
                int col = current[1];  // 当前列索引

                // 检查四个方向的相邻单元格
                for (int[] dir : directions) {
                    // 计算相邻单元格的坐标
                    int newRow = row + dir[0];  // 新行索引
                    int newCol = col + dir[1];  // 新列索引

                    // 检查边界条件和是否为新鲜橘子
                    // 1. 新坐标不能越界
                    // 2. 相邻单元格必须是新鲜橘子（值为1）
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols &&
                        grid[newRow][newCol] == 1) {
                        // 腐烂新鲜橘子：将新鲜橘子标记为腐烂
                        grid[newRow][newCol] = 2;

                        // 将新腐烂的橘子加入队列，用于下一轮BFS
                        queue.offer(new int[]{newRow, newCol});

                        // 减少新鲜橘子计数
                        freshCount--;

                        // 标记本轮有橘子腐烂
                        rotten = true;
                    }
                }
            }

            // 如果本轮有橘子腐烂，时间加1分钟
            // 注意：只有当有新的橘子被腐烂时才增加时间
            if (rotten) {
                minutes++;
            }
        }

        // 检查是否还有新鲜橘子
        // 如果还有新鲜橘子，说明无法腐烂所有橘子，返回-1
        // 否则返回腐烂所有橘子所需的分钟数
        return freshCount == 0 ? minutes : -1;
    }

    /**
     * 方法2：简化版BFS
     *
     * @param grid 二维网格
     * @return 腐烂所有橘子所需的最短分钟数
     */
    public int orangesRottingSimple(int[][] grid) {
        // 边界条件检查
        if (grid == null || grid.length == 0) return 0;

        // 获取网格的行数和列数
        int rows = grid.length;
        int cols = grid[0].length;

        // 创建队列用于BFS
        Queue<int[]> queue = new LinkedList<>();

        // 统计新鲜橘子的数量
        int freshCount = 0;

        // 初始化：将所有腐烂橘子加入队列，统计新鲜橘子数量
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 如果当前单元格是腐烂橘子
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
                // 如果当前单元格是新鲜橘子
                else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        // 如果没有新鲜橘子，直接返回0
        if (freshCount == 0) return 0;

        // 定义四个方向的偏移量
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // 记录时间
        int time = 0;

        // BFS过程：当队列不为空且还有新鲜橘子时继续
        while (!queue.isEmpty() && freshCount > 0) {
            // 时间增加1分钟
            time++;

            // 获取当前层级的节点数量
            int size = queue.size();

            // 处理当前层级的所有节点
            for (int i = 0; i < size; i++) {
                // 从队列中取出一个节点
                int[] curr = queue.poll();

                // 检查四个方向
                for (int[] dir : directions) {
                    // 计算相邻单元格坐标
                    int x = curr[0] + dir[0];
                    int y = curr[1] + dir[1];

                    // 检查边界和是否为新鲜橘子
                    if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == 1) {
                        // 腐烂新鲜橘子
                        grid[x][y] = 2;

                        // 将新腐烂的橘子加入队列
                        queue.offer(new int[]{x, y});

                        // 减少新鲜橘子计数
                        freshCount--;
                    }
                }
            }
        }

        // 如果所有新鲜橘子都被腐烂，返回时间；否则返回-1
        return freshCount == 0 ? time : -1;
    }

    /**
     * 辅助方法：读取用户输入的网格
     *
     * @param rows 行数
     * @param cols 列数
     * @return 二维网格
     */
    public static int[][] readGrid(int rows, int cols) {
        // 创建Scanner对象用于读取输入
        Scanner scanner = new Scanner(System.in);

        // 创建二维数组存储网格数据
        int[][] grid = new int[rows][cols];

        // 提示用户输入网格元素
        System.out.println("请输入网格元素（0:空, 1:新鲜橘子, 2:腐烂橘子）：");

        // 逐行读取网格元素
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 读取每个单元格的值
                grid[i][j] = scanner.nextInt();
            }
        }

        // 返回读取的网格
        return grid;
    }

    /**
     * 辅助方法：打印网格
     *
     * @param grid 二维网格
     */
    public static void printGrid(int[][] grid) {
        // 遍历网格的每一行
        for (int[] row : grid) {
            // 遍历当前行的每个单元格
            for (int cell : row) {
                // 打印单元格的值
                System.out.print(cell + " ");
            }
            // 每行结束后换行
            System.out.println();
        }
    }

    /**
     * 主函数：处理用户输入并计算腐烂所有橘子的时间
     */
    public static void main(String[] args) {
        // 打印程序标题
        System.out.println("腐烂的橘子");

        // 创建Scanner对象用于读取输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入网格的行数和列数
        System.out.print("请输入网格行数和列数（用空格分隔）：");

        // 读取行数和列数
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();

        // 读取网格数据
        int[][] grid = readGrid(rows, cols);

        // 打印初始网格状态
        System.out.println("初始网格：");
        printGrid(grid);

        // 创建解决方案对象
        OrangesRotting994 solution = new OrangesRotting994();

        // 使用第一种方法计算腐烂时间
        int result1 = solution.orangesRotting(grid);

        // 重新读取网格用于第二种方法（因为第一种方法会修改原网格）
        int[][] grid2 = readGrid(rows, cols);

        // 使用第二种方法计算腐烂时间
        int result2 = solution.orangesRottingSimple(grid2);

        // 输出两种方法的结果
        System.out.println("方法1结果: " + result1 + " 分钟");
        System.out.println("方法2结果: " + result2 + " 分钟");
    }
}
