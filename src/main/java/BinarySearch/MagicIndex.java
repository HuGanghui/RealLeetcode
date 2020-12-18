package BinarySearch;

/**
 * 面试题 08.03. 魔术索引 Easy
 * https://leetcode-cn.com/problems/magic-index-lcci/
 *
 * 没解决，不能直接使用二分查找，因为有可能由多个符合条件的数，题解有分治的方法，后面再看
 */
public class MagicIndex {
    private int result = -1;

    public int findMagicIndex(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (nums[middle] == middle) {
                if (result== -1 || middle < result) {
                    result = middle;
                }
                right = middle - 1;
            } else if (nums[middle] > middle) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MagicIndex magicIndex = new MagicIndex();
        magicIndex.findMagicIndex(new int[]{0, 0, 2});
    }
}
