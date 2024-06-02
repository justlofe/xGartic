package pr.lofe.mdr.xgartic.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.ArrayList;
import java.util.List;

public class TextWrapper {

    private final static List<String> fonts = new ArrayList<>(){{
        add("a,ꑿ");
        add("b,ꒀ");
        add("c,ꒁ");
        add("d,ꒂ");
        add("e,ꒃ");
        add("f,ꒄ");
        add("g,ꒅ");
        add("h,ꒆ");
        add("i,ꒇ");
        add("j,ꒈ");
        add("k,ꒉ");
        add("l,ꒊ");
        add("m,ꒋ");
        add("n,ꒌ");
        add("o,\uA48D");
        add("p,\uA48E");
        add("q,\uA48F");
        add("r,꒐");
        add("s,꒑");
        add("t,꒒");
        add("u,꒓");
        add("v,꒔");
        add("w,꒕");
        add("x,꒖");
        add("y,꒗");
        add("z,꒘");
        add("1,꒙");
        add("2,꒚");
        add("3,꒛");
        add("4,꒜");
        add("5,꒝");
        add("6,꒞");
        add("7,꒟");
        add("8,꒠");
        add("9,꒡");
        add("0,꒢");
        add("_,꒣");
        add(".,꒤");
        add("+,꒦");
    }};

    public static Component wrap(String text) {
        return MiniMessage.miniMessage().deserialize(text).decoration(TextDecoration.ITALIC, false);
    }

    public static void main(String[] args) {
        System.out.println(mini("вы завершилки постройку, ожидайте других участников"));
    }

    public static String mini(String text) {
        String result = text;
        for(String raw : fonts) {
            String[] unboxed = raw.split(",");
            result = result.replace(unboxed[0].charAt(0), unboxed[1].charAt(0));
        }
        return result;
    }

    public static List<Component> wrapArray(String... text) {
        List<Component> list = new ArrayList<>();
        for(String i : text) {
            list.add(wrap(i).decoration(TextDecoration.ITALIC, false));
        }
        return list;
    }

}
