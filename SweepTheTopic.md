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

