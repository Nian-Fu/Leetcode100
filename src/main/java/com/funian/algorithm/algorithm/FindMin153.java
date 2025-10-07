package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 寻找旋转排序数组中的最小值（LeetCode 153）
 *
 * 时间复杂度：O(log n)
 * - 使用二分查找，每次将搜索范围减半
 *
 * 空间复杂度：O(1)
 * - 只使用了常数个额外变量
 */
public class FindMin153 {

    /**
     * 寻找旋转排序数组中的最小值
     *
     * 算法思路：
     * 使用二分查找，关键在于判断最小值在哪一半
     * 1. 比较中间元素和右边界元素
     * 2. 如果中间元素小于右边界元素，说明右半部分有序，最小值在左半部分（包括中间）
     * 3. 如果中间元素大于右边界元素，说明左半部分有序，最小值在右半部分
     *
     * 执行过程分析（以`nums=[3,4,5,1,2]`为例）：
     *
     * 初始状态：
     * left = 0, right = 4
     * nums = [3,4,5,1,2]
     * 索引:   0 1 2 3 4
     *
     * 第一次查找：
     * mid = 0 + (4-0)/2 = 2
     * nums[2] = 5, nums[4] = 2
     * 5 > 2，说明左半部分有序，最小值在右半部分
     * left = mid + 1 = 3
     *
     * 第二次查找：
     * left = 3, right = 4
     * mid = 3 + (4-3)/2 = 3
     * nums[3] = 1, nums[4] = 2
     * 1 < 2，说明右半部分有序，最小值在左半部分（包括中间）
     * right = mid = 3
     *
     * 循环结束：left=3 == right=3
     * 返回nums[3] = 1
     *
     * 执行过程分析（以`nums=[4,5,6,7,0,1,2]`为例）：
     *
     * 初始状态：
     * left = 0, right = 6
     *
     * 第一次查找：
     * mid = 3, nums[3] = 7, nums[6] = 2
     * 7 > 2，left = 4
     *
     * 第二次查找：
     * left = 4, right = 6
     * mid = 5, nums[5] = 1, nums[6] = 2
     * 1 < 2，right = 5
     *
     * 第三次查找：
     * left = 4, right = 5
     * mid = 4, nums[4] = 0, nums[5] = 1
     * 0 < 1，right = 4
     *
     * 循环结束：left=4 == right=4
     * 返回nums[4] = 0
     *
     * 时间复杂度分析：
     * - 二分查找：O(log n)
     * - 每次迭代操作：O(1)
     * - 总时间复杂度：O(log n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param nums 旋转排序数组（无重复元素）
     * @return 数组中的最小值
     */
    public int findMin(int[] nums) {
        // 边界情况：空数组
        // if (nums == null || nums.length == 0) 检查数组是否为空
        if (nums == null || nums.length == 0) {
            // throw new IllegalArgumentException("数组不能为空") 抛出异常
            throw new IllegalArgumentException("数组不能为空");
        }

        // 如果数组只有一个元素，直接返回
        // if (nums.length == 1) 检查数组长度是否为1
        if (nums.length == 1) {
            // return nums[0] 返回唯一元素
            return nums[0];
        }

        // 左右指针
        // int left = 0 初始化左指针
        int left = 0;
        // int right = nums.length - 1 初始化右指针
        int right = nums.length - 1;

        // 如果数组没有旋转，第一个元素就是最小值
        // if (nums[left] < nums[right]) 检查数组是否未旋转
        if (nums[left] < nums[right]) {
            // return nums[left] 返回第一个元素
            return nums[left];
        }

        // 二分查找
        // while (left < right) 当左指针小于右指针时循环
        while (left < right) {
            // 计算中间索引
            // int mid = left + (right - left) / 2 计算中间索引
            int mid = left + (right - left) / 2;

            // 关键判断：比较中间元素和右边界元素
            // if (nums[mid] > nums[right]) 比较中间元素和右边界元素
            if (nums[mid] > nums[right]) {
                // 中间元素大于右边界元素，说明最小值在右半部分
                // left = mid + 1 更新左指针
                left = mid + 1;
            } else {
                // 中间元素小于等于右边界元素，说明最小值在左半部分（包括中间）
                // right = mid 更新右指针
                right = mid;
            }
        }

        // left == right时，指向最小值
        // return nums[left] 返回最小值
        return nums[left];
    }

