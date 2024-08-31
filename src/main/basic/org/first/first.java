package first;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class first {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        List<String> x = new ArrayList<>();
        while (in.hasNext()) {
            x.add(in.next());
        }
        int res = 0;
        Set<String> set = new HashSet<>();
        for (String ss:x) {
            if (check(ss)) {
                if (set.contains(ss)) {

                } else {
                    res++;
                    set.add(ss);
                }
            }
        }
        System.out.println(res);
    }
    private static boolean check(String ss) {
        if (ss == null) return false;
        if (ss.charAt(0) >= 'A' && ss.charAt(0) <= 'Z') {
            return true;
        }
        return false;
    }
}