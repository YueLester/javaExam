package third;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class third {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int N = in.nextInt();
            int[] arr = new int[N];
            for (int j = 0; j < N; j++) {
                arr[j] = in.nextInt();
            }
            doBiz(arr);
        }
    }
    private static void doBiz(int[] arr) {
        int res = 1;

        // 1 2 5 4:
        // 如果需要调整有两种调整法： 4 > 2， 可以把5变成3
        // 或者把 4变成 6
        // 倾向于前者
        int beginIndex = 0;
        // 举例： 上述的beginIndex只包括 0， 3
        while (beginIndex < arr.length) {
            // 默认从 -1 开始, 辅助变化
            int beginValue = -1;
        }
    }
}
