package second;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        int[] after = new int[n];

        for (int i = 0; i < n; i ++) {
            nums[i] = in.nextInt();
        }
        int interval = in.nextInt();
        for (int i = 0; i < n; i++) {
            after[i] = nums[i] % interval;
        }
        // 统计一下
        int[] hash = new int[interval];
        int maxTarget = -1;
        for (int i = 0; i < n; i++) {
            hash[after[i]]++;
        }
        for (int i = 0; i < interval; i++) {
            maxTarget = Math.max(maxTarget, hash[i]);
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (hash[after[i]] == maxTarget) {
                res = Math.min(res, nums[i]);
            }
        }
        System.out.print(res);

    }
}