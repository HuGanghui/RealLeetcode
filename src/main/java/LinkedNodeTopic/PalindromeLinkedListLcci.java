package LinkedNodeTopic;

import java.util.Stack;

/**
 * 面试题 02.06. 回文链表 Easy
 * https://leetcode-cn.com/problems/palindrome-linked-list-lcci/
 *
 * 看到[1,2,2,1]的例子，我就想到了使用栈，但是一开始忽视了回文还可以是[1,0,1]这种，这种总数为奇数的，
 * 就得不考虑中间的元素。
 */
public class PalindromeLinkedListLcci {
    Stack<Integer> stack = new Stack();

    public boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) {
            return true;
        }

        int size = 0;
        ListNode headB = head;
        while (headB != null) {
            size++;
            headB = headB.next;
        }

        int middle = -1;
        if (size % 2 != 0) {
            middle = (size - 1) / 2; // 从0开始算
        }

        int i = 0;
        while (head != null) {
            if (middle != 0 && i == middle) {

            } else if (!stack.isEmpty() && stack.peek() == head.val) {
                stack.pop();
            } else {
                stack.push(head.val);
            }
            head = head.next;
            i++;
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
