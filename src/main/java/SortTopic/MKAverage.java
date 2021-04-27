package SortTopic;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 1825. 求出 MK 平均值 Hard
 * https://leetcode-cn.com/problems/finding-mk-average/
 *
 * 这题首先相当于维护一个窗口大小为m的序列，在这个序列内，再进行一定的
 * 操作，这里就涉及到流的移动，需要对插入、删除进行相应的处理。不过有个
 * 小的trick就是删除时，在m序列内删除一个等值的其实就行，没有必要一定是
 * 真的最先进入的。然后这题的普遍做法就是维护三个堆，最小的k个元素，最大的
 * k个元素以及中间的元素，但是值得注意的是，由于中间部分需要和最小、最大的都
 * 有交互，为了降低时间复杂度，对中间同时维护大顶堆和小顶堆。
 * 然后基本的逻辑其实就很简单，但写对不容易，先插入，然后判断最小、最大是否超过了
 * k个，超过了就移动到中间。然后如果目前窗口达到了m，那么我们需要进行删除操作，
 * 三堆中任意删除一个就行，最后还要确保在窗口达到m时，最小、最大堆的数量是k，因为
 * 如果之前刚好删除了最小、最大的，我们得在中间补过去。
 *
 * 其实这里的思想可以用到求流的四分位数中，固定大小确保不会无限增长，同时在log(m)的
 * 复杂度内完成四分位数的求解。
 */
public class MKAverage {
    private int sum;
    private int k;
    private int m;
    private Queue<Integer> list;
    private PriorityQueue<Integer> lower;
    private PriorityQueue<Integer> upper;
    private PriorityQueue<Integer> middleMinHeap;
    private PriorityQueue<Integer> middleMaxHeap;
    public MKAverage(int m, int k) {
        this.k = k;
        this.m = m;
        list = new LinkedList<>();
        lower = new PriorityQueue<>((o1, o2) -> {return -o1 + o2;}); // 大顶堆
        upper = new PriorityQueue<>(); // 小顶堆
        middleMinHeap = new PriorityQueue<>((o1, o2) -> {return -o1 + o2;}); // 大顶堆
        middleMaxHeap = new PriorityQueue<>(); // 小顶堆
    }

    public void addElement(int num) {
        list.offer(num);

        if (!lower.isEmpty() && lower.peek() >= num) {
            lower.offer(num);
        } else if (!upper.isEmpty() && upper.peek() <= num) {
            upper.offer(num);
        } else {
            middleMaxHeap.offer(num);
            middleMinHeap.offer(num);
            sum += num;
        }

        if (lower.size() > k) {
            int top = lower.poll();
            middleMaxHeap.offer(top);
            middleMinHeap.offer(top);
            sum += top;
        }

        if (upper.size() > k) {
            int down = upper.poll();
            middleMaxHeap.offer(down);
            middleMinHeap.offer(down);
            sum += down;
        }

        if (list.size() > m) {
            int removed = list.poll();
            if (!lower.isEmpty() && lower.peek() >= removed) {
                lower.remove(removed);
            } else if (!upper.isEmpty() && upper.peek() <= removed) {
                upper.remove(removed);
            } else {
                middleMaxHeap.remove(removed);
                middleMinHeap.remove(removed);
                sum -= removed;
            }
        }

        if(list.size() >= m){
            while(lower.size()<k && !middleMaxHeap.isEmpty()) {
                int temp = middleMaxHeap.poll();
                middleMinHeap.remove(temp);
                sum -= temp;
                lower.offer(temp);
            }
            while(upper.size()<k && !middleMinHeap.isEmpty()){
                int temp = middleMinHeap.poll();
                middleMaxHeap.remove(temp);
                sum -= temp;
                upper.offer(temp);
            }
        }
    }

    public int calculateMKAverage() {
        if (list.size() < m) {
            return -1;
        } else {
            return (int) (sum / (m - 2*k));
        }
    }
}
