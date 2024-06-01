package pr.lofe.mdr.xgartic.manage;

import org.bukkit.Bukkit;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pr.lofe.mdr.xgartic.api.LobbyDisbandEvent;
import pr.lofe.mdr.xgartic.display.ISound;
import pr.lofe.mdr.xgartic.display.ITitle;
import pr.lofe.mdr.xgartic.game.Game;
import pr.lofe.mdr.xgartic.lobby.Lobby;
import pr.lofe.mdr.xgartic.xGartic;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class GameManager {

    private final HashMap<Lobby, Integer> tasks;
    private final List<Game> games;

    public GameManager() {
        tasks = new HashMap<>();
        games = new ArrayList<>();
    }

    public Game getPlayerGame(@NotNull Player player) {
        for(Game game : games) {
            if(game.getPlayers().contains(player)) return game;
        }
        return null;
    }

    public Collection<Game> getGames() {
        return games;
    }

    public void startGame(Lobby lobby) {
        ITitle title = new ITitle("зᴀпʏск...", "%black%", Duration.ofMillis(500), Duration.ofSeconds(Integer.MAX_VALUE), Duration.ZERO);
        ISound sound = new ISound("custom.misc.teleport", .8f, 1);
        for(Player player: lobby.getPlayers()) {
            player.closeInventory();
            title.show(player);
            sound.show(player);
        }


        Game game = new Game();
        games.add(game);
        tasks.put(lobby, Bukkit.getScheduler().scheduleSyncRepeatingTask(xGartic.I, () -> {
            if(game.isWorldLoaded()) {
                game.load(lobby.getPlayers());
                xGartic.getLobbies().disband(lobby, LobbyDisbandEvent.Reason.LOBBY_STARTED_GAME);
                game.start();
                Bukkit.getScheduler().cancelTask(tasks.get(lobby));
            }
        }, 10, 5L));
    }


}
