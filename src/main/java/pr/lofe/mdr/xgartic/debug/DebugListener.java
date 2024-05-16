package pr.lofe.mdr.xgartic.debug;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import pr.lofe.mdr.xgartic.api.LobbyCreateEvent;
import pr.lofe.mdr.xgartic.api.LobbyDisbandEvent;
import pr.lofe.mdr.xgartic.api.LobbyPrivacyEvent;

@SuppressWarnings("deprecation")
public class DebugListener implements Listener {

    @EventHandler public void onLobbyCreate(LobbyCreateEvent event) {
        Bukkit.broadcastMessage(event.getLobby().getLeader().getName() + " created a lobby");
    }

    @EventHandler public void onLobbyDisband(LobbyDisbandEvent event) {
        Bukkit.broadcastMessage("Lobby disbanded with a reason " + event.getReason().name());
    }

    @EventHandler public void onLobbyPrivacy(LobbyPrivacyEvent event) {
        Bukkit.broadcastMessage("Lobby change privacy type from " + event.getOld() + " to " + event.getNew());
    }

    @EventHandler public void onInventoryClick(InventoryClickEvent event) {
        Bukkit.broadcastMessage("clicked a " + event.getSlot() + " slot");
    }

}
