package pr.lofe.mdr.xgartic.game.obj;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Text extends GameObject {

    private String content;
    private boolean locked = false;
    private final Player player;

    public Text(Player player) {
        this.player = player;
    }

    public boolean complete(@NotNull String string) {
        if(!locked) {
            content = string;
            return locked = true;
        }
        return false;
    }

    public @NotNull String content() {
        return content;
    }

    @Override public @NotNull Player getMember() { return player; }
    @Override public boolean isCompleted() { return locked; }
}
