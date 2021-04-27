package SortTopic;

import java.util.PriorityQueue;

/**
 * 703. 数据流中的第 K 大元素 Easy
 * https://leetcode-cn.com/problems/kth-largest-element-in-a-stream/
 *
 * 这里和求数据流中的中位数类似，可以暴力每次排序；也可以内部维护一个有序数组/链表，
 * 不过数组虽然可以二分，但是移动元素需要O(n)，而链表插入只要O(1)，但是查找则需要
 * O(n)，两者都需要O(n)的复杂度。这种方法其实就是选择排序；当然还有大杀器，利用堆
 * 来进行排序，不过有个小的trick，就是只维护k个大小的小顶堆，这样堆顶原始就是我们需要的。
 *
 */
public class KthLargestElementInStream {
    // 这里其实有个空间优化，因为我们只需要第k大的，其他排在后面的都不需要，
    // 因此这里可以只要大小为k的数组
    private final int CAP = (int) (2 * Math.pow(10, 4));
    private int[] array = new int[CAP];
    private int size;
    private int k;

    public KthLargestElementInStream(int k, int[] nums) {
        size = 0;
        this.k = k;
        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    public int add(int val) {
        if (size == 0) {
            array[0] = val;
        } else {
            int index = binarySearch(val);
            if (index == size) {
                array[size] = val;
            } else {
                for (int i = size; i > index; i--) {
                    array[i] = array[i-1];
                }
                array[index] = val;
            }
        }
        size++;
        if (size < k) {
            return -1;
        } else {
            return array[size-k];
        }
    }

    private int binarySearch(int val) {
        int left = 0;
        int right = size - 1;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] < val) {
                left = mid + 1;
            } else {
                if (mid == 0 || array[mid - 1] < val) {
                    result = mid;
                    break;
                } else {
                    right = mid - 1;
                }
            }
        }
        if (result == -1) {
            result = size;
        }
        return result;
    }
}

class KthLargestElementInStreamHeap {
    private PriorityQueue<Integer> pq;
    private int k;

    public KthLargestElementInStreamHeap(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<>(k);
        for (int i = 0; i < nums.length; i++) {
            pq.offer(nums[i]);
            if (pq.size() > k) {
                pq.poll();
            }
        }
    }

    public int add(int val) {
        pq.offer(val);
        if (pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }
}
