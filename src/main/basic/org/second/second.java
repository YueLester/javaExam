import java.util.*;


// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt(), k = in.nextInt();
        long[] sit = new long[n];
        for (int i = 0; i < n; i ++) {
            sit[i] = in.nextLong();
        }
        Arrays.sort(sit);
        if (k == 0) {
            System.out.println(0);
            return;
        }

        int l = 1, r = k;
        while (l < r) {
            int mid = (l+r) /2;
            if (check(sit, mid, k)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        System.out.println(r);
    }
    private static boolean check(long[] sit, int mid, int k) {

        long res = 0;

        long n = sit.length;
        for (int i = 0; i < n-1; i++) {
            res += (sit[i+1]-sit[i]) <= mid ? sit[i+1]-sit[i] : mid;
        }
        res += mid;
        return res >= k;
    }
}