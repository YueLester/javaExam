import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int T = in.nextInt();

        for (int count= 0; count < T; count++) {
            int N = in.nextInt();
            int[] price = new int[N];
            for (int i = 0; i < N; i++ ) {
                price[i] = in.nextInt();
            }
            int res = doBiz(price, N);
            System.out.println(res);
        }
    }
    // 最多有多少张优惠券？
    // 99积分开始， 
    // 第一天 50， 1张优惠券+ 49 积分
    // 第二天， 1 + 99积分
    // 第三天， 2 + 49积分
    // 第四天， 2 + 99 积分
    // 第五天， 3 -1 + 99 积分
    // 同时最多拥有两张优惠券
    // 定义优惠券状态：
    // 30： 四位二进制数
    // 无优惠： 0000
    // 1张满配， 0011
    // 过了一天，0010
    // 过了两天，0001
    private static int doBiz(int[] price, int n) {
        int fistVoucher = 12, secondVoucher = 3;
        // 取&判断 是否存在， 
        // 取 | 后取 ^ 表示消费
        // dp: 状态的最低消费
        // i:
        // j
        // k
        int[][][] dp = new int[n+1][20][101];
        for (int day = 0; day <= n; day++) {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 101; j++) {
                    dp[day][i][j] = Integer.MAX_VALUE;
                }
            }
        }
        dp[0][0][0] = 0;
        for (int i = 0; i < n; i++) {
            for (int voucher = 0; voucher < 20; voucher++) {
                for (int score = 0; score < 101; score++) {
                    // 不存在的状态
                    if (dp[i][voucher][score] == Integer.MAX_VALUE) continue;
                    int cur = dp[i][voucher][score];

                    // System.out.println("i = " + i +", voucher = " + voucher + ", scores = " + score + ", cost = " + cur);
                    // 1. 直接消费
                    // if (nextScore )
                    // 先把卡过期一下
                    int voucheStatus = 0;
                    int nextVoucher = 0;
                    if ((voucher & fistVoucher) > 0) {
                        voucheStatus |= 2;
                        nextVoucher = ((voucher & fistVoucher) - 1) & fistVoucher;
                    }
                    if ((voucher & secondVoucher) > 0) {
                        voucheStatus |= 1;
                        nextVoucher = ((voucher & secondVoucher) - 1) & secondVoucher;
                    }

                    int nextScore = score + price[i];
                    if (nextScore >= 100) {
                        if ((nextVoucher & fistVoucher) == 0 ) {

                            // 赋值给第一个券
                            dp[i+1][nextVoucher+fistVoucher][nextScore-100] =  Math.min(cur + price[i], dp[i+1][nextVoucher+fistVoucher][nextScore-100]);
                        } else {
                            // 赋值给第二个券
                            dp[i+1][nextVoucher+secondVoucher][nextScore-100] = Math.min(cur + price[i],  dp[i+1][nextVoucher+secondVoucher][nextScore-100]) ;
                        }
                    } else {
                        dp[i+1][nextVoucher][nextScore] = Math.min(cur + price[i], dp[i+1][nextVoucher][nextScore]);
                    }

                    // 2. 使用优惠券， 优先使用剩余时间短的
                    if (voucheStatus == 0) continue;
                    // 可以消费第一张
                    if ((voucher & fistVoucher) > 0) {
                        int temp = (nextVoucher | fistVoucher) ^ fistVoucher;
                        dp[i+1][temp][score] = Math.min(cur,  dp[i+1][temp][score]);
                    }
                    // 可以消费第二章
                    if ((voucher & secondVoucher) > 0) {
                        int temp = (nextVoucher | secondVoucher) ^ secondVoucher;
                        dp[i+1][temp][score] =  Math.min( cur, dp[i+1][temp][score]);
                    }
                }
            }
        }


        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 101; j++) {
                res = Math.min(res, dp[n][i][j]);
            }
        }
        return res;
    }
}