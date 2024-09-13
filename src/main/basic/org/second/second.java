package second;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class second {
    final static long MOD = 1000_000_007;
    static long[] pow = new long[10003];
    static {
        pow[0] = 1;
        for (int i = 1; i < 10002; i++) {
            pow[i] = (pow[i-1] * 2) % MOD;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i ++) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] nums = new int[n];
            for (int j = 0; j < n; j++) {
                nums[j] = in.nextInt();
            }
            doBiz(n, k, nums);
        }
    }
    private static void doBiz(int n, int k, int[] nums) {
        // 插入k 次， 表示每次都查最大的
        // 某部分的和是 m, 插入后
        // 1： 2m
        // 2： 4m
        // k； 2^k m
        // 最终结果 是 sum + 2^k m - m

        // 1. 计算m : 最大子数组
        long m = 0;
        long cur = 0;
        long res = 0;
        for (int i = 0; i < n; i++) {
            cur = Math.max(nums[i], cur + nums[i]);
            m = Math.max(cur, m);
            // 先求和
            res = (res + nums[i]) % MOD;
        }
        res = res + m * (pow[k]-1);
        // 负数情况
        while (res < 0) {
            res += MOD;
        }
        System.out.println(res%MOD);
        // todo:是否包含特殊情况？ 全负？ 包含
    }
}
