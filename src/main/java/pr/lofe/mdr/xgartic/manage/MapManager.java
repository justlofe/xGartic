package pr.lofe.mdr.xgartic.manage;

import org.bukkit.Bukkit;
import org.bukkit.World;
import pr.lofe.mdr.xgartic.config.ConfigAccessor;

public class MapManager extends ConfigAccessor {

    private final World map;

    public MapManager() throws IllegalArgumentException {
        String mapName = cfg().getString("map.world", "");
        World world = Bukkit.getWorld(mapName);
        if(world != null) map = world;
        else {
            throw new IllegalArgumentException("World " + mapName + " does not exist! Please provide a real world name and restart a plugin");
        }
    }

    public World getMap() {
        return map;
    }

}
