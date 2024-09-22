import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int t = in.nextInt();
        for (int count = 0; count < t; count++) {
            int n = in.nextInt();
            int[] nums = new int[n];
            boolean withZero = false;

            Map<Integer, Integer> map = new HashMap<>();
            for (int i =0; i < n; i++ ) {
                nums[i] = in.nextInt();
                if (nums[i] == 0) withZero = true;
                map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            }
            boolean res = doBiz(nums, withZero, map);
            if (res ) System.out.println("yes");
            else System.out.println("no");
        }
    }
    private static boolean doBiz(int[] nums, boolean withZero, Map<Integer, Integer> map ) {
        if (nums.length == 0) return false;
        if (withZero) return true;
        // 存在相反数 或者 重复， 一定可以
        for (int num:nums) {
            if (map.get(num) > 1) return true;
            if (map.containsKey(-num)) return true;
        }
        Map<Integer, Integer> map2 = new HashMap<>();
        if (traceBack(0, nums, 0, 0, nums.length, map, map2)) return true;
        // map2 是所有大于2个的组合值
        boolean resValue = false;
        for (Map.Entry<Integer, Integer> entry:map2.entrySet()) {
            int key = entry.getKey(), value = entry.getValue();
            if (value > 1){
                return true;
            }
            if (map.containsKey(-key) || map2.containsKey(-key)){
                resValue = true;
            }
        }

        return resValue;
    }
    private static boolean traceBack(int index, int[] nums, int contaisNum, int curSum, int n,  Map<Integer, Integer> map,  Map<Integer, Integer> map2) {
        if (index == n ) {
            if (contaisNum >= 2)
                map2.put(curSum, map2.getOrDefault(curSum, 0) + 1);

            if (contaisNum < 2) return false;
            return map.containsKey(curSum);
        }
        if (traceBack(index + 1, nums, contaisNum + 1, curSum + nums[index], n, map, map2)) return true;
        if (traceBack(index + 1, nums, contaisNum, curSum, nums.length, map, map2)) return true;
        return false;
    }
}
