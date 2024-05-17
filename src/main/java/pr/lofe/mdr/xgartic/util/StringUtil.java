package pr.lofe.mdr.xgartic.util;

public class StringUtil {

    private final static String SYMBOLS = "0123456789.-";

    public static boolean isNumeric(String string) {
        for(char s : string.toCharArray()) {
            if(SYMBOLS.indexOf(s) == -1) return false;
        }
        return true;
    }

}
