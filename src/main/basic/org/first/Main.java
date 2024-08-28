package first;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    final static int max = 9999;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<Integer> nums = new ArrayList<>();
        while (in.hasNext()) {
            int cur = in.nextInt();
            nums.add(cur);
        }
        List<Integer> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int n:nums) {
            // 不存在， 直接进洞
            if (!set.contains(n)) {
                if (stack.size() < max) {
                    stack.push(n);
                    set.add(n);
                }
                continue;
            }
            // 存在， 出洞，直到当前不存在
            while (set.contains(n)) {
                // 里面的一个一个挪动，直到当前出来。
                set.remove(stack.peek());
                res.add(stack.poll());
            }
            // 还要再进去
            stack.push(n);
            set.add(n);
        }
        while (!stack.isEmpty()) {
            res.add(stack.poll());
        }
        for (int i = 0; i < res.size() - 1; i++) {
            System.out.print(res.get(i)+" ");
        }
        System.out.print(res.get(res.size()-1));
    }
}