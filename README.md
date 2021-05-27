# Real Leetcode
![](https://img.shields.io/badge/Goal-AlgoAbility-brightgreen)
![](https://img.shields.io/badge/Hgh-Get%20it-green)
![](https://img.shields.io/badge/Number-139-blue)

this repository is to train and improve my algorithm ability, it will contain
most of my solutions of algorithm problems in leetcode. 

## 1. Books & Tutorials

### 1.1 Books

* [算法（第4版）](https://book.douban.com/subject/19952400/)
  算法四这本书，讲解细致，不仅仅与实际应用相结合，并且用科学的方法分析算法的复杂度和正确性，
  是一本值得一看的算法书籍。
  
* [数据结构（清华-邓俊辉）](https://book.douban.com/subject/25859528/)
  这本书由浅入深的向我们介绍了常见的数据结构以及算法，相比传统教材更加通俗易懂，
  有完整可运行的代码，非常推荐。（不仅仅有C++版本，还有Java版本哦）

### 1.2 Tutorials

* [OI Wiki](https://oi-wiki.org)
  介绍了基本的算法题型、包含不少概念和编码小技巧
  状压DP就是从这了解的
  
* [背包问题九讲](https://github.com/tianyicui/pack/blob/master/V2.pdf)
  从0-1背包开始，总结了绝大部分的背包问题的变种，很多问题其本质就是背包问题，因此
  背包问题值得学习。
  
* [LeetCode Cookbook](https://github.com/YS-THX/leetcode)
  用Go语言解决了LeetCode中的绝大部分题目，并且做了很好的分类和梳理，并提供了一定的
  模板值得一看，尤其二分查找。

## 2. Courses/Seminars/Videos

## 3. Toolbox & Websites

* [BZOJ离线题库](https://acm.taifua.com/bzoj/index.html)
  有不少ACM的原题题目

## 4. Solutions 

通过打力扣2021年第一场周赛（222th）以及赛后观看[B站刷题UP主-喂你脚下有坑](https://www.bilibili.com/video/BV1164y1Z7WL?p=2)
的实时视频解说，了解到一个很重要的刷题技巧/或者说细节因素，那就是在写题目的时候一定要注意以下几点：

1. 关注数值问题
   * 判断是否数值大小会超过常用的int的表示范围，不要无脑int 2^31大概是10^10 long 2^63大概是10^19
   * 如果题目提醒需要求余，一定要提前做好标记，不然到后面肯定容易忘
2. 关注给定的数据的数量范围，大概可以通过数量范围判断本题最大可以达到的时间复杂度，提前了解是否可以暴力，是否需要优化
   * 常见运行时限为1s，最大运算在10^7,千万级别，对于该时限
   * O(n^2) n <= 3000 
   * O(n) n <= 10^7
   * O(nlogn) <= 10^5 (log2(10^5)约等于10)
   如果n的大小在20以内，基本上可以料定是类似DFS/BFS之类的穷举方法了，同时加上题目的其它信息可以判断出来。

commit 相关前缀含义：

 * Better Solution 有更好的解法需要后续学习
 * DFS             有DFS的写法不熟练
 * down2up?        DP中自底向上的方法没有理解/掌握
 * Base Case       边界条件/case需要注意
 * LeetcodeWeekly  力扣周赛题目
 * Unsolved        没有理解/掌握的题目
 
[SweepTheTopic](./SweepTheTopic.md)：按模块扫题，快速归纳同模块的不同考察方式以及检验当前模块的自我掌握情况  

### 良好的编码习惯

1. 需要多次复用的值，存下来为好
   * 比如需要频繁使用s.length()进行比较或者其他操作，应当缓存下来，
     因为你不确定其API的时间复杂度是多少，如果是O(N),就不太好。

### 4.0 Java内置数据结构常用API

#### == 和 equals

java中的数据类型，可分为两类：

1.基本数据类型，也称原始数据类型。byte,short,char,int,long,float,double,boolean 
  他们之间的比较，应用双等号（==）,比较的是他们的值。
  
2.复合数据类型(类) 
  * 当他们用（==）进行比较的时候，比较的是他们在内存中的存放地址，所以，除非是同一个new出来的对象，他们的比较后的结果为true，
    否则比较后结果为false。
  * JAVA当中所有的类都是继承于Object这个基类的，在Object中的基类中定义了一个equals的方法，这个方法的初始行为是比较对象的内存地址，
    但在一些类库当中这个方法被覆盖掉了，如String,Integer,Date在这些类当中equals有其自身的实现，而不再是比较类在堆内存中的存放地址了。
    对于复合数据类型之间进行equals比较，在没有覆写equals方法的情况下，他们之间的比较还是基于他们在内存中的存放位置的地址值的，
    因为Object的equals方法也是用双等号（==）进行比较的，所以比较后的结果跟双等号（==）的结果相同。
    
    这里有个实际使用场景就是Map中只能使用Integer，然后比较当数据量不大的情况，使用 == 比较Map中的Integer是没有问题的，
    这是因为虽然 == 要比较地址，但是Java有缓存机制，数值范围为-128到127，在此范围内直接返回缓存值，超过该范围就会new一个对象，
    因此平时使用没有问题，却容易忽视这个问题。对象还是要使用equals安全。

#### Map 

Java中的Map有三个比较常用的实现类

* HashMap 最常见的哈希表
* LinkedHashMap 哈希+双向链表来维护key-value的插入顺序
* TreeMap 哈希+红黑树来维护key-value的有序
  * 自然排序 所有key实现Comparable接口
  * 定制排序 创建TreeMap时，传入一个Comparator对象

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

#### List

```java
    // list 默认排序方式
    Collections.sort(list);
    
    // 同样的，继承了List接口的也有内置排序方法可以使用。
    Collections.sort(list, (o1, o2) -> {return o1.val - o2.val;});
    
    // list to array 
    List<String> result = new ArrayList<>();
    result.toArray(new String[result.size()]);
```

#### PriorityQueue

```java
   // 队列常用的API：offer，poll，peek
   // 并且优先队列有时候需要根据元素配置比较规则，一般使用匿名表达式非常简洁,
   // 其中 o1 - o2 就是小顶堆，反正就是大顶堆
   PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> {return o1.val - o2.val;});

```

#### Arrays 

```java
    // 数组的内置排序方式，默认从小到大
    int[] arrays = new int[n];
    Arrays.sort(arrays);
    
    // 可以自己配置比较规则，这里对二维数组尤其有用
    int[][] tasks = new int[n][m];
    // 通过lambda表达式来进行比较规则的设置
    Arrays.sort(tasks, (x,y)->x[0]-y[0]);
    
```

#### String和Char

值得注意的是我们喜欢常用的`char[i] - 'a'` 或者 `chars[i] - '0'` 来获取直接的数值是OK的，因为
这是char类型之间的计算，但是如果想通过 `'a' + 1` 得到 `'b'` 是不行的，因为这是涉及到char和int类型
直接的计算，会自动转换为int类型，因此需要有一个强制转换。
```java
    // 得到String中某一指定位置的char
    String.charAt(index);

    // 得到将包含整个String的char数组
    String.toCharArray()
    
    // 单个char转换为String
    String s = String.valueOf('c');
    
    //将一个char数组转换成String
    String s = String.valueOf(new char[]{'c'});
    
    //将char数组的一部分转换为String
    // 剑指 Offer 05. 替换空格 有用到这个技巧
    String s = new String(array, 0, size);
    
    // char与int类型计算，需要强制转换
    char incre = (char) (chars[i] - '0');
    chars[i] = (char)(chars[i-1] + incre);
    
    // 解数独那题遇到一个数值的问题，需要
    // (char) ('0' + i) OK，(char)(i) 就不OK
```

#### Comparable and Comparator接口

让类继承Comparable接口，重写compareTo方法，从而可以使类原生支持排序，Comparator接口则是外部的一个排序规则，
如果某个类原本没有继承Comparable接口或者需要新的排序规则，可以使用这个接口。

```java
public interface Comparable<T> {
    public int compareTo(T o);
}
```

### 4.1 栈和队列

#### 单调队列/栈 

这里的单调队列表示双端队列，头尾都可插入的，使用双端队列是因为有时候栈不能完全满足，比如队列最大值就有在头部删除的需求

### 4.2 二分查找/搜索
本小节参考：https://github.com/halfrost/LeetCode-Go

二分搜索的经典写法（默认元素从小小到大排列）。需要注意的三点：
* 循环退出条件，注意是low，high初始化都是可选范围内的最left和最right的数，
  且low <= high，而不是 low < high。
* mid 的取值，mid := low + (high-low)>>1
* low 和 high 的更新。low = mid + 1，high = mid - 1。

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

二分搜索的变种写法。有 4 个基本变种（以下都是默认元素都是从小到大排序）:
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

二分查找还有一个小小的变种题，就是二分插入，区别就在于需要考虑到一个边界条件，就是当所要插入的
数值大于当前数组/list中的所有，那不应该返回-1或者什么都不做，而是需要将该值插入到最后。
根据题目需要，如果需要返回对应index，则设置一个result变量，如果需要完成插入的动作，则设置一个
boolean类型来做标志即可（例子可见SortTopic/StreamMedianFinderBinarySearch）。

```java
public int searchInsert(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;
    int result = -1;
    while (left <= right) {
        int mid = left + ((right - left) >> 1);
        if (nums[mid] >= target) {
            if (mid == 0 || nums[mid-1] < target) {
                result = mid;
                break;
            }
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }

    if (result == -1) {
        return nums.length;
    } else {
        return result;
    }
}
```

### 4.3 树

树可以有多种数据结构，以及不同的表示方式，当然最常见的树的数据结构就是二叉树，最常见的表示方式便是包含值和孩子节点。

```java
class Node {
    public int val;
    public Node left;
    public Node right; // 多叉树便是 List<Node> children;
}
```

树的遍历方式就是常见的两种：深度遍历和广度遍历，然后深度遍历可以再分为前序遍历和后序遍历，如果是二叉树，再多一个中序遍历，
然后深度遍历具体的实现有递归和迭代两种，递归方法比较简单，迭代方法可以借助栈来实现。

深度遍历实现：

```java
// 前序遍历
private void preorder(Node root, List<Integer> result) {
    if (root != null) {
        result.add(root.val);
        for (int i = 0; i < root.children.size(); i++) {
            preorder(root.children.get(i), result);
        }
    }
}

// 后序遍历
private void postorder(Node root, List<Integer> result) {
    if (root != null) {
        for (int i = 0; i < root.children.size(); i++) {
            postorder(root.children.get(i), result);
        }
        result.add(root.val);
    }
}

// 二叉树中序遍历
private void inorder(Node root, List<Integer> result) {
    if (root != null) {
        inorder(root.left, result);
        result.add(root.val);
        inorder(root.right, result);
    }
}
```

```java
// 栈实现多叉树的前序遍历，后序只是访问顺序的区别, 二叉树则是children变成左右子树即可
private void preorder(Node root, List<Integer> result) {
        if (root != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            while(!stack.isEmpty()) {
                Node node = stack.pop();
                result.add(node.val);
                for (int i = node.children.size() - 1; i >= 0; i--) { // 栈需要反着放
                    stack.push(node.children.get(i));
                }
            }
        }
    }

// TODO 二叉树中序遍历的实现
```

广度遍历实现：

```java
private void BFS(Node root, List<Integer> result) {
    Queue<Node> queue = new LinkedList<>();
    if(root != null) {
        queue.offer(root);
    }
    while(!queue.isEmpty()) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            Node node = queue.poll();
            result.add(node.val);
            for (int i = 0; i < root.children.size(); i++) {
                queue.offer(root.children.get(i));
            }
        }
    }
}
```

PS. （当树的表示方式包含了父节点，checkstyle内部有实现一个不借助栈的迭代方式。）

二叉搜索树是一种常见的树数据结构，特定包括：

* 左子树的所有节点一定不大于父节点，右子树的所有节点一定不小于父节点
* 中序遍历得到数值的有序排列

完全二叉树也是一种常见的数据结构，比如用来完成堆排序，特定包括：

* 除了最底层节点可能没填满外，其余每层节点数都达到最大值

### 4.4 回溯法

本小结参考：https://github.com/youngyangyang04/leetcode-master/blob/master/problems/回溯算法理论基础.md

回溯算法的本质就是穷举，并不是什么高效的算法，但是既然不高效，为何还要使用，因为很多问题能暴力枚举出来就
非常不错了，做多就是加上一些剪枝操作，没有更高效的解法了。 那什么问题只能暴力搜索呢？

回溯方法解决的问题：

* 组合问题：N个数里按一定的规则找出k个数的集合
* 排列问题：N个数按一定规则全排列，给出所有排列方式
* 切割问题：一个字符串按一定规则，给出所有的切割方式
* 子集问题：一个N个数的集合里有多少符合条件的子集
* 棋盘问题：N皇后、解数独等等。

回溯法都是通过在集合中递归查找子集，集合的大小构成数的宽度，递归的深度构成树的深度。

回溯遍历过程的伪代码：
```java
void backtracking(参数) {
    if (终止条件) {
        存放结果;
        return;
    }

    for (选择：本层集合中元素（树中节点孩子的数量就是集合的大小）) {
        处理节点;
        backtracking(路径，选择列表); // 递归
        回溯，撤销处理结果
    }
}
```


### 4.5 动态规划

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
        
        memo[n] = up2down(n - 1, memo) + up2down(n - 2, memo);
        return memo[n];
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

动态规划其实可以细分一些类别：
* [背包DP](https://oi-wiki.org/dp/knapsack/)
* [树形DP](https://oi-wiki.org/dp/tree/)
* [状压DP](https://oi-wiki.org/dp/state/)
   * [旅行商问题（状压dp入门）](https://www.cnblogs.com/hhlya/p/13305987.html)
* ... 更多类别参考[OI Wiki 动态规划](https://oi-wiki.org/dp/)

### 4.6 并查集（union-find）算法

本小节参考：https://github.com/labuladong/fucking-algorithm & 《算法4》UF算法部分

并查集是一种数据结构，主要是为了解决图论中的动态连通性的问题。（《算法4》开头就介绍了这种算法）

UF算法核心就是实现union和connected两个API：

```java
interface UF {
    void union(int p, int q); // 在 p、q之间添加一条连接
    boolean connected(int p, int q); // 如果p、q存在于同一个分量，则返回true
    int find(int p); // 返回p所在的分量的标识符
    int count(); // 返回当前联通分量的数量
}
```
union会将两个分量归并，connected判断两个节点是否存在于同一个分量之中。
连通是一种等价关系，具有如下三个性质：

* 自反性：节点`p`和`p`是连通的
* 对称性：如果节点`p`和`q`连通，那么`q`和`p`也连通
* 传递性：如果节点`p`和`q`连通，`q`和`r`连通，那么`p`和`r`也连通

那么用什么模型来表示这幅图的连通状态呢？用什么数据结构来实现代码呢？

使用森林（若干棵树）来表示图的动态连通性，用数组来具体实现这个森林。

怎么用森林来表示连通性呢？我们设定树的每个节点有一个指针指向其父节点，如果是根节点的话，这个指针指向自己。

```java
class UF {
    // 记录连通分量
    private int count;
    // 记录所有节点的父节点
    private int[] parent;
    
    public UF(int n) {
        // 一开始互不连通
        this.count = n;
        // 父节点指针初始指向自己
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
    
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        // 合并两棵树
        parent[rootP] = rootQ;
        count--;
    }
    
    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }
    
    // 返回某个节点的根节点，根节点的标志就是父节点就是自己
    private int find(int x) {
        while(parent[x] != x) {
            x = parent[x];
        }
        return x;
    }
    
    public int count() {
        return count;
    }
}
```
这个用数组模拟了一个森林，其实就是清华的数据结构书中提到的使用数组实现树并且只有父指针的一种树型数据结构的实现方式，
看了每种数据结构都是有自己的用处的。

该算法的复杂度是多少？connected和union的复杂度都是find函数造成的，因此复杂度和find一致。

find主要功能就是从某个节点向上遍历到树根，其时间复杂度就是树的高度。
我们可能习惯性地认为树的高度就是logN，但这并不一定。logN的高度只存在于平衡二叉树，
对于一般的树可能出现极端不平衡的情况，使得「树」几乎退化成「链表」，树的高度最坏情况下可能变成N。
所以上述算法的复杂度为O(N)。

问题的关键在于，如何想办法避免树的不平衡呢？只需要略施小计即可。

**平衡性优化**

我们其实是希望，小一些的树接到大一些的树下面，这样就能避免头重脚轻，更平衡一些。

解决方法是额外使用一个`size`数组，记录每棵树包含的节点数，我们不妨称为「重量」：

```java
// 加权quick-union算法
class UF {
    private int count;
    private int[] parent;
    private int[] size;
    
    public UF(int n) {
        this.count = n;
        parent = new int[n];
        // 初始每棵树只有一个节点
        // 重量都是1
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    /* 其他函数 */
    
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        
        // 小树接到大树下面，较平衡
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        
        count--;
    }
}

```
可以证明加权quick-union算法的find、union、connected的时间复杂度都为O(logN),对数级别，即便规模上亿(10^9),
也只需要20次以内的计算，非常快。

**最优算法-路径压缩**
是否有保证可以在常数时间内完成各种操作的算法？研究人员研究了很多年，有很多quick-union和加权quick-union的变体，其中
一种非常容易实现的就是**路径压缩**，只需要在find中添加一行代码，但是路径压缩版本的quick-union非常非常接近但均摊成本
仍没有达到常数级别O(1)：
```java
private int find(int x) {
    while(parent[x] != x) {
        // 进行路径压缩
        parent[x] = parent[parent[x]];
        x = parent[x];
    }
    return x;
}
```
完整路径压缩版本的UF：

```java
class UF {
    private int count;
    private int[] parent;
    private int[] size;
    
    public UF(int n) {
        count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    
    public void union(int p , int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        count--;
    }
    
    private int find(int p) {
        while (parent[p] != p) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
    
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    
    public int count() {
        return count;
    }
}
```

### 4.7 图

所谓的图(Graph)可以表示为 G = (V, E)，其中集合V中的对象称作顶点(Vertex)，
而集合E中的每一元素都对应于V中某一对顶点⎯⎯说明这两个顶点之间存在某种关系⎯⎯称作边(Edge)。
有的还将顶点称作节点(Node)，或将边称作弧(Arc)，只是叫法不同。

#### 图的基本概念

图按照是否有向，可分为不同的种类：

* 有向图
* 无向图
* 混合图（比较少见）

如果图中边还需要包含某种数量关系，就构成了带权网络（图）。

**度：** 顶点v的关联边的总数，称为v的度数，有向图中，以u为起点的有向边称作u的出边，以v为终点的边则称作v的入边，
v的出边总数称作v的出度，入边总数称作入度。

**简单图：** 不包含平行边、自环等特殊边的图称为简单图。
对简单图而言，其中的边必然构成一个集合，而且每条边只能联接于不同的顶点之间。

#### 图的存储结构（表示方法）

* 邻接矩阵
  若图G中包含n个顶点，我们就使用一个n×n的方阵A，并使每一顶点都分别对应于某一行(列)。
  虽然这个结构直观且简单，但是空间利用率太低，除非是处理稠密图，否则邻接矩阵中的大多数单元都将是重复闲置的。
  并且其结构是静态的，无法后续动态调整，对于需要动态调整结构的图而言，比较麻烦。
  
  而且如果图包含平行边，那就无法使用邻接矩阵来表示。
  
* 边的数组
  这是一种比较直观简单的表示方法，每个边用一个包含2个元素的数组表示，比如`[1,2]`, 多个这样
  的数组构成一个集合/列表，这种方式的缺点在于当我们需要获取某个节点的相邻节点的时候就需要遍历
  整个集合/列表。
  
* 邻接表数组
  由于图是由顶点集和边集构成的，因此处理好这两者的表示和实现，就可以搞定图的表示。
  清华邓老师的[数据结构](https://book.douban.com/subject/25859528/)提供的邻接表
  感觉比较复杂了。
  邻接表是对每个顶点构建一个单链表，每个链表中的节点表示以该定点为出度的边，然后所有节点构成一个数组。
  这种表有个缺陷就是对无向图OK，但是对有向图对话，如果需要求顶点的入度，必须遍历整个邻接表了，因此可以再构建
  一个逆邻接表。而将两者结合的链式存储结构就是**十字链表**。还有针对无向图的类似十字链表的**邻接多重表**，
  这样不会出现在邻接表中一条边有两个结点表示的情况。
  该数据结构特点：
  
    * 使用空间复杂度为O(V+E)
    * 添加一条边的时间复杂度为O(1)
    * 遍历顶点v的所有顶点的时间复杂度为O(degree(v))
    * 支持平行边和自环
    
  对于这些操作，这样的特性已经是最优的了。
     
  目前着重掌握邻接表的对应的算法，十字链表和邻接多重表这两个比较复杂的数据结构后面有时间再了解。
  
#### 图的遍历

这部分主要以邻接表为数据结构进行遍历算法的实现。

#### 深度优先算法

其实核心非常简单，类似树的深度优先，但是由于节点直接可能互相连通，为防止多次访问一个节点，需要多一个访问标记数组。
并且由于图的不一定所有节点全连通，因此需要迭代的对所有节点都过一遍，而不是像树一样有一个根节点就行。

```java
private boolean[] visited = new boolean[g.numVertexes];

public void DFSTraverse(Graph g) {
    // 图遍历需要每个节点都过一遍
    for (int i = 0; i < g.numVertexes; i++) {
        if (!visited[i]) {
            DFS(g, i);
        }
    }
}

public void DFS(Graph g, int i) {
    visited[i] = true;
    for(int j : G.adj(i)) { // 递归访问临近节点
        if(!visited[j]) {
            DFS(g, j); 
        }
    }
}
```

#### 广度优先算法

广度优先和深度优先的时间复杂度是一样的，只是访问节点顺序不同。
```java
public void BFSTraverse(Graph g) {
    Deque<Node> queue = new Deque<>();
    for (int i = 0; i < g.numVertexes; i++) {
        if (!visited[i]) {
            visited[i] = true;
            queue.addLast(i);
            while(!queue.isEmpty()) {
                int i = queue.removeFirst();
                for (int j : G.adj(i)) {
                    if (!visited[j]) {
                        visited[j] = true;
                        queue.addLast(j);
                    }
                }
            }
        }
    }
}
```

#### 拓扑排序
拓扑排序是针对有向无环图的一种排序表示，这是一个充分必要条件。
因此有一类基础题目就是判断该图是否具有拓扑排序，也就是判断该图是否有环。

拓扑排序有两个性质：
* 图一定是有向无环图，有环是无法出现拓扑的
* 拓扑排序的可能性不止一种

常见的处理方法还是图常见的套路：深度遍历和广度遍历，不过针对拓扑排序，广度排序是正向思维，比较好理解，
依次去除入度为0的点。需要根据题目构建图的入度数组和邻接表。一定记住一个原则就是讲不熟悉的输入转换为
自己常见的输入。


应用实例：在调用链数据中，需要将只有父节点指针的节点结构转换为具有子节点的结构
（[转换算法](https://www.geeksforgeeks.org/construct-a-binary-tree-from-parent-array-representation/)），
其实就是一个图的遍历算法的应用，当然有有一些细节的调整，比如访问标记数组换成用哈希表的有无该节点关键字来表示。

### 4.8 排序算法
本小节参考：https://github.com/hustcc/JS-Sorting-Algorithm && 邓老师的数据结构第五章优先队列 && 
          https://zhuanlan.zhihu.com/p/34894768

排序算法大体上可以分为两类：比较和非比较

* 比较算法：比较算法适合各种规模的数据，也不在乎数据分布
    * 冒泡排序
    * 选择排序
    * 插入排序
    * 快速排序
    * 归并排序
    * 堆排序
* 非比较算法：时间复杂度较低，但是前提是对数据分布有一定的要求
    * 计数排序
    * 桶排序
    * 基数排序

TODO：还差桶排序和基数排序的实现    

![排序算法比较](https://github.com/hustcc/JS-Sorting-Algorithm/blob/master/res/sort.png)

### 4.9 位运算
位运算符
* `&` 按位与，当两位同时为1才返回1
  * 连续按位与，结果是单减的
* `|` 按位或，只要有一位为1就返回1
  * 连续按位或，结果是单增的
* `^` 按位异或，当且两位相同返回0，不同返回1
  * 如果一个数与0异或，它的值不改变，因此多个异或结合，只需要考虑1的个数，奇数为1，否则为0
  * 一个数与自身异或，结果为0

性质：如果我们需要计算的表达式只包含位运算（即按位与运算以及按位异或运算），那么每一位是独立的，我们可以分别考虑每一位的情况，
然后最后汇总得到答案。

下面四个是单目运算符

* `~` 按位非，操作数的每一位包括符号位全部取反
* `<<` 左移运算符
* `>>` 右移运算符
* `>>>` 无符号右移运算符

位运算常用技巧：

* 依次判断n的每一位是否是1

```java
int result = 0;
for (int i = 0; i < 32; i++) {
    if ((n & bit) != 0) {
        result++;
    }
    bit <<= 1;
}

// 或者数字左移动
int result = 0;
for (int j = 0; j < 32; j++) {
    result += (n & 1);
    n >>>= 1;
}
```

* 找到n到最低位1

```java
int bit = 1;
while ((n & bit) == 0) {
    bit <<= 1;
}
```    

* 每次将n最右边的1变为0

```java
int res = 0;
while(n != 0) {
    res++;
    n &= n - 1;
}
return res;
```

### 4.10 贪心算法

### 4.11 双指针技巧
本小节参考：labuladong的[NSum](https://mp.weixin.qq.com/s/fSyJVvggxHq28a0SdmZm6Q) 
和 [双指针技巧汇总](https://mp.weixin.qq.com/s/yLc7-CZdti8gEMGWhd0JTg)

双指针还可以分为两类：
* 快慢指针
  解决链表问题，比如判断是否有环
* 左右指针
  主要解决数组、字符串问题，比如二分查找

左右指针常用算法
* 二分查找 (要求有序)
* 两数之和 (要求有序)
* 反转数组
* 滑动窗口
  滑动窗口可以解决一大类子字符串匹配的问题，但是稍微复杂些。
  参考labuladong的[滑动窗口](https://mp.weixin.qq.com/s/ioKXTMZufDECBUwRRp3zaA)
  大体的逻辑参考框架：
```java
int left = 0, right = 0;
while (right < s.size()) {
    // 增大窗口
    window.add(s[right]);
    right++;  
    while (window needs shrink) {
        // 缩小窗口
        window.remove(s[left]);
        left++;
    }
}
```
这个算法技巧的时间复杂度是O(N),比一般的字符串暴力算法要高效得多。

### 4.12 链表相关操作

### 4.13 差分数组
本小结主要参考：labuladong的[差分数组](https://mp.weixin.qq.com/s/9L6lz0XDZ9gi-d_iPrSs8Q)

差分数组和前缀和都是频繁涉及到数组区间操作时，可以考虑到数据结构/算法技巧。

* 前缀和
  主要适用的场景是原始数组不会被修改的情况下，频繁查询某个区间的累加和。
```java
class PrefixSum {
    // 前缀和数组
    private int[] prefix;

    /* 输入一个数组，构造前缀和 */
    public PrefixSum(int[] nums) {
        prefix = new int[nums.length + 1];
        // 计算 nums 的累加和
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
    }

    /* 查询闭区间 [i, j] 的累加和 */
    public int query(int i, int j) {
        return prefix[j + 1] - prefix[i];
    }
}
```
* 差分数组
  主要适用场景是频繁对原始数组的某个区间的元素进行增减。

```java
int[] diff = new int[nums.length];
// 构造差分数组
diff[0] = nums[0];
for (int i = 1; i < nums.length; i++) {
    diff[i] = nums[i] - nums[i - 1];
}

int[] res = new int[diff.length];
// 根据差分数组构造结果数组
res[0] = diff[0];
for (int i = 1; i < diff.length; i++) {
    res[i] = res[i - 1] + diff[i];
}
```
这样构造差分数组diff，就可以快速进行区间增减的操作，如果你想对区间nums`[i..j]`的元素全部加 3，
那么只需要让`diff[i] += 3`，然后再让`diff[j+1] -= 3`即可。

```java
class Difference {
    // 差分数组
    private int[] diff;

    public Difference(int[] nums) {
        assert nums.length > 0;
        diff = new int[nums.length];
        // 构造差分数组
        diff[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }
    }

    /* 给闭区间 [i,j] 增加 val（可以是负数）*/
    public void increment(int i, int j, int val) {
        diff[i] += val;
        // 当j+1 >= diff.length时，说明是对nums[i]及以后的整个数组都进行修改，
        // 那么就不需要再给diff数组减val了。
        if (j + 1 < diff.length) {
            diff[j + 1] -= val;
        }
    }

    public int[] result() {
        int[] res = new int[diff.length];
        // 根据差分数组构造结果数组
        res[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        return res;
    }
}
```
有人总结：单点更新，范围查询，就用线段树。范围更新，单独查询，就用差分数组。
