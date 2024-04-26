package pr.lofe.mdr.xgartic.display;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import pr.lofe.mdr.xgartic.util.TextWrapper;

public class ChatMessage extends DisplayObject {

    private final String input;
    private final boolean mini;

    public ChatMessage(String string, boolean isMini) {
        input = string;
        mini = isMini;
    }

    @Override
    public void show(Player... players) {
        Component text = TextWrapper.wrap(mini ? TextWrapper.mini(input) : input);
        for (Player player : players) player.sendMessage(text);
        text = null;
    }

}
