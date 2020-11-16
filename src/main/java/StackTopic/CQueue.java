package StackTopic;

import java.util.Stack;

/**
 * 剑指 Offer 09. 用两个栈实现队列 Easy
 * https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 *
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 *
 * 利用stack可以反转数组的特点，stackOne用来完成队列的正常尾部插入，top就是尾指针
 * 另一个从stackOne按顺序装入stackTwo，然后就得到逆顺序，来完成队列头部的删除功能，
 * 关键点在只有当stackTwo为空了，再从stackOne去获取元素。
 */
class CQueue {

    Stack<Integer> stackOne = new Stack<>();

    Stack<Integer> stackTwo = new Stack<>();

    public CQueue() {

    }

    public void appendTail(int value) {

        stackOne.push(value);

    }

    public int deleteHead() {

        if (stackTwo.isEmpty()) {
            while (!stackOne.isEmpty()) {
                stackTwo.push(stackOne.pop());
            }
        }

        if (!stackTwo.isEmpty()) {
            return stackTwo.pop();
        } else {
            return -1;
        }

    }

    public static void main(String[] args) {
        CQueue obj = new CQueue();
        System.out.println(obj.deleteHead());
        obj.appendTail(5);
        obj.appendTail(2);
        System.out.println(obj.deleteHead());
        System.out.println(obj.deleteHead());
    }

}