package second;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class second {
    final static int max = 1<<30;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(), M = in.nextInt(), K = in.nextInt(), L = in.nextInt();
        // 一个用户的好友关系需要 x 个int来保存
        // 一个数字存30个
        int x = (N/30) + 1;
        int[][] relation = new int[N][x];
        for (int i = 0; i < M; i ++) {
            int first = in.nextInt() - 1;
            int second = in.nextInt() - 1;
            // 用位图存相互之间的关系
            add(relation, first, second);
            add(relation, second, first);
        }
        // 最终目标是获得一个数组， 其中是每个用户的分数
        // 0: 序号， 注意序号是减了1的
        // 1: 分数
        int[][] score = new int[N][2];
        for (int i = 0; i < N; i++) {
            score[i][0] = i;
            // 是自己或者已经有好友关系， 负分
            if (queryRelation(relation, i, K-1) || i==K-1) {
                score[i][1] = -1;
            } else {
                score[i][1] = getCommonFriends(relation, i, K-1);
            }
//            System.out.println("i = " + i + ", score = " + score[i][1]);
        }
        // 根据 分数倒序排序
        Arrays.sort(score, (a,b) -> b[1]-a[1]);
//        for (int i = 0; i < N; i ++) {
//            System.out.println("i = " + score[i][0] + ", score = " + score[i][1]);
//        }
        for (int i = 0; i < L; i++) {
            if (score[i][1] == -1) {
                System.out.print(0);
            } else {
                int res = score[i][0] + 1;
                System.out.print(res);
            }
            // 华为会检测空格
            if (i != L-1) {
                System.out.print(" ");
            }
        }

    }
    private static void add(int[][] relation, int a, int b) {
        // 在 relation[a] 的 数组里操作， 加上好友b
        int index = 0;
        // 每个好友关系用 1<<0, 1<<1, ... 1<< 29表示
        // 一个int 存储30个人的关系
        while (b >= 30) {
            index++;
            b-=30;
        }
        relation[a][index] |= 1<<b;
    }
    // 获取共同好友数量
    private static int getCommonFriends(int[][] relation, int a, int b) {
        int res = 0;
        int[] first = relation[a], second = relation[b];
        for (int i = 0; i < first.length; i++) {
            int cur = first[i] & second[i];
            // 快速计算一个数的1的个数， 求和表示共同好友数
            while (cur > 0) {
                cur &= cur - 1;
                res++;
            }
        }
        return res;
    }
    // 询问两人之间关系
    private static boolean queryRelation(int[][] relation, int a, int b) {
        // 在 relation[a] 的 数组里操作， 查询 好友 b
        int index = 0;
        // 每个好友关系用 1<<0, 1<<1, ... 1<< 29表示
        // 一个int 存储30个人的关系
        while (b >= 30) {
            index++;
            b-=30;
        }
        int res = relation[a][index] & (1<<b);
        return res > 0;
    }
}
