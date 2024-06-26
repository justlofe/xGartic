package pr.lofe.mdr.xgartic.api;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import pr.lofe.mdr.xgartic.lobby.Lobby;

public class LobbyCreateEvent extends LobbyEvent {

    private final static HandlerList handlers = new HandlerList();

    private final Player who;

    public LobbyCreateEvent(@NotNull Lobby lobby, @NotNull Player who) {
        super(lobby);
        this.who = who;
    }

    public Player getWho() {
        return who;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
