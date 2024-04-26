package pr.lofe.mdr.xgartic.api;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import pr.lofe.mdr.xgartic.lobby.Lobby;

public class LobbyCreateEvent extends PlayerEvent {

    private final static HandlerList handlers = new HandlerList();
    protected Lobby lobby;

    public LobbyCreateEvent(@NotNull Lobby lobby, @NotNull Player who) {
        super(who);
        this.lobby = lobby;
    }

    public Lobby getLobby() {
        return lobby;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
