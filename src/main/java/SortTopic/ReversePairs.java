package SortTopic;

/**
 * 剑指 Offer 51. 数组中的逆序对 Hard
 * https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 *
 * 逆序对问题，暴力解法就是枚举，但是复杂度到了O(n^2)，观察会发现，如果将数组分为两部分，
 * 那么不需要依次去比较，而是可以根据排序，在O(n)内完成计算，这个和归并排序的思想是一致的。
 * 因此基本上就是在归并排序的基础上加上了一行对逆序对的计算。
 */
public class ReversePairs {
    private int[] aux;

    public int reversePairs(int[] nums) {
        aux = new int[nums.length];
        return reversePairs(nums, 0, nums.length - 1);
    }

    private int reversePairs(int[] nums, int low, int high) {
        if (low >= high) {
            return 0;
        }

        int mid = low + (high - low) / 2;
        int c1 = reversePairs(nums, low, mid);
        int c2 = reversePairs(nums, mid+1, high);
        int c3 = merge(nums, low, mid, high);

        return c1 + c2 + c3;
    }

    private int merge(int[] nums, int low, int mid, int high) {
        for (int i = low; i <= high; i++) {
            aux[i] = nums[i];
        }
        int i = low;
        int j = mid+1;
        int k = low;
        int c3 = 0;
        while (i <= mid && j <= high) {
            if (aux[i] < aux[j]) {
                nums[k++] = aux[i++];
            } else {
                c3 = mid - i + 1; // 计算逆序对
                nums[k++] = aux[j++];
            }
        }
        while (i <= mid) {
            nums[k++] = aux[i++];
        }
        while (j <= high) {
            nums[k++] = aux[j++];
        }
        return c3;
    }
}
