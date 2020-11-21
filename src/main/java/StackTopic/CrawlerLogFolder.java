package StackTopic;

import java.util.Stack;

/**
 * 1598. 文件夹操作日志搜集器
 * https://leetcode-cn.com/problems/crawler-log-folder/
 *
 * 一个字——栈。
 */
public class CrawlerLogFolder {
    public int minOperations(String[] logs) {
        Stack<String> stack = new Stack<>();
        for (String ele : logs) {
            if (ele.equals("../")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (ele.equals("./")) {

            } else {
                stack.push(ele);
            }
        }

        return stack.size();
    }
}
