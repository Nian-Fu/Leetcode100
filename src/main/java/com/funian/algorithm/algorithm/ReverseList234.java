package com.funian.algorithm.algorithm;

import java.util.Scanner;

/**
 * 回文链表
 *
 * 时间复杂度：O(n)
 * - 找到中间节点：O(n/2)
 * - 反转后半部分：O(n/2)
 * - 比较两部分：O(n/2)
 * - 恢复链表：O(n/2)
 * - 总时间复杂度：O(n)
 *
 * 空间复杂度：O(1)
 * - 只使用了常数级别的额外空间
 * - 没有使用递归或额外的数据结构
 */
public class ReverseList234 {

    /**
     * 链表节点定义
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入链表节点数
        System.out.print("请输入链表的节点数: ");
        int n = scanner.nextInt();

        // 创建链表
        ListNode head = null;
        ListNode current = null;
        System.out.println("请输入链表的节点值: ");
        for (int i = 0; i < n; i++) {
            int val = scanner.nextInt();
            ListNode node = new ListNode(val);
            if (head == null) {
                head = node;
                current = node;
            } else {
                current.next = node;
                current = node;
            }
        }

        // 创建解决方案实例
        ReverseList234 solution = new ReverseList234();

        // 调用 isPalindrome 方法检查是否为回文链表
        boolean result = solution.isPalindrome(head);

        // 输出结果
        if (result) {
            System.out.println("该链表是回文链表");
        } else {
            System.out.println("该链表不是回文链表");
        }

        scanner.close();
    }

    /**
     * 判断链表是否为回文链表
     *
     * 算法思路：
     * 1. 使用快慢指针找到链表的中间节点
     * 2. 反转链表的后半部分
     * 3. 比较前半部分和反转后的后半部分
     * 4. 恢复链表结构（可选）
     *
     * 示例过程（以链表 1->2->2->1 为例）：
     *
     * 原链表: 1 -> 2 -> 2 -> 1
     *
     * 步骤1 - 找到中间节点:
     * 快指针: 1 -> 2 -> 2 (每次走两步)
     * 慢指针: 1 -> 2 (每次走一步)
     * 中间节点: 2 (第一个2)
     *
     * 步骤2 - 反转后半部分:
     * 原后半部分: 2 -> 1
     * 反转后: 1 -> 2
     *
     * 步骤3 - 比较两部分:
     * 前半部分: 1 -> 2
     * 后半部分: 1 -> 2
     * 比较结果: 相等，是回文链表
     *
     * 步骤4 - 恢复链表:
     * 将后半部分再次反转恢复原状
     *
     * @param head 链表的头节点
     * @return 如果是回文链表返回true，否则返回false
     */
    public boolean isPalindrome(ListNode head) {
        // 边界条件检查：空链表被认为是回文链表
        if (head == null) return true;

        // 使用快慢指针找到链表的中间节点
        ListNode firstHalfEnd = endOfFirstHalf(head);
        // 反转链表的后半部分
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 检查链表是否回文
        ListNode p1 = head;  // 指向前半部分的头节点
        ListNode p2 = secondHalfStart;  // 指向后半部分的头节点
        boolean result = true;
        // 比较两个部分的节点值
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;  // 发现不相等的节点值，不是回文链表
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 恢复链表结构（可选，保持原链表不变）
        firstHalfEnd.next = reverseList(secondHalfStart);

        return result;
    }

    /**
     * 反转链表
     * 使用迭代方法反转链表
     *
     * @param head 需要反转的链表头节点
     * @return 反转后的链表头节点
     */
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;  // 前一个节点
        ListNode curr = head;  // 当前节点
        // 遍历链表，逐个反转节点指向
        while (curr != null) {
            ListNode next = curr.next;  // 保存下一个节点
            curr.next = prev;  // 反转当前节点的指向
            prev = curr;  // 移动prev指针
            curr = next;  // 移动curr指针
        }
        return prev;  // 返回新的头节点
    }

    /**
     * 使用快慢指针找到链表的中间节点
     *
     * @param head 链表的头节点
     * @return 链表前半部分的最后一个节点
     */
    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;  // 快指针，每次移动两步
        ListNode slow = head;  // 慢指针，每次移动一步
        // 当快指针还能继续移动时
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;      // 慢指针移动一步
            fast = fast.next.next; // 快指针移动两步
        }
        return slow;  // 返回前半部分的最后一个节点
    }

}
