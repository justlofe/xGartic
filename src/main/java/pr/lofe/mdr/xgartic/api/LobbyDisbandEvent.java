package pr.lofe.mdr.xgartic.api;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import pr.lofe.mdr.xgartic.lobby.Lobby;

public class LobbyDisbandEvent extends LobbyEvent {

    private final static HandlerList handlers = new HandlerList();
    private final Reason reason;

    public LobbyDisbandEvent(Lobby lobby, @NotNull Reason reason) {
        super(lobby);
        this.reason = reason;
    }

    public @NotNull Reason getReason() {
        return reason;
    }
    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public enum Reason {
        LOBBY_STARTED_GAME,
        LAST_PLAYER_LEAVE,
        UNEXPECTED
    }

}
