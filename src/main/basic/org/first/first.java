import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        // java 自带字典序排序
        // 先排序， 有PDD 的标记一下
        int N = in.nextInt(), M = in.nextInt();
        String[] words = new String[N];
        int[] mark = new int[N];
        for (int i = 0; i < N; i ++) {
            words[i] = in.next();
        }
        Arrays.sort(words);
        for (int i = 0; i < N; i++) {
            mark[i] = help(words[i]);
            // System.out.println("words = " + words[i] + ", mark = " + mark[i]);
        }
        int index = 0;
        int with = 1;
        for (int i = 0; i < M; i++) {
            //  开始招值为1的
            while ((int)(index/N) != 1 - mark[index%N]) {
                index++;
            }
            // System.out.println("index = " + index);
            System.out.println(words[index%N]);
            index++;
        }
    }
    private static int help(String ss) {
        for (int i = 0; i < ss.length() - 2; i++) {
            if (ss.charAt(i) == 'P' && ss.charAt(i+1) == 'D' && ss.charAt(i+2) == 'D') {
                return 1;
            }
        }
        return 0;
    }
}