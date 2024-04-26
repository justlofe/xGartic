package pr.lofe.mdr.xgartic.api;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import pr.lofe.mdr.xgartic.game.Game;

public class GameLoadEvent extends GameEvent {

    private final static HandlerList handlers = new HandlerList();

    public GameLoadEvent(@NotNull Game game) {
        super(game);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}