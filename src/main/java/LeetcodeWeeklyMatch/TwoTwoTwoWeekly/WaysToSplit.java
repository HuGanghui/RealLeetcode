package LeetcodeWeeklyMatch.TwoTwoTwoWeekly;

import java.util.ArrayList;
import java.util.List;

/**
 * 5643. 将数组分成三个子数组的方案数 Median
 * https://leetcode-cn.com/problems/ways-to-split-array-into-three-subarrays/
 *
 * 一看到分割问题，我就无脑想到了回溯。。。，确实可以做，有两个问题：1. 分割的数量非常小，只有3个，因为完全
 * 可以for循环，而不是递归，而且写法上会更简单。当然for循环/回溯也是O（n^2)的时间复杂度，会超时。
 *
 * 这题的考点有 1. 前缀和 （我想到了） 2. 更优的获取切分方式-前缀和是递增的，因此可以使用二分
 */
public class WaysToSplit {

    public static void main(String[] args) {
        WaysToSplit waysToSplit = new WaysToSplit();
        System.out.println(waysToSplit.waysToSplit(new int[] {1,3,9,3,10,5,7,7}));
    }

    public int waysToSplit(int[] nums) {
        int size = 0;
        int[] total = new int[nums.length];
        // 前缀和
        total[0] = nums[0];
        for (int i = 1; i< nums.length; i++) {
            total[i] = nums[i] + total[i-1];
        }

        for (int i = 0; i< nums.length-2; i++) {
            int minIndex = findSplitMinIndex(total, nums,i+1,nums.length-2);
            int maxIndex = findSplitMaxIndex(total, nums, i, i+1, nums.length-2);
            if (minIndex == -1 || maxIndex == -1) {
                continue;
            }
            if (maxIndex >= minIndex) {
                size += (maxIndex - minIndex + 1) % (int)(Math.pow(10, 9) + 7);
            }
        }
        return size % (int)(Math.pow(10, 9) + 7);
    }

    private int findSplitMinIndex(int[] total, int[] nums, int start, int end) {
        int left = start;
        int right = end;
        int result = -1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (total[middle] - total[start] + nums[start] >= total[start-1]) {
                if (middle-1 < start || total[middle-1] - total[start] + nums[start] < total[start-1]) {
                    result = middle;
                    return result;
                }
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return result;
    }

    private int findSplitMaxIndex(int[] total, int[] nums, int prevStart, int start, int end) {
        int left = start;
        int right = end;
        int result = -1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (total[nums.length-1] - total[middle] >= total[middle] - total[prevStart]) {
                if (middle+1 > end || total[nums.length-1] - total[middle+1]
                        < total[middle+1] - total[prevStart]) {
                    result = middle;
                    return result;
                }
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return result;
    }

    public int waysToSplitFor(int[] nums) {
        int size = 0;
        // 前缀和
        int[] total = new int[nums.length];
        total[0] = nums[0];
        for (int i = 1; i< nums.length; i++) {
            total[i] = nums[i] + total[i-1];
        }

        for (int i = 0; i< nums.length; i++) {
            for (int j = i+1; j< nums.length-1; j++) {
                int sum2 = total[j] - total[i];
                int sum3 = total[nums.length-1] - total[j];
                if (total[i] <= sum2 && sum2 <= sum3) {
                    size = (size + 1) % (int) (Math.pow(10, 9) + 7);
                }
            }
        }
        return size;
    }
}

// 回溯方法
class WaysToSplitBackTracking {
    private int size = 0;

    public int waysToSplit(int[] nums) {
        ArrayList<Integer> sum = new ArrayList<>();
        int[] total = new int[nums.length];
        total[0] = nums[0];
        for (int i = 1; i< nums.length; i++) {
            total[i] = nums[i] + total[i-1];
        }

        for(int i = 0; i < 3; i++) {
            sum.add(0);
        }
        backtracking(0, total, nums, sum, 0);
        return size;
    }

    private void backtracking(int startIndex, int[] total, int[] nums, List<Integer> sum, int k) {
        if (k == 3) {
            if (sum.get(0) <= sum.get(1) && sum.get(1) <= sum.get(2)) {
                size = (size + 1) % (int) (Math.pow(10, 9) + 7);
            }
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            if (k==2) {
                sum.set(k, total[nums.length -1] - total[startIndex] + nums[startIndex]);
                backtracking(nums.length, total, nums, sum, k+1);
                sum.set(k, 0);
                break;
            } else {
                sum.set(k, total[i] - total[startIndex] + nums[startIndex]);
                backtracking(i+1, total, nums, sum, k+1);
                sum.set(k, 0);
            }
        }
    }

    public static void main(String[] args) {
        WaysToSplitBackTracking waysToSplit = new WaysToSplitBackTracking();
        System.out.println(waysToSplit.waysToSplit(new int[] {1,2,2,2,5,0}));
    }
}
