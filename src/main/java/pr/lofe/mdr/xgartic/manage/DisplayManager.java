package pr.lofe.mdr.xgartic.manage;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.entity.Player;
import pr.lofe.mdr.xgartic.xGartic;

public class DisplayManager {

    public final BukkitAudiences adventure = BukkitAudiences.create(xGartic.I);

    public static void show(Player player, Type type, Object... data) {

    }

    public enum Type {
        BLACK_SCREEN,
        TIMER
    }
}
