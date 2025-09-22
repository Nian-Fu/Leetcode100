package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 相交链表
 *
 * 时间复杂度：O(m + n)
 * - 需要遍历两个链表各一次
 * - 最坏情况下两个指针都要走完两个链表
 *
 * 空间复杂度：O(1)
 * - 只使用了常数级别的额外空间
 * - 没有使用与链表长度相关的额外存储空间
 */
public class GetIntersectionNode160 {

    /**
     * 链表节点定义
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入链表A
        System.out.println("请输入链表A的节点数：");
        int aLength = scanner.nextInt();
        ListNode headA = null;
        ListNode currentA = null;

        System.out.println("请输入链表A的节点值：");
        for (int i = 0; i < aLength; i++) {
            int val = scanner.nextInt();
            ListNode node = new ListNode(val);
            if (headA == null) {
                headA = node;
                currentA = node;
            } else {
                currentA.next = node;
                currentA = node;
            }
        }

        // 提示用户输入链表B
        System.out.println("请输入链表B的节点数：");
        int bLength = scanner.nextInt();
        ListNode headB = null;
        ListNode currentB = null;

        System.out.println("请输入链表B的节点值：");
        for (int i = 0; i < bLength; i++) {
            int val = scanner.nextInt();
            ListNode node = new ListNode(val);
            if (headB == null) {
                headB = node;
                currentB = node;
            } else {
                currentB.next = node;
                currentB = node;
            }
        }

        // 输入相交节点位置（可选）
        System.out.println("请输入链表A中相交节点的位置（从0开始，-1表示无相交）：");
        int intersectPos = scanner.nextInt();

        // 创建相交节点
        if (intersectPos >= 0) {
            // 找到链表A的相交节点
            ListNode intersectNode = headA;
            for (int i = 0; i < intersectPos && intersectNode != null; i++) {
                intersectNode = intersectNode.next;
            }

            // 将链表B的尾部连接到相交节点
            if (intersectNode != null) {
                ListNode tailB = headB;
                while (tailB.next != null) {
                    tailB = tailB.next;
                }
                tailB.next = intersectNode;
            }
        }

        // 调用 getIntersectionNode 方法查找相交节点
        ListNode result = getIntersectionNode(headA, headB);

        // 输出结果
        if (result != null) {
            System.out.println("两个链表相交于节点值: " + result.val);
        } else {
            System.out.println("两个链表不相交");
        }
    }

    /**
     * 找到两个单链表相交的起始节点
     *
     * 算法思路：
     * 使用双指针技巧，让两个指针分别遍历两个链表
     * 当一个指针到达链表末尾时，让它从另一个链表的头部开始
     * 这样两个指针最终会在相交节点相遇，或者同时到达末尾（无相交）
     *
     * 核心原理：
     * 假设链表A长度为a+c，链表B长度为b+c（c为相交部分长度）
     * 指针A走过的路径：a+c+b
     * 指针B走过的路径：b+c+a
     * 两者相等，如果存在相交节点，必然在相交节点相遇
     *
     * 示例过程（链表A: 4->1->8->4->5, 链表B: 5->6->1->8->4->5）：
     *
     * 初始状态:
     * 指针A: 4->1->8->4->5->5->6->1->8->4->5 (到达末尾后从B开始)
     * 指针B: 5->6->1->8->4->5->4->1->8->4->5 (到达末尾后从A开始)
     *
     * 在第8个节点处相遇，即相交节点
     *
     * @param headA 链表A的头节点
     * @param headB 链表B的头节点
     * @return 如果存在相交节点，返回相交节点；否则返回null
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 边界条件检查
        if (headA == null || headB == null) {
            return null;
        }

        // 初始化两个指针
        ListNode pointerA = headA;
        ListNode pointerB = headB;

        // 当两个指针不相等时继续循环
        while (pointerA != pointerB) {
            // 如果指针A到达链表末尾，则从链表B的头部开始
            if (pointerA == null) {
                pointerA = headB;
            } else {
                pointerA = pointerA.next;
            }

            // 如果指针B到达链表末尾，则从链表A的头部开始
            if (pointerB == null) {
                pointerB = headA;
            } else {
                pointerB = pointerB.next;
            }
        }

        // 返回相交节点（如果无相交则返回null）
        return pointerA;
    }
}
