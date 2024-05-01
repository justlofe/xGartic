package pr.lofe.mdr.xgartic.manage;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import pr.lofe.mdr.xgartic.api.LobbyDisbandEvent;
import pr.lofe.mdr.xgartic.lobby.Lobby;
import pr.lofe.mdr.xgartic.xGartic;

import java.util.ArrayList;
import java.util.List;

public class LobbyManager {

    private final List<Lobby> lobbies;
    public LobbyManager() {
        lobbies = new ArrayList<>();
    }

    public @Nullable Lobby getPlayerLobby(Player player) {
        for(Lobby lobby : lobbies) {
            if(lobby.getPlayers().contains(player)) return lobby;
        }
        return null;
    }

    public void remove(Player player) {
        Lobby lobby = getPlayerLobby(player);
        if(lobby != null) {
            List<Player> players = lobby.getPlayers();
            if (players.size() == 1) xGartic.getLobbies().disband(lobby, LobbyDisbandEvent.Reason.LAST_PLAYER_LEAVE);
            else {
                players.remove(player);
                Player newLead = players.get(0);
                lobby.removePlayer(player);
                lobby.setLeader(newLead);
            }
        }
        player.closeInventory();
    }

    public @Nullable Lobby create(Player player, boolean opened) {
        if(getPlayerLobby(player) == null) {
            return new Lobby(player){{
                lobbies.add(this);
                setOpened(opened);
            }};
        }
        return null;
    }

    public Lobby getLobbyByCode(String code) {
        for(Lobby lobby : lobbies) {
            if(lobby.getCode().equals(code))
                return lobby;
        }
        return null;
    }

    public List<Lobby> getLobbies() {
        return lobbies;
    }

    public void disband(Lobby lobby, LobbyDisbandEvent.Reason reason) {
        if(lobbies.contains(lobby)) {
            lobby.disband(reason);
            lobbies.remove(lobby);
        }
    }

}
