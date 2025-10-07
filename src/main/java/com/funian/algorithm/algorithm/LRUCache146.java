package com.funian.algorithm.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * LRU缓存机制（LeetCode 146）
 *
 * 时间复杂度：O(1)
 * - get和put操作的平均时间复杂度都是O(1)
 * - 哈希表的查找、插入和删除操作平均时间复杂度为O(1)
 * - 双向链表的插入和删除操作时间复杂度为O(1)
 *
 * 空间复杂度：O(capacity)
 * - 哈希表和双向链表最多存储capacity个节点
 */
public class LRUCache146 {

    /**
     * 双向链表节点定义
     */
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;

        public DLinkedNode() {}

        public DLinkedNode(int _key, int _value) {
            key = _key;
            value = _value;
        }
    }

    // 哈希表：用于快速定位节点
    // cache key到节点的映射
    private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
    // size 当前缓存大小
    private int size;
    // capacity 缓存容量
    private int capacity;
    // head 双向链表的伪头节点
    // tail 双向链表的伪尾节点
    private DLinkedNode head, tail;

    /**
     * 构造函数：初始化LRU缓存
     *
     * @param capacity 缓存容量
     */
    public LRUCache146(int capacity) {
        // this.size = 0 初始化缓存大小为0
        this.size = 0;
        // this.capacity = capacity 设置缓存容量
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点简化链表操作
        // head = new DLinkedNode() 创建伪头节点
        head = new DLinkedNode();
        // tail = new DLinkedNode() 创建伪尾节点
        tail = new DLinkedNode();
        // head.next = tail 伪头节点指向伪尾节点
        head.next = tail;
        // tail.prev = head 伪尾节点指向伪头节点
        tail.prev = head;
    }

    /**
     * 获取缓存中key对应的value
     *
     * 算法思路：
     * 1. 如果key不存在，返回-1
     * 2. 如果key存在，通过哈希表定位节点，将其移到链表头部，并返回value
     *
     * @param key 要获取的键
     * @return 对应的值，如果不存在返回-1
     */
    public int get(int key) {
        // DLinkedNode node = cache.get(key) 通过哈希表查找节点
        DLinkedNode node = cache.get(key);
        // if (node == null) 检查节点是否存在
        if (node == null) {
            // return -1 节点不存在，返回-1
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        // moveToHead(node) 将节点移到链表头部
        moveToHead(node);
        // return node.value 返回节点的值
        return node.value;
    }

    /**
     * 向缓存中添加或更新键值对
     *
     * 算法思路：
     * 1. 如果key不存在：
     *    - 创建新节点
     *    - 添加到哈希表
     *    - 添加到链表头部
     *    - 如果超出容量，删除链表尾部节点和哈希表中对应项
     * 2. 如果key存在：
     *    - 更新value
     *    - 将节点移到链表头部
     *
     * @param key 键
     * @param value 值
     */
    public void put(int key, int value) {
        // DLinkedNode node = cache.get(key) 通过哈希表查找节点
        DLinkedNode node = cache.get(key);
        // if (node == null) 检查节点是否存在
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            // DLinkedNode newNode = new DLinkedNode(key, value) 创建新节点
            DLinkedNode newNode = new DLinkedNode(key, value);
            // 添加进哈希表
            // cache.put(key, newNode) 将新节点添加到哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            // addToHead(newNode) 将新节点添加到链表头部
            addToHead(newNode);
            // ++size 增加缓存大小
            ++size;
            // if (size > capacity) 检查是否超出容量
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                // DLinkedNode tail = removeTail() 删除链表尾部节点
                DLinkedNode tail = removeTail();
                // 删除哈希表中对应的项
                // cache.remove(tail.key) 从哈希表中删除尾部节点
                cache.remove(tail.key);
                // --size 减少缓存大小
                --size;
            }
        }
        else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            // node.value = value 更新节点的值
            node.value = value;
            // moveToHead(node) 将节点移到链表头部
            moveToHead(node);
        }
    }

    /**
     * 辅助方法：将节点添加到链表头部
     *
     * @param node 要添加的节点
     */
    private void addToHead(DLinkedNode node) {
        // node.prev = head 节点的prev指向head
        node.prev = head;
        // node.next = head.next 节点的next指向原来head的下一个节点
        node.next = head.next;
        // head.next.prev = node 原来head的下一个节点的prev指向node
        head.next.prev = node;
        // head.next = node head的next指向node
        head.next = node;
    }

    /**
     * 辅助方法：从链表中删除指定节点
     *
     * @param node 要删除的节点
     */
    private void removeNode(DLinkedNode node) {
        // node.prev.next = node.next 节点前一个节点的next指向节点的下一个节点
        node.prev.next = node.next;
        // node.next.prev = node.prev 节点后一个节点的prev指向节点的前一个节点
        node.next.prev = node.prev;
    }

    /**
     * 辅助方法：将节点移动到链表头部
     *
     * @param node 要移动的节点
     */
    private void moveToHead(DLinkedNode node) {
        // removeNode(node) 先删除节点
        removeNode(node);
        // addToHead(node) 再将节点添加到头部
        addToHead(node);
    }

    /**
     * 辅助方法：删除链表尾部节点
     *
     * @return 被删除的节点
     */
    private DLinkedNode removeTail() {
        // DLinkedNode res = tail.prev 获取尾部节点（最后一个有效节点）
        DLinkedNode res = tail.prev;
        // removeNode(res) 删除该节点
        removeNode(res);
        // return res 返回被删除的节点
        return res;
    }

    /**
     * 辅助方法：打印当前缓存状态
     */
    public void printCache() {
        // System.out.print("当前缓存内容（从最近使用到最久未使用）: ") 打印提示信息
        System.out.print("当前缓存内容（从最近使用到最久未使用）: ");
        // DLinkedNode current = head.next 当前节点指向头节点的下一个节点
        DLinkedNode current = head.next;
        // while (current != tail) 当前节点不等于尾节点时继续
        while (current != tail) {
            // System.out.print("(" + current.key + "," + current.value + ") ") 打印节点信息
            System.out.print("(" + current.key + "," + current.value + ") ");
            // current = current.next 移动到下一个节点
            current = current.next;
        }
        // System.out.println() 换行
        System.out.println();
        // System.out.println("当前缓存大小: " + size + "/" + capacity) 打印缓存大小信息
        System.out.println("当前缓存大小: " + size + "/" + capacity);
    }

    /**
     * 主函数：处理用户输入并演示LRU缓存操作
     */
    public static void main(String[] args) {
        // 创建Scanner对象读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 输入缓存容量
        System.out.print("请输入LRU缓存的容量: ");
        // 读取缓存容量
        int capacity = scanner.nextInt();
        // 创建LRUCache146实例
        LRUCache146 lruCache = new LRUCache146(capacity);

        // 打印初始化完成信息
        System.out.println("LRU缓存初始化完成，容量为: " + capacity);

        // 演示操作循环
        while (true) {
            // 打印操作选项
            System.out.println("\n请选择操作:");
            System.out.println("1. put(key, value) - 添加或更新缓存");
            System.out.println("2. get(key) - 获取缓存值");
            System.out.println("3. 查看当前缓存状态");
            System.out.println("4. 退出");
            System.out.print("请输入选项(1-4): ");

            // 读取用户选择
            int choice = scanner.nextInt();

            // 根据用户选择执行相应操作
            switch (choice) {
                case 1:
                    System.out.print("请输入key: ");
                    // 读取要put的key
                    int putKey = scanner.nextInt();
                    System.out.print("请输入value: ");
                    // 读取要put的value
                    int putValue = scanner.nextInt();
                    // 调用put方法
                    lruCache.put(putKey, putValue);
                    // 打印操作信息
                    System.out.println("执行 put(" + putKey + ", " + putValue + ")");
                    break;

                case 2:
                    System.out.print("请输入key: ");
                    // 读取要get的key
                    int getKey = scanner.nextInt();
                    // 调用get方法
                    int result = lruCache.get(getKey);
                    // 根据结果打印相应信息
                    if (result == -1) {
                        System.out.println("执行 get(" + getKey + ")，结果: 未找到");
                    } else {
                        System.out.println("执行 get(" + getKey + ")，结果: " + result);
                    }
                    break;

                case 3:
                    // 调用printCache方法打印缓存状态
                    lruCache.printCache();
                    break;

                case 4:
                    // 退出程序
                    System.out.println("退出程序");
                    // 关闭scanner
                    scanner.close();
                    // 退出main方法
                    return;

                default:
                    // 处理无效选项
                    System.out.println("无效选项，请重新输入");
            }
        }
    }
}
