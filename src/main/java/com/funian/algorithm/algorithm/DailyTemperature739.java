package com.funian.algorithm.algorithm;

import java.util.Scanner;
import java.util.Stack;
import java.util.Arrays;

/**
 * 每日温度（LeetCode 739）- 单调栈
 *
 * 时间复杂度：O(n)
 * - 每个元素最多入栈和出栈一次
 *
 * 空间复杂度：O(n)
 * - 栈最多存储n个元素
 * - 结果数组需要n个空间
 */
public class DailyTemperature739 {

    /**
     * 主函数：处理用户输入并计算每日温度问题
     *
     * 算法流程：
     * 1. 读取用户输入的温度数组
     * 2. 调用[dailyTemperatures](file:///Users/funian/Documents/JavaProject/Algorithm/src/main/java/com/funian/algorithm/algorithm/DailyTemperature739.java#L125-L151)方法计算结果
     * 3. 输出结果
     */
    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in) 创建Scanner对象用于读取输入
        Scanner scanner = new Scanner(System.in);
        // System.out.println("请输入每天的温度（以空格分隔）：") 打印输入提示
        System.out.println("请输入每天的温度（以空格分隔）：");

        // 读取用户输入的温度字符串
        // 例如用户输入："73 74 75 71 69 72 76 73"
        // String line = scanner.nextLine() 读取用户输入的一行
        String line = scanner.nextLine();

        // 按空格分割字符串得到字符串数组
        // line.split(" ") 将 "73 74 75 71 69 72 76 73" 分割为 ["73","74","75","71","69","72","76","73"]
        // String[] strTemps = line.split(" ") 按空格分割字符串
        String[] strTemps = line.split(" ");

        // 创建整型数组存储温度
        // new int[strTemps.length] 创建长度为8的整型数组
        // int[] temperatures = new int[strTemps.length] 创建温度数组
        int[] temperatures = new int[strTemps.length];

        // 将输入的字符串温度转换为整数数组
        // 逐个将字符串转换为整数并存储到temperatures数组中
        // for (int i = 0; i < strTemps.length; i++) 遍历字符串数组
        for (int i = 0; i < strTemps.length; i++) {
            // Integer.parseInt(strTemps[i]) 将字符串转换为整数
            // i=0: strTemps[0]="73" -> temperatures[0]=73
            // i=1: strTemps[1]="74" -> temperatures[1]=74
            // i=2: strTemps[2]="75" -> temperatures[2]=75
            // ...以此类推
            // temperatures[i] = Integer.parseInt(strTemps[i]) 将字符串转换为整数并存储
            temperatures[i] = Integer.parseInt(strTemps[i]);
        }
        // 最终得到 temperatures = [73,74,75,71,69,72,76,73]

        // 调用dailyTemperatures方法计算结果
        // int[] answer = dailyTemperatures(temperatures) 调用dailyTemperatures方法计算结果
        int[] answer = dailyTemperatures(temperatures);

        // 输出结果
        // System.out.println("下一个更高温度出现在几天后：" + Arrays.toString(answer)) 打印结果
        System.out.println("下一个更高温度出现在几天后：" + Arrays.toString(answer));
    }

    /**
     * 计算每天需要等待多少天才能遇到更高的温度
     *
     * 算法思路：
     * 使用单调递减栈存储温度的索引
     * 1. 遍历温度数组
     * 2. 当当前温度大于栈顶索引对应的温度时，说明找到了更高温度
     * 3. 弹出栈顶元素并计算天数差
     * 4. 将当前索引入栈
     *
     * 执行过程分析（以`temperatures=[73,74,75,71,69,72,76,73]`为例）：
     *
     * 初始状态：
     * stack = []
     * answer = [0,0,0,0,0,0,0,0]
     *
     * 遍历过程：
     * i=0, temp=73:
     *   栈为空，将0入栈
     *   stack = [0]
     *
     * i=1, temp=74:
     *   74 > temperatures[0]=73，找到更高温度
     *   弹出0，answer[0] = 1-0 = 1
     *   栈为空，将1入栈
     *   stack = [1]
     *
     * i=2, temp=75:
     *   75 > temperatures[1]=74，找到更高温度
     *   弹出1，answer[1] = 2-1 = 1
     *   栈为空，将2入栈
     *   stack = [2]
     *
     * i=3, temp=71:
     *   71 < temperatures[2]=75，将3入栈
     *   stack = [2,3]
     *
     * i=4, temp=69:
     *   69 < temperatures[3]=71，将4入栈
     *   stack = [2,3,4]
     *
     * i=5, temp=72:
     *   72 > temperatures[4]=69，弹出4，answer[4] = 5-4 = 1
     *   72 > temperatures[3]=71，弹出3，answer[3] = 5-3 = 2
     *   72 < temperatures[2]=75，将5入栈
     *   stack = [2,5]
     *
     * i=6, temp=76:
     *   76 > temperatures[5]=72，弹出5，answer[5] = 6-5 = 1
     *   76 > temperatures[2]=75，弹出2，answer[2] = 6-2 = 4
     *   栈为空，将6入栈
     *   stack = [6]
     *
     * i=7, temp=73:
     *   73 < temperatures[6]=76，将7入栈
     *   stack = [6,7]
     *
     * 最终结果：answer = [1,1,4,2,1,1,0,0]
     *
     * 时间复杂度分析：
     * - 遍历温度数组：O(n)
     * - 栈操作：每个元素最多入栈和出栈一次，总共O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 栈存储空间：O(n)
     * - 结果数组：O(n)
     * - 总空间复杂度：O(n)
     *
     * @param temperatures 温度数组
     * @return 等待更高温度的天数数组
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        // 获取温度数组长度
        // n = temperatures.length = 8（对于[73,74,75,71,69,72,76,73]）
        // int n = temperatures.length 获取数组长度
        int n = temperatures.length;

        // 初始化结果数组，默认值为0
        // 创建长度为8的整型数组 answer = [0,0,0,0,0,0,0,0]
        // int[] answer = new int[n] 创建结果数组
        int[] answer = new int[n];

        // 使用栈来保存索引，维护单调递减栈
        // 创建空的Integer类型栈
        // Stack<Integer> stack = new Stack<>() 创建栈用于存储索引
        Stack<Integer> stack = new Stack<>();

        // 遍历温度数组
        // i 从 0 遍历到 7
        // for (int i = 0; i < n; i++) 遍历温度数组
        for (int i = 0; i < n; i++) {
            // 当栈不为空且当前温度大于栈顶索引对应的温度时
            // 说明找到了更高温度，可以更新结果
            // !stack.isEmpty() 检查栈是否为空
            // temperatures[i] > temperatures[stack.peek()] 比较当前温度与栈顶索引对应的温度
            // while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) 当栈不为空且当前温度大于栈顶温度时循环
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                // 弹出栈顶元素（索引）
                // stack.pop() 弹出并返回栈顶元素
                // int idx = stack.pop() 弹出栈顶索引
                int idx = stack.pop();
                // 计算天数差并更新结果数组
                // answer[idx] = i - idx 计算当前索引与弹出索引的差值
                // answer[idx] = i - idx 计算等待天数
                answer[idx] = i - idx;
            }
            // 将当前索引入栈
            // stack.push(i) 将当前索引压入栈
            // stack.push(i) 将当前索引入栈
            stack.push(i);
        }

        // 返回结果数组
        // answer = [1,1,4,2,1,1,0,0]
        // return answer 返回结果数组
        return answer;
    }

    /**
     * 方法2：从右到左的解法
     *
     * 算法思路：
     * 从右到左遍历，利用已计算的结果跳过不必要的比较
     *
     * 时间复杂度分析：
     * - 外层循环：O(n)
     * - 内层循环：平均O(1)，最坏O(n)
     * - 总时间复杂度：平均O(n)，最坏O(n²)
     *
     * 空间复杂度分析：
     * - 结果数组：O(n)
     * - 总空间复杂度：O(n)
     *
     * @param temperatures 温度数组
     * @return 等待更高温度的天数数组
     */
    public int[] dailyTemperaturesReverse(int[] temperatures) {
        // 获取温度数组长度
        // int n = temperatures.length 获取数组长度
        int n = temperatures.length;
        // 初始化结果数组
        // int[] answer = new int[n] 创建结果数组
        int[] answer = new int[n];

        // 从右到左遍历
        // i 从 n-2 遍历到 0（即从6遍历到0）
        // for (int i = n - 2; i >= 0; i--) 从右到左遍历温度数组
        for (int i = n - 2; i >= 0; i--) {
            // j表示下一个要比较的位置
            // j = i + 1 初始为当前索引的下一个位置
            // int j = i + 1 初始化比较位置
            int j = i + 1;

            // 当j在有效范围内且温度不大于当前温度时继续循环
            // while (j < n && temperatures[j] <= temperatures[i]) 当j在有效范围内且温度不大于当前温度时循环
            while (j < n && temperatures[j] <= temperatures[i]) {
                // 如果answer[j] > 0，说明位置j之后有更高温度
                // if (answer[j] > 0) 检查位置j之后是否有更高温度
                if (answer[j] > 0) {
                    // 利用已计算的结果跳转
                    // j += answer[j] 跳转到下一个更高温度的位置
                    // j += answer[j] 利用已计算结果跳转
                    j += answer[j];
                } else {
                    // 没有更高温度
                    // j = n 设置j为超出范围的值
                    j = n;
                }
            }

            // 如果j在有效范围内，说明找到了更高温度
            // if (j < n) 检查是否找到更高温度
            if (j < n) {
                // 计算天数差
                // answer[i] = j - i 计算等待天数
                answer[i] = j - i;
            }
        }

        // return answer 返回结果数组
        return answer;
    }

    /**
     * 方法3：暴力解法（仅供对比）
     *
     * 算法思路：
     * 对于每个温度，向后遍历寻找第一个更高温度
     *
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     *
     * @param temperatures 温度数组
     * @return 等待更高温度的天数数组
     */
    public int[] dailyTemperaturesBruteForce(int[] temperatures) {
        // 获取温度数组长度
        // int n = temperatures.length 获取数组长度
        int n = temperatures.length;
        // 初始化结果数组
        // int[] answer = new int[n] 创建结果数组
        int[] answer = new int[n];

        // 外层循环遍历每个温度
        // for (int i = 0; i < n; i++) 外层循环遍历每个温度
        for (int i = 0; i < n; i++) {
            // 内层循环向后寻找更高温度
            // for (int j = i + 1; j < n; j++) 内层循环向后寻找更高温度
            for (int j = i + 1; j < n; j++) {
                // 如果找到更高温度
                // if (temperatures[j] > temperatures[i]) 检查是否找到更高温度
                if (temperatures[j] > temperatures[i]) {
                    // 计算天数差
                    // answer[i] = j - i 计算等待天数
                    answer[i] = j - i;
                    // 跳出内层循环
                    // break 跳出内层循环
                    break;
                }
            }
        }

        // return answer 返回结果数组
        return answer;
    }

    /**
     * 方法4：使用数组代替栈（优化空间）
     *
     * 算法思路：
     * 使用数组模拟栈操作，避免使用Stack类的开销
     *
     * 时间复杂度分析：
     * - 遍历温度数组：O(n)
     * - 数组操作：每个元素最多入栈和出栈一次，总共O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 模拟栈数组：O(n)
     * - 结果数组：O(n)
     * - 总空间复杂度：O(n)
     *
     * @param temperatures 温度数组
     * @return 等待更高温度的天数数组
     */
    public int[] dailyTemperaturesArrayStack(int[] temperatures) {
        // int n = temperatures.length 获取数组长度
        int n = temperatures.length;
        // int[] answer = new int[n] 创建结果数组
        int[] answer = new int[n];
        // 使用数组模拟栈
        // int[] stack = new int[n] 创建模拟栈的数组
        int[] stack = new int[n];
        // 栈顶指针
        // int top = -1 初始化栈顶指针
        int top = -1;

        // for (int i = 0; i < n; i++) 遍历温度数组
        for (int i = 0; i < n; i++) {
            // 当栈不为空且当前温度大于栈顶索引对应的温度时
            // while (top >= 0 && temperatures[i] > temperatures[stack[top]]) 当栈不为空且当前温度大于栈顶温度时循环
            while (top >= 0 && temperatures[i] > temperatures[stack[top]]) {
                // 弹出栈顶元素
                // int idx = stack[top--] 弹出栈顶索引
                int idx = stack[top--];
                // 计算天数差
                // answer[idx] = i - idx 计算等待天数
                answer[idx] = i - idx;
            }
            // 将当前索引入栈
            // stack[++top] = i 将当前索引入栈
            stack[++top] = i;
        }

        // return answer 返回结果数组
        return answer;
    }
}
