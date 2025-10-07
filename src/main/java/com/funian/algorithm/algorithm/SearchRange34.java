package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Arrays;

/**
 * 在排序数组中查找元素的第一个和最后一个位置（LeetCode 34）
 *
 * 时间复杂度：O(log n)
 * - 需要进行两次二分查找
 *
 * 空间复杂度：O(1)
 * - 只使用了常数个额外变量
 */
public class SearchRange34 {

    /**
     * 查找目标值在排序数组中的起始和结束位置
     *
     * 算法思路：
     * 使用两次二分查找分别找到目标值的左边界和右边界
     * 1. 第一次二分查找找到最左边的目标值位置（左边界）
     * 2. 第二次二分查找找到最右边的目标值位置（右边界）
     *
     * 执行过程分析（以`nums=[5,7,7,8,8,10]`, `target=8`为例）：
     *
     * 查找左边界：
     * 初始：left=0, right=5
     * mid=2, nums[2]=7 < 8, left=3
     * mid=4, nums[4]=8 == 8, right=4（继续向左查找）
     * mid=3, nums[3]=8 == 8, right=3（继续向左查找）
     * left=3, right=2，循环结束
     * 左边界=3
     *
     * 查找右边界：
     * 初始：left=0, right=5
     * mid=2, nums[2]=7 < 8, left=3
     * mid=4, nums[4]=8 == 8, left=5（继续向右查找）
     * mid=4, nums[4]=8 == 8, left=5（继续向右查找）
     * left=5, right=4，循环结束
     * 右边界=4
     *
     * 最终结果：[3,4]
     *
     * 时间复杂度分析：
     * - 两次二分查找：O(log n)
     * - 每次迭代操作：O(1)
     * - 总时间复杂度：O(log n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param nums 升序排序的整数数组
     * @param target 目标值
     * @return 目标值的起始和结束位置，如果不存在返回[-1,-1]
     */
    public int[] searchRange(int[] nums, int target) {
        // 边界情况：空数组
        // if (nums == null || nums.length == 0) 检查数组是否为空
        if (nums == null || nums.length == 0) {
            // return new int[]{-1, -1} 返回[-1,-1]
            return new int[]{-1, -1};
        }

        // 查找左边界
        // int leftBound = findLeftBound(nums, target) 调用findLeftBound方法查找左边界
        int leftBound = findLeftBound(nums, target);
        // 如果左边界不存在，说明目标值不存在
        // if (leftBound == -1) 检查左边界是否存在
        if (leftBound == -1) {
            // return new int[]{-1, -1} 返回[-1,-1]
            return new int[]{-1, -1};
        }

        // 查找右边界
        // int rightBound = findRightBound(nums, target) 调用findRightBound方法查找右边界
        int rightBound = findRightBound(nums, target);

        // return new int[]{leftBound, rightBound} 返回目标值范围
        return new int[]{leftBound, rightBound};
    }

    /**
     * 查找目标值的左边界（第一次出现的位置）
     *
     * 算法思路：
     * 使用二分查找找到目标值第一次出现的位置
     *
     * 时间复杂度分析：
     * - 二分查找：O(log n)
     * - 总时间复杂度：O(log n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param nums 升序排序的整数数组
     * @param target 目标值
     * @return 左边界索引，如果不存在返回-1
     */
    private int findLeftBound(int[] nums, int target) {
        // int left = 0 初始化左指针
        int left = 0;
        // int right = nums.length - 1 初始化右指针
        int right = nums.length - 1;

        // while (left <= right) 当左指针小于等于右指针时循环
        while (left <= right) {
            // int mid = left + (right - left) / 2 计算中间索引
            int mid = left + (right - left) / 2;

            // if (nums[mid] == target) 检查中间元素是否等于目标值
            if (nums[mid] == target) {
                // 找到目标值，但继续向左查找是否有更左边的
                // right = mid - 1 更新右指针
                right = mid - 1;
            } else if (nums[mid] < target) {
                // left = mid + 1 更新左指针
                left = mid + 1;
            } else {
                // right = mid - 1 更新右指针
                right = mid - 1;
            }
        }

        // 检查是否找到目标值
        // if (left < nums.length && nums[left] == target) 检查是否找到目标值
        if (left < nums.length && nums[left] == target) {
            // return left 返回左边界索引
            return left;
        }
        // return -1 返回-1
        return -1;
    }

