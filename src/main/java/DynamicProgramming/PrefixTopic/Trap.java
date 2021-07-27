package DynamicProgramming.PrefixTopic;

/**
 * 42. 接雨水 Hard
 * https://leetcode-cn.com/problems/trapping-rain-water/
 *
 * 分别记录左右的前/后缀最大值，然后计算即可。时间复杂度O(n)。
 */
public class Trap {
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }

        rightMax[n-1] = height[n-1];
        for (int i = n-2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            result += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return result;
    }
}
