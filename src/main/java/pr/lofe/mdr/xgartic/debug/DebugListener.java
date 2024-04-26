package pr.lofe.mdr.xgartic.debug;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import pr.lofe.mdr.xgartic.api.LobbyCreateEvent;
import pr.lofe.mdr.xgartic.api.LobbyDisbandEvent;

@SuppressWarnings("deprecation")
public class DebugListener implements Listener {

    @EventHandler public void onLobbyCreate(LobbyCreateEvent event) {
        Bukkit.broadcastMessage(event.getLobby().getLeader().getName() + " created a lobby");
    }

    @EventHandler public void onLobbyDisband(LobbyDisbandEvent event) {
        Bukkit.broadcastMessage("Lobby disbanded with a reason " + event.getReason().name());
    }

}
