# Leetcode100: 算法学习与实践仓库

## 简介

Leetcode100 是一个专注于 LeetCode 算法题解的仓库，旨在帮助开发者系统学习和实践算法知识。仓库包含 100 道经典 LeetCode 题目的详细解题方案，每道题目都提供多种解法、复杂度分析和示例执行过程，适合算法初学者和进阶者学习参考。

## 仓库特点

1. **详尽的复杂度分析**：每道题目均包含时间复杂度和空间复杂度的详细分析，帮助理解算法效率。
2. **多种解题方法**：针对同一问题提供不同的解决方案，并附带详细的代码注释，便于对比学习。
3. **示例执行过程**：每个解法都配有样本案例的分步执行分析，直观展示算法运行流程。
4. **便捷的学习体验**：集中整理经典题目，避免学习过程中频繁切换平台，提高学习效率。

## 项目结构

```
Leetcode100/
├── src/main/java/com/funian/algorithm/algorithm/  # 算法题解代码
│   ├── TwoSum1.java          # 两数之和
│   ├── LRUCache146.java      # LRU缓存机制
│   ├── LongestCommonSubsequence1143.java  # 最长公共子序列
│   └── MinWindow76.java      # 最小覆盖子串
├── pom.xml                   # Maven配置文件
├── mvnw.cmd                  # Maven包装器脚本
└── .gitignore                # Git忽略文件配置
```

## 使用方法

1. 克隆仓库到本地：
   ```bash
   git clone https://github.com/Nian-Fu/Leetcode100.git
   ```

2. 环境要求：
   - JDK 21 及以上
   - Maven 3.6 及以上（或使用项目自带的 `mvnw` 脚本）

3. 运行示例（以 TwoSum 为例）：
   ```bash
   # 编译项目
   ./mvnw compile
   
   # 运行指定类
   ./mvnw exec:java -Dexec.mainClass="com.funian.algorithm.algorithm.TwoSum1"
   ```

4. 交互操作：程序运行后，根据控制台提示输入测试数据，即可查看算法执行结果。

## 贡献指南

欢迎提交 Issue 或 Pull Request 来完善仓库内容，包括但不限于：
- 补充新的解题方法
- 优化现有代码
- 修正文档错误
- 添加更多经典题目

---

# Leetcode100: Algorithm Learning and Practice Repository

## Introduction

Leetcode100 is a repository focused on LeetCode algorithm solutions, designed to help developers systematically learn and practice algorithm knowledge. The repository contains detailed solutions to 100 classic LeetCode problems, each with multiple solution methods, complexity analysis, and example execution processes, suitable for both beginners and advanced learners of algorithms.

## Repository Advantages

1. **Detailed Complexity Analysis**: Each problem includes a detailed analysis of time complexity and space complexity to help understand algorithm efficiency.
2. **Multiple Solution Methods**: Different solutions are provided for the same problem, along with detailed code comments for comparative learning.
3. **Sample Execution Process**: Each solution is accompanied by a step-by-step execution analysis of sample cases, intuitively demonstrating the algorithm's running process.
4. **Convenient Learning Experience**: Classic problems are centrally organized to avoid frequent platform switching during learning, improving learning efficiency.

## Project Structure

```
Leetcode100/
├── src/main/java/com/funian/algorithm/algorithm/  # Algorithm solution code
│   ├── TwoSum1.java          # Two Sum
│   ├── LRUCache146.java      # LRU Cache
│   ├── LongestCommonSubsequence1143.java  # Longest Common Subsequence
│   └── MinWindow76.java      # Minimum Window Substring
├── pom.xml                   # Maven configuration file
├── mvnw.cmd                  # Maven wrapper script
└── .gitignore                # Git ignore file configuration
```

## Usage

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/Nian-Fu/Leetcode100.git
   ```

2. Environment requirements:
   - JDK 21 or higher
   - Maven 3.6 or higher (or use the project's built-in `mvnw` script)

3. Run an example (taking TwoSum as an example):
   ```bash
   # Compile the project
   ./mvnw compile
   
   # Run the specified class
   ./mvnw exec:java -Dexec.mainClass="com.funian.algorithm.algorithm.TwoSum1"
   ```

4. Interactive operation: After running the program, enter test data according to the console prompts to view the algorithm execution results.

## Contribution Guidelines

Issues or Pull Requests are welcome to improve the repository content, including but not limited to:
- Adding new solution methods
- Optimizing existing code
- Correcting documentation errors
- Adding more classic problems
