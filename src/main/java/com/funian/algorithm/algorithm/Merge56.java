package com.funian.algorithm.algorithm;

import java.util.*;

/**
 * 合并区间
 *
 * 时间复杂度：O(n log n)
 * - 排序需要 O(n log n) 时间
 * - 遍历数组需要 O(n) 时间
 * - 总时间复杂度为 O(n log n)
 *
 * 空间复杂度：O(n)
 * - 存储合并结果的列表需要 O(n) 空间
 * - 排序可能需要 O(log n) 的递归栈空间
 */
public class Merge56 {
    /**
     * 合并重叠区间
     *
     * 算法思路：
     * 1. 首先按照区间的起始位置对所有区间进行排序
     * 2. 遍历排序后的区间，逐个检查是否与前一个区间重叠
     * 3. 如果重叠则合并，否则将当前区间添加到结果中
     *
     * 重叠判断条件：当前区间起始位置 <= 前一个区间结束位置
     * 合并方法：新区间的结束位置为两个区间结束位置的最大值
     *
     * 示例过程（以 intervals=[[1,3],[2,6],[8,10],[15,18]] 为例）：
     *
     * 排序后: [[1,3],[2,6],[8,10],[15,18]]
     *
     * 步骤1: merged = [[1,3]]
     * 步骤2: 检查[2,6]，2 <= 3，重叠，合并为[1,6]，merged = [[1,6]]
     * 步骤3: 检查[8,10]，8 > 6，不重叠，添加到结果，merged = [[1,6],[8,10]]
     * 步骤4: 检查[15,18]，15 > 10，不重叠，添加到结果，merged = [[1,6],[8,10],[15,18]]
     *
     * 结果：[[1,6],[8,10],[15,18]]
     *
     * @param intervals 输入的区间数组
     * @return 合并后的不重叠区间数组
     */
    public int[][] merge(int[][] intervals) {
        // 如果输入为空，直接返回空数组
        if (intervals.length == 0) return new int[0][0];

        // 按照每个区间的起始值进行排序
        Arrays.sort(intervals, (a,b) -> Integer.compare(a[0], b[0]));

        // 存储合并后的区间
        List<int[]> merged = new ArrayList<>();

        // 将第一个区间添加到合并列表中
        merged.add(intervals[0]);

        // 从第二个区间开始遍历
        for (int i = 1; i < intervals.length; i++) {
            // 获取当前区间
            int[] current = intervals[i];
            // 获取已合并区间中的最后一个区间
            int[] lastMerged = merged.get(merged.size() - 1);

            // 检查当前区间是否与最后一个合并区间重叠
            // 重叠条件：当前区间起始位置 <= 已合并区间结束位置
            if (current[0] <= lastMerged[1]) {
                // 如果重叠，合并区间
                // 合并后的结束位置为两个区间结束位置的最大值
                lastMerged[1] = Math.max(lastMerged[1], current[1]);
            } else {
                // 如果不重叠，直接添加当前区间到合并列表
                merged.add(current);
            }
        }

        // 将 List 转换为二维数组并返回
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        // 创建 Scanner 对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 输入区间数
        System.out.print("请输入区间的数量: ");
        // 读取区间数量
        int n = Integer.parseInt(scanner.nextLine());
        // 创建二维数组存储区间
        int[][] intervals = new int[n][2];

        // 输入区间
        System.out.println("请输入区间 (每个区间用空格分隔，格式为 start end):");
        // 读取每个区间
        for (int i = 0; i < n; i++) {
            // 按空格分割输入字符串
            String[] strArray = scanner.nextLine().split(" ");
            // 解析起始位置
            intervals[i][0] = Integer.parseInt(strArray[0]);
            // 解析结束位置
            intervals[i][1] = Integer.parseInt(strArray[1]);
        }

        // 创建 Merge56 实例并合并区间
        Merge56 solution = new Merge56();
        // 调用 merge 方法合并区间
        int[][] mergedIntervals = solution.merge(intervals);

        // 输出结果
        System.out.println("合并后的区间:");
        // 遍历并打印合并后的区间
        for (int[] interval : mergedIntervals) {
            System.out.println(Arrays.toString(interval));
        }
    }
}
