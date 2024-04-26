package pr.lofe.mdr.xgartic.api;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import pr.lofe.mdr.xgartic.game.Game;

public class GameStartEvent extends GameEvent {

    private final static HandlerList handlers = new HandlerList();

    public GameStartEvent(@NotNull Game game) {
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