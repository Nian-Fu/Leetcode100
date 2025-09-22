package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 两数相加（LeetCode 2）
 *
 * 时间复杂度：O(max(m, n))
 * - m 是第一个链表的长度，n 是第二个链表的长度
 * - 需要遍历两个链表，直到两个链表都遍历完且无进位
 * - 总时间复杂度为两个链表中较长者的长度
 *
 * 空间复杂度：O(max(m, n))
 * - 结果链表的长度最多为 max(m, n) + 1
 * - 需要创建新节点存储结果
 */
public class AddTwoList2 {

    /**
     * 链表节点定义
     */
    static class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
    }

    /**
     * 主函数：处理用户输入并输出两数相加的结果
     *
     * 算法流程：
     * 1. 读取用户输入的两个链表
     * 2. 调用addTwoNumbers方法计算两数之和
     * 3. 输出结果链表
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 输入第一个链表
        System.out.println("输入第一个链表（用空格分隔每个节点的值）：");
        ListNode l1 = createList(scanner);

        // 输入第二个链表
        System.out.println("输入第二个链表（用空格分隔每个节点的值）：");
        ListNode l2 = createList(scanner);

        ListNode result = addTwoNumbers(l1, l2);
        System.out.println("结果链表：");
        printList(result);
    }

    /**
     * 创建链表的辅助方法
     *
     * 算法思路：
     * 使用哑节点简化链表创建过程
     * 依次将输入的数值转换为链表节点
     *
     * @param scanner Scanner对象用于读取输入
     * @return 创建的链表头节点
     */
    private static ListNode createList(Scanner scanner) {
        String line = scanner.nextLine();
        String[] strArray = line.split(" ");
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        for (String s : strArray) {
            current.next = new ListNode(Integer.parseInt(s));
            current = current.next;
        }
        return dummy.next;
    }

    /**
     * 打印链表的辅助方法
     *
     * 算法思路：
     * 从头节点开始依次遍历并打印每个节点的值
     *
     * @param head 链表的头节点
     */
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    /**
     * 两数相加的核心方法
     *
     * 算法思路：
     * 模拟手工加法过程，从低位到高位依次相加
     * 处理进位情况，直到两个链表都遍历完且无进位
     *
     * 执行过程分析（以 l1=[2,4,3], l2=[5,6,4] 为例，表示 342 + 465 = 807）：
     *
     * 初始状态：
     * l1: 2 -> 4 -> 3 -> null (表示数字 342)
     * l2: 5 -> 6 -> 4 -> null (表示数字 465)
     * dummy -> null
     * current -> dummy
     * carry = 0
     *
     * 第1位相加（2 + 5）：
     * sum = 0 + 2 + 5 = 7
     * carry = 7 / 10 = 0
     * 创建节点：new ListNode(7 % 10) = new ListNode(7)
     * dummy -> 7 -> null
     * current -> 7
     * l1: 4 -> 3 -> null
     * l2: 6 -> 4 -> null
     *
     * 第2位相加（4 + 6）：
     * sum = 0 + 4 + 6 = 10
     * carry = 10 / 10 = 1
     * 创建节点：new ListNode(10 % 10) = new ListNode(0)
     * dummy -> 7 -> 0 -> null
     * current -> 0
     * l1: 3 -> null
     * l2: 4 -> null
     *
     * 第3位相加（3 + 4）：
     * sum = 1 + 3 + 4 = 8
     * carry = 8 / 10 = 0
     * 创建节点：new ListNode(8 % 10) = new ListNode(8)
     * dummy -> 7 -> 0 -> 8 -> null
     * current -> 8
     * l1: null
     * l2: null
     *
     * 循环结束（l1和l2都为null，且carry为0）
     *
     * 返回 dummy.next，即 7 -> 0 -> 8 -> null（表示数字 807）
     *
     * @param l1 第一个数的链表表示（逆序存储）
     * @param l2 第二个数的链表表示（逆序存储）
     * @return 两数之和的链表表示（逆序存储）
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0); // 哑节点，用于简化链表操作
        ListNode current = dummy; // 当前节点指针
        int carry = 0; // 进位值

        // 遍历两个链表，直到两个链表都遍历完且无进位
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry; // 初始化和为进位

            // 如果 l1 不为空，则加上 l1 的值
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next; // 移动到下一个节点
            }

            // 如果 l2 不为空，则加上 l2 的值
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next; // 移动到下一个节点
            }

            carry = sum / 10; // 计算新的进位
            current.next = new ListNode(sum % 10); // 创建新节点，保存当前位的值
            current = current.next; // 移动到下一个节点
        }

        return dummy.next; // 返回结果链表
    }
}
