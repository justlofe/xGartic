package pr.lofe.mdr.xgartic.util;

public class CodeUtil {

    private final static String lower_case = "ABCDEFGHIJKLMNOPSRTQUVWXYZ";
    private final static String numbers = "0123456789";

    public static String gen(String format) {
        char[] result = format.toCharArray();
        for(int i = 0; i < format.length(); i++) {
            switch (format.charAt(i)) {
                case '0' -> result[i] = numbers.charAt(RandomUtil.nextInt(0, 9));
                case 'A' -> result[i] = lower_case.charAt(RandomUtil.nextInt(0, 25));
                case '#' -> result[i] = '#';
                default -> {}
            }
        }
        return new String(result);
    }


}
