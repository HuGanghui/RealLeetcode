package THUDataStructure.Stack.Application;

import THUDataStructure.Stack.Stack_Array;

public class Application {
    // 数组倒置
    public static Integer[] reverse(Integer[] a) {
        Stack_Array S = new Stack_Array(a.length);
        Integer[] b = new Integer[a.length];
        for (int i = 0; i<a.length; i++) {
            S.push(a[i]);
        }
        for (int i = 0; i<a.length; i++) {
            b[i] = (Integer) (S.pop());
        }
        return b;
    }

}
