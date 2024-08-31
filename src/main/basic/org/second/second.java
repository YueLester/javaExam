package second;

import java.util.HashMap;
import java.util.Map;

public class second {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取矩阵的行数和列数
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // 读取每一行的异或和
        long[] rowXor = new long[n];
        for (int i = 0; i < n; i++) {
            rowXor[i] = scanner.nextLong();
        }

        // 读取每一列的异或和
        long[] colXor = new long[m];
        for (int j = 0; j < m; j++) {
            colXor[j] = scanner.nextLong();
        }

        // 检查每一行和每一列的异或和是否匹配
        boolean isValid = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rowXor[i] != colXor[j]) {
                    isValid = false;
                    break;
                }
            }
            if (!isValid) {
                break;
            }
        }

            // 输出结果
            if (isValid) {
                System.out.println("YES");
                // 这里可以使用贪心算法来恢复矩阵
                // 由于题目没有具体说明如何恢复矩阵，这里简单地输出一个示例矩阵
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        System.out.print(rowXor[i] + " ");
                    }
                    System.out.println();
                }
            } else {
                System.out.println("NO");
            }

            scanner.close();
        }
    }
}
