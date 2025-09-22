package com.funian.algorithm.algorithm;

/**
 * 旋转数组
 *
 * 时间复杂度：O(n)
 * - 需要翻转整个数组：O(n)
 * - 需要翻转前k个元素：O(k)
 * - 需要翻转后n-k个元素：O(n-k)
 * - 总时间复杂度：O(n) + O(k) + O(n-k) = O(n)
 *
 * 空间复杂度：O(1)
 * - 只使用了常数级别的额外空间
 * - 翻转操作是原地进行的，不需要额外的存储空间
 */
public class RotateArray189 {

    /**
     * 主函数测试
     * 测试数组 [1,2,3,4,5,6,7] 向右旋转 3 位的结果
     */
    public static void main(String[] args) {
        // 初始化测试数组
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        // 调用 rotate 方法将数组向右旋转 3 位
        rotate(nums, 3);
        // 输出旋转后的数组
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println(); // 换行
    }

    /**
     * 旋转数组
     * 将数组中的元素向右移动 k 个位置
     *
     * 算法思路：
     * 1. 先将整个数组翻转
     * 2. 再将前 k 个元素翻转
     * 3. 最后将后 n-k 个元素翻转
     *
     * 示例过程（以数组 [1,2,3,4,5,6,7]，k=3 为例）：
     * 原数组:     [1,2,3,4,5,6,7]
     * 步骤1 - 翻转整个数组: [7,6,5,4,3,2,1]
     * 步骤2 - 翻转前k个元素: [5,6,7,4,3,2,1]
     * 步骤3 - 翻转后n-k个元素: [5,6,7,1,2,3,4]
     * 结果: [5,6,7,1,2,3,4] （相当于原数组向右移动3位）
     *
     * @param nums 输入的整数数组
     * @param k    向右旋转的步数
     */
    public static void rotate(int[] nums, int k) {
        // 获取数组长度
        int n = nums.length;
        // 处理 k 大于数组长度的情况，取模运算
        k = k % n;

        // 步骤1：翻转整个数组
        reverse(nums, 0, n - 1);
        // 步骤2：翻转前 k 个元素
        reverse(nums, 0, k - 1);
        // 步骤3：翻转后 n-k 个元素
        reverse(nums, k, n - 1);
    }

    /**
     * 翻转数组指定范围内的元素
     * 使用双指针技术，从两端向中间交换元素
     *
     * @param nums  输入的整数数组
     * @param start 翻转范围的起始索引（包含）
     * @param end   翻转范围的结束索引（包含）
     */
    public static void reverse(int[] nums, int start, int end) {
        // 双指针从两端向中间移动
        while (start < end) {
            // 交换 start 和 end 位置的元素
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            // 移动指针
            start++;
            end--;
        }
    }
}
