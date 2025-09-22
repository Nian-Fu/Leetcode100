package com.funian.algorithm.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 三数之和
 *
 * 时间复杂度：O(n²)
 * - 数组排序：O(n log n)
 * - 外层循环：O(n)
 * - 内层双指针：O(n)
 * - 总体时间复杂度：O(n log n) + O(n) × O(n) = O(n²)
 *
 * 空间复杂度：O(1)（不考虑返回结果占用的空间）
 * - 排序可能需要 O(log n) 的递归栈空间
 * - 使用了常数级别的额外变量空间
 */
public class ThreeSum15 {
    public static void main(String[] args) {
        // 创建 Scanner 对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入数组
        System.out.println("输入数组：");
        // 读取一行输入
        String line = scanner.nextLine();
        // 按空格分割字符串得到字符串数组
        String[] strs = line.split(" ");
        // 获取数组长度
        int n = strs.length;
        // 创建整型数组
        int[] nums = new int[n];
        // 将字符串数组转换为整型数组
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(strs[i]);
        }

        // 调用 threeSum 方法查找所有和为0的三元组
        List<List<Integer>> result = threeSum(nums);
        // 输出结果
        System.out.println("结果为：" + result);
    }

    /**
     * 查找所有和为0的不重复三元组
     * 给定一个整数数组，判断是否存在三个元素 a, b, c，
     * 使得 a + b + c = 0，找出所有满足条件且不重复的三元组。
     *
     * 算法思路：
     * 1. 首先对数组进行排序
     * 2. 固定第一个数，用双指针在剩余数组中查找两数之和等于第一个数的相反数
     * 3. 通过跳过重复元素避免重复三元组
     *
     * 示例过程（以数组 [-1,0,1,2,-1,-4] 为例）：
     *
     * 排序后: [-4, -1, -1, 0, 1, 2]
     *
     * i=0, nums[0]=-4: target=4
     *   left=1, right=5: sum=-1+2=1 < 4, left++
     *   left=2, right=5: sum=-1+2=1 < 4, left++
     *   ...无法找到和为4的两个数
     *
     * i=1, nums[1]=-1: target=1
     *   left=2, right=5: sum=-1+2=1 = 1, 找到[-1,-1,2]
     *   继续查找直到left>=right
     *
     * i=2, nums[2]=-1 = nums[1]: 跳过重复元素
     *
     * i=3, nums[3]=0: target=0
     *   left=4, right=5: sum=1+2=3 > 0, right--
     *   left=4, right=4: 结束
     *
     * 最终结果: [[-1,-1,2], [-1,0,1]]
     *
     * @param nums 输入的整数数组
     * @return 所有和为0的不重复三元组列表
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        // 存储结果的列表
        List<List<Integer>> result = new ArrayList<>();

        // 对数组进行排序，为使用双指针做准备
        Arrays.sort(nums);

        // 获取数组长度
        int n = nums.length;

        // 外层循环固定第一个数，从索引0到n-3
        for (int i = 0; i < n - 2; i++) {
            // 跳过重复元素，避免出现重复的三元组
            // 如果当前元素与前一个元素相同，则跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 设置双指针，left指向i+1，right指向数组末尾
            int left = i + 1;
            int right = n - 1;

            // 双指针向中间移动寻找满足条件的组合
            while (left < right) {
                // 计算三个数的和
                int sum = nums[i] + nums[left] + nums[right];

                // 如果和为0，找到一个满足条件的三元组
                if (sum == 0) {
                    // 将三元组添加到结果列表中
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 跳过重复元素，避免出现重复的三元组
                    // 跳过左侧重复元素
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 跳过右侧重复元素
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    // 继续寻找下一组可能的解
                    left++;
                    right--;
                }
                // 如果和小于0，说明左侧元素太小，需要增大，移动左指针
                else if (sum < 0) {
                    left++;
                }
                // 如果和大于0，说明右侧元素太大，需要减小，移动右指针
                else {
                    right--;
                }
            }
        }

        // 返回所有满足条件的三元组
        return result;
    }
}
