import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //
        int t = in.nextInt();
        for (int count = 0; count < t; count++) {
            int n = in.nextInt();
            long[] nums = new long[n];
            long sum = 0;
            Map<Long, Integer> map = new HashMap<Long, Integer>();

            for (int i = 0; i < n; i++) {
                nums[i] = in.nextInt();
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
                sum += nums[i];
            }
            long res = doBiz(map, nums, sum, n);
            System.out.println(res);
        }
    }
    private static long doBiz(Map<Long, Integer> map, long[] nums, long sum, int n) {
        if (sum * 2 % n != 0) {
            return 0;
        }
        long target = sum * 2 / n;
        Set<Long> set = new HashSet<>();
        long res = 0;

        for (long num:nums) {
            if (set.contains(num) || set.contains(target - num)) {
                continue;
            }
            set.add(num); set.add(target-num);

            if (!map.containsKey(num) || !map.containsKey(target - num)) continue;

            if (target == 2 * num) {
                long xx = map.get(num);
                res += (xx-1) * xx / 2;
            } else {
                res += map.getOrDefault(num, 0) * map.getOrDefault(target-num, 0);
            }
        }

        return res;
    }
}