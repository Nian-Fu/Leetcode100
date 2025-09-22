package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 链表反转算法
 *
 * 时间复杂度：O(n)
 * - 只需要遍历链表一次
 * - 每个节点只访问一次
 * - 总时间复杂度为 O(n)，其中 n 是链表的长度
 *
 * 空间复杂度：O(1)
 * - 只使用了常数个额外变量（prev、curr、next）
 * - 没有使用与输入规模相关的额外空间
 * - 递归调用栈深度为常数级别
 */


public class ReverseList206 {

    // 定义链表节点类
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }
    /**
     * 主函数：处理用户输入并输出链表反转结果
     *
     * 算法流程：
     * 1. 读取用户输入的链表长度和元素
     * 2. 构建原始链表
     * 3. 调用reverseList方法反转链表
     * 4. 输出反转后的链表
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入链表的长度
        System.out.print("请输入链表的长度: ");
        int n = scanner.nextInt();
        if (n <= 0) {
            System.out.println("链表长度必须大于0");
            return;
        }

        // 提示用户输入链表的元素
        System.out.println("请输入链表的元素:");
        ListNode head = new ListNode(scanner.nextInt());
        ListNode current = head;

        for (int i = 1; i < n; i++) {
            current.next = new ListNode(scanner.nextInt());
            current = current.next;
        }

        // 调用反转链表的方法
        ListNode reversedHead = reverseList(head);

        // 输出反转后的链表
        System.out.println("反转后的链表:");
        printList(reversedHead);

        scanner.close();
    }

    /**
     * 反转链表的核心方法
     *
     * 算法思路：
     * 使用三个指针（prev、curr、next）逐个反转链表中的节点指向关系
     * 通过迭代方式实现链表反转，不使用额外的存储空间
     *
     * 执行过程分析（以链表 1->2->3->4->NULL 为例）：
     *
     * 初始状态：
     * prev = null, curr = 1->2->3->4->NULL
     *
     * 第1次迭代：
     * next = 2->3->4->NULL
     * curr.next = null (1->null)
     * prev = 1->null
     * curr = 2->3->4->NULL
     *
     * 第2次迭代：
     * next = 3->4->NULL
     * curr.next = 1->null (2->1->null)
     * prev = 2->1->null
     * curr = 3->4->NULL
     *
     * 第3次迭代：
     * next = 4->NULL
     * curr.next = 2->1->null (3->2->1->null)
     * prev = 3->2->1->null
     * curr = 4->NULL
     *
     * 第4次迭代：
     * next = null
     * curr.next = 3->2->1->null (4->3->2->1->null)
     * prev = 4->3->2->1->null
     * curr = null
     *
     * 循环结束，返回 prev（即 4->3->2->1->NULL）
     *
     * @param head 原始链表的头节点
     * @return 反转后链表的头节点
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;    // 指向前一个节点，初始为null
        ListNode curr = head;    // 指向当前节点，初始为头节点

        // 当当前节点不为null时继续循环
        while (curr != null) {
            ListNode next = curr.next; // 暂存下一个节点，防止丢失
            curr.next = prev;          // 当前节点的next指向前一个节点
            prev = curr;               // prev前移，指向当前节点
            curr = next;               // curr前移，指向下一个节点
        }

        return prev;  // 返回新链表的头节点（原链表的最后一个节点）
    }

    /**
     * 打印链表的方法
     *
     * 算法思路：
     * 从头节点开始，依次遍历每个节点并打印其值
     * 直到遇到null为止
     *
     * @param head 链表的头节点
     */
    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}
