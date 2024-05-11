package pr.lofe.mdr.xgartic.api;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import pr.lofe.mdr.xgartic.lobby.Lobby;

public class LobbyPrivacyEvent extends LobbyEvent {

    private final static HandlerList handlers = new HandlerList();

    private final boolean
            oldPrivacy,
            newPrivacy;

    public LobbyPrivacyEvent(Lobby lobby, boolean oldPrivacy, boolean newPrivacy) {
        super(lobby);
        this.oldPrivacy = oldPrivacy;
        this.newPrivacy = newPrivacy;
    }

    public boolean getNew() {
        return newPrivacy;
    }

    public boolean getOld() {
        return oldPrivacy;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}