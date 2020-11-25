package ArrayTopic;

import java.util.ArrayList;
import java.util.List;

/**
 * 1476. 子矩形查询 Medium
 * https://leetcode-cn.com/problems/subrectangle-queries/
 *
 * 暴力更新其实就可以完成，时间复杂度update: O(r * c), get: O(1);
 * 另外一种方法是记录下每次更新的范围，这样get: O(k) k为更新次数, update: O(1),
 * 这种方式的时间复杂度与矩阵大小无关，与更新频率有关，所以如果是且小矩阵频繁更新，
 * 则需要有所权衡。
 */
public class SubrectangleQueries {
    private int[][] rectangle;

    private List<int[]> list = new ArrayList<>();

    public SubrectangleQueries(int[][] rectangle) {
        this.rectangle = rectangle;
    }

    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        int[] change = new int[] {row1, row2, col1, col2, newValue};
        list.add(change);
    }

    public int getValue(int row, int col) {
        for (int i = list.size()-1; i >=0; i--) {
            if (list.get(i)[0] <= row  && row <= list.get(i)[1]
                    && list.get(i)[2] <= col  && col <= list.get(i)[3] ) {
                return list.get(i)[4];
            }
        }
        return rectangle[row][col];
    }
}
