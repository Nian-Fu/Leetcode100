package com.funian.algorithm.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * 滑动窗口最大值
 *
 * 时间复杂度：O(n)
 * - 每个元素最多被加入和移出双端队列各一次
 * - 虽然有内层while循环，但整体上每个元素的操作是常数时间
 *
 * 空间复杂度：O(k)
 * - 双端队列中最多存储k个元素的索引
 * - 结果数组空间不计入，因为它是输出的一部分
 */
public class MaxSlidingWindow239 {
    public static void main(String[] args) {
        // 读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入整数数组
        System.out.print("请输入整数数组 nums（以空格分隔）：");
        // 读取输入并按空格分割
        String[] input = scanner.nextLine().split(" ");
        // 创建整型数组
        int[] nums = new int[input.length];
        // 将字符串转换为整数
        for (int i = 0; i < input.length; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }

        // 提示用户输入滑动窗口大小
        System.out.print("请输入滑动窗口大小 k：");
        // 读取窗口大小
        int k = scanner.nextInt();

        // 调用 maxSlidingWindow 方法计算每个滑动窗口的最大值
        int[] result = maxSlidingWindow(nums, k);

        // 输出结果
        System.out.print("滑动窗口中的最大值为：");
        for (int val : result) {
            System.out.print(val + " ");
        }
        System.out.println(); // 换行
    }

    /**
     * 计算滑动窗口中的最大值
     * 使用双端队列维护一个单调递减的队列
     *
     * 算法思路：
     * 1. 使用双端队列存储数组元素的索引
     * 2. 队列中索引对应的元素值保持单调递减顺序
     * 3. 队列头部始终是当前窗口的最大值索引
     * 4. 遍历数组时维护队列的性质
     *
     * 示例过程（以数组 [1,3,-1,-3,5,3,6,7], k=3 为例）：
     *
     * 窗口位置        队列状态(存储索引)    队列元素值    最大值
     * [1  3 -1] -3 5 3 6 7  [1,2]         [3,-1]       3
     *  1 [3 -1 -3] 5 3 6 7  [1,2,3]       [3,-1,-3]    3
     *  1  3 [-1 -3 5] 3 6 7  [4]           [5]          5
     *  1  3 -1 [-3 5 3] 6 7  [4,5]         [5,3]        5
     *  1  3 -1 -3 [5 3 6] 7  [6]           [6]          6
     *  1  3 -1 -3 5 [3 6 7]  [7]           [7]          7
     *
     * 结果：[3,3,5,5,6,7]
     *
     * @param nums 整数数组
     * @param k 滑动窗口大小
     * @return 每个滑动窗口中的最大值数组
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        // 边界条件检查
        if (nums == null || nums.length == 0 || k == 0) return new int[0];

        // 获取数组长度
        int n = nums.length;
        // 创建结果数组，长度为 n - k + 1
        int[] result = new int[n - k + 1];
        // 使用双端队列存储数组元素的索引
        Deque<Integer> deque = new ArrayDeque<>();

        // 遍历数组
        for (int i = 0; i < n; i++) {
            // 移除滑动窗口外的元素索引
            // 如果队列头部索引超出当前窗口范围，则移除
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // 保持队列递减顺序
            // 移除所有小于当前元素的队列尾部元素
            // 这样保证队列头部始终是当前窗口的最大值索引
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // 添加当前元素下标到队列尾部
            deque.offerLast(i);

            // 当窗口形成时（i >= k - 1），记录当前窗口的最大值
            if (i >= k - 1) {
                // 队列头部索引对应的元素就是当前窗口的最大值
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        // 返回每个滑动窗口的最大值数组
        return result;
    }
}