    /**
     * 查找目标值的右边界（最后一次出现的位置）
     *
     * 算法思路：
     * 使用二分查找找到目标值最后一次出现的位置
     *
     * 时间复杂度分析：
     * - 二分查找：O(log n)
     * - 总时间复杂度：O(log n)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param nums 升序排序的整数数组
     * @param target 目标值
     * @return 右边界索引
     */
    private int findRightBound(int[] nums, int target) {
        // int left = 0 初始化左指针
        int left = 0;
        // int right = nums.length - 1 初始化右指针
        int right = nums.length - 1;

        // while (left <= right) 当左指针小于等于右指针时循环
        while (left <= right) {
            // int mid = left + (right - left) / 2 计算中间索引
            int mid = left + (right - left) / 2;

            // if (nums[mid] == target) 检查中间元素是否等于目标值
            if (nums[mid] == target) {
                // 找到目标值，但继续向右查找是否有更右边的
                // left = mid + 1 更新左指针
                left = mid + 1;
            } else if (nums[mid] < target) {
                // left = mid + 1 更新左指针
                left = mid + 1;
            } else {
                // right = mid - 1 更新右指针
                right = mid - 1;
            }
        }

        // 返回右边界（此时right指向最后一次出现的位置）
        // return right 返回右边界索引
        return right;
    }

    /**
     * 方法2：使用Java内置二分查找
     *
     * 算法思路：
     * 使用Java标准库的二分查找方法，然后向两边扩展找到边界
     *
     * 时间复杂度分析：
     * - 二分查找：O(log n)
     * - 向两边扩展：O(k)，k为目标值出现次数
     * - 总时间复杂度：O(log n + k)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param nums 升序排序的整数数组
     * @param target 目标值
     * @return 目标值的起始和结束位置，如果不存在返回[-1,-1]
     */
    public int[] searchRangeBuiltIn(int[] nums, int target) {
        // if (nums == null || nums.length == 0) 检查数组是否为空
        if (nums == null || nums.length == 0) {
            // return new int[]{-1, -1} 返回[-1,-1]
            return new int[]{-1, -1};
        }

        // 查找目标值第一次出现的位置
        // int leftIndex = Arrays.binarySearch(nums, target) 调用Java内置二分查找
        int leftIndex = Arrays.binarySearch(nums, target);
        // if (leftIndex < 0) 检查是否找到目标值
        if (leftIndex < 0) {
            // return new int[]{-1, -1} 返回[-1,-1]
            return new int[]{-1, -1};
        }

        // 向左找到第一次出现的位置
        // while (leftIndex > 0 && nums[leftIndex - 1] == target) 向左扩展找到起始位置
        while (leftIndex > 0 && nums[leftIndex - 1] == target) {
            // leftIndex-- 更新起始位置
            leftIndex--;
        }

        // 向右找到最后一次出现的位置
        // int rightIndex = leftIndex 初始化结束位置
        int rightIndex = leftIndex;
        // while (rightIndex < nums.length - 1 && nums[rightIndex + 1] == target) 向右扩展找到结束位置
        while (rightIndex < nums.length - 1 && nums[rightIndex + 1] == target) {
            // rightIndex++ 更新结束位置
            rightIndex++;
        }

        // return new int[]{leftIndex, rightIndex} 返回目标值范围
        return new int[]{leftIndex, rightIndex};
    }

