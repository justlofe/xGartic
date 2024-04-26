package pr.lofe.mdr.xgartic.util;

public class QueueUtil {

    public static Object[][] generate(Object[] input) {
        int l = input.length;
        Object[][] matrix = new Object[l][l];

        for (int x = 0; x < l; x++) {
            Object[] temp = null;
            if(x == 0) temp = input;
            else {
                for (int i = 0; i < x; i++) {
                    if(temp == null) temp = shift(input);
                    else temp = shift(temp);
                }
            }
            matrix[x] = temp;
        }

        return matrix;
    }

    private static Object[] shift(Object[] input) {
        Object[] temp = new Object[input.length];
        for(int i = 0; i < temp.length; i++) {
            if(i == 0) temp[temp.length - 1] = input[0];
            else temp[i-1] = input[i];
        }
        return temp;
    }

}

/*

123
---
123
231
312

 */