package BinarySearch;

/**
 * 35. 搜索插入位置 Easy
 * https://leetcode-cn.com/problems/search-insert-position/
 *
 * 这题是典型的可以使用二分查找/搜索的题，但是也没有那么简单，学到一个新的概念叫做循环不变量，
 * 大体意思如下：
 *
 * 参考博客：https://www.cnblogs.com/wuyuegb2312/archive/2013/05/26/3090369.html （其中有些边界条件也不完全正确）
 * 循环不变式：
 *
 * 　　如果key存在于原始数组[0,n-1]，那么它一定在[left,right]中。
 *
 * 初始化：
 *
 * 　　第一轮循环开始之前，处理的数组就是原始数组，这时显然成立。
 *
 * 保持：
 *
 * 　　每次循环开始前，key存在于待处理数组array[left, ..., right]中。
 *
 * 　　对于array[mid]<key，array[left, ..., mid]均小于key，key只可能存在于array[mid+1, ..., right]中；
 *
 * 　　对于array[mid]>key，array[mid, ..., right]均大于key，key只可能存在于array[left, ..., mid-1]中；
 *
 * 终止：
 *    这里需要根据具体题目的需要进行一下判断，是不存在就返回-1还是返回应当插入的位置下标
 *
 */
public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int size = nums.length;
        int left = 0;
        int right = size;

        while(left < right) {  // 这里因为right无法取到size，因此无法让left==right
            int middle = left + (right - left) / 2; // 防止溢出

            if (nums[middle] >= target) { // 这道题其实这里的等号可以剥离出来，放到一起默认当有重复元素，返回第一个的下标
                right = middle; // 本质上right=size, middle能取到的值也就是[0,size-1], 因此左右都是闭的没有问题
            } else if (nums[middle] < target) {
                left = middle + 1;
            }
        }

        return right;
    }

    public static void main(String[] args) {
        SearchInsertPosition searchInsertPosition = new SearchInsertPosition();
        int result = searchInsertPosition.searchInsert(new int[] {1,1,1,2}, 1);
        System.out.println(result);
    }
}
