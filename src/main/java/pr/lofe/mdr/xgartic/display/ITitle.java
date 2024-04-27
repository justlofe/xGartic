package pr.lofe.mdr.xgartic.display;

import org.bukkit.entity.Player;
import pr.lofe.mdr.xgartic.config.ConfigAccessor;
import pr.lofe.mdr.xgartic.util.TextWrapper;

import java.time.Duration;

public class ITitle extends DisplayObject {

    private final String title, subtitle;
    private final Duration in, stay, out;

    public ITitle(String title, String subtitle, Duration in, Duration stay, Duration out) {
        this.title = title;
        this.subtitle = subtitle;
        this.in = in;
        this.stay = stay;
        this.out = out;
    }

    public ITitle(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
        this.in = Duration.ofMillis(250);
        this.stay = Duration.ofMillis(1000);
        this.out = Duration.ofMillis(500);
    }

    private String format(String string) {
        ConfigAccessor data = new ConfigAccessor();
        return string
                .replaceAll("%black%", data.cfg().getString("", "display.title.black"));
    }

    @Override
    public void show(Player... players) {
        net.kyori.adventure.title.Title output = net.kyori.adventure.title.Title.title(
                TextWrapper.wrap(format(title)),
                TextWrapper.wrap(format(subtitle)),
                net.kyori.adventure.title.Title.Times.times(in, stay, out)
        );
        for(Player player: players) player.showTitle(output);
        output = null;
    }

}
