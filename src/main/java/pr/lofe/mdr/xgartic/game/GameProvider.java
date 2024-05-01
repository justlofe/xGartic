package pr.lofe.mdr.xgartic.game;

import org.bukkit.Bukkit;
import org.bukkit.World;
import pr.lofe.mdr.xgartic.util.WorldUtil;

public class GameProvider {

    private World world;

    public GameProvider() {}

    public void load(World base, String id) {
        World world = WorldUtil.copyWorld(base, id);
        this.world = world == null ? Bukkit.getWorld(id) : world;
    }

    public void unload() {
        WorldUtil.removeWorld(world);
    }
    public World getWorld() {
        return world;
    }

}
