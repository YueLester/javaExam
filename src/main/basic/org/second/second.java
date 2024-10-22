package second;

import java.util.*;

public class second {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        int[] help = new int[n];
        Arrays.fill(help, -1);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            if (map.containsKey(arr[i])) {
                help[map.get(arr[i])] = i;
                map.remove(arr[i]);
            } else {
                map.put(arr[i], i);
            }
        }


        // 开始决定每一位的留不留
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (help[i] != -1) {
                // 坐标有1的偏移
                res.add(i + 1);
                res.add(help[i] + 1);
            }
        }
        for (int i = 0; i < res.size(); i++ ){
            System.out.print(res.get(i) + " ");
        }
    }
}
