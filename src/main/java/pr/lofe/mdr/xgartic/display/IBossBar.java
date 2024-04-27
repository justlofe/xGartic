package pr.lofe.mdr.xgartic.display;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.entity.Player;
import pr.lofe.mdr.xgartic.util.TextWrapper;
import pr.lofe.mdr.xgartic.xGartic;

public class IBossBar extends DisplayObject {

    private final BossBar bossBar;

    public IBossBar(String title) {
        this.bossBar = BossBar.bossBar(TextWrapper.wrap(title), 5, BossBar.Color.YELLOW, BossBar.Overlay.PROGRESS);
    }

    public BossBar getBossBar() {
        return bossBar;
    }

    public void setProgress(float progress) {
        bossBar.progress(progress);
    }

    @Override
    public void show(Player... players) {
        BukkitAudiences adventure = xGartic.getDisplay().adventure;
        for(Player player: players) adventure.player(player).showBossBar(bossBar);
    }

    public void close(Player... players) {
        BukkitAudiences adventure = xGartic.getDisplay().adventure;
        for(Player player: players) adventure.player(player).hideBossBar(bossBar);
    }

}
