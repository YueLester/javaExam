package first;

import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class first {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.print("Hello and welcome!， result = " + doBiz());

    }
    public static int doBiz() {
        try {
            System.out.println("finally" + 1/0);
            return 1;
        } catch (Exception e) {

            return 3;
        } finally {
            return 2;
        }
    }
}