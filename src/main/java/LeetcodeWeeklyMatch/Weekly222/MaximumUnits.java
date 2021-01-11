package LeetcodeWeeklyMatch.Weekly222;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 5641. 卡车上的最大单元数 Easy
 * https://leetcode-cn.com/problems/maximum-units-on-a-truck/
 *
 * 本题很简单，就是贪心思想，不过从这题学到的不是解题思路，而是代码实现的
 * 一些经验，从而可以有效的减少代码量：1. 最常用的Comparator接口是一个函数式接口
 * 因此可以使用Lambda表达式。2. 尽量使用Math.min/max，而不是if-else比较大小。
 */
public class MaximumUnits {

    // 直接使用Arrays.sort()可以对int[][]排序，因为本质上也是int[]
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        int result = 0;
        Arrays.sort(boxTypes, (o1, o2) -> o1[1] - o2[1]);
        int i = 0;
        while (i < boxTypes.length && truckSize != 0) {
            int min = Math.min(boxTypes[i][0], truckSize);
            result += boxTypes[i][1] * min;
            truckSize -= min;
        }
        return result;
    }

    // 使用优先队列进行排列
    public int maximumUnitsPQ(int[][] boxTypes, int truckSize) {
        // 匿名类写法
//        PriorityQueue<box> priorityQueue = new PriorityQueue<>(new Comparator<box>() {
//            @Override
//            public int compare(box o1, box o2) {
//                return o1.maxUnit - o2.maxUnit;
//            }
//        });

        PriorityQueue<box> priorityQueue =
                new PriorityQueue<>((o1, o2) -> o2.maxUnit - o1.maxUnit);

        for (int[] ele : boxTypes) {
            priorityQueue.add(new box(ele[1], ele[0]));
        }
        int result = 0;
        while (!priorityQueue.isEmpty() && truckSize != 0) {
            box ele = priorityQueue.poll();
            int num = Math.min(truckSize, ele.num);
            result += ele.maxUnit * num;
            truckSize -= num;
            // if-else 写法
//            if (truckSize >= ele.num) {
//                result += ele.maxUnit * ele.num;
//                truckSize -= ele.num;
//            }else {
//                result += truckSize * ele.maxUnit;
//                truckSize -= truckSize;
//            }
        }
        return result;
    }
}

class box {
    int maxUnit;
    int num;
    public box(int maxUnit, int num) {
        this.maxUnit = maxUnit;
        this.num = num;
    }
}
