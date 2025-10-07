package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 搜索旋转排序数组（LeetCode 33）
 *
 * 时间复杂度：O(log n)
 * - 使用二分查找，每次将搜索范围减半
 *
 * 空间复杂度：O(1)
 * - 只使用了常数个额外变量
 */
public class Search33 {

    /**
     * 主函数：处理用户输入并搜索目标值
     *
     * 算法流程：
     * 1. 读取用户输入的旋转排序数组和目标值
     * 2. 调用[search](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/Search33.java#L109-L155)方法查找目标值的下标
     * 3. 输出结果
     */
    public static void main(String[] args) {
        // 创建 Scanner 对象用于获取用户输入
        // Scanner scanner = new Scanner(System.in) 创建Scanner对象用于读取输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入数组长度
        // System.out.print("请输入数组长度：") 打印数组长度输入提示
        System.out.print("请输入数组长度：");
        // int n = scanner.nextInt() 读取数组长度
        int n = scanner.nextInt();

        // 创建数组并读取元素
        // int[] nums = new int[n] 创建整数数组
        int[] nums = new int[n];
        // System.out.println("请输入数组元素（用空格分隔）：") 打印数组元素输入提示
        System.out.println("请输入数组元素（用空格分隔）：");
        // for (int i = 0; i < n; i++) 循环读取数组元素
        for (int i = 0; i < n; i++) {
            // nums[i] = scanner.nextInt() 读取数组元素
            nums[i] = scanner.nextInt(); // 读取数组元素
        }

        // 提示用户输入目标值
        // System.out.print("请输入目标值：") 打印目标值输入提示
        System.out.print("请输入目标值：");
        // int target = scanner.nextInt() 读取目标值
        int target = scanner.nextInt();

        // 调用 search 方法查找目标值的下标
        // int result = search(nums, target) 调用search方法查找目标值下标
        int result = search(nums, target);

        // 输出结果
        // System.out.println("目标值 " + target + " 的下标：" + result) 打印结果
        System.out.println("目标值 " + target + " 的下标：" + result);
    }

    /**
     * 在旋转排序数组中搜索目标值
     *
     * 算法思路：
     * 使用修改版的二分查找
     * 1. 数组被旋转后，至少有一半是有序的
     * 2. 每次判断哪一半是有序的
     * 3. 判断目标值是否在有序的那一半中
     * 4. 根据判断结果调整搜索范围
     *
     * 执行过程分析（以`nums=[4,5,6,7,0,1,2]`, `target=0`为例）：
     *
     * 初始状态：
     * left = 0, right = 6
     * nums = [4,5,6,7,0,1,2]
     * 索引:   0 1 2 3 4 5 6
     *
     * 第一次查找：
     * mid = 0 + (6-0)/2 = 3
     * nums[3] = 7
     * nums[left]=4 <= nums[mid]=7，左半部分[4,5,6,7]有序
     * target=0 不在[4,7)范围内
     * left = mid + 1 = 4
     *
     * 第二次查找：
     * left = 4, right = 6
     * mid = 4 + (6-4)/2 = 5
     * nums[5] = 1
     * nums[left]=0 <= nums[mid]=1，左半部分[0,1]有序
     * target=0 在[0,1)范围内
     * right = mid - 1 = 4
     *
     * 第三次查找：
     * left = 4, right = 4
     * mid = 4 + (4-4)/2 = 4
     * nums[4] = 0 == target
     * 返回索引4
     *
     * 执行过程分析（以`nums=[4,5,6,7,0,1,2]`, `target=3`为例）：
     *
     * 第一次查找：
     * mid = 3, nums[3] = 7
     * 左半部分[4,5,6,7]有序
     * target=3 不在[4,7)范围内
     * left = 4
     *
     * 第二次查找：
     * left = 4, right = 6
     * mid = 5, nums[5] = 1
     * 右半部分[1,2]有序
     * target=3 不在(1,2]范围内
     * right = 4
     *
     * 第三次查找：
     * left = 4, right = 4
     * mid = 4, nums[4] = 0
     * 0 != target=3
     * left = 5
     *
     * 循环结束：left=5 > right=4
     * 返回-1（未找到）
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
     * @param target 目标值
     * @return 目标值的下标，如果不存在返回-1
     */
    public static int search(int[] nums, int target) {
        // 左指针，指向搜索范围的左边界
        // int left = 0 初始化左指针
        int left = 0;
        // 右指针，指向搜索范围的右边界
        // int right = nums.length - 1 初始化右指针
        int right = nums.length - 1;

        // 二分查找
        // while (left <= right) 当左指针小于等于右指针时循环
        while (left <= right) {
            // 计算中间索引，避免整数溢出
            // int mid = left + (right - left) / 2 计算中间索引
            int mid = left + (right - left) / 2;

            // 检查中间元素是否是目标值
            // if (nums[mid] == target) 检查中间元素是否等于目标值
            if (nums[mid] == target) {
                // return mid 返回目标值下标
                return mid; // 返回目标值的下标
            }

            // 判断哪部分是有序的
            // 关键判断条件：nums[left] <= nums[mid]
            // 如果成立，说明左半部分是有序的
            // if (nums[left] <= nums[mid]) 判断左半部分是否有序
            if (nums[left] <= nums[mid]) {
                // 左半部分有序
                // 判断目标值是否在左半部分的有序区间内
                // if (nums[left] <= target && target < nums[mid]) 判断目标值是否在左半部分有序区间内
                if (nums[left] <= target && target < nums[mid]) {
                    // 目标在左半部分，缩小搜索范围到左半部分
                    // right = mid - 1 更新右指针
                    right = mid - 1;
                } else {
                    // 目标在右半部分，缩小搜索范围到右半部分
                    // left = mid + 1 更新左指针
                    left = mid + 1;
                }
            } else {
                // 右半部分有序
                // 判断目标值是否在右半部分的有序区间内
                // if (nums[mid] < target && target <= nums[right]) 判断目标值是否在右半部分有序区间内
                if (nums[mid] < target && target <= nums[right]) {
                    // 目标在右半部分，缩小搜索范围到右半部分
                    // left = mid + 1 更新左指针
                    left = mid + 1;
                } else {
                    // 目标在左半部分，缩小搜索范围到左半部分
                    // right = mid - 1 更新右指针
                    right = mid - 1;
                }
            }
        }

        // 如果未找到目标值，返回 -1
        // return -1 返回-1表示未找到
        return -1;
    }

