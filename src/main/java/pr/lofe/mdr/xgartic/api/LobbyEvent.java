package pr.lofe.mdr.xgartic.api;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import pr.lofe.mdr.xgartic.lobby.Lobby;

public class LobbyEvent extends Event {

    private final static HandlerList handlers = new HandlerList();

    protected Lobby lobby;

    public LobbyEvent(Lobby lobby) {
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