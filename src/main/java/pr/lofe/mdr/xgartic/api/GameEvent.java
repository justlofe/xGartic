package pr.lofe.mdr.xgartic.api;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;
import org.checkerframework.checker.units.qual.N;
import org.jetbrains.annotations.NotNull;
import pr.lofe.mdr.xgartic.game.Game;

public class GameEvent extends Event {

    private final static HandlerList handlers = new HandlerList();
    private final Game game;

    public GameEvent(@NotNull Game game) {
        this.game = game;
    }

    public @NotNull Game getGame() {
        return game;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}