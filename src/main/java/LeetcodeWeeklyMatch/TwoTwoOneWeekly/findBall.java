package LeetcodeWeeklyMatch.TwoTwoOneWeekly;

/**
 * 5210. 球会落何处 Median
 * https://leetcode-cn.com/problems/where-will-the-ball-fall/
 *
 * 一道过程模拟题，关键是正常模拟过程，处理边界条件，我卡了很久这道题，可能还是没有
 * 经验，没有理清楚这里相当于有三个变量，
 * 首先明确对于每个出发位置都有一个answer，然后每个出发位置都会经历从row0->rowN
 * 这个过程中j会左右变化。
 */
public class findBall {
    public int[] findBall(int[][] grid) {
        int c = grid[0].length;
        int r = grid.length;
        int[] answer = new int[c];
        for (int k = 0; k < c; k++) {
            int j = k;
            for (int i = 0; i < r; i++) {
                if ((j == c-1 && grid[i][j] == 1) || (j == 0 && grid[i][j] == -1)) { // 边界条件
                    answer[k] = -1;
                    break;
                } else if (grid[i][j] == 1 && grid[i][j+1] == -1) {
                    answer[k] = -1;
                    break;
                } else if (grid[i][j] == 1 && grid[i][j+1] == 1) {
                    j++;
                    answer[k] = j;
                } else if (grid[i][j] == -1 && grid[i][j-1] == 1) {
                    answer[k] = -1;
                    break;
                } else if (grid[i][j] == -1 && grid[i][j-1] == -1) {
                    j--;
                    answer[k] = j;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        findBall findBall = new findBall();
        int[] answer = findBall.findBall(new int[][] {new int[]{1,1,1,-1,-1},
                new int[]{1,1,1,-1,-1},
                new int[]{-1,-1,-1,1,1},
                new int[]{1,1,1,1,-1},
                new int[]{-1,-1,-1,-1,-1}});
        System.out.println();
    }
}
