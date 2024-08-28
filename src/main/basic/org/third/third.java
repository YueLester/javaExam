package third;

import java.util.*;

public class third {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[][] nums = new int[n][2];
        int beginDay = Integer.MAX_VALUE;
        int endDay = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            nums[i][0] = in.nextInt();
            nums[i][1] = in.nextInt();
            beginDay = Math.min(beginDay, nums[i][0]);
//            endDay = Math.max()
        }
        Arrays.sort(nums, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) return o1[0]-o2[0];
                return o1[1]-o2[1];
            }
        });
        int[][] dp = new int[n][k+1];
    }
}
