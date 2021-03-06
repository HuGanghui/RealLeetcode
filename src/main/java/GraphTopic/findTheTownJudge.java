package GraphTopic;

/**
 * 997. 找到小镇的法官 Easy
 * https://leetcode-cn.com/problems/find-the-town-judge/
 *
 * 本质上只需要统计一下每个节点的出度和入度，然后满足条件即可。同时这道题目提供的存储格式
 * 不同于邻接矩阵和邻接表，算是介于两者之间的，不会受稀疏性导致大量空间浪费，同时对于获取
 * 每个节点的出度和入度的相邻边都需要遍历整个存储结构。
 */
public class findTheTownJudge {
    public int findJudge(int N, int[][] trust) {
        int[][] result = new int[N+1][2];
        for (int[] ele : trust) {
            result[ele[0]][0] += 1; // 统计出度
            result[ele[1]][1] += 1; // 统计入度
        }
        int judge = -1;
        for (int i = 1; i < result.length; i++) {
            if (result[i][0] == 0 && result[i][1] == N-1) {
                judge = i;
                break;
            }
        }
        return judge;
    }
}
