package pr.lofe.mdr.xgartic.game;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import pr.lofe.mdr.xgartic.util.WorldUtil;

public class GameProvider {

    private final World world;

    public GameProvider(World base, String id) {
        this.world = WorldUtil.copyWorld(base, id);
    }

    public void unload() {
        Bukkit.unloadWorld(world, false);
    }
    public World getWorld() {
        return world;
    }

}
