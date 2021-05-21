package SortTopic.ClassicSort;

/**
 * 计数排序
 *
 * 计数排序核心在于将数据值转换为键存在额外开辟的数组空间中。
 * 并且作为线性时间复杂度的排序O(n+k)，计数排序要求输入的数据是必须有确定的范围的整数。
 * 强调：1. 有确定范围，不能太大，否则需要开辟非常大的辅助空间 2. 数据必须为整数
 *
 * 算法描述：
 * 1. 找出待排序的数组中最大和最小的元素；
 * 2. 统计数组中每个值为i的元素出现的次数，存入数组C的第i项；
 * 3. 对所有的计数累加（从C中的第一个元素开始，每一项和前一项相加）；
 * 4. 反向填充目标数组：将每个元素i放在新数组的第C(i)项，每放一个元素就将C(i)减去1。
 *
 */
public class CountingSort implements  ArraySort {
    @Override
    public void sort(int[] array) {
        int bias;
        int min = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (array[i] < min) {
                min = array[i];
            }
        }
        bias = 0 - min;
        int[] bucket  = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            bucket[array[i] + bias]++;
        }

        int index = 0;
        int bucket_index = 0;
        while(index < array.length) {
            if (bucket[bucket_index] != 0) {
                array[index] = bucket_index - bias;
                bucket[bucket_index]--;
                index++;
            } else {
                bucket_index++;
            }
        }
    }
}