    /**
     * 方法2：递归版本
     *
     * 算法思路：
     * 使用递归实现修改版的二分查找
     *
     * 时间复杂度分析：
     * - 递归二分查找：O(log n)
     * - 递归调用栈：O(log n)
     * - 总时间复杂度：O(log n)
     *
     * 空间复杂度分析：
     * - 递归调用栈：O(log n)
     *
     * @param nums 旋转排序数组
     * @param target 目标值
     * @return 目标值的下标，如果不存在返回-1
     */
    public int searchRecursive(int[] nums, int target) {
        // return searchHelper(nums, 0, nums.length - 1, target) 调用递归辅助方法
        return searchHelper(nums, 0, nums.length - 1, target);
    }

    private int searchHelper(int[] nums, int left, int right, int target) {
        // if (left > right) 检查递归终止条件
        if (left > right) {
            // return -1 返回-1表示未找到
            return -1;
        }

        // int mid = left + (right - left) / 2 计算中间索引
        int mid = left + (right - left) / 2;

        // if (nums[mid] == target) 检查中间元素是否等于目标值
        if (nums[mid] == target) {
            // return mid 返回目标值下标
            return mid;
        }

        // if (nums[left] <= nums[mid]) 判断左半部分是否有序
        if (nums[left] <= nums[mid]) {
            // if (nums[left] <= target && target < nums[mid]) 判断目标值是否在左半部分有序区间内
            if (nums[left] <= target && target < nums[mid]) {
                // return searchHelper(nums, left, mid - 1, target) 递归搜索左半部分
                return searchHelper(nums, left, mid - 1, target);
            } else {
                // return searchHelper(nums, mid + 1, right, target) 递归搜索右半部分
                return searchHelper(nums, mid + 1, right, target);
            }
        } else {
            // if (nums[mid] < target && target <= nums[right]) 判断目标值是否在右半部分有序区间内
            if (nums[mid] < target && target <= nums[right]) {
                // return searchHelper(nums, mid + 1, right, target) 递归搜索右半部分
                return searchHelper(nums, mid + 1, right, target);
            } else {
                // return searchHelper(nums, left, mid - 1, target) 递归搜索左半部分
                return searchHelper(nums, left, mid - 1, target);
            }
        }
    }

    /**
     * 方法3：先找到旋转点，再进行二分查找
     *
     * 算法思路：
     * 1. 先找到旋转点（最小元素位置）
     * 2. 在两个有序部分分别进行二分查找
     *
     * 时间复杂度分析：
     * - 查找旋转点：O(log n)
     * - 两次二分查找：O(log n)
     * - 总时间复杂度：O(log n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param nums 旋转排序数组
     * @param target 目标值
     * @return 目标值的下标，如果不存在返回-1
     */
    public int searchFindPivot(int[] nums, int target) {
        // if (nums == null || nums.length == 0) 检查数组是否为空
        if (nums == null || nums.length == 0) {
            // return -1 返回-1
            return -1;
        }

        // 找到旋转点
        // int pivot = findPivot(nums) 调用findPivot方法找到旋转点
        int pivot = findPivot(nums);

        // 在两个有序部分分别进行二分查找
        // int result1 = Arrays.binarySearch(nums, 0, pivot + 1, target) 在左半部分查找
        int result1 = Arrays.binarySearch(nums, 0, pivot + 1, target);
        // int result2 = Arrays.binarySearch(nums, pivot + 1, nums.length, target) 在右半部分查找
        int result2 = Arrays.binarySearch(nums, pivot + 1, nums.length, target);

        // if (result1 >= 0) 检查是否在左半部分找到
        if (result1 >= 0) {
            // return result1 返回左半部分查找结果
            return result1;
        } else if (result2 >= 0) {
            // return result2 返回右半部分查找结果
            return result2;
        } else {
            // return -1 返回-1表示未找到
            return -1;
        }
    }

    /**
     * 找到旋转点（最小元素的索引）
     *
     * 算法思路：
     * 使用二分查找找到最小元素的位置
     *
     * 时间复杂度分析：
     * - 二分查找：O(log n)
     * - 总时间复杂度：O(log n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     */
    private int findPivot(int[] nums) {
        // int left = 0 初始化左指针
        int left = 0;
        // int right = nums.length - 1 初始化右指针
        int right = nums.length - 1;

        // while (left < right) 当左指针小于右指针时循环
        while (left < right) {
            // int mid = left + (right - left) / 2 计算中间索引
            int mid = left + (right - left) / 2;
            // if (nums[mid] > nums[right]) 比较中间元素和右边界元素
            if (nums[mid] > nums[right]) {
                // left = mid + 1 更新左指针
                left = mid + 1;
            } else {
                // right = mid 更新右指针
                right = mid;
            }
        }

        // return left 返回旋转点索引
        return left;
    }
}
