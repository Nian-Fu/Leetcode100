package com.funian.algorithm.algorithm;

/**
 * 合并两个有序链表（LeetCode 21）
 *
 * 时间复杂度：O(m + n)
 * - m 是第一个链表的长度，n 是第二个链表的长度
 * - 每个节点只被访问一次
 * - 总时间复杂度为两个链表长度之和
 *
 * 空间复杂度：O(1)
 * - 只使用了常数个额外变量（dummy、current等）
 * - 不使用与输入规模相关的额外空间
 */
public class MergeTwoList21 {

    /**
     * 链表节点定义
     */
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * 合并两个有序链表
     *
     * 算法思路：
     * 使用双指针技术，依次比较两个链表的节点值
     * 将较小的节点连接到结果链表中
     * 最后将剩余的节点直接连接到结果链表
     *
     * 执行过程分析（以 l1=[1,2,4], l2=[1,3,4] 为例）：
     *
     * 初始状态：
     * l1: 1 -> 2 -> 4 -> null
     * l2: 1 -> 3 -> 4 -> null
     * dummy -> null
     * current -> dummy
     *
     * 第1次比较（1 vs 1）：
     * 选择l1的节点1
     * dummy -> 1 -> null
     * current -> 1
     * l1: 2 -> 4 -> null
     * l2: 1 -> 3 -> 4 -> null
     *
     * 第2次比较（2 vs 1）：
     * 选择l2的节点1
     * dummy -> 1 -> 1 -> null
     * current -> 1（第二个）
     * l1: 2 -> 4 -> null
     * l2: 3 -> 4 -> null
     *
     * 第3次比较（2 vs 3）：
     * 选择l1的节点2
     * dummy -> 1 -> 1 -> 2 -> null
     * current -> 2
     * l1: 4 -> null
     * l2: 3 -> 4 -> null
     *
     * 第4次比较（4 vs 3）：
     * 选择l2的节点3
     * dummy -> 1 -> 1 -> 2 -> 3 -> null
     * current -> 3
     * l1: 4 -> null
     * l2: 4 -> null
     *
     * 第5次比较（4 vs 4）：
     * 选择l1的节点4（相等时选择l1）
     * dummy -> 1 -> 1 -> 2 -> 3 -> 4 -> null
     * current -> 4
     * l1: null
     * l2: 4 -> null
     *
     * l1为空，将l2剩余部分连接到结果链表：
     * dummy -> 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> null
     *
     * 返回 dummy.next，即 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> null
     *
     * @param l1 第一个有序链表的头节点
     * @param l2 第二个有序链表的头节点
     * @return 合并后的有序链表的头节点
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 创建哨兵节点，简化边界条件的处理
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // 遍历两个链表，直到其中一个为空
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        // 将剩余节点链接到结果链表中
        if (l1 != null) {
            current.next = l1;
        } else {
            current.next = l2;
        }

        // 返回合并后的链表的头节点
        return dummy.next;
    }

    /**
     * 递归解法（补充）
     *
     * 算法思路：
     * 将问题分解为更小的子问题
     * 每次选择较小的节点作为当前节点，然后递归处理剩余部分
     *
     * @param l1 第一个有序链表的头节点
     * @param l2 第二个有序链表的头节点
     * @return 合并后的有序链表的头节点
     */
    public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        // 基础情况：其中一个链表为空
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // 递归情况：选择较小的节点
        if (l1.val <= l2.val) {
            l1.next = mergeTwoListsRecursive(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsRecursive(l1, l2.next);
            return l2;
        }
    }

    /**
     * 辅助方法：创建链表（用于测试）
     */
    public ListNode createList(int[] values) {
        if (values.length == 0) return null;
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        return head;
    }

    /**
     * 辅助方法：打印链表（用于测试）
     */
    public void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println(" -> null");
    }

    /**
     * 测试方法和使用示例
     */
    public static void main(String[] args) {
        MergeTwoList21 solution = new MergeTwoList21();

        // 创建测试链表
        // l1: 1 -> 2 -> 4
        ListNode l1 = solution.createList(new int[]{1, 2, 4});
        // l2: 1 -> 3 -> 4
        ListNode l2 = solution.createList(new int[]{1, 3, 4});

        System.out.println("链表1:");
        solution.printList(l1);
        System.out.println("链表2:");
        solution.printList(l2);

        // 测试迭代解法
        ListNode merged = solution.mergeTwoLists(l1, l2);
        System.out.println("合并后的链表（迭代）:");
        solution.printList(merged);

        // 重新创建链表用于递归测试
        l1 = solution.createList(new int[]{1, 2, 4});
        l2 = solution.createList(new int[]{1, 3, 4});

        // 测试递归解法
        merged = solution.mergeTwoListsRecursive(l1, l2);
        System.out.println("合并后的链表（递归）:");
        solution.printList(merged);
    }
}
