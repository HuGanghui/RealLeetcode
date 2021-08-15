package SimulationTopic;

import java.util.*;

/**
 * 机器人炸弹 美团笔试题 2021.08.15
 *
 * 整点相遇就会爆炸，输出是每个机器人过了多久爆炸，每个
 * 机器人速度是1。
 *
 * 输入：
 * 10
 * 94 R
 * 74 L
 * 90 L
 * 75 R
 * 37 R
 * 99 R
 * 62 R
 * 4 L
 * 92 L
 * 44 R
 *
 * 输出：
 * -1
 * 6
 * 23
 * -1
 * -1
 * -1
 * 6
 * -1
 * -1
 * 23
 */
public class RobotBomb {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        List<Pair> leftList = new ArrayList<>();
        List<Pair> rightList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] strs = sc.nextLine().split(" ");
            if (strs[1].equals("L")) {
                rightList.add(new Pair(Integer.parseInt(strs[0]), i));
            } else {
                leftList.add(new Pair(Integer.parseInt(strs[0]), i));
            }
        }
        Collections.sort(leftList, (o1, o2)-> {
            return o1.value - o2.value;
        });
        Collections.sort(rightList, (o1, o2)-> {
            return o1.value - o2.value;
        });
        getResult(leftList, rightList);
    }

    private static void getResult(List<Pair> leftList, List<Pair> rightList) {
        int total = leftList.size() + rightList.size();
        int[] result = new int[total];
        Arrays.fill(result, -1);
        int rightSum = rightList.size();
        for (int i = 0; i < rightSum; i++) {
            Pair rightPair = rightList.get(i);
            int temp = binarySearch(leftList, rightPair.value);
            if (temp != -1) {
                for (int j = temp; j >= 0; j--) {
                    Pair leftPair = leftList.get(j);
                    int dis = rightPair.value - leftPair.value;
                    if (dis % 2 == 0) {
                        result[leftPair.index] = dis / 2;
                        result[rightPair.index] = dis / 2;
                        leftList.remove(leftPair);
                        break;
                    } else {
                        continue;
                    }
                }
            }
        }

        for (int i = 0; i < total; i++) {
            System.out.println(result[i]);
        }
    }

    private static int binarySearch(List<Pair> list, int target) {
        int left = 0;
        int n = list.size();
        int right = n - 1;
        Integer result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid).value < target) {
                if (mid == n -1 || list.get(mid + 1).value > target) {
                    result = mid;
                    break;
                } else {
                    left = mid + 1;
                }
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

}

class Pair {
    public int value;
    public int index;
    public Pair() {

    }
    public Pair(int value, int index) {
        this.value = value;
        this.index = index;
    }
}