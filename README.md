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

通过打力扣2021年第一场周赛（222th）以及赛后观看[B站刷题UP主-喂你脚下有坑](https://www.bilibili.com/video/BV1164y1Z7WL?p=2)
的实时视频解说，了解到一个很重要的刷题技巧/或者说细节因素，那就是在写题目的时候一定要注意以下几点：

1. 关注数值问题
   * 判断是否数值大小会超过常用的int的表示范围，不要无脑int
   * 如果题目提醒需要求余，一定要提前做好标记，不然到后面肯定容易忘
2. 关注给定的数据的数量范围，大概可以通过数量范围判断本题最大可以达到的时间复杂度，提前了解是否可以暴力，是否需要优化
   * 常见运行时限为1s，最大运算在10^7,千万级别，对于该时限
   * O(n^2) n <= 3000 
   * O(n) n <= 10^7
   * O(nlogn) <= 10^5 (log2(10^5)约等于10)

commit 相关前缀含义：

 * Better Solution 有更好的解法需要后续学习
 * DFS             有DFS的写法不熟练
 * down2up?        DP中自底向上的方法没有理解/掌握
 * Base Case       边界条件/case需要注意
 * LeetcodeWeekly  力扣周赛题目
 * Unsolved        没有理解/掌握的题目
 

### 4.0 Java内置数据结构常用API

#### Map 

```java
    // 常用的 get put replace 操作
    Map<Character, Integer> map = new HashMap<>();
    char[] chars = s.toCharArray();
    for (char ele : chars) {
        if (map.containsKey(ele)) {
            map.replace(ele, map.get(ele)+1); 
        } else {
            map.put(ele, 1);
        }
    }
```
```java
    // 不同的遍历方法
    // 迭代Entry
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
        System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
    }
    
    // 迭代键
    for (Integer key : map.keySet()) {
        System.out.println("Key = " + key);
    }

    // 迭代值
    for (Integer value : map.values()) {
        System.out.println("Value = " + value);
    }
    
    // 迭代器
    Iterator<Map.Entry<Integer, Integer>> entries = map.entrySet().iterator();
    while (entries.hasNext()) {
        Map.Entry<Integer, Integer> entry = entries.next();
        System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
    }
```

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
### 4.2 二分查找/搜索
本小节参考：https://github.com/halfrost/LeetCode-Go

二分搜索的经典写法。需要注意的三点：
循环退出条件，注意是 low <= high，而不是 low < high。
mid 的取值，mid := low + (high-low)>>1
low 和 high 的更新。low = mid + 1，high = mid - 1。

```Go
func binarySearchMatrix(nums []int, target int) int {
	low, high := 0, len(nums)-1
	for low <= high {
		mid := low + (high-low)>>1
		if nums[mid] == target {
			return mid
		} else if nums[mid] > target {
			high = mid - 1
		} else {
			low = mid + 1
		}
	}
	return -1
}

```

二分搜索的变种写法。有 4 个基本变种:
查找第一个与 target 相等的元素，时间复杂度 O(logn)
查找最后一个与 target 相等的元素，时间复杂度 O(logn)
查找第一个大于等于 target 的元素，时间复杂度 O(logn)
查找最后一个小于等于 target 的元素，时间复杂度 O(logn)

```Go
// 二分查找第一个与 target 相等的元素，时间复杂度 O(logn)
func searchFirstEqualElement(nums []int, target int) int {
	low, high := 0, len(nums)-1
	for low <= high {
		mid := low + ((high - low) >> 1)
		if nums[mid] > target {
			high = mid - 1
		} else if nums[mid] < target {
			low = mid + 1
		} else {
			if (mid == 0) || (nums[mid-1] != target) { // 找到第一个与 target 相等的元素
				return mid
			}
			high = mid - 1
		}
	}
	return -1
}

// 二分查找最后一个与 target 相等的元素，时间复杂度 O(logn)
func searchLastEqualElement(nums []int, target int) int {
	low, high := 0, len(nums)-1
	for low <= high {
		mid := low + ((high - low) >> 1)
		if nums[mid] > target {
			high = mid - 1
		} else if nums[mid] < target {
			low = mid + 1
		} else {
			if (mid == len(nums)-1) || (nums[mid+1] != target) { // 找到最后一个与 target 相等的元素
				return mid
			}
			low = mid + 1
		}
	}
	return -1
}

// 二分查找第一个大于等于 target 的元素，时间复杂度 O(logn)
func searchFirstGreaterElement(nums []int, target int) int {
	low, high := 0, len(nums)-1
	for low <= high {
		mid := low + ((high - low) >> 1)
		if nums[mid] >= target {
			if (mid == 0) || (nums[mid-1] < target) { // 找到第一个大于等于 target 的元素
				return mid
			}
			high = mid - 1
		} else {
			low = mid + 1
		}
	}
	return -1
}

// 二分查找最后一个小于等于 target 的元素，时间复杂度 O(logn)
func searchLastLessElement(nums []int, target int) int {
	low, high := 0, len(nums)-1
	for low <= high {
		mid := low + ((high - low) >> 1)
		if nums[mid] <= target {
			if (mid == len(nums)-1) || (nums[mid+1] > target) { // 找到最后一个小于等于 target 的元素
				return mid
			}
			low = mid + 1
		} else {
			high = mid - 1
		}
	}
	return -1
}
```

### 4.3 回溯法

### 4.4 动态规划
本小节参考：https://github.com/labuladong/fucking-algorithm & 《算法导论》动态规划章节

**动态规划问题的一般形式就是求最值**。动态规划其实是运筹学的一种最优化方法，只不过在计算机问题上应用比较多，比如求最长递增子序列，
最小编辑距离等等。

既然是要求最值，核心问题是什么呢？**求解动态规划的核心问题是穷举**。因为要求最值，肯定要把所有可行的答案穷举出来，然后在其中找最值。

对，动态规划的核心就是穷举，类似回溯法/分治法，可以用递归求解，但是又有特别之处，特别之处就在于具有**重叠子问题**，这种情况，
如果直接使用分治/递归，会有许多不必要的工作，反复求解公共子问题，但动态规划对每个子问题只求解一次，将其保存到一个表格中，无限重复计算。
（dynamic programming, "programming"指的就是一种表格法）。 

而且动态规划问题一定会**具备最优子结构**，这样才能通过子问题的最值得到原问题的最值，注意**满足最优子结构，子问题必然互相独立**，
负责如果相互制约，就不具有最优子结构。

最优子结构性质隐含了问题最优解和子问题最优解之间的一种递推关系。它是动态规划的基础，递推方程是最优子结构性质的体现。

有最优子结构之后，关键核心便是正确列出**状态转移方程**，也就是如何通过子问题求解当前问题。因此确定/设计最优子结构，并列出状态转移
方程是动态规划的最大难点。

因此可以总结出动态规划问题的三要素：

* 重叠子问题
* 最优子结构
* 状态转移方程

其中状态转移方程是最关键的，这里有个labuladong的思维框架：

**明确「状态」 -> 定义 dp 数组/函数的含义 -> 明确「选择」-> 明确 base case。**

[斐波那契数列](https://leetcode-cn.com/problems/fibonacci-number/submissions/)
以及[零钱兑换问题](https://leetcode-cn.com/problems/coin-change/)，都使用了动态规划来求解，
下面列出这两题递归/回溯、动态规划-自顶向下（备忘录法）以及 动态规划-自低向上 三种方法，以供思考：

斐波那契数列：

严格说斐波那契数列不太算是动态规划问题，因为并不是求最值，也没有最优子结构，只是直接有了状态转移方程。。。，所以就可以套用
动态规划的优化思想。

```java
    // 递归
    private int recursive(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
    
        return recursive(n - 1) + recursive(n - 2);
    }
```


```java
    // 自顶向下
    public int fib(int n) {
        int[] memo = new int[n+1]; // 备忘录
        return up2down(n, memo);
    }
    
    private int up2down(int n, int[] memo) {
        if (n == 0 || n == 1) {
            return n;
        }
    
        if (memo[n] != 0) {
            return memo[n];
        }
    
        return up2down(n - 1, memo) + up2down(n - 2, memo);
    }
```

```java
    // 自低向上
    private int down2up(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
```

零钱兑换问题：

该问题具有最优子结构，因为硬币数量都是无限的，不会出现子问题相关联的情况。

```java
    // 穷举回溯的解法，会超时 复杂度 O(S^n) S为coins数量，n为每个coin在amount下可取的最大值
    private int result = -1;
    public int coinChange(int[] coins, int amount) {
        int size = 0;
        backtracking(0, size, coins, amount);
        return result;
    }

    private void backtracking(int startIndex, int size, int[] coins, int amount) {
        if (amount == 0) {
            if (result == -1 || size < result) {
                result = size;
            }
            return;
        }

        for (int i = startIndex; i < coins.length; i++) {
            if (result != -1 && size > result) {
                continue;
            }
            if (amount >= coins[i]) {
                size++;
                backtracking(i, size, coins, amount - coins[i]);
                size--;
            }
        }
    }
```

```java
    // 动态规划-自上而下，备忘录的形式
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) {
            return 0;
        }
        return coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int[] count) {
        if (rem  < 0) {
            return -1;
        }

        if (rem == 0) {
            return 0;
        }

        if (count[rem - 1] != 0) {
            return count[rem - 1];
        }

        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }
```
```java
    // 动态规划-自下而上
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        int max = amount + 1;
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
```
