package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

/**
 * 二叉树的直径（LeetCode 543）
 *
 * 时间复杂度：O(n)
 * - n是二叉树中的节点数
 * - 每个节点都需要被访问一次
 *
 * 空间复杂度：O(h)
 * - h是二叉树的高度，递归调用栈的深度
 * - 最坏情况下（完全不平衡的树）为O(n)，最好情况下（完全平衡的树）为O(log n)
 */
public class DiameterBinaryTree543 {

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

    // 全局变量，用于记录二叉树的最大直径
    private int maxDiameter = 0;

    /**
     * 计算二叉树的直径
     *
     * 算法思路：
     * 二叉树的直径是任意两个节点之间的最长路径长度（边数）
     * 这条路径可能经过根节点，也可能不经过根节点
     * 对于每个节点，经过该节点的最长路径等于其左子树的最大深度加上右子树的最大深度
     * 因此，我们可以在计算每个节点最大深度的同时，更新全局最大直径
     *
     * 执行过程分析（以二叉树 [1,2,3,4,5] 为例）：
     *
     *       1
     *      / \
     *     2   3
     *    / \
     *   4   5
     *
     * 递归调用过程：
     * diameterOfBinaryTree(1)
     * ├─ depth(1)
     * │  ├─ depth(2)
     * │  │  ├─ depth(4) -> 返回1，更新maxDiameter=max(0,0+0)=0
     * │  │  ├─ depth(5) -> 返回1，更新maxDiameter=max(0,0+0)=0
     * │  │  └─ 返回max(1,1)+1=2
     * │  ├─ depth(3)
     * │  │  ├─ depth(null) -> 返回0
     * │  │  ├─ depth(null) -> 返回0
     * │  │  └─ 返回max(0,0)+1=1
     * │  ├─ 更新maxDiameter=max(0,2+1)=3（经过节点2的路径4->2->5最长）
     * │  └─ 返回max(2,1)+1=3
     * └─ 返回maxDiameter=3
     *
     * @param root 二叉树的根节点
     * @return 二叉树的直径
     */
    public int diameterOfBinaryTree(TreeNode root) {
        // 重置全局变量
        maxDiameter = 0;

        // 计算树的深度，在计算过程中更新最大直径
        depth(root);

        // 返回最大直径
        return maxDiameter;
    }

    /**
     * 辅助方法：计算以当前节点为根的子树的最大深度
     * 同时更新全局最大直径
     *
     * @param node 当前节点
     * @return 以当前节点为根的子树的最大深度
     */
    private int depth(TreeNode node) {
        // 基础情况：如果节点为空，深度为0
        if (node == null) {
            return 0;
        }

        // 递归计算左子树的最大深度
        int leftDepth = depth(node.left);

        // 递归计算右子树的最大深度
        int rightDepth = depth(node.right);

        // 更新最大直径：经过当前节点的路径长度等于左子树深度加右子树深度
        maxDiameter = Math.max(maxDiameter, leftDepth + rightDepth);

        // 返回以当前节点为根的子树的最大深度
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 另一种实现方式：使用自定义类封装返回值
     *
     * 算法思路：
     * 对于每个节点，我们需要知道两个信息：
     * 1. 以该节点为根的子树的最大深度
     * 2. 以该节点为根的子树中的最大直径
     * 可以用一个类来封装这两个信息
     */
    static class Result {
        int depth;     // 子树的最大深度
        int diameter;  // 子树中的最大直径

        Result(int depth, int diameter) {
            this.depth = depth;
            this.diameter = diameter;
        }
    }

    /**
     * 使用自定义类的解法
     *
     * @param root 二叉树的根节点
     * @return 二叉树的直径
     */
    public int diameterOfBinaryTreeAlternative(TreeNode root) {
        return diameterHelper(root).diameter;
    }

    /**
     * 辅助方法：返回以当前节点为根的子树的深度和最大直径
     *
     * @param node 当前节点
     * @return 包含深度和直径的Result对象
     */
    private Result diameterHelper(TreeNode node) {
        // 基础情况：如果节点为空
        if (node == null) {
            return new Result(0, 0);
        }

        // 递归获取左右子树的信息
        Result leftResult = diameterHelper(node.left);
        Result rightResult = diameterHelper(node.right);

        // 计算当前子树的最大深度
        int currentDepth = Math.max(leftResult.depth, rightResult.depth) + 1;

        // 计算经过当前节点的路径长度
        int diameterThroughCurrent = leftResult.depth + rightResult.depth;

        // 计算当前子树中的最大直径
        int currentDiameter = Math.max(diameterThroughCurrent,
                                     Math.max(leftResult.diameter, rightResult.diameter));

        return new Result(currentDepth, currentDiameter);
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
            if (node == null) {
                System.out.print("null ");
            } else {
                System.out.print(node.val + " ");
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        System.out.println();
    }

    /**
     * 主函数：处理用户输入并计算二叉树的直径
     */
    public static void main(String[] args) {
        System.out.println("二叉树直径计算");

        // 创建二叉树
        TreeNode root = createTree();

        if (root == null) {
            System.out.println("创建的二叉树为空，直径为: 0");
            return;
        }

        System.out.println("创建的二叉树:");
        printLevelOrder(root);

        // 计算直径
        DiameterBinaryTree543 solution = new DiameterBinaryTree543();
        int diameter1 = solution.diameterOfBinaryTree(root);
        int diameter2 = solution.diameterOfBinaryTreeAlternative(root);

        // 打印结果
        System.out.println("方法1计算的直径: " + diameter1);
        System.out.println("方法2计算的直径: " + diameter2);
    }
}
