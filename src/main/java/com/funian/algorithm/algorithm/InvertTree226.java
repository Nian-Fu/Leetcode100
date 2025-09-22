package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

/**
 * 翻转二叉树（LeetCode 226）
 *
 * 时间复杂度：O(n)
 * - n是二叉树中的节点数
 * - 每个节点都需要被访问一次
 *
 * 空间复杂度：
 * - 方法1（递归）：O(h)
 *   h是二叉树的高度，递归调用栈的深度
 *   最坏情况下（完全不平衡的树）为O(n)，最好情况下（完全平衡的树）为O(log n)
 * - 方法2（迭代）：O(w)
 *   w是二叉树的最大宽度，队列最多存储一层的所有节点
 */
public class InvertTree226 {

    /**
     * 二叉树节点定义
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 方法1：递归解法
     *
     * 算法思路：
     * 递归地翻转左右子树，然后交换当前节点的左右子树
     *
     * 执行过程分析（以二叉树 [4,2,7,1,3,6,9] 为例）：
     *
     * 翻转前：
     *       4
     *      / \
     *     2   7
     *    / \ / \
     *   1  3 6  9
     *
     * 翻转后：
     *       4
     *      / \
     *     7   2
     *    / \ / \
     *   9  6 3  1
     *
     * 递归调用过程：
     * invertTree(4)
     * ├─ invertTree(2)
     * │  ├─ invertTree(1) -> 返回1
     * │  ├─ invertTree(3) -> 返回3
     * │  ├─ 交换2的左右子树：2.left=3, 2.right=1
     * │  └─ 返回2
     * ├─ invertTree(7)
     * │  ├─ invertTree(6) -> 返回6
     * │  ├─ invertTree(9) -> 返回9
     * │  ├─ 交换7的左右子树：7.left=9, 7.right=6
     * │  └─ 返回7
     * ├─ 交换4的左右子树：4.left=7, 4.right=2
     * └─ 返回4
     *
     * @param root 二叉树的根节点
     * @return 翻转后的二叉树根节点
     */
    public TreeNode invertTree(TreeNode root) {
        // 如果节点为空，直接返回 null
        if (root == null) {
            return null;
        }

        // 递归翻转左子树
        TreeNode left = invertTree(root.left);

        // 递归翻转右子树
        TreeNode right = invertTree(root.right);

        // 交换当前节点的左右子树
        root.right = left;
        root.left = right;

        // 返回当前翻转后的根节点
        return root;
    }

    /**
     * 方法2：迭代解法（层序遍历）
     *
     * 算法思路：
     * 使用队列进行层序遍历，对每个节点交换其左右子树
     *
     * @param root 二叉树的根节点
     * @return 翻转后的二叉树根节点
     */
    public TreeNode invertTreeIterative(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            // 交换当前节点的左右子树
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            // 将非空子节点加入队列
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return root;
    }

    /**
     * 辅助方法：根据数组创建二叉树（用于测试）
     */
    public static TreeNode createTree() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入二叉树节点值，按层序遍历输入，null表示空节点，用空格分隔：");
        String input = scanner.nextLine();
        String[] values = input.split(" ");

        if (values.length == 0 || "null".equals(values[0]) || values[0].isEmpty()) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode node = queue.poll();

            // 处理左子节点
            if (i < values.length && !"null".equals(values[i]) && !values[i].isEmpty()) {
                node.left = new TreeNode(Integer.parseInt(values[i]));
                queue.offer(node.left);
            }
            i++;

            // 处理右子节点
            if (i < values.length && !"null".equals(values[i]) && !values[i].isEmpty()) {
                node.right = new TreeNode(Integer.parseInt(values[i]));
                queue.offer(node.right);
            }
            i++;
        }

        return root;
    }

    /**
     * 辅助方法：层序遍历打印二叉树
     */
    public static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("空树");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        System.out.print("层序遍历结果: ");

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        System.out.println();
    }

    /**
     * 主函数：处理用户输入并演示翻转二叉树
     */
    public static void main(String[] args) {
        System.out.println("二叉树翻转演示");

        // 创建二叉树
        TreeNode root = createTree();

        if (root == null) {
            System.out.println("创建的二叉树为空");
            return;
        }

        System.out.println("翻转前的二叉树:");
        printLevelOrder(root);

        // 翻转二叉树
        InvertTree226 solution = new InvertTree226();
        TreeNode invertedRoot = solution.invertTree(root);

        System.out.println("翻转后的二叉树:");
        printLevelOrder(invertedRoot);
    }
}
