package pr.lofe.mdr.xgartic.game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import pr.lofe.mdr.xgartic.config.ConfigAccessor;
import pr.lofe.mdr.xgartic.game.obj.*;
import pr.lofe.mdr.xgartic.util.QueueUtil;
import pr.lofe.mdr.xgartic.xGartic;

import java.util.*;

public class Game extends ConfigAccessor {

    private final List<Room> availableRooms = new ArrayList<>();

    private final GameProvider gameProvider;
    private final GameController controller;
    private final HashSet<Player> players;
    private GameObject[][] chains;

    private int stage;
    private int maxStage;

    private boolean worldLoaded = false;

    public Game() {
        gameProvider = new GameProvider(xGartic.getMaps().getMap(), "game-" + UUID.randomUUID());
        controller = new GameController(this);
        players = new HashSet<>();
    }

    public void markWorldLoaded() {
        if(!worldLoaded) worldLoaded = true;
    }

    public boolean isWorldLoaded() {
        return worldLoaded;
    }

    public void load(Collection<? extends Player> players) {
        this.players.addAll(players);
        maxStage = players.size();

        for(String raw : cfg().getStringList("map.rooms")) {
            Room room = Room.fromString(raw);
            availableRooms.add(room);
        }
    }

    public Collection<Player> getPlayers() {
        return players;
    }

    public void start() {
        stage = -1;
        Object[][] players = QueueUtil.generate(this.players.toArray());
        int l = players.length;
        chains = new GameObject[l][l];
        for(int x = 0; x < l; x++) {
            for(int z = 0; z < l; z++) {
                if(z % 2 == 0) chains[x][z] = new Text((Player) players[x][z]);
                else chains[x][z] = new Build((Player) players[x][z]);
            }
        }
        nextStage();
    }

    public boolean nextStage() {
        if(stage + 1 <= maxStage) {
            stage++;
            for(int x = 0; x < maxStage; x++) {
                GameObject object = chains[x][stage];
                if(object instanceof Build) {
                    if(!availableRooms.isEmpty())
                        letBuild(object.getMember(), availableRooms.remove(availableRooms.size() - 1));
                }
                else if (object instanceof Text) letWrite(object.getMember());
            }
            return true;
        }
        return false;
    }

    public GameObject getByPlayer(Player player) {
        for(int x = 0; x < maxStage; x++) {
            GameObject object = chains[x][stage];
            if(object.getMember().equals(player)) return object;
        }
        return null;
    }

    private void letBuild(Player player, Room room) {
        Location loc = Point.fromPoint(room.spawn(), gameProvider.getWorld());
        player.teleport(loc);
    }
    private void completeBuild(Player player) {

    }

    private void letWrite(Player player) {

    }
    private void completeWrite(Player player, String string) {

    }

    public void end() {
        HandlerList.unregisterAll(controller);
    }

    public GameProvider getProvider() {
        return gameProvider;
    }
}
