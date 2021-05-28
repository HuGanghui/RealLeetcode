## SweepTheTopic

### 栈和队列

* [1673. 找出最具竞争力的子序列](https://leetcode-cn.com/problems/find-the-most-competitive-subsequence/)
  这题核心其实是要维护一个单调队列/栈（只能在尾部插入/删除），优先找最小的数，如果后面准备入队的数比当前队列中的数要小，
  就让队列里的数依此从尾部出列，然后加入该数。**这个队列的性质便是单调递增，队首是目前入队了的元素最小值。**

```java
for (int i = 0; i < nums.length; i++) {
	while (!deque.isEmpty() && deque.peekLast() > nums[i]) {
    	deque.removeLast();
    }
    deque.addLast(nums[i]);
}
```

同时本题有个特殊之处在于有个约束，是要找到长度为k的最具竞争力子序列，因此在不断出队的时候，
最后一定要保证队列中有k个数，因此while还有额外一个约束条件 (k - deque.size()) < nums.length - i

```java
!deque.isEmpty() && deque.peekLast() > nums[i] && (k - deque.size()) < nums.length - i
```

* [剑指 Offer 59 - II. 队列的最大值](https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof/)
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

* [剑指 Offer 59 - I. 滑动窗口的最大值](https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/)
   滑动窗口类似一个有限长度的队列，在完成初始化之后的每次滑动都相当于一次出队 + 一次入队操作，
   因此只是我们需要在每次出队 + 入队后获取当前队列的最大值而已，本质上和队列最大值相同。同样维护额外的一个单调队列即可

```java
for (int i = 0; i < n; i++) {
    // 初始化还未到滑窗大小
    if (i < k - 1) {
        while (!deque.isEmpty() && nums[i] > deque.peekLast()) {
            deque.removeLast();
        }
        deque.addLast(nums[i]);
    } else {
        if (i != k - 1) {
            // 先出队，判断是否与队首元素相等
            int removed = nums[i - k];
            if (deque.peekFirst() == removed) {
                deque.removeFirst();
            }
        }
        // 然后按规则加入单调队列
        while (!deque.isEmpty() && nums[i] > deque.peekLast()) {
            deque.removeLast();
        }
        deque.addLast(nums[i]);
        result[i - k + 1] = deque.peekFirst();
    }
}
```

* [503. 下一个更大元素 II](https://leetcode-cn.com/problems/next-greater-element-ii/)
  能想到使用单调队列/栈也不容易，不过有个特征就是需要之后的元素和之前的进行比较，那就应该想到单调队列/栈。
  这题也是类似的单调队列/栈的用法，思考每次新元素过来，都要先判断是否比队内元素大，如果是，队内元素依此出队/栈，然后加入该元素，
  还是保证了队首元素是当前队列的最大值。只不过这道题还需要使用一个pair的数据结构，在记录value的同时，还记录一下自己在数组中原本的index，
  因为放入单调队列/栈后，是会没有这个顺序信息的。以及元素出队的时候记录一下这个比它大的元素是多少。
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

* [下一个更大元素 I](https://leetcode-cn.com/problems/next-greater-element-i)

* [链表中的下一个更大节点](https://leetcode-cn.com/problems/next-greater-node-in-linked-list)    
  这两个是类似也是类似的解法

* [456. 132模式](https://leetcode-cn.com/problems/132-pattern/)
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

### 二分查找/搜索（Binary Search）

#### 基本框架：

```java
public class BinarySearch {
    public int simpleEqual(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int result = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < left) {
                left = mid + 1;
            } else {
                result = mid;
                break;
            }
        }
        return result;
    }
    
    // lastEqual 类似
    public int firstEqual(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                right = mid -1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                if (mid == 0 || nums[mid - 1] != target) {
                    return mid;
                }
                right = mid - 1;
            }
        }
    }
    
    // firstEqualOrGreat、LastEqualOrLess、LastEqualOrGreat 类似
    public int firstEqualOrLess(int[] nums, int target) {
        int left = 0;
        int right = nums.length -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                if (mid == 0 || nums[mid - 1] > target) {
                    return mid;
                }
                right = mid - 1;
            }
        }
    }
}
```
#### 1. 简单变形的一维二分

特点：含有有序的特点，比如
 * 简单的，直接有序
 * 或者山脉的先升后降
 * 或者先1后0这种

Easy:

* [374. 猜数字大小](https://leetcode-cn.com/problems/guess-number-higher-or-lower/)
  有序的特点，换个外观，传统套路了，最简单的simpleEqual
  基本思路：时间复杂度 O(log(n))，空间复杂度O(1)

* [35. 搜索插入位置](https://leetcode-cn.com/problems/search-insert-position/)
  暴力思路：遍历查找 时间复杂度 O(n)，空间复杂度O(1)
  优化思路：有序的特点，然后本质就是寻找第一个小于等于target的元素的index，二分模版套用就行，但是有个特例就是target大于nums中所有值，此时
  应该返回nums.length，时间复杂度 O(log(n))，空间复杂度O(1)
  
* [744. 寻找比目标字母大的最小字母](https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target/)
  有序的特点，显然的firstGreat
  基本思路：时间复杂度 O(log(n))，空间复杂度O(1)  

* [852. 山脉数组的峰顶索引](https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/)
  暴力思路：遍历查找 时间复杂度 O(n)，空间复杂度O(1)
  基本思路：二分查找第一个nums[i] < nums[i-1] 的下标，**但这就要注意left从1开始，因为第0个必然不会是答案**
          时间复杂度 O(log(n))，空间复杂度O(1)

* [1337. 矩阵中战斗力最弱的 K 行](https://leetcode-cn.com/problems/the-k-weakest-rows-in-a-matrix/)
  这题没有虽然是二维矩阵，但是行列没有单调性质，但有有序的特点，因此可以用一维的二分，然后排序获取结果，
  O(mlog(n) + mlog(m))，空间复杂度O(m)
  ```java
    // 关于排序的写法，第一关键字和第二关键字
    Arrays.sort(sort, (o1, o2) -> {
                if (o1[1] != o2[1]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[0] - o2[0];
                }
            });
  ```
  
* [167. 两数之和 II - 输入有序数组](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/)
  暴力思路：遍历查找 时间复杂度 O(n^2)，空间复杂度 O(1)
  基本思路：1. 有序的特点，二分查找，对每个元素，从它右边第一个元素开始二分，时间复杂度 O(nlog(n))，空间复杂度 O(1)
          2. 使用哈希表存储数组元素大小和下标，然后依次去哈希表中查询差值是否存在，注意不可使用重复元素，因此相同元素
             记得处理一下 时间复杂度 O(n)，空间复杂度 O(n)
  优化思路：**双指针，一个从头开始，一个从尾开始**，如果小于target，头指针右移动，否则，尾指针左移动，
          时间复杂度 O(n)，空间复杂度 O(1)
  
#### 2. 行列/二个维度均为单调排列：

Easy:

* [1351. 统计有序矩阵中的负数](https://leetcode-cn.com/problems/count-negative-numbers-in-a-sorted-matrix/)
  暴力思路：每行进行顺序查找 时间复杂度 O(mn)
  基本思路：每行/列进行二分查找，时间复杂度 O(mlogn/nlogm)，空间复杂度O(1) (m行n列)
  优化思路：由于每行每列都是单调的，因此当上一行确定了首个元素，当前行的必然在其左边或者右边，从而减少了复杂度，
          如果继续使用二分，最差也要 O(mlogn/nlogm)，空间复杂度O(1)。也可以不使用二分，就直接依次遍历，时间
          复杂度为O(m + n);
  
* [1237. 找出给定方程的正整数解](https://leetcode-cn.com/problems/find-positive-integer-solution-for-a-given-equation/)
  与有序矩阵一样，x对于行，y对应列即可

#### 3. 数组交集：

Easy:

* [349. 两个数组的交集](https://leetcode-cn.com/problems/intersection-of-two-arrays/)
  暴力思路：两层for循环，在一个数组里面查找元素在里另一个数组是否存在，时间复杂度 O(mn)，空间复杂度O(1)
  基本思路：对数组A进行排序，然后数组B的每个元素在A中进行二分，时间复杂度 O(nlogn + mlogn/ mlogm + nlogm)，空间复杂度O(1)
  优化思路：1. 使用两个HashSet保存两个数组，然后遍历其中一个在另一个中进行判断，时间复杂度 O(m+n), 空间复杂度 O(m+n)
          2. 排序 + 双指针，时间复杂度 O(nlogn + mlogm)（主要是排序），双指针寻找O(m + n)，空间复杂度主要看排序使用的额外空间
  
* [350. 两个数组的交集 II](https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/)
  II和I的区别主要在于对于重复元素也是要算交集的
  暴力思路：同I
  基本思路：二分不行了，因为虽然排序后，继续判断是否存在，但是无法确定数量
  优化思路：1. 使用哈希表保存元素和元素个数，然后遍历判断，时间复杂度 O(n + m)，空间复杂度O(n)，
             为了降低空间复杂度，首先遍历较短的数组并在哈希表中记录每个数字以及对应出现的次数，然后遍历较长的数组得到交集
          2. 排序 + 双指针，时间复杂度 O(nlogn + mlogm)（主要是排序），双指针寻找O(m + n)，空间复杂度主要看排序使用的额外空间
  
  额外思考：1. 如果数组已经排好序，那种方法更好？ 显然可以使用双指针的方法更优，不需要额外空间
          2. 如果 nums1 的大小比 nums2 小很多，哪种方法更优？哈希表，并且使用较小进行哈希
          3. 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
             哈希对于nums只涉及查询，问题不大；如果仍然使用排序，就需要归并排序类似的，每次排序一部分，然后进行K个数组的排序这样，就比较
             复杂了，后续可以思考复杂度。
             
#### 4. 平方数/开根号之类：

Easy:

* [367. 有效的完全平方数](https://leetcode-cn.com/problems/valid-perfect-square/)
* [69. x 的平方根](https://leetcode-cn.com/problems/sqrtx/)  
  两道题都是类似的，一个是simpleEqual，一个是LastLessOrEqual，
  依次判断从1-x是否符合条件即可，满足二分的有序特点，使用二分即可，当然上限x其实还可以进一步的优化就是了
  
#### 5. 完全二叉树的性质：

Median:

* [222. 完全二叉树的节点个数](https://leetcode-cn.com/problems/count-complete-tree-nodes/)
  暴力/基本思路：层次/深度遍历，时间复杂度 O(n)，空间复杂度O(1)，n为树的节点个数，忽视了完全二叉树的性质
  优化思路：二分查找+位运算，时间复杂度 O((log(n))^2)，空间复杂度O(1)，规定根节点位于第0层，完全二叉树的最大层数为h，完全二叉树的最左边的节点一定位于最底层，
  因此从根节点出发，每次访问左子节点，直到遇到叶子节点，该叶子节点即为完全二叉树的最左边的节点，经过的路径长度即为最大层数。
  当 0<=i<h时，每层的节点数为2^i个，如果最底层只有一个节点，总节点为2^h个，如果最底层有2^h个，那么总共就有2^(h+1)-1个。
  因此可以尝试使用二分来判断2^h到2^(h+1)-1之间到底有多少个，如何判断就是利用位运算来表示最后一层的每个节点，判断节点是否存在，
  如果1代表存在，0代表不存在，我们需要找的就是LastEqual为1的节点位置。
  具体表示：如果节点位于h层，那么其二进制表示有h+1位，除了最高位，其余的如果为0，则表示移动到左分支，1移动到右分支。
  
  常见的二分查找模板
  ```java
    int left = 1 << depth;
    int right = (1 << (depth+1)) - 1;
    int result = 0;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (exits(root, depth, mid)) {
            if (mid+1 == (1 << (depth+1)) || !exits(root, depth, mid+1)) {
                result = mid;
                break;
            }
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return result;
  ```
  
  位运算表示路径
  ```java
    private boolean exits(TreeNode root, int depth, int k) {
              int bits = 1 << (depth -1); // 从第二高位开始，这里有个边界条件就是当depth为0，要记得提前处理
              TreeNode node = root;
              while (node != null && bits > 0) { 
                  if ((bits & k) == 0) {
                      node = node.left;
                  } else {
                      node = node.right;
                  }
                  bits >>= 1;
              }
              return node != null;
          }
  ```

#### 6. 最大最小化问题：

Median:

* [1011. 在 D 天内送达包裹的能力](https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/)

* [1552. 两球之间的磁力](https://leetcode-cn.com/problems/magnetic-force-between-two-balls/)
  这题也是套路和数组分割就是没有区别，只不过这题是最小值的最大
  

Hard:

* [410. 分割数组的最大值](https://leetcode-cn.com/problems/split-array-largest-sum/)
   这题和1011本质上是一样的题目，都是分割数组的最大值最小，**最大最小/最小最大也是二分的标志之一**。
   暴力思路：回溯枚举，依次遍历第一个划分位置，然后第二个，直到第m个，时间复杂度 O(m^m)，空间复杂度O(m)
   优化思路：1. 动态规划 到时候总结
           2. 二分+贪心 「使……最大值尽可能小」是二分搜索题目常见的问法。本题，我们可以首先确定一个值x，然后
              贪心的添加使得当前的分割片段大于x，然后分割数量cnt加1，最后验证cnt是否达到了要求的划分数量m个。
              思想很简单，需要学会。
              
    ```java
    // check 验证
      private boolean check(int[] nums, int m, int x) {
              int sum = 0;
              int cnt = 1;
              for (int i =0; i < nums.length; i++) {
                  if (sum + nums[i] > x) {
                      cnt++;
                      sum = nums[i];
                  } else {
                      sum += nums[i];
                  }
              }
              return cnt <= m; // 最大值最小是要<=，最小值最大是>=
          }
    ```

#### 7. 旋转数组系列：
旋转系列的核心在于如何利用其特点，来使用二分查找。旋转的特点在于其大部分区间还是有序的，因此
81. 搜索旋转排序数组 II 和 153. 寻找旋转排序数组中的最小值 的解法核心都在于通过和数组的left或者
right进行比较，来判断出哪部分是有序的，然后如果是求最小，那就一定在无序那部分，如果是寻找target是否存在，
那就在有序的那部分好比较，从而判断是否存在。

* [153. 寻找旋转排序数组中的最小值 Median](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/)
  旋转题基本也都是可以使用二分，但是相比之前总结的二分查找的模板，有一定的出入，需要先挖掘旋转后数组的特点，然后重复利用。
  这题难度在于：1. 如何进行比较，从而判断使用左边还是右边 2. 特殊情况的处理，当mid与right相等。

```java
public int findMin(int[] numbers) {
        return binarySearch(numbers);
    }

private int binarySearch(int[] array) {
    int left = 0;
    int right = array.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        // 小于 说明是右边是递增的，因此在左边找，并且mid可能是最小值，不能跳过
        if (array[mid] < array[right]) {
            right = mid;
        // 大于 说明左边是整体大的那部分，因此最小值在右边，且mid不可能是最小值
        } else if (array[mid] > array[right]) {
            left = mid + 1;
        /** 等于， 有一种情况就是类似凹字，这种：那这种情况下，我们只能排除right，因为即便它是，也有mid可以代替
        *    * * mid     * r *
        *          min *
        */  
        } else {
            right = right - 1;
        }
    }
    // 最后left==right相等的情况，right会-1，因此left会是最后的答案
    return array[left];
}
```

* [33. 搜索旋转排序数组 Median](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/)
  这题是要求在旋转数组中寻找给定的target值，这题的思路是通过每次二分后有序的那部分来
  进行比较，通过mid 和 left 来的大小来判断哪left-mid mid-right 哪部分是有序的，然后
  在有序的那部分进行判断，如果在有序那部分的范围内则进一步缩小范围，否则就转到另外一部分去。
  利用有序部分的思想还是值得借鉴的。   

* [81. 搜索旋转排序数组 II Median](https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/)
  和上面的区别仅仅在于这个这里会有相同元素，因此这题其实是通解，主要就在于对于凹字的情况，通过left/right移动一个来化解
  掉这种情况。
  
```java
public boolean search(int[] nums, int target) {
    int left = 0;
    int right = nums.length -1;
    int result = -1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            result = mid;
            break;
        }
        
        if (nums[mid] == nums[left]) {
            left += 1;
        } else if (nums[mid] > nums[left]) {
            if (nums[mid] > target && nums[left] <= target) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        } else {
            if (nums[mid] < target && nums[right] >= target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
    }
    return result != -1;
}
```

#### 8. 最值系列：
都没有像 6.最大最小化问题那样，而是直接求最值，根据题意，很多时候，也是可以用二分来求解的。基本上想到二分了，就是框架+check函数。

* [5764. 准时到达的列车最小时速 Median](https://leetcode-cn.com/problems/minimum-speed-to-arrive-on-time/)
  这题是要求最小正整数时速，然后题目感觉就是非常典型的二分查找的类型，依次二分判断直到获得最小。
  话不多说，直接二分框架，然后check根据题目进行编写即可。

### 动态规划（Dynamic Programming）

#### 基本框架：

动态规划问题的三要素：

* 重叠子问题
* 最优子结构
* 状态转移方程

其中状态转移方程是最关键的，这里有个labuladong的思维框架：

**明确「状态」 -> 定义 dp 数组/函数的含义 -> 明确「选择」-> 明确 base case。**

具体的代码框架和例子在[README](./README.md)中已经有详细的描述。

#### 通用空间优化技巧：

滚动数组：

大部分的DP，都仅仅依赖前几个状态，这时可以对空间复杂度进行优化，从O(n)->O(1)或者O(n^2)->O(n)/O(1)

确定了状态，一般如果有一个状态，那正常遍历就是O(n)，如果有两个状态，那正常的就是O(n^2)，因此很多动态规划题
的时间复杂度其实O(n^2)也是正常的，如果根据数据范围，需要更低的时间复杂度，记住除了换个思路，有的时候单纯的根据
条件进行剪枝也是非常有效的方式，有些题这样就可以过。

#### 背包问题总结：

* [背包问题九讲](https://github.com/tianyicui/pack/blob/master/V2.pdf)
  从0-1背包开始，总结了绝大部分的背包问题的变种，很多问题其本质就是背包问题，因此
  背包问题值得学习。
  
#### 0. 遍历顺序的重要性
不同的遍历顺序对于做题还是非常的重要的，有的遍历顺序是根据状态转移，必须那样，而有的则是如果不这样，
就无法重复利用已经计算好的结果进行剪枝操作。不过遍历顺序也没有特别多，多总结，做题的时候多尝试即可。
遍历顺序，大部分是通过转移方程可以确定的，但是有些情况，比如子数组按位或，可以有多种遍历方式（正、反、斜都行），
那就要思考是不是哪一种有优势。

```java
// 正向遍历
int[][] dp = new int[m][n];
for (int i = 0; i < m; i++)
    for (int j = 0; j < n; j++) // 有时三角一半即可 j = i+1 即可
        // 计算 dp[i][j]
```

```java
// 反向遍历
for (int i = m - 1; i >= 0; i--)
    for (int j = n - 1; j >= 0; j--) // 有时三角一半即可 int j = i; j <= m-1; j++ 
        // 计算 dp[i][j]
        
// 关于三角一半的反向遍历的另外一种形式，可以从头开始，但是计算矩阵左半边的三角
// a
// a|b     b
// a|b|c   b|c   c     
for (int i = 0; i <= m -1; i++) {
    for (int j = i; j >= 0; j--) {
         // 计算 dp[i][j]
    }
}
```

```java
// 斜着遍历数组, base case是对角线
for (int l = 1; l < n; l++) {
    for (int i = 0; i + l < n; i++) {
        int j = i + l;
        // 计算 dp[i][j]
    }
}
```

反向遍历:

* [898. 子数组按位或操作](https://leetcode-cn.com/problems/bitwise-ors-of-subarrays/)
  ```java
    // a
    // a|b     b
    // a|b|c   b|c   c
  ``` 
  基本思路：就两层for循环来进行遍历，时间复杂度O(n^2)
  优化思路：剪枝操作，计算一个非负数组A i-j (i <= j) 之间的按位或操作，然后返回结果的可能数量，这里有个性质就是按位或是
          随着数量增多，必然递增的（如果有负数，可能会不一样）。因此有想到二分可以利用，但是这题不太用的上，而是
          用了剪枝的操作，并且需要反向遍历，这样的话，当dp[j] = dp[j]|d[i] (0 <= j < i) (也利用了滚动数组，因为
          只与上一行相关)，那其实就可以剪枝了，这样虽然遍历仍然是O(n^2)，但却可以剪枝了，大大减少实际的时间复杂度。
  猿辅导笔试题，有一题就是关于这个的，只是有了一个很小的改动，就是要求所有按位或后小于某个target的数量，其实遍历方向还是需要
  反向遍历，但是可以使用二分了。

斜着遍历:

* [5. 最长回文子串](https://leetcode-cn.com/problems/longest-palindromic-substring/)
  基本思路：以每个字母为中心，依次向外扩展，比较两端是否相等，直到不等，然后获取其中的最大值。这种方法叫中心
          扩展法，时间复杂度O(n^2)，空间复杂度O(1)。这道题这种方式其实是比较不错的。
          需要注意的是，中心分两种情况，分别是长度为1和2。
  动规思路：dp[i][j] = true/false 表示 是否是回文串， 则dp[i][j] = dp[i+1][j-1] ^ (si == sj)，
          边界情况 dp[i][i] = true, dp[i][i+1] = (si == sj)。
          通过递推公式可以看出这里的遍历顺序需要斜着遍历。

斜着遍历/反向遍历：
* [516. 最长回文子序列](https://leetcode-cn.com/problems/longest-palindromic-subsequence/)
  基本思路：暴力枚举，就利用递归来依次计算i-j，时间复杂度应该是指数级别。
  优化思路：会发现有非常多的重复问题，利用memo备忘录来记录，这样就获得了自顶向下的动态规划；当然也可以自底向
          上来进行动态规划，时间复杂度O(n^2)。

#### 1. 具有递推公式

**前缀和问题：**

前缀和本身的就包含了递推公式

Easy:

* [303. 区域和检索 - 数组不可变](https://leetcode-cn.com/problems/range-sum-query-immutable/)
  简单一维前缀和递推公式：$dp[i] = dp[i-1] + nums[i]$，$dp[i]$表示从0-i的所有元素和，
  一次O(n)时间复杂度的计算，可以保证后续O(1)时间复杂度获取任何两个索引下标之间的元素和。

**约瑟夫环问题** 
 
也是利用递推公式来解决

* [剑指 Offer 62. 圆圈中最后剩下的数字](https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/)

* [1823. 找出游戏的获胜者 Median](https://leetcode-cn.com/problems/find-the-winner-of-the-circular-game/)

**丑数问题**

* [剑指 Offer 49. 丑数 Median](https://leetcode-cn.com/problems/chou-shu-lcof/)
  这题的递推方程本身我其实就没特别理解，强行记忆，核心就是用三个指针分别指向2，3，5目前乘的次数，
  然后每次比较大小，最小的就是第i个丑数，最后返回的是第n个。
  
#### 2. 数组求和转换

Easy:

* [剑指 Offer 42. 连续子数组的最大和](https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/)
  基本思路：动态规划，时间复杂度O(n)，空间复杂度O(n)，这题是一道经典的转换题，如何获取整个数组的连续子数组的最大和，
          那就是首先**获取以每个元素结尾（这种状态表示方法值得记住，可以解决很多类似的数组题目）** 的连续子数组的最大和，
          然后再取其中的最大值，并且递推公式为：$dp[i] = max(dp[i-1] + nums[i], nums[i])$, 
          dp[i]表示以第i元素结尾的连续子数组的最大和。
          更精妙的思路：分治，后续可以尝试。
  
* [368. 最大整除子集](https://leetcode-cn.com/problems/largest-divisible-subset/)
  基本思路：动态规划，时间复杂度O(n^2)，空间复杂度O(n^2)，这题和连续子数组的最大和是一个套路的转换，
          如何获取整个数组的最大整除子集，直接获取比较困难，那就先获取以每个元素的最大整除子集，最后取其中最大的就行，
          递推公式为：$dp[i] = max(dp[k] | 0 <= k <= i && nums[i] % nums[k] == 0)$，不过这题和传统的DP的区别在于，
          它需要保存每个元素的最大整除子集，所以不能使用简单的数组，而是需要list或者map来保存。
Hard:
          
* [面试题 17.24. 最大子矩阵](https://leetcode-cn.com/problems/max-submatrix-lcci/)
  思路：二维转换一维，枚举（有用到前缀和的思想来降低复杂度）然后利用连续子序列的最大和的思想进行求解，不过这题需要的是下标，而不是最值，
       但是问题不大，只需要在求最值的过程中，记录一下就行。
  
#### 3. 最低花费/代价（最高收益）类型问题

一般的要求最低花费/代价（最高收益）的类型的题目，都是具有最优子结构的，即子问题相互独立，
dp[i-1]达到最低花费/代价和dp[i]是相互独立的，没有任何的关系。

Easy:

* [746. 使用最小花费爬楼梯](https://leetcode-cn.com/problems/min-cost-climbing-stairs/)
  爬楼梯问题也很经典，比如多少种爬楼梯的方式，递推公式就是：$dp[i] = dp[i-1] + dp[i-2]$，
  (之前没有思考过，爬楼梯多少种方式可以直接相加，因为是个排列问题，如果是组合问题，那就不行了)，这题的
  递推公式也比较容易想到就是：$dp[i] = min(dp[i-1], dp[i-2]) + cost[i]$，dp[i]表示到第i需要的最小代价，
  那么选择就是从i-1还是i-2来，然后我们选择两种方式中代价最小的，
  值得注意的是，这题最后要的结果是到n+1的情况，因此最后选择$min(dp[size-1], dp[size-2])$ 即可。
  
* [面试题 17.16. 按摩师](https://leetcode-cn.com/problems/the-masseuse-lcci/)
  本题dp[i]表示选择到第i个时，可获得的最大收益，选择就是选i还是不选i，这样显然递推公式
  就是：$max(dp[i-1], dp[i-2] + nums[i])$，这里可能有的疑惑是dp[i-1]可能是dp[i-2], 
  没有选择nums[i-1]，这样就可能出现dp[i-1] + nums[i],但值得注意的是，这里就相当于dp[i-2] + nums[i]，
  所以递推公式没关系。
  
* [198. 打家劫舍](https://leetcode-cn.com/problems/house-robber/submissions/)
  和17.16按摩师一模一样的，递推公式：$dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i])$
  
Median:

* [322. 零钱兑换](https://leetcode-cn.com/problems/coin-change/)
  暴力思路：回溯枚举，遍历每一种组合的可能性，然后选择其中数量最小的。
  基本思路：动态规划，考虑到我们只需要最小数量，不需要列举出具体的解，这种最值题型其实也可以算作让我们思考DP解法的暗示，
          当然，其实需要最小数量的具体解，其实也是可以使用DP的，只不过一般不要求。只有要求列出所有的
          递推公式为：$dp[i] = min(dp[i-cj]) + 1$，cj为第j枚硬币的面值。

#### 4. 计数类型问题

计数问题需要搞清楚本质上是组合问题还是排列问题，这个关乎如何选择何种dp状态表示以及含义的。

**排列问题：**

Easy:

排列问题比较简单，就是直接相加就可以，不会有重复的。

* [70. 爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/)
  递推公式就是：$dp[i] = dp[i-1] + dp[i-2]$
  
* [面试题 08.01. 三步问题](https://leetcode-cn.com/problems/three-steps-problem-lcci/)
  基本思路：动态规划，时间复杂度O(n)，空间复杂度O(n)，递推公式就是：$dp[i] = dp[i-1] + dp[i-2] + dp[i-3]$，
          这题更值得关注的是三个很大的数的取模的顺序和方法的问题，当然可以进行状态压缩，因为只需要使用前三个数，使得空间复杂度到O(1)。
  优化思路：1.矩阵快速幂，参考[题解](https://leetcode-cn.com/problems/three-steps-problem-lcci/solution/mei-ri-suan-fa-day-80-suo-you-ren-du-hui-zuo-de-ru/)
  
* [剑指 Offer 46. 把数字翻译成字符串](https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/)
  递推公式为：$dp[i] = dp[i-1] + dp[i-2] (i>=1, 10<=x<=25)$，本质上和爬楼梯是一样的，只不过需要注意base case 以及
  一个trick，涉及到多个字符转换为数字的情况，还是String类型好用，有现成的`Integer.parseInt()`。        

Median:

* [377. 组合总和 Ⅳ](https://leetcode-cn.com/problems/combination-sum-iv/)
  暴力思路：回溯枚举，时间复杂度O(k^n)，空间复杂度O(n)，k为nums中元素个数
  优化思路：动态规划，时间复杂度O(kn)，空间复杂度O(n)，这题就是最原始本质的计数排列问题，
          递推公式就是直接相加：$dp[i] += dp[i-nums[j]]$

* [1641. 统计字典序元音字符串的数目](https://leetcode-cn.com/problems/count-sorted-vowel-strings/)
  基本思路：动态规划，时间复杂度O(n)，空间复杂度O(n)，当然可以进行状态压缩，因为只需要使用5个数，使得空间复杂度到O(1)。
          这题稍微复杂些，考虑长度为i以u结尾的字符串数量：$dp[i][u] = dp[i-1][a] + dp[i-1][e] + dp[i-1][i] + dp[i-1][o] + dp[i-1][u]$ 
          依次类推，$dp[i][a] = dp[i-1][a]$，最后返回总数$dp[i][a]+...+dp[i][u]$。
          这题特殊在于递推公式有5个，而不是一个，可能有点超乎想象那种，所有就没想到。本质上还是一样的，就是多了一个状态而已，然后选择和
          状态是挂钩的。
          
* [494. 目标和](https://leetcode-cn.com/problems/target-sum/)
  这题本质上同样是排列问题，但是稍有不同就是，排列的是正负号，以及状态有两个了，一个是前i个元素，一个是总数，
  但其实不管怎么，排列问题就是从之前的状态累加就行。
  递推公式为：$dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]]$，dp[i][j]表示用前i个元素
  来组合成j的方案数。
          
**组合问题**

Median:

* [518. 零钱兑换 II](https://leetcode-cn.com/problems/coin-change-2/)
  这道题就是典型的计数问题中的组合问题，如果不注意，很容易直接写出这个递推公式：$dp[i] = sum(dp[i-cj]), 0<=j<=n$，
  以为就和爬楼梯一样的，但恰恰不是，因为比如 3 = 1 + 2 = 2 + 1，这个在爬楼梯就是2种方式，但是硬币组合就是一种，因此
  不能简单的使用排列问题的直接相加递推式，而是需要按组合问题考虑，因此考虑状态有两个，一个是使用前多少个硬币，一个是总金额
  数，因此递推公式为：$dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]] (j > coins[i-1])$，每个金额组合数等于只使用前i-1个硬币完成的加上
  使用了第i个完成的，这个是组合问题的经典公式，划分为使用i和不使用i，之前回溯中组合问题的遍历方式其实也是这个思想。base case
  就是$dp[i][0] = 1$，金额为0的认为有一种，而不是0。
  
* [39. 组合总和](https://leetcode-cn.com/problems/combination-sum/)
  这题如果求的是组合数，那就是518. 零钱兑换 II的本质了，但这里求的确实所有的组合，其实递推公式都是一样的，如果要用DP也是可以的，只不过
  写法上需要复杂一些，就不是单纯的相加了，而是在之前的每个组合上加上一个数，构成当前的组合。
  
* [面试题 08.11. 硬币](https://leetcode-cn.com/problems/coin-lcci/)
  基本上和518. 零钱兑换 II一样，题解具体的分析了一下递推的由来，并介绍了滚动数组的思想来减少空间复杂度。
  
#### 5. 字符串类型问题

Median:

* [5. 最长回文子串](https://leetcode-cn.com/problems/longest-palindromic-substring/)
  基本思路：动态规划，时间复杂度O(n^2)，空间复杂度O(n^2)，回文尝试从短的到长的判断，
          递推公式为：$dp[i][j] = dp[i+1][j-1] && s.charAt(i) == s.charAt(j)$，dp[i][j]表示i-j这段字符串是否是回文串，
          然后递推顺序注意从短到长判断
  优化思路：中心扩展算法，就是以每个元素为中心，逐步向外扩展，判断是否是回文，从而记录下最长的，不过需要注意，边界情况有两种，一种
          是一个元素为中心，一个是有两个元素为中心，所以这两种都要考虑。时间复杂度O(n^2)，空间复杂度O(1)，相比DP，主要是空间复杂度
          的降低，但如果需要保存每个i-j是否是回文的结果，还是需要DP的。
          
* [131. 分割回文串](https://leetcode-cn.com/problems/palindrome-partitioning/)
  这题放在这里，主要是反应了一个现象：如果需要不断重复使用i-j是否是回文（或者其它结果）的结果，那就最好提前预处理一下，存储下来，
  这题就是要利用最长回文子串DP过程中会记录下所有i-j是否是回文。这种预处理的优化值得注意。
  
#### 6. 使用自顶向下-带备忘录的搜索更直观简单的问题

本质上，动态规划也就是穷举，和DFS/回溯是一样的，尤其自顶向下-带备忘录的模式，只不过是它发现在穷举搜索过程中会有
重复子问题而已，然后有些问题我们直观上就会想到使用递归/回溯的方法解决，那么这种情况下，可以再思考是不是可以使用备忘录
来解决重复子问题。

Median:

* [96. 不同的二叉搜索树](https://leetcode-cn.com/problems/unique-binary-search-trees/)
  暴力思路：递归，直观上就是递归，利用二叉搜索树的性质，当前节点大于左子树所有节点，小于所有右子树所有节点，递归返回值就是
          当前start-end的不同二叉搜索树的总个数，递归参数就是start和end，终止条件就是start > end，
          然后类似后序遍历，遍历当前数组，将左右子树返回的个数相乘再求和即可。
  优化思路：动态规划，而显而易见的是相同的start-end，答案是固定的，因此可以利用备忘录来解决这个重复子问题。
          这样就很直观的使用了DP算法。
  与之类似的[95. 不同的二叉搜索树 II](https://leetcode-cn.com/problems/unique-binary-search-trees-ii/)，要求返回
  的是所有的不同二叉树的结果，这种情况基本上就是递归/回溯来解决了，当然其实也可以使用类似的备忘录形式来解决重复子问题，但是
  形式上会更加复杂些。
  
* [638. 大礼包](https://leetcode-cn.com/problems/shopping-offers/)
  暴力思路：回溯，组合问题，遍历找到所有的可行解，然后选择其中的划分最小的
  优化思路：动态规划，这题本质上其实也是属于最低花费/代价（最高收益）类型问题，本质上是和零钱兑换是一样的题目，区别仅仅在于
          这题的维度比较高，无法简单的使用DP table来自底向上的方法，而备忘录的方法则更加的方便，因为这里我们使用的备忘录是
          Map形式，并且其中的key选择了list来表示，这里有个java小知识就是List<Integer>的hashcode只与其实包含的元素相关，
          因此哪怕两个不同的List<Integer>，hashcode也一样，因此可以做key。这里的递归参数就是needs-list，递归返回值就是
          当前needs-list的最小花费，递归结束条件就是needs-list均为0了，返回0。
  当然，本题还有一个特殊的点，在于必然包含单位"1"，因此利用这个特点同样可以有一个写法简便上的优化，具体可以看commit中的解答。
  
#### 7. 多状态+限制条件+选择

这部分的总结是因为有一类题目的递推公式没有那么直观，但是通过我们明确状态（一般不止一个），限制条件以及选择之后就会发现其实没有那么的复杂。

* [801. 使序列递增的最小交换次数 Median](https://leetcode-cn.com/problems/minimum-swaps-to-make-sequences-increasing/)
  分析：多了一个状态是否交换，然后在此基础上考虑和i-1的不同状态组合再结合限制条件后的选择的可能性，基本上递归公式就可以搞定。
       同时注意在某些情况下不满足限制条件，数值应该是多少。
  
* [2020年腾讯技术笔试题-最少休息天数](/src/main/java/DynamicProgramming/MinRest.java)
  分析：这题就是状态换成了当天是工作，休息还是健身，其它没有区别。
  
* [1641. 统计字典序元音字符串的数目](https://leetcode-cn.com/problems/count-sorted-vowel-strings/)
  分析：这题其实也是，多了一个结尾的字符是什么，也算是一个状态，然后状态影响选择（递推公式）

* [494. 目标和](https://leetcode-cn.com/problems/target-sum/)
  分析：如果将和也看作另一状态，其实这个算可以属于这一类，这个还简单点，因为没有啥因为不同状态的限制条件。

* [股票买卖系列](https://leetcode-cn.com/problemset/all/?topicSlugs=dynamic-programming&search=股票)
  参考labuladong文章：https://mp.weixin.qq.com/s/lQEj_K1lUY83QtIzqTikGA
  分析：股票系列，从状态或者DP table入手，其实也就是有两种状态，一个是天数，另一个就是当前是否持有股票，当然这是k为
       无限的情况，如果有k数量限制，则是再多一个状态，dp[0-i][0-k][0/1]，都是可以通过一个通用的分析来获得一个基本通用
       的递归方程，而冷冻期/手续费都是在这个基础上一点点小的修改罢了。
  labuladong 通过穷举+memo备忘录完成本题的自顶向下的动态规划通用框架：https://mp.weixin.qq.com/s/TrN7mMdLEPCmT5mOXzgP5A
  通过比较可以发现，两者从思路上还是有所差别的，基本上可以总结一下：从一般的穷举出发，然后memo，就自顶向下了，如果看出来可以利用状态出发，
  那就是DP table，自底向上的套路了。
  
  不同的题目可能更适合或者说想到某一种更容易/直观。
  
  不过我感觉股票系列的动态规划的最优子结构还是不太确定，直观上不太好理解，暂时认为有的吧。
  
### 树（Tree）

#### 1. 两棵树同时遍历：

* [617. 合并二叉树](https://leetcode-cn.com/problems/merge-two-binary-trees/)
  基本思路还是深度遍历，这种两个树的，就细心点考虑一些不同的情况组合就行，并且这里涉及到构建一颗新树，
  一般这种情况都是需要递归函数返回当前节点，然后上一层来拼接左右孩子节点就行。
  
#### 2. 深度递归返回左右子树结果，然后当前节点拼接左右子树结果获得最终结果：

* [124. 二叉树中的最大路径和](https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/)
  由于最大路径不一定是过root节点，因此这题需要设一个max来比较，并且递归过程中返回的和与max比较的还不太一样，
  一个是左右子树结果如果大于0都相加，另一个则是选择其中一个并且大于0才相加。
  
* [236. 二叉树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/)
  这题设置的返回值是该节点及其子节点是否包含p或者q节点，然后判断节点是否是最近公共祖先，
  使用 (left && right) || ((node.val == p.val || node.val == q.val) && (left || right)) 这个条件，看着有的复杂，
  但其实还好，记住吧。
  
* [98. 验证二叉搜索树](https://leetcode-cn.com/problems/validate-binary-search-tree/)
  注意二叉搜索树的性质是小于/大于左右所有节点，因此最好的方式是传递一个范围。
  
#### 3. 完全二叉树的性质利用：

* [222. 完全二叉树的节点个数](https://leetcode-cn.com/problems/count-complete-tree-nodes/)
  重复利用完全二叉树的性质，只有最后一层需要计算，那如何计算就利用二分 + 位运算，来判断底层那个点是否存在。
  还有要注意的就是位运算的级别最低，一定要加括号。
  
### 贪心算法

#### 涉及两个变量的贪心策略
  
  核心在于，通常一个是时间time，另一个是收益或者惩罚，那么一般的套路是对time有一定的限制，需要根据一定的规则放入
  优先队列，并且time按什么顺序放，有可能题目固定好了，比如单线程CPU，但也可能正反都可以，那就要思考是正还是反，
  我总结了规律，一般从当前选择比较少的入手。
  
  写法上一般先确定time顺序，然后对time进行数组排序，然后定义好优先队列比较规则，然后两层循环外部是出队，内部是入队，
  然后可能还需要根据题目定义一些curtime之类的变量，在循环过程中可能需要逻辑比较什么的。

* [5736. 单线程 CPU Median](https://leetcode-cn.com/problems/single-threaded-cpu/)
  需要根据cpu时间来确定顺序入队的时机，然后需要不断更新curtime。
  
* [2021.4.18 腾讯笔试 最少游戏惩罚](./src/main/java/SimulationTopic/MinPunitiveScore.java)
  特别的，这题需要反着，从后往前遍历curtime。
 
### 设计数据结构题

* [170. 两数之和 III - 数据结构设计 Easy](https://leetcode-cn.com/problems/two-sum-iii-data-structure-design/)
  这题给了一个很好的范例就是侧重优化哪个API，以查为主还是还是以写为主。

#### 树数据结构

* [208. 实现 Trie (前缀树)](https://leetcode-cn.com/problems/implement-trie-prefix-tree/)
  前缀树，空间换时间，内置Trie[] 和 isEnd。
  
#### 数据流结构

  其实这三题都是涉及到排序的方法选择，中位数和第k大其实比较类似，都是可以有暴力方法、二分查找（选择排序）以及
  堆排序来解决。堆排序都是最优解。MK平均数多了滑窗的概念，然后概念上有三段，因此比较有技巧的使用四个堆来维护（
  其中中间堆需要一个大顶堆，一个小顶堆。）

* [295. 数据流的中位数](https://leetcode-cn.com/problems/find-median-from-data-stream/)
  这题有两个思考题：
  1. 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
  2. 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
  感觉需要了解9大排序算法后面的几种，然后来解答这个问题。

* [703. 数据流中的第 K 大元素](https://leetcode-cn.com/problems/kth-largest-element-in-a-stream/)

* [1825. 求出 MK 平均值](https://leetcode-cn.com/problems/finding-mk-average/)
  
  
### 双指针技巧题

#### 快慢指针

**判断链表是否有环、环的入口、获取第k个节点等：**

* [141. 环形链表 Easy](https://leetcode-cn.com/problems/linked-list-cycle/)

* [142. 环形链表 II Median](https://leetcode-cn.com/problems/linked-list-cycle-ii/)

* [剑指 Offer 22. 链表中倒数第k个节点 Easy](https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/)

* [876. 链表的中间结点 Easy](https://leetcode-cn.com/problems/middle-of-the-linked-list/)

**对数字进行按大小/奇偶进行前半部分后半部分的归类：**

算是一个新的应用场景了

* [剑指 Offer 21. 调整数组顺序使奇数位于偶数前面 Easy](https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/)

* [快速排序](./src/main/java/SortTopic/ClassicSort/QuickSort.java)
  快排中的partition部分，就是利用了快慢指针进行原地排序。

**移除数组、链表中的重复元素的各种变形，有几点值得总结的：**

* [26. 删除有序数组中的重复项 Easy](https://leetcode-cn.com/problems/remove-linked-list-elements/)
  slow-fast，fast探路来遍历整个数组，发现不重复的元素就让slow走一步，这样最后slow就是指向不重复数组的结尾。
    
* [80. 删除有序数组中的重复项 II Median](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/)
  这题要求最多保留2两位，其实有个通解，保留k位，那就前k位其实都可以保留，然后比较的是slow-k和fast
  
* [27. 移除元素 Easy](https://leetcode-cn.com/problems/remove-element/)

* [283. 移动零](https://leetcode-cn.com/problems/move-zeroes/)
  换个说法，不就是移除元素0，然后放到最后呗。

如果头节点可能被删除，那需要引入一个哑元节点，然后不一定快慢指针，
用一个指针也行，然后判断ptr.next，这种最后一个节点会被判断到，只有第一个ptr没有被判断到，也就是哑元节点。

```java
while (ptr.next != null) {
    if (ptr.next.val == val) {
        ptr.next = ptr.next.next;
    } else {
        ptr = ptr.next; 
    }
}
```

* [83. 删除排序链表中的重复元素 Easy](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/)
  这种方式都是保留了重复元素的第一个
  
* [82. 删除排序链表中的重复元素 II Median](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/)
  这题要求重复的都删去，因此头节点可能被删除，所以要有哑元节点，然后通过两个连续的比较来判断
  删除与否，通过while删除干净。

```java
ListNode dummy = new ListNode(-1);
dummy.next = head;
ListNode first = dummy;
while(first.next != null && first.next.next != null) {
    if (first.next.val == first.next.next.val) {
        int x = first.next.val;
        while(first.next != null && first.next.val == x) {
            first.next = first.next.next;
        }
    } else {
        first = first.next;
    }
}
return dummy.next;
```

* [面试题 02.01. 移除重复节点 Easy](https://leetcode-cn.com/problems/remove-duplicate-node-lcci/)
  这题的区别在于无序，因此需要利用哈希表，并且需要删除因此用first.next来进行判断。


#### 左右指针

* [1. 两数之和](https://leetcode-cn.com/problems/two-sum/)
  可以排序然后双指针解决，同时也可以哈希表来用空间换时间。
  
* [653. 两数之和 IV - 输入 BST](https://leetcode-cn.com/problems/two-sum-iv-input-is-a-bst/)
  本质还是TwoSum问题，并且BST中序天然有序，自然双指针就行，还不用去重，有一个OK就行，更简单些。

* [15. 三数之和 Median](https://leetcode-cn.com/problems/3sum/)

* [18. 四数之和 Median](https://leetcode-cn.com/problems/4sum/)
  上述两题都是NSum的特例，其核心是TwoSum，然后递归处理就行，但是其中值得注意的是
  对于去重的要求，就需要排序了，排序然后跳过，是一个基本的方式。
  
#### 滑动窗口
滑动窗口可以解决一大类子字符串匹配的问题，但是稍微复杂些。也是左右指针中的集大成者了。
滑动窗口由于其两端增减的性质，适合解决的题目大多是子字符串类型的，并且不考虑其中的顺序，只要满足
条件就行，比如常用的need的map，一般不会说考虑排列顺序的。

* [76. 最小覆盖子串 Hard](https://leetcode-cn.com/problems/minimum-window-substring/)
  这题要求的是子串的长度，虽然中间会包含很多无用的字符，但是没有要求所谓的返回字典序最小这种需要内部再删除排序那种，
  所以还是可以用滑动窗口来解决。

* [438. 找到字符串中所有字母异位词 Median](https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/)

* [567. 字符串的排列 Median](https://leetcode-cn.com/problems/permutation-in-string/)
  异构词只是排列的换个名字罢了，还是找子串，并且子串长度大小都固定好了，缩减时机其实也就是当满足子串长度时
  
* [3. 无重复字符的最长子串 Median](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/)
  这个稍微新意点，匹配条件，不过也是可以滑动窗口，缩减时机就是出现了重复字符，那需要缩减到没有为止。
  
* [32. 最长有效括号 Hard](https://leetcode-cn.com/problems/longest-valid-parentheses/)
  这题也是最长子串，当然先到可以用滑动窗口，结合有效括号的性质，缩减时机遇到左括号数量小于右括号数量时，
  缩减则是把之前的积累的左右括号数量全部归零，并让left直接移动到right处。
  还有就是，如果只在left_count == right_count时记录，对于`(()`这种情况，就无法记录到，因此解决方案就是
  反过来再进行一遍取最大值，这个应该是利用了有效括号的对称性，来弥补这种情况。
  
* [30. 串联所有单词的子串 Hard](https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/)
  这题从题目看和之前找到匹配子串是非常相似的，因此很直观的尝试使用之前的滑动窗口的思路来完成。
  但是有细微的差别，并且有个关键条件就是word长度均相等，其实就可以让我们以String为单元做一些事，因为之前一直以char为单元，
  但其实只要word长度一直，就可以以String为单元。
  
* [剑指 Offer 57 - II. 和为s的连续正数序列 Easy](https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/)
  这是利用滑动窗口解决连续数字和的问题，目前唯一一道非字符串的滑动窗口题，不过连续数字和为target确实适合滑动窗口。
  
* * [5763. 哪种连续子字符串更长 Easy](https://leetcode-cn.com/problems/longer-contiguous-segments-of-ones-than-zeros/)
    这题也是解决连续字符串/数字的问题，典型的滑动窗口或者快慢指针也行（滑动窗口算快慢指针的一类）。      
  
### 回溯

#### 组合问题
N个数里按一定的规则找出k个数的集合

基本思路是 **每次从集合中选取元素，可选择的范围随着选择的进行而收缩，调整可选择的范围**

子集问题回溯函数的参数都需要有一个startIndex，来控制横向遍历范围

子集需要随着递归的深入，横向遍历的选择范围是随之而减小的。(但也有特例，当可以无限重复取，就不用缩小范围)

同时如果需要去重，则加上**排列+used数组**，其它的方式都无法去重干净。

![子集回溯过程](https://camo.githubusercontent.com/625a45f9e4f060f5fe958b7563bc2d9738695da2a09d8228844e27ced2541a91/68747470733a2f2f696d672d626c6f672e6373646e696d672e636e2f32303230313132333139353332383937362e706e67)

* [77. 组合 Median](https://leetcode-cn.com/problems/combinations/)
  典型的组合问题，基本就是按照组合的套路来，不过针对这题有个剪枝操作，当后续元素个数加上已在temp中
  的个数小于k个，便可以剪枝。
  
* [216. 组合总和 III](https://leetcode-cn.com/problems/combination-sum-iii/)
  同样是n个选择k个的组合，但是同时再加上一个额外的条件就是需要为n，基本差别不大。并且不需要
  考虑去重的问题。
  
* [39. 组合总和 Median](https://leetcode-cn.com/problems/combination-sum/)
  这题也是一个组合，其实也可说是子集，需要满足的条件是和为target，但是这题有个特殊的点在于
  candidates 中的数字可以无限制重复被选取，那如何满足这点，就通过在每个纵向上，其横向选择范围保存不变。
  并且这题不包含重复元素，因此少了个去重的操作。

* [40. 组合总和 II Median](https://leetcode-cn.com/problems/combination-sum-ii/)
  这题相比上题，难度在有重复元素需要处理，这需要我们进行树层上的去重，而在树枝上，遇到之前碰到
  的数字，是没关系的。关于需要去重的组合问题的套路便是：排列+used数组。
  
```java
// 利用used进行数层去重
if (i > 0 && (candidates[i-1] == candidates[i] && used[i-1] == false)) {
    continue;
}
```

#### 子集问题
子集问题其实和组合很类似，就是在其基础上需要树枝方向每个都需要保存结果而已。

基本套路都是回溯函数的参数都需要有一个startIndex，来控制横向遍历范围
子集需要随着递归的深入，横向遍历的选择范围是随之而减小的。

但是树枝方向每个都需要保存结果而已。

同时如果需要去重，则加上**排列+used数组**，其它的方式都无法去重干净。

* [78. 子集](https://leetcode-cn.com/problems/subsets/submissions/)

* [90. 子集 II](https://leetcode-cn.com/problems/subsets-ii/)
  这题需要去重，就是套路 排列+used数组
  
* [491. 递增子序列](https://leetcode-cn.com/problems/increasing-subsequences/)
  这题本质就是子集问题，但是要满足的条件是递增，并且元素大于2，然后由于要原始有序，因此特别点
  在于其去重方式是用哈希表就行，**因为哈希表树层去除虽然不干净，但是结合递增的要求就可以保证没有
  重复的了，比较特别。**
```java
// 数层去重的另外一种方式，使用哈希表，但是针对子集/组合问题，需要加入额外条件才能保证干净去重
Set<Integer> set = new HashSet<>();
```

#### 全排列

排序的和之前的组合，子集问题最大的不同就是[1,2] 和[2,1]是两个集合，因此排序不能随着递归的深入，横向遍历的选择范围是随之而减小的，
而是每个递归的遍历范围是一样的，**也就不需要startIndex这个参数，但是这样天生需要used来明确之前已经有哪些元素使用过了。**

并且，如果还需要树层去重，那就是用哈希表来处理了。

* [46. 全排列](https://leetcode-cn.com/problems/permutations/)

* [47. 全排列 II](https://leetcode-cn.com/problems/permutations-ii/)
  这就需要去重，这里去重方式有两种，一种就是哈希，一种就利用排序+used的方式

* [剑指 Offer 38. 字符串的排列 Median](https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof/)
  核心在于横向上的去重方式，就是全排列II的字符串版本。

#### 字符串切分

关于回溯字符串分解转换为整数，越界问题必然要考虑，优先用long来存储，同时long也可以越界，那
可以尝试用异常处理来捕获同时根据题意做相应的处理。

* [842. 将数组拆分成斐波那契序列](https://leetcode-cn.com/problems/split-array-into-fibonacci-sequence/)

* [5747. 将字符串拆分为递减的连续值](https://leetcode-cn.com/problems/splitting-a-string-into-descending-consecutive-values/)

#### 二维回溯问题
所谓的二维回溯问题，其实就是形式上给了一个二维的图形/棋盘，然后需要回溯穷举所有的可行解，
但是其实这些问题看上去是二维的，基本上可以转换为一维的情况去做，也就是横向和纵向两个方向，只是相对代码量多了些，然后多了
一个转换的过程而已。

* [51. N 皇后 Hard](https://leetcode-cn.com/problems/n-queens/)
  看似二维，其实也就是横向每行的不同位置，纵向为不同行，然后改进复杂的点在于 1.isValid的代码量稍微多了些， 2.Java对字符串
  的支持不够，需要先利用char[]数组来处理，然后最后转换为String。
  
* [37. 解数独 Hard](https://leetcode-cn.com/problems/sudoku-solver/)
  基本套路就是回溯，但是有一下几点需要注意：
  1. 数独是二维的，通过一个list来保存需要填充的位置，从而将二维降到一维
  2. 关于char类型，涉及到int类型转换，都还是用 (char) ('0' + i)，这个和'0'做一个运算保险
  3. 由于这题直接利用给的board作为答案，因此一定要在回溯后加上一个flag判断，否则得不到答案
  
N皇后和解数独其实使用的都是类似全排列的套路，只不过全排列中的used换成了isValid判断规则。

### 图

#### 矩阵遍历
矩阵遍历类似图遍历，不过也有些许差异，这里单拎出来总结一下，有一些小的技巧：

* 遍历方向  
```java
// 矩阵无非就是四个方向，并且是连续的，因此可以通过dx，dy来解决
private int[] dx = new int[]{0, 1, 0, -1};
private int[] dy = new int[]{1, 0, -1, 0};
for (int i = 0; i < 4; i++) {
    backtracking(x + dx[i], y + dy[i]);
}
```

* 访问标记数组
```java
// 标记是否已经访问过
private boolean[][] visited;
visited[x][y] = true;
```

* 判断是否超出矩阵范围或已经访问过
```java
if (x >= m || y >= n || x < 0 || y < 0 || visited[x][y]) {
    return;
}
```

通常的矩阵遍历，遍历方向就四个，有时可以更少一些，然后递归的结束条件通常是：
1. 超出矩阵范围
2. 是否已经访问过
3. 题目特定的要求

* [剑指 Offer 12. 矩阵中的路径](https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/)
  就利用上述说的相关技巧，回溯体现在当当前节点四个方向访问完毕后，需要将visited重新设置回false，这样不影响后续
  的递归求解答案。
  本题的特殊结束条件/确定树的纵向树枝的深度在于words的长度，达到了就说明找到答案了。
  这题有一个特殊点在于起点是不确定的，因此需要遍历一下。
  
* [剑指 Offer 13. 机器人的运动范围 Median](https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/)
  这题最特殊的地方，在于求解的是运动范围，相当于图中的节点个数，因此需要类似图的遍历一样，visited变成true之后，就不再回溯了，
  同时这题还有一个对数值每一位的求和的程序编写，这个其实运用的也是相当广泛。
  
```java
// 对 x 和 y 每一位的数字求和
private int get(int x, int y) {
    int result = 0;
    while (x != 0) {
        result += x % 10;
        x /= 10;
    }
    while (y != 0) {
        result += y % 10;
        y /= 10;
    }
    return result;
}
```  
  
### 链表

链表题常用的技巧：

* 建哑元节点，尤其当头节点不确定或者也会改变的时候
  很多题目都用到了，基本可以无脑建一个先
  
* 尾部节点要注意可能需要设置为null的情况，防止循环链表
  比如反转链表II和分区链表都需要设置
  
  
* [剑指 Offer 52. 两个链表的第一个公共节点 Easy](https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/)
  基本思路就是，两个指针遍历链表，这样走过的长度是相同的，两个这题的trick在于可能存在两个链表不相交的情况，
  因此需要利用first（sec） == null，跳到null这个方式来处理，而first.next（sec.next） == null 则无法处理不相交的情况。  

#### 反转链表/断链接链

反转链表对应迭代和递归算法都有一个通用的解法，都值得掌握。

* [206. 反转链表 Easy](https://leetcode-cn.com/problems/reverse-linked-list/)

* [92. 反转链表 II Median](https://leetcode-cn.com/problems/reverse-linked-list-ii/)

* [61. 旋转链表 Median](https://leetcode-cn.com/problems/rotate-list/)

#### 合并/分离链表

* [21. 合并两个有序链表 Easy](https://leetcode-cn.com/problems/reverse-linked-list-ii/)

* [86. 分隔链表 Median](https://leetcode-cn.com/problems/partition-list/)

TODO：还有 k个一组反转链表和判断回文链表没看

### 模拟题

#### 按顺序打印/生成矩阵
基本逻辑就是明确好访问顺序，然后进行打印还是生成操作都是类似的，
顺时针打印矩阵提供了一个很好的按层访问方法：
这里使用的方法题解的按层模拟，依次通过：

1.（top, left）-> (top, right）
2. (top + 1, right) -> (bottom, right)
3. 如果 left < right && top < bottom，则遍历从右到左遍历下侧元素，在从下到上遍历左侧元素
   (bottom, right - 1) -> (bottom, left+1)
   (bottom, left) -> (top + 1, left)
遍历完成后进行 left top ++ right bottom --，接着继续遍历。

后续即便是逆时针，或者(bottom, right)开始，都是类似的可以仿照的。

* [剑指 Offer 29. 顺时针打印矩阵 Easy](https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/)

* [59. 螺旋矩阵 II Median](https://leetcode-cn.com/problems/spiral-matrix-ii/)

TODO：更难的 885. 螺旋矩阵 III，https://leetcode-cn.com/problems/spiral-matrix-iii/

#### 栈的模拟

* [剑指 Offer 31. 栈的压入、弹出序列 Median](https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/)
   首先有个结论需要知道，就是给定pushed 和 poped序列，则压入 / 弹出操作的顺序（即排列）是 唯一确定的。
   因此验证的做法就是每次入栈后，比较栈首元素是否与当前poped的元素相同，如果相同则出栈，最后查看stack
   中是否还有元素。
   
### 位运算

* [剑指 Offer 56 - I. 数组中数字出现的次数 Median](https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/)
  这题有两个数字只出现一次，而不是一个数字，因此直接全量异或是不ok的，题解提到的方法是，分组异或，如果我们能够
  将两个出现一次的数字分到两个组，同时其它出现两次的相同的数字也分到一个组，那不就分组异或后的结果就是
  答案嘛，那如何做到这一点呢？
  通过全量异或，得到的是`x=a^b`的结果，那我们找到`x`中最低位的1不就代表这两个数字不同嘛，然后根据这一位的1来进行
  分组的话，两个数字肯定可以被分到不同的组，同时相同的数字该位必然相同，因此也被分到同一组，所以这个方法OK。
  
* [剑指 Offer 56 - II. 数组中数字出现的次数 II Median](https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/)
  这题变了一些，变成只有一个数字出现一次，其它都出现三次了，其实本质上还是去找位运算有啥规律，
  这题的每一位求和再除以3，就可以获得只出现一次那个，就通过这个方式去还原得到答案即可。  

### DFSearch
DFS当然在树以及DP的带memo的自顶向下以及图中有很多应用，所以这里总结一些看上去没有那么典型的，非上述那些类的DFS的应用

* [剑指 Offer 16. 数值的整数次方](https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/)
  很明显可以通过DFS来减少时间复杂度，思想上其实很像DP，和Fib数列类似，虽然不是典型
  的DP问题，但是有递推公式，并且有重复子问题，因此这里的DFS也可以说是带memo的自顶向下。
  只不过这里没有显式的构建一个memo，而是知道重复计算何时出现，直接利用了。
  另外，这里设计到负指数的问题，当然可以变成正指数来做，但是需要注意的是，有越界的可能，
  因为int范围是-2^31 <= n <= 2^31-1，所以需要考虑越界问题。
  
### 数组技巧题
这边主要总结一些有一些看似简单，但是有小技巧trick的数组题

* [88. 合并两个有序数组 Easy](https://leetcode-cn.com/problems/merge-sorted-array/)
  这题看上去非常简单，但是关键在于如何原地完成合并，就一个trick，就是从大到小来合并，
  这样就可以利用上nums1的充足的空间。
  
* [剑指 Offer 39. 数组中出现次数超过一半的数字 Easy](https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/)
  最直观的做法：就是用哈希来存储所有元素的个数，然后遍历获得答案，时间空间复杂度都是O(n)
  一个trick：因为这里众数的定义是超过一半并且题目保证一定存在，那么排序后，index位于中间的元素一定是答案
  更加巧妙的解法-摩尔投票法：核心理念为 票数正负抵消 。此方法时间和空间复杂度分别为O(n)，为本题的最佳解法。
  
### 智力/找规律题

* [剑指 Offer 61. 扑克牌中的顺子](https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof/)
  先排序，然后遍历获得joker数量，同时保证没有重复元素，关键trick在于最后如果非joker的最大最小值小于5（要求的连续数量）
  则说明可以是顺子。
  
* [263. 丑数](https://leetcode-cn.com/problems/ugly-number/)
  根据质因数的定义，小于等于0的必然不是丑数，否则就一直除2，3，5，如果最后剩下1，说明就是丑数。
 
