package StringTopic;

/**
 * 165. 比较版本号 Median
 * https://leetcode-cn.com/problems/compare-version-numbers/
 *
 * 这题有两个语言上的点需要注意，一个`version1.split("\\.")`，点需要转义，
 * 另一个就是Integer.parseInt会自动去除前缀零。
 */
public class CompareVersion {
    public int compareVersion(String version1, String version2) {
        String[] arrayOne = version1.split("\\.");
        String[] arrayTwo = version2.split("\\.");
        int n = arrayOne.length;
        int m = arrayTwo.length;
        for (int i = 0; i < n || i < m; i++) {
            int a = i < n ? Integer.parseInt(arrayOne[i]) : 0;
            int b = i < m ? Integer.parseInt(arrayTwo[i]) : 0;
            if (a < b) {
                return -1;
            } else if (a > b) {
                return 1;
            }
        }
        return 0;
    }
}
