package pr.lofe.mdr.xgartic.game.obj;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Build extends GameObject {

    private final Player player;
    private boolean locked = false;
    private Room build;

    public Build(Player player) {
        this.player = player;
    }

    public boolean complete(@NotNull Room room) {
        if(!locked) {
            build = room;
            return locked = true;
        }
        return false;
    }

    public @NotNull Room content() {
        return build;
    }

    @Override public @NotNull Player getMember() {
        return player;
    }
    @Override public boolean isCompleted() { return locked; }

}
