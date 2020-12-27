package DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 638. 大礼包 Median
 * https://leetcode-cn.com/problems/shopping-offers/
 *
 * 这题一看上去，第一印象就是组合问题，可以使用回溯法，枚举搜索，结果也是可以通过的，但是有个问题就是
 * 需要将price的单个商品同样转换成special的礼包形式，然后进行遍历搜索。转换过程收获一个知识点，ArrayList
 * 需要手动add每个元素。。。构造函数传入的num只代表初识容量 时间复杂度O(x^n) x为多个礼物的要求数只和，
 * n为special的礼包种类数
 *
 * 然后本质上这题和零钱兑换是一样的，可以使用DP算法，但是这题相比之下，是多维度的，且维度不定的，因此使用DP的自低向
 * 上比较难去构造DP Table，因此感觉这题是少见的用自顶向下DP更合适的题目，谨记，说明每种写法都是有用处的。时间复杂度O(
 */
public class ShoppingOffers {
    // 回溯搜索法
    private int sum = -1;

    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int num = price.size();
        ArrayList<Integer> tempDemo = new ArrayList<>(num);
        for (int j = 0; j < num; j++) {
            tempDemo.add(0);
        }
        for (int i = 0; i < num; i++) {
            ArrayList<Integer> temp = new ArrayList<>(tempDemo);
            temp.set(i, 1);
            temp.add(price.get(i));
            special.add(temp);
        }
        ArrayList<Integer> temp = new ArrayList<>(tempDemo);
        temp.add(0);
        backtracking(0, temp, special, needs);
        return sum;
    }

    private void backtracking(int startIndex, List<Integer> temp,
                              List<List<Integer>> special, List<Integer> needs) {
        // 结束条件，完成购买
        if (isEnough(temp, needs)) {
            if (sum == -1 || !temp.isEmpty() && sum > temp.get(temp.size() - 1)) {
                sum = temp.get(temp.size() - 1);
                return;
            }
        }

        // 剪枝1，不能超买
        for (int i = 0; i < temp.size() - 1; i++) {
            if (temp.get(i) > needs.get(i)) {
                return;
            }
        }

        for (int i = startIndex; i < special.size(); i++) {
            // 剪枝2，大于当前最小值
            if (sum != -1 && temp.get(temp.size() - 1) >= sum) {
                continue;
            }
            List<Integer> ele = special.get(i);
            for (int j = 0; j < ele.size(); j++) {
                temp.set(j, temp.get(j) + ele.get(j));
            }
            backtracking(i, temp, special, needs);
            for (int j = 0; j < ele.size(); j++) {
                temp.set(j, temp.get(j) - ele.get(j));
            }
        }
    }

    private boolean isEnough(List<Integer> temp, List<Integer> needs) {
        boolean result = true;
        for (int i = 0; i < temp.size() - 1; i++) {
            if (!temp.get(i).equals(needs.get(i))) {
                result = false;
                break;
            }
        }
        return result;
    }
}


class ShopingOffersDP {
    // 动态规划-自顶向下法
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int num = price.size();
        // 特殊的点，没有使用数组，而是Map来做memo，原因也是多维的needs，
        // 然后由于List<Integer>的hashcode只与其实包含的元素相关，因此哪怕两个不同的List<Integer>
        // hashcode也一样，因此可以做key
        Map<List<Integer>, Integer> map = new HashMap<>();
        // 正常思路，同样是将price转换为special形式，然后去做DP
        ArrayList<Integer> tempDemo = new ArrayList<>(num);
        for (int j = 0; j < num; j++) {
            tempDemo.add(0);
        }
        for (int i = 0; i < num; i++) {
            ArrayList<Integer> temp = new ArrayList<>(tempDemo);
            temp.set(i, 1);
            temp.add(price.get(i));
            special.add(temp);
        }
        int sum = dfs(num, special, needs, map);
        return sum;
    }

    private int dfs(int num, List<List<Integer>> special, List<Integer> needs,
                    Map<List<Integer>, Integer> map) {
        // memo
        if (map.containsKey(needs)) {
            return map.get(needs);
        }

        // 结束条件，完成购买
        if (isEnough(needs)) {
            return 0;
        }

        int sum = Integer.MAX_VALUE;
        for (int i = 0; i < special.size(); i++) {
            ArrayList<Integer> clone = new ArrayList<>(needs);
            int j;
            // 剪枝1，不能超买
            for (j = 0; j < num; j++) {
                int diff = clone.get(j) - special.get(i).get(j);
                if (diff < 0) {
                    break;
                } else {
                    clone.set(j, diff);
                }
            }
            if (j == num) {
                int res = dfs(num, special, clone, map);
                // res >= 0 是排除返回-1的结果，说明该方案不行，但是在本题中
                // 其实这种情况不会出现，因为候选里必然包含了"单位1"
                // 但是如果题目改了，没有包含"单位1"了，那这种写法同样适用，是更通用的
                if (res >= 0 && res + special.get(i).get(num) < sum) {
                    sum = res + special.get(i).get(num);
                }
            }
        }
        // 如果是最大值，说明方案不行，返回-1
        sum = (sum == Integer.MAX_VALUE) ? -1 : sum;
        map.put(needs, sum);
        return sum;
    }

    private boolean isEnough(List<Integer> needs) {
        boolean result = true;
        for (int i = 0; i < needs.size(); i++) {
            result = result && needs.get(i) == 0;
        }
        return result;
    }

    public static void main(String[] args) {
        ShopingOffersDP shopingOffersDP = new ShopingOffersDP();
        List<Integer> price = new ArrayList<>();
        price.add(2);
        price.add(5);
        List<Integer> bag1 = new ArrayList<>();
        bag1.add(3);
        bag1.add(0);
        bag1.add(5);
        List<Integer> bag2 = new ArrayList<>();
        bag2.add(1);
        bag2.add(2);
        bag2.add(10);
        List<List<Integer>> special = new ArrayList<>();
        special.add(bag1);
        special.add(bag2);
        List<Integer> needs = new ArrayList<>();
        needs.add(3);
        needs.add(2);
        int sum = shopingOffersDP.shoppingOffers(price, special, needs);
        System.out.println(sum);
    }
}

class ShopingOffersDP_ {
    // 动态规划-自顶向下法 + "单位1"优化
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        Map<List<Integer>, Integer> map = new HashMap<>();
        return dfs(price, special, needs, map);
    }

    public int dfs(List<Integer> price, List<List<Integer>> special,
                   List<Integer> needs, Map<List<Integer>, Integer> map) {

        if (map.containsKey(needs))
            return map.get(needs);
        // 改进之处，没有使用Integer.MAX_VALUE， 而是使用了"单位1"的组合作为最差的选择，可以做这个小优化，
        // 也是因为这题必然包含"单位1"，同时也不需要在一开始将price换成special了
        int j = 0, res = dot(needs, price);
        for (List<Integer> s : special) {
            ArrayList<Integer> clone = new ArrayList<>(needs);
            for (j = 0; j < needs.size(); j++) {
                int diff = clone.get(j) - s.get(j);
                if (diff < 0)
                    break;
                clone.set(j, diff);
            }
            if (j == needs.size())
                res = Math.min(res, s.get(j) + dfs(price, special, clone, map));
        }
        map.put(needs, res);
        return res;
    }

    public int dot(List<Integer> a, List<Integer> b) {
        int sum = 0;
        for (int i = 0; i < a.size(); i++) {
            sum += a.get(i) * b.get(i);
        }
        return sum;
    }
}
