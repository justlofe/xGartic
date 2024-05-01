package pr.lofe.mdr.xgartic.game;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pr.lofe.mdr.xgartic.config.ConfigAccessor;
import pr.lofe.mdr.xgartic.display.IChatMessage;
import pr.lofe.mdr.xgartic.game.obj.*;
import pr.lofe.mdr.xgartic.util.QueueUtil;
import pr.lofe.mdr.xgartic.xGartic;

import java.util.*;

public class Game extends ConfigAccessor {

    private final String id;

    private final List<Room> availableRooms = new ArrayList<>();
    private final HashMap<Player, Room> where = new HashMap<>();

    private final GameProvider gameProvider;
    private final GameController controller;
    private final GameClock clock;

    private final HashSet<Player> players;
    private GameObject[][] chains;

    /**
     * Magic value, don't check and modify
     */
    private int completed = -1;
    private int maxCompleted;

    private int stage;
    private int maxStage;

    private boolean worldLoaded = false;

    public Game() {
        id = "game-" + UUID.randomUUID();

        gameProvider = new GameProvider();
        controller = new GameController(this);
        Bukkit.getPluginManager().registerEvents(controller, xGartic.I);

        gameProvider.load(xGartic.getMaps().getMap(), id);

        players = new HashSet<>();
        clock = new GameClock(this);
    }

    public String getId() {
        return id;
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
        maxCompleted = players.size();
        for(String raw : cfg().getStringList("map.rooms")) {
            Room room = Room.fromString(raw);
            availableRooms.add(room);
            xGartic.I.getLogger().warning("loaded room: " + room);
        }
    }

    public Collection<Player> getPlayers() {
        return players;
    }

    public HashMap<Player, Room> getAssigns() {
        return where;
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
        if(stage + 1 < maxStage) {
            completed = 0;
            stage++;
            for(int x = 0; x < maxStage; x++) {
                GameObject object = chains[x][stage];
                GameObject prev = null;
                if(stage - 1 >= 0) prev = chains[x][stage - 1];
                if(object instanceof Build) {
                    if(!availableRooms.isEmpty())
                        letBuild(object.getMember(), availableRooms.remove(availableRooms.size() - 1), (Text) prev);
                }
                else if (object instanceof Text) letWrite(object.getMember(), (Build) prev);
            }
            if(stage % 2 == 0) clock.start(2400);
            else clock.start(6000);
            return true;
        }
        return false;
    }

    public int getStage() {
        return stage;
    }

    public GameObject getByPlayer(Player player) {
        for(int x = 0; x < maxStage; x++) {
            GameObject object = chains[x][stage];
            if(object.getMember().equals(player)) return object;
        }
        return null;
    }

    private void letBuild(@NotNull Player player, @NotNull Room where, @Nullable Text parent) {
        Location loc = Point.fromPoint(where.spawn(), gameProvider.getWorld());
        this.where.put(player, where);

        String theme = parent == null ? "" : parent.content();

        new IChatMessage(cfg().getString("display.messages.game.start_build", "").replaceAll("%theme%", theme), false)
                .show(player);

        player.teleport(loc);
        player.setGameMode(GameMode.CREATIVE);
    }
    private void completeBuild(Player player) {
        GameObject object = getByPlayer(player);
        if(object instanceof Build build)  {
            Room room = this.where.remove(player);
            if(room != null) build.complete(room);

            player.setGameMode(GameMode.ADVENTURE);
            player.setFlying(true);
            completeObject(false);
        }
    }

    private void letWrite(Player player, @Nullable Build parent) {
        if(parent != null) {
            Location loc = Point.fromPoint(parent.content().spawn(), gameProvider.getWorld());
            player.teleport(loc);
            player.setFlying(true);

            new IChatMessage(cfg().getString("display.messages.game.describe_build", ""), false)
                    .show(player);
        }
        else {
            new IChatMessage(cfg().getString("display.messages.game.start_texting", ""), false)
                    .show(player);
        }
    }
    public void completeWrite(Player player, String string) {
        GameObject object = getByPlayer(player);
        if(object instanceof Text text) {
            text.complete(string);

            new IChatMessage(cfg().getString("display.messages.game.text_completed", "").replaceAll("%theme%", string), false)
                    .show(player);

            completeObject(false);
        }
    }

    public void completeObject(boolean full) {
        if(full) {
            for(Player player: players) {
                GameObject object = getByPlayer(player);
                if(!object.isCompleted()) {
                    if(object instanceof Build) completeBuild(player);
                    else if(object instanceof Text) completeWrite(player, "");
                }
            }
            completed = maxCompleted;
        }
        else completed++;

        if(completed >= maxCompleted) {
            if (!nextStage()) initFinal();
        }
    }

    private void initFinal() {
        end();
    }

    public void end() {
        HandlerList.unregisterAll(controller);
        gameProvider.unload();
    }

    public GameProvider getProvider() {
        return gameProvider;
    }
}
