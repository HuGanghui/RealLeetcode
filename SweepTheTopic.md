## SweepTheTopic

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

#### 背包问题总结：

* [背包问题九讲](https://github.com/tianyicui/pack/blob/master/V2.pdf)
  从0-1背包开始，总结了绝大部分的背包问题的变种，很多问题其本质就是背包问题，因此
  背包问题值得学习。

#### 1. 具有递推公式

**前缀和问题：**

前缀和本身的就包含了递推公式

Easy:

* [303. 区域和检索 - 数组不可变](https://leetcode-cn.com/problems/range-sum-query-immutable/)
  简单一维前缀和递推公式：$dp[i] = dp[i-1] + nums[i]$，$dp[i]$表示从0-i的所有元素和，
  一次O(n)时间复杂度的计算，可以保证后续O(1)时间复杂度获取任何两个索引下标之间的元素和。
  
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

Median:

* [377. 组合总和 Ⅳ](https://leetcode-cn.com/problems/combination-sum-iv/)
  暴力思路：回溯枚举，时间复杂度O(k^n)，空间复杂度O(n)，k为nums中元素个数
  优化思路：动态规划，时间复杂度O(kn)，空间复杂度O(n)，这题就是最原始本质的计数排列问题，
          递推公式就是直接相加：$dp[i] += dp[i-nums[j]]$

* [1641. 统计字典序元音字符串的数目](https://leetcode-cn.com/problems/count-sorted-vowel-strings/)
  基本思路：动态规划，时间复杂度O(n)，空间复杂度O(n)，当然可以进行状态压缩，因为只需要使用5个数，使得空间复杂度到O(1)。
          这题稍微复杂些，考虑长度为i以u结尾的字符串数量：$dp[i][u] = dp[i-1][a] + dp[i-1][e] + dp[i-1][i] + dp[i-1][o] + dp[i-1][u]$ 
          依次类推，$dp[i][a] = dp[i-1][a]$，最后返回总数$dp[i][a]+...+dp[i][u]$。
          这题特殊在于递推公式有5个，而不是一个，可能有点超乎想象那种，所有就没想到。
          
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
  