    /**
     * 方法2：比较中间元素和左边界元素的版本
     *
     * 算法思路：
     * 通过比较中间元素和左边界元素来判断最小值位置
     *
     * 时间复杂度分析：
     * - 二分查找：O(log n)
     * - 每次迭代操作：O(1)
     * - 总时间复杂度：O(log n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param nums 旋转排序数组
     * @return 数组中的最小值
     */
    public int findMinAlternative(int[] nums) {
        // if (nums == null || nums.length == 0) 检查数组是否为空
        if (nums == null || nums.length == 0) {
            // throw new IllegalArgumentException("数组不能为空") 抛出异常
            throw new IllegalArgumentException("数组不能为空");
        }

        // int left = 0 初始化左指针
        int left = 0;
        // int right = nums.length - 1 初始化右指针
        int right = nums.length - 1;

        // while (left < right) 当左指针小于右指针时循环
        while (left < right) {
            // int mid = left + (right - left) / 2 计算中间索引
            int mid = left + (right - left) / 2;

            // 比较中间元素和左边界元素
            // if (nums[mid] > nums[left]) 比较中间元素和左边界元素
            if (nums[mid] > nums[left]) {
                // 左半部分有序
                // if (nums[left] < nums[right]) 检查整个数组是否有序
                if (nums[left] < nums[right]) {
                    // 整个数组有序，最小值在最左边
                    // return nums[left] 返回最左边元素
                    return nums[left];
                } else {
                    // 最小值在右半部分
                    // left = mid + 1 更新左指针
                    left = mid + 1;
                }
            } else {
                // 右半部分有序，最小值在左半部分（包括中间）
                // right = mid 更新右指针
                right = mid;
            }
        }

        // return nums[left] 返回最小值
        return nums[left];
    }

    /**
     * 方法3：线性搜索解法（仅供对比）
     *
     * 算法思路：
     * 遍历数组找到最小值
     *
     * 时间复杂度分析：
     * - 遍历数组：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param nums 旋转排序数组
     * @return 数组中的最小值
     */
    public int findMinLinear(int[] nums) {
        // if (nums == null || nums.length == 0) 检查数组是否为空
        if (nums == null || nums.length == 0) {
            // throw new IllegalArgumentException("数组不能为空") 抛出异常
            throw new IllegalArgumentException("数组不能为空");
        }

        // int min = nums[0] 初始化最小值
        int min = nums[0];
        // for (int i = 1; i < nums.length; i++) 遍历数组
        for (int i = 1; i < nums.length; i++) {
            // if (nums[i] < min) 比较当前元素和最小值
            if (nums[i] < min) {
                // min = nums[i] 更新最小值
                min = nums[i];
            }
        }
        // return min 返回最小值
        return min;
    }

    /**
     * 辅助方法：读取用户输入的数组
     *
     * 时间复杂度分析：
     * - 读取和解析输入：O(n)
     *
     * 空间复杂度分析：
     * - 存储数组：O(n)
     *
     * @return 用户输入的整数数组
     */
    public static int[] readArray() {
        // Scanner scanner = new Scanner(System.in) 创建Scanner对象
        Scanner scanner = new Scanner(System.in);
        // System.out.println("请输入旋转排序数组（用空格分隔）：") 打印提示信息
        System.out.println("请输入旋转排序数组（用空格分隔）：");
        // String input = scanner.nextLine() 读取输入
        String input = scanner.nextLine();
        // String[] strArray = input.split(" ") 分割字符串
        String[] strArray = input.split(" ");

        // int[] nums = new int[strArray.length] 创建整数数组
        int[] nums = new int[strArray.length];
        // for (int i = 0; i < strArray.length; i++) 遍历字符串数组
        for (int i = 0; i < strArray.length; i++) {
            // nums[i] = Integer.parseInt(strArray[i]) 转换为整数
            nums[i] = Integer.parseInt(strArray[i]);
        }

        // return nums 返回整数数组
        return nums;
    }

    /**
     * 主函数：处理用户输入并找出数组中的最小值
     */
    public static void main(String[] args) {
        // System.out.println("寻找旋转排序数组中的最小值") 打印程序标题
        System.out.println("寻找旋转排序数组中的最小值");

        // 读取用户输入的数组
        // int[] nums = readArray() 调用readArray方法读取数组
        int[] nums = readArray();
        // System.out.println("输入数组: " + Arrays.toString(nums)) 打印输入数组
        System.out.println("输入数组: " + Arrays.toString(nums));

        // 计算最小值
        // FindMin153 solution = new FindMin153() 创建FindMin153对象
        FindMin153 solution = new FindMin153();
        // int result1 = solution.findMin(nums) 调用findMin方法
        int result1 = solution.findMin(nums);
        // int result2 = solution.findMinAlternative(nums) 调用findMinAlternative方法
        int result2 = solution.findMinAlternative(nums);
        // int result3 = solution.findMinLinear(nums) 调用findMinLinear方法
        int result3 = solution.findMinLinear(nums);

        // 输出结果
        // System.out.println("二分查找方法结果: " + result1) 打印二分查找方法结果
        System.out.println("二分查找方法结果: " + result1);
        // System.out.println("替代方法结果: " + result2) 打印替代方法结果
        System.out.println("替代方法结果: " + result2);
        // System.out.println("线性搜索方法结果: " + result3) 打印线性搜索方法结果
        System.out.println("线性搜索方法结果: " + result3);
    }
}
