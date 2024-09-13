package first;

import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class first {
    public static void main(String[] args) {
        // 每一站的花费
        int[] nums = {1,2,3,4,1,2,3,4,1,2,3,4,1,2,3,4,1,2,3,4};
        int n = nums.length;
        // 地铁最多 k 站
        int k = 10;
        // 地铁最多坐的站数
        int m = 3;
        // 地铁花费
        int cost = 2;

        // i 表示 过了 nums[i-1]
        // j 表示 坐地铁次数， 0， 1， 2， 。。。 k
        int[][] dp = new int[n+1][k+1];
        for (int i = 1 ; i <= n; i++) {
            // 当前站走路花费 nums[i-1]
            // 地铁是0 只能走过来
            dp[i][0] = dp[i-1][0] + nums[i-1];
            for (int j = 1; j <= k; j++) {
                // dp[i][j]: 从前一站走过来； 从前m站地铁过来
                dp[i][j] = dp[i-1][j] + nums[i-1];
                for (int pre = i-1; i - pre >= m && pre >= 0; pre--) {
                    dp[i][j] = Math.max(dp[i][j], dp[pre][j-1] + cost);
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 0; j <= k; j++) {
            res = Math.min(dp[n][j], res);
        }
        System.out.print(res);
    }
}