    /**
     * 方法3：一次二分查找后向两边扩展
     *
     * 算法思路：
     * 先用二分查找找到目标值，然后向两边扩展找到边界
     *
     * 时间复杂度分析：
     * - 二分查找：O(log n)
     * - 向两边扩展：O(k)，k为目标值出现次数
     * - 总时间复杂度：O(log n + k)
     *
     * 空间复杂度分析：
     * - 只使用常数额外变量：O(1)
     *
     * @param nums 升序排序的整数数组
     * @param target 目标值
     * @return 目标值的起始和结束位置，如果不存在返回[-1,-1]
     */
    public int[] searchRangeExpand(int[] nums, int target) {
        // if (nums == null || nums.length == 0) 检查数组是否为空
        if (nums == null || nums.length == 0) {
            // return new int[]{-1, -1} 返回[-1,-1]
            return new int[]{-1, -1};
        }

        // int index = Arrays.binarySearch(nums, target) 调用Java内置二分查找
        int index = Arrays.binarySearch(nums, target);
        // if (index < 0) 检查是否找到目标值
        if (index < 0) {
            // return new int[]{-1, -1} 返回[-1,-1]
            return new int[]{-1, -1};
        }

        // 向左扩展找到起始位置
        // int left = index 初始化起始位置
        int left = index;
        // while (left > 0 && nums[left - 1] == target) 向左扩展找到起始位置
        while (left > 0 && nums[left - 1] == target) {
            // left-- 更新起始位置
            left--;
        }

        // 向右扩展找到结束位置
        // int right = index 初始化结束位置
        int right = index;
        // while (right < nums.length - 1 && nums[right + 1] == target) 向右扩展找到结束位置
        while (right < nums.length - 1 && nums[right + 1] == target) {
            // right++ 更新结束位置
            right++;
        }

        // return new int[]{left, right} 返回目标值范围
        return new int[]{left, right};
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
        // System.out.println("请输入排序数组（用空格分隔）：") 打印提示信息
        System.out.println("请输入排序数组（用空格分隔）：");
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
     * 主函数：处理用户输入并查找目标值的范围
     */
    public static void main(String[] args) {
        // System.out.println("在排序数组中查找元素的第一个和最后一个位置") 打印程序标题
        System.out.println("在排序数组中查找元素的第一个和最后一个位置");

        // 读取用户输入的数组
        // int[] nums = readArray() 调用readArray方法读取数组
        int[] nums = readArray();
        // System.out.println("输入数组: " + Arrays.toString(nums)) 打印输入数组
        System.out.println("输入数组: " + Arrays.toString(nums));

        // 读取目标值
        // Scanner scanner = new Scanner(System.in) 创建Scanner对象
        Scanner scanner = new Scanner(System.in);
        // System.out.print("请输入目标值: ") 打印提示信息
        System.out.print("请输入目标值: ");
        // int target = scanner.nextInt() 读取目标值
        int target = scanner.nextInt();

        // 查找目标值范围
        // SearchRange34 solution = new SearchRange34() 创建SearchRange34对象
        SearchRange34 solution = new SearchRange34();
        // int[] result1 = solution.searchRange(nums, target) 调用searchRange方法
        int[] result1 = solution.searchRange(nums, target);
        // int[] result2 = solution.searchRangeBuiltIn(nums, target) 调用searchRangeBuiltIn方法
        int[] result2 = solution.searchRangeBuiltIn(nums, target);
        // int[] result3 = solution.searchRangeExpand(nums, target) 调用searchRangeExpand方法
        int[] result3 = solution.searchRangeExpand(nums, target);

        // 输出结果
        // System.out.println("两次二分查找方法结果: " + Arrays.toString(result1)) 打印两次二分查找方法结果
        System.out.println("两次二分查找方法结果: " + Arrays.toString(result1));
        // System.out.println("内置二分查找方法结果: " + Arrays.toString(result2)) 打印内置二分查找方法结果
        System.out.println("内置二分查找方法结果: " + Arrays.toString(result2));
        // System.out.println("扩展查找方法结果: " + Arrays.toString(result3)) 打印扩展查找方法结果
        System.out.println("扩展查找方法结果: " + Arrays.toString(result3));
    }
}
