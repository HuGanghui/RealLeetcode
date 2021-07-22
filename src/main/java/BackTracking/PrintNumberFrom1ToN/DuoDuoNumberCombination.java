package BackTracking.PrintNumberFrom1ToN;

import java.util.*;

/**
 * 拼多多2021笔试题-多多的数字组合
 * https://www.nowcoder.com/questionTerminal/3d6b53e097ea41bda049d111f30db28e
 *
 * 这题本质上就是需要进行从小到大枚举数字，也就是一个带used的全排列，然后通过数字总和判断是否满足条件，
 * 找到满足条件的最小值即可。
 * 需要注意的就是，对于打印1到最大n位数这种，需要额外多一个for循环来控制每次打印的位数，
 * 这样才能保证是从小到大的顺序。
 *
 */
public class DuoDuoNumberCombination {

    private static List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int size = 1; size < 10; size++) {
            if (result.size() > 0) {
                break;
            }
            for (int i = 1; i <= 9; i++) {
                if (result.size() > 0) {
                    break;
                }
                List<Integer> temp = new ArrayList<>();
                boolean[] used = new boolean[10];
                temp.add(i);
                used[i] = true;
                dfs(n, i, temp, size, used);
            }
        }

        if (result.size() > 0) {
            System.out.println(getNum(result.get(0)));
        } else {
            System.out.println(-1);
        }
    }

    private static int getNum(List<Integer> list) {
        int result = 0;
        for (int ele : list) {
            result *= 10;
            result += ele;
        }
        return result;
    }

    private static void dfs(int n, int sum, List<Integer> temp, int size, boolean[] used) {
        if (result.size() > 0) {
            return;
        }

        if (sum > n) {
            return;
        }

        if (temp.size() == size) {
            if (sum == n) {
                result.add(new ArrayList<>(temp));
            }
            return;
        }


        for (int i = 0; i <= 9; i++) {
            if (used[i]) {
                continue;
            }
            temp.add(i);
            used[i] = true;
            dfs(n, sum+i, temp, size, used);
            used[i] = false;
            temp.remove(temp.size() - 1);
        }
    }
}
