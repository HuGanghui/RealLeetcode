package BJTUAlgorithomBook.Chapter4DivideAndConquer;

import THUDataStructure.PriorityQueue.Entry;
import THUDataStructure.PriorityQueue.EntryDefault;
import org.junit.Test;

import java.util.*;

public class PriorityQueueTest {

    private final Queue<Entry> pQueue = new PriorityQueue<>(new Comparator<Entry>() {
        @Override
        public int compare(Entry o1, Entry o2) {
            return (Integer) o1.getKey() - (Integer) o2.getKey();
        }
    });

    List<Entry> listOne = new ArrayList<>();
    List<Entry> listTwo = new ArrayList<>();
    List<Entry> listThree = new ArrayList<>();
    List<List<Entry>> lists = new ArrayList<>();

    @Test
    public void test() throws Exception {
        int[] arrayOne = new int[]{3, 4, 5, 6, 7, 8};
        int[] arrayTwo = new int[]{2, 3, 4, 5, 10};
        int[] arrayThree = new int[]{3, 6, 8, 9, 11};
        for (int ele : arrayOne) {
            listOne.add(new EntryDefault(ele, 1));
        }
        for (int ele : arrayTwo) {
            listTwo.add(new EntryDefault(ele, 2));
        }
        for (int ele : arrayThree) {
            listThree.add(new EntryDefault(ele, 3));
        }
        lists.add(listOne);
        lists.add(listTwo);
        lists.add(listThree);
        pQueue.add(listOne.remove(0));
        pQueue.add(listTwo.remove(0));
        pQueue.add(listThree.remove(0));
        while (!pQueue.isEmpty()) {
            int index = outEntry();
            insert(index);
        }
    }

    private int outEntry() {
        int index;
        if (!pQueue.isEmpty()) {
            Entry tempEntry = pQueue.poll();
            System.out.println("" + tempEntry.getKey() + " " + tempEntry.getValue());
            index = (Integer) tempEntry.getValue();
        }
        else {
            index = -1;
        }
        return index;
    }

    private void insert(int index) {
        if (index == 1) {
            enqueueOtherList(listOne, index);

        }
        else if (index == 2) {
            enqueueOtherList(listTwo, index);

        }
        else if (index == 3) {
            enqueueOtherList(listThree, index);
        }
    }

    private void enqueueOtherList(List<Entry> indexList, int index) {
        if (indexList.isEmpty()) {
// 这里当前List为空，是没必要从其他List中入队的
//            for (List<Entry> list : lists) {
//                if (!list.isEmpty()) {
//                    pQueue.add(list.remove(0));
//                    break;
//                }
//            }
            System.out.println("list " + index + " is empty");
        } else {
            pQueue.add(indexList.remove(0));
        }
    }
}
