package pr.lofe.mdr.xgartic.game.obj;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class GameObject {

    public abstract @NotNull Player getMember();
    public abstract boolean isCompleted();

}