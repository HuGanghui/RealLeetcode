package ArrayTopic;

/**
 * 1539. 第 k 个缺失的正整数 Easy --
 * https://leetcode-cn.com/problems/kth-missing-positive-number/
 *
 * 思想很简单就是枚举遍历比较，但是一些特殊例子也需要考虑，题解还有二分方法，回头可以看看。
 */
public class FindKthPositive {
    public int findKthPositive(int[] arr, int k) {
        int result = 0;
        int j = 0;
        for (int i = 1, m = 0; i < arr[arr.length - 1]; i++) {
            if (arr[m] == i) {
                m++;
            } else {
                j++;
                if (j == k) {
                    result = i;
                    break;
                }
            }
        }
        if (result == 0) {
            result = arr[arr.length - 1] + k - j;
        }
        return result;
    }

    public static void main(String[] args) {
        FindKthPositive findKthPositive = new FindKthPositive();
        int[] array = new int[]{1,13,18};

        findKthPositive.findKthPositive(array, 17);
    }
}
