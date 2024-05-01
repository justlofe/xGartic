package pr.lofe.mdr.xgartic.util;

import java.util.Random;

@SuppressWarnings("unused")
public class RandomUtil {

    public static int nextInt(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public static boolean nextBool(double chance) {
        return Math.random() < chance / 100;
    }

}
