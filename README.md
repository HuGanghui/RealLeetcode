# Real Leetcode
![](https://img.shields.io/badge/Goal-AlgoAbility-brightgreen)
![](https://img.shields.io/badge/Hgh-Get%20it-green)
![](https://img.shields.io/badge/Number-37-blue)

this repository is to train and improve my algorithm ability, it will contain
most of my solutions of algorithm problems in leetcode. 

## 1. Books & Tutorials

### 1.1 Books

### 1.2 Tutorials

## 2. Courses/Seminars/Videos

## 3. Toolbox & Websites

## 4. Solutions 

### 4.1 栈和队列

#### 单调队列/栈 

（这里的单调队列表示双端队列，头尾都可插入的），使用双端队列是因为有时候栈不能完全满足，比如队列最大值就有在头部删除的需求

[1673. 找出最具竞争力的子序列](https://leetcode-cn.com/problems/find-the-most-competitive-subsequence/)

这题核心其实是要维护一个单调队列/栈（只能在尾部插入/删除），优先找最小的数，如果后面准备入队的数比当前队列中的数要小，就让队列里的数依此从尾部出列，然后加入该数。**这个队列的性质便是单调递增，队首是目前入队了的元素最小值。**

```java
for (int i = 0; i < nums.length; i++) {
	while (!deque.isEmpty() && deque.peekLast() > nums[i]) {
    	deque.removeLast();
    }
    deque.addLast(nums[i]);
}
```

同时本题有个特殊之处在于有个约束，是要找到长度为k的最具竞争力子序列，因此在不断出队的时候，最后一定要保证队列中有k个数，因此while还有额外一个约束条件 (k - deque.size()) < nums.length - i

```java
!deque.isEmpty() && deque.peekLast() > nums[i] && (k - deque.size()) < nums.length - i
```

[剑指 Offer 59 - II. 队列的最大值](https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/)

本质同样是额外维护一个单调队列，在入队时更新单调队列（在尾部插入/删除），**队首是目前入队了的元素最大值**：

```java
public void push_back(int value) {
    // dequeOne 默认队列 dequeTwo 额外的单调队列
        dequeOne.addLast(value);
    while (!dequeTwo.isEmpty() && dequeTwo.peekLast() < value) {
        dequeTwo.removeLast();
    }
    dequeTwo.addLast(value);
}
```

但是多了一个队列出队操作，因此当出队元素等于队首元素时，队首出队：

```java
public int pop_front() {
        int ele;
        if (!dequeOne.isEmpty()) {
            ele = dequeOne.pollFirst();
            // 如果出队元素等于队首元素时，队首出队
            if (ele == dequeTwo.peekFirst()) {
                dequeTwo.pollFirst();
            }
        } else {
            ele = -1;
        }
        return ele;
    }
```

[剑指 Offer 59 - I. 滑动窗口的最大值](https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/)

滑动窗口类似一个有限长度的队列，在完成初始化之后的每次滑动都相当于一次出队 + 一次入队操作，因此只是我们需要在每次出队 + 入队后获取当前队列的最大值而已，本质上和队列最大值相同。同样维护额外的一个单调队列即可

```java
for (int i = 0; i < nums.length; i++) {
    if (i > (k-1)) {
        // 先出队，判断是否与队首元素相等
        if (nums[i - k] == queue.peekFirst()) {
            queue.remove();
        }
        // 然后按规则加入单调队列
        while (!queue.isEmpty() && queue.peekLast() < nums[i]) {
            queue.removeLast();
        }
        queue.addLast(nums[i]);
        result[i - (k - 1)] = queue.peekFirst();
    // 初始化达到滑窗大小
    } else {
        while (!queue.isEmpty() && queue.peekLast() < nums[i]) {
            queue.removeLast();
        }
        queue.addLast(nums[i]);
        if (i == k -1 ) {
            result[0] = queue.peek();
        }
    }
}
```

[503. 下一个更大元素 II](https://leetcode-cn.com/problems/next-greater-element-ii/)

能想到使用单调队列/栈也不容易，不过有个特征就是需要之后的元素和之前的进行比较，那就应该想到单调队列/栈。

这题也是类似的单调队列/栈的用法，思考每次新元素过来，都要先判断是否比队内元素大，如果是，队内元素依此出队/栈，然后加入该元素，还是保证了队首元素是当前队列的最大值。只不过这道题还需要使用一个pair的数据结构，在记录value的同时，还记录一下自己在数组中原本的index，因为放入单调队列/栈后，是会没有这个顺序信息的。以及元素出队的时候记录一下这个比它大的元素是多少。

这题额外的循环数组其实只需要再从头遍历一次就行。

```java
public class Solution {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Pair> stack = new Stack<>();
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // 同样的比较
            while (!stack.isEmpty() && nums[i] > stack.peek().value) {
                result[stack.pop().index] = nums[i];
            }
            // 然后pair入队/栈
            Pair pair = new Pair(nums[i], i);
            stack.push(pair);
            result[i] = -1;
        }
        // 循环数组其实只需要再从头遍历一次就行
        if (!stack.isEmpty()) {
            for (int i = 0; i < nums.length; i++) {
                while (!stack.isEmpty() && nums[i] > stack.peek().value) {
                    result[stack.pop().index] = nums[i];
                }
            }
        }
        return result;
    }
}

class Pair {
    int value;
    int index;

    public Pair(int value , int index) {
        this.value = value;
        this.index = index;
    }
}
```

[下一个更大元素 I](https://leetcode-cn.com/problems/next-greater-element-i)

[链表中的下一个更大节点](https://leetcode-cn.com/problems/next-greater-node-in-linked-list)    

这两个是类似也是类似的解法

[456. 132模式](https://leetcode-cn.com/problems/132-pattern/)

这题有很多值得考究的点：
       1. 对于题目要求的子序列符合 ai < ak < aj, 其中 i<j<k，首先将问题学会转化为找到一个元素 aj,
  在区间[1, j-1]里有比他小的元素M1，在区间[j+1, n]里也有比他小的元素M2, 并且M2>M1，因此首先找的
  [1, j-1]里的最小值M1
       2. 然后我们可以暴力求解了，复杂度O(n^2), 比较难想的就是可以从数组尾部开始，委会一个单调递减的栈，要求对a[j], 栈内元素
  必须大于对应M1，否则就出栈，然后比较栈顶元素和a[j], 如果栈顶元素 < a[j], 那我们就找的了，不然就入栈，符合单减栈的要求
       3. 在2.隐藏了一个点就是M1所在数组也是单减的，因此对于栈内元素大于对应M1，否则就出栈是不影响后面的。

```java
for (int j = nums.length -1; j >= 1; j--) {
    if (nums[j] > min[j]) {
        while (!deque.isEmpty() && deque.peekLast() <= min[j]) { // 多了个第三方排除条件
            deque.removeLast();
        }
        if (!deque.isEmpty() && deque.peekLast() < nums[j]) { // 只有小于栈顶元素才能入栈，维护了单调栈
            return true;
        }
        deque.addLast(nums[j]);
    }
}
```



### 4.2 回溯法