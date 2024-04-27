package pr.lofe.mdr.xgartic.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.ArrayList;
import java.util.List;

public class TextWrapper {

    private final static List<String> fonts = new ArrayList<>(){{
        add("а,ᴀ");
        add("е,ᴇ");
        add("р,ᴘ");
        add("у,ʏ");
        add("ф,ȹ");
        add("a,ᴀ");
        add("b,в");
        add("d,d");
        add("e,ᴇ");
        add("f,ғ");
        add("g,ɢ");
        add("h,ʜ");
        add("i,ɪ");
        add("j,ᴊ");
        add("k,ᴋ");
        add("l,ʟ");
        add("m,ᴍ");
        add("n,ɴ");
        add("p,ᴘ");
        add("q,ǫ");
        add("t,ᴛ");
        add("u,ᴜ");
        add("w,ᴡ");
        add("y,ʏ");
        add("r,ʀ");
    }};

    public static Component wrap(String text) {
        return MiniMessage.miniMessage().deserialize(text).decoration(TextDecoration.ITALIC, false);
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
