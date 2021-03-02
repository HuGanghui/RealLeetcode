package SortTopic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 剑指 Offer 41. 数据流中的中位数 Hard
 * https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/
 *
 * 这题有多种思路，从暴力的每次排序到利用当前有序的二分，再到可能比较
 * 难想到的维护一个大顶堆，一个小顶堆。利用堆的做法，时间复杂度为O(log(n))。
 * 中位数通过两个堆的堆顶元素计算而得。有个实现的细节就是，由于获得一个新的
 * 元素，我们无法区分其应该属于较小的一半，还是较大的一半，因此addNum(num)函数
 * 有对应先加入另一个，再将另一个的堆顶放入当前应当加入的堆中。
 *
 * 参考博客：https://leetcode-cn.com/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/
 * solution/mian-shi-ti-41-shu-ju-liu-zhong-de-zhong-wei-shu-y/
 *
 * 不过堆性能最优，但是在特定的应用场景下，有可能二分的方法却更适用，比如动态基线的原型系统
 * 中，不仅仅需要中位数，还需要第一、第三四分位数，因此在这种情况下，堆的做法就有其局限性了，
 * 貌似维护多个堆也能做，但是比较复杂了，并且需要以一定的空间代价来完成。
 */
public class StreamMedianFinder {
    private PriorityQueue<Integer> smallerPQ;
    private PriorityQueue<Integer> biggerPQ;
    private int size;

    /** initialize your data structure here. */
    public StreamMedianFinder() {
        smallerPQ = new PriorityQueue<>();
        biggerPQ = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        size = 0;
    }

    public void addNum(int num) {
        if (size % 2 == 0) {
            smallerPQ.add(num);
            biggerPQ.add(smallerPQ.poll());
        } else {
            biggerPQ.add(num);
            smallerPQ.add(biggerPQ.poll());
        }
        size++;
    }

    public double findMedian() {
        if (size % 2 == 0) {
            return  (biggerPQ.peek() + smallerPQ.peek()) / 2.0;
        } else {
            return biggerPQ.peek();
        }
    }

}

// 这里重复利用当前list已经是有序的特点，使用二分查找来进行插入index的获取，
// addNum时间复杂度O(log(n)) + O(n) (移动后续元素），findMedianO(1)。
class StreamMedianFinderBinarySearch {
    private List<Integer> list;

    /** initialize your data structure here. */
    public StreamMedianFinderBinarySearch() {
        list = new ArrayList<>();
    }

    public void addNum(int num) {
        if (list.size() > 0) {
            int left = 0;
            int right = list.size() -1;
            boolean last = true;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (list.get(mid) < num) {
                    left = mid + 1;
                } else if (list.get(mid) >= num) {
                    if (mid == 0 || list.get(mid-1) < num) {
                        list.add(0);
                        for (int i = list.size() -2; i >= mid; i--) {
                            list.set(i+1, list.get(i));
                        }
                        list.set(mid, num);
                        last = false;
                        break;
                    } else {
                        right = mid - 1;
                    }
                }
            }
            if (last) {
                list.add(num);
            }
        } else {
            list.add(num);
        }
    }

    public double findMedian() {
        int mid = list.size() / 2;
        if (list.size() % 2 == 0) {
            return (list.get(mid) + list.get(mid - 1)) / 2.0;
        } else {
            return list.get(mid);
        }
    }

    public static void main(String[] args) {
        StreamMedianFinderBinarySearch test = new StreamMedianFinderBinarySearch();
        test.addNum(1);
        test.addNum(2);
        System.out.println(test.findMedian());
        test.addNum(3);
        System.out.println(test.findMedian());
    }

}

// 最直观的做法，每来一个数据就加入list，然后进行排序，addNum O(nlog(n))
// findMedian O(1)
class StreamMedianFinderSimpleSort {
    private List<Integer> list;

    /** initialize your data structure here. */
    public StreamMedianFinderSimpleSort() {
        list = new ArrayList<>();
    }

    public void addNum(int num) {
        list.add(num);
        Collections.sort(list);
    }

    public double findMedian() {
        int median = list.size() / 2;
        if (list.size() % 2 == 0) {
            return (list.get(median) + list.get(median - 1)) / 2.0;
        } else {
            return list.get(median);
        }
    }
}
