package SortTopic;

/**
 * 剑指 Offer 45. 把数组排成最小的数 Median
 * https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/
 *
 * 这题需要把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个，最后输出一个
 * 字符串，就是要获得字典序最小，那有一种做法是将数字转换为字符串，然后比较两个字符串 x，y的大小
 * 排序规则为 x + y < y + x 则x更小，这样将排序后的字符串拼接便是所求答案。
 *
 * 因此这题本质上转换成了一个排序问题，只不过排序对象变了以及排序规则变了，其它的就是利用常见的
 * 排序算法，比如快排，归并或者堆排序。
 */
public class MinNumber {
    public String minNumber(int[] nums) {
        int n = nums.length;
        String[] strings = new String[n];
        for (int i =0; i < n; i++) {
            strings[i] = String.valueOf(nums[i]);
        }
        quickSort(strings, 0, n - 1);

        StringBuilder res = new StringBuilder();
        for(String s : strings)
            res.append(s);
        return res.toString();

    }

    private void quickSort(String[] strings, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(strings, left, right);
            quickSort(strings, left, partitionIndex - 1);
            quickSort(strings, partitionIndex + 1, right);
        }
    }

    private int partition(String[] strings, int left, int right) {
        int pivot = left;
        int index = left + 1;
        for (int i = index; i <= right; i++) {
            // 排序对象和对应规则
            if ((strings[i] + strings[pivot]).compareTo(strings[pivot] + strings[i]) < 0) {
                swap(strings, i, index);
                index++;
            }
        }
        swap(strings, pivot, index - 1);
        return index - 1;
    }

    private void swap(String[] strings, int i , int j) {
        String temp = strings[i];
        strings[i] = strings[j];
        strings[j] = temp;
    }
}
