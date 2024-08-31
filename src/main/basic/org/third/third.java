import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = in.nextInt(), q = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        int[][] query = new int[q][2];
        for (int i = 0; i < q; i ++) {
            query[i][0] = in.nextInt();
            query[i][1] = in.nextInt();
        }
        // 使用两个辅助数组
        // 每个数左边最大的和右边更大的值
        // 单调栈或者直接n^2 构造辅助数组。
        // -1 表示不存在。
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        // 需要额外考虑平局情况
        int maxValue = buildHelp(left, right, nums);
        List<Integer> helpIndex = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] == maxValue) {
                helpIndex.add(i);
            }
        }
        int nnnnn = helpIndex.size();
        right[helpIndex.get(nnnnn-1)] = -1;
        left[helpIndex.get(nnnnn-1)] = -1;
        for (int i = 0; i < nnnnn-1; i ++) {
            right[helpIndex.get(i)] = helpIndex.get(i+1);
        }
        for (int i = nnnnn-1; i >0; i--) {
            left[helpIndex.get(i)] = helpIndex.get(i-1);
        }
        for (int[] qqq:query) {
            int l = qqq[0]-1, r = qqq[1]-1;
            int maxIndex = l;
            for (int i = l+1; i <= r; i++) {
                if (nums[i] > nums[maxIndex]) {
                    maxIndex = i;
                }
            }
            // System.out.println("maxIndex = " + maxIndex +", value = " + nums[maxIndex]);

            // 需要特判 平局情况
            if (nums[maxIndex] == maxValue && nnnnn != 1) {
                int L = left[maxIndex], R = right[maxIndex];
                System.out.println("draw");
                List<Integer> res = new ArrayList<>();
                if (L != -1) {
                    res.add(R-l+1);
                }
                if (r != -1) {
                    res.add(r-L+1);
                }
                int realOut = res.get(0);
                for (int i = 1; i < res.size(); i++) {
                    realOut = Math.min(realOut, res.get(i));
                }
                System.out.println(realOut);
                continue;
            }
            // 上述过程可以用树状数组优化
            int L = left[maxIndex], R = right[maxIndex];
            if (L==-1&&R==-1) {
                System.out.println("lose");
                if (r!=l) {
                    System.out.println(r-l+1);
                } else {
                    System.out.println(r-l+2);
                }
                continue;
            }
            List<Integer> res = new ArrayList<>();
            if (L != -1) {
                res.add(R-l+1);
            }
            if (r != -1) {
                res.add(r-L+1);
            }
            int realOut = res.get(0);
            for (int i = 1; i < res.size(); i++) {
                realOut = Math.min(realOut, res.get(i));
            }
            System.out.println("win");
            System.out.println(realOut);
        }
    }
    private static int buildHelp(int[] left, int[] right, int[] nums) {
        int maxValue = nums[0];
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (nums[j] > nums[i]) {
                    right[i] = j;
                    break;
                }
            }
            maxValue = Math.max(maxValue, nums[i]);
            // System.out.println("right" + i +", " + right[i]);
        }
        for (int i = n-1; i >= 0; i--) {
            for (int j = i-1; j >= 0; j--) {
                if (nums[j] > nums[i]) {
                    left[i] = j;
                    break;
                }
            }
            // System.out.println("left" + i +", " + left[i]);
        }
        return maxValue;
    }
}