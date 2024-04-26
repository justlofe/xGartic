package pr.lofe.mdr.xgartic.display;

import org.bukkit.entity.Player;
import pr.lofe.mdr.xgartic.util.TextWrapper;

import java.time.Duration;

public class Title extends DisplayObject {

    private final String title, subtitle;
    private final Duration in, stay, out;

    public Title(String title, String subtitle, Duration in, Duration stay, Duration out) {
        this.title = title;
        this.subtitle = subtitle;
        this.in = in;
        this.stay = stay;
        this.out = out;
    }

    public Title(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
        this.in = Duration.ofMillis(250);
        this.stay = Duration.ofMillis(1000);
        this.out = Duration.ofMillis(500);
    }

    @Override
    public void show(Player... players) {
        net.kyori.adventure.title.Title output = net.kyori.adventure.title.Title.title(
                TextWrapper.wrap(title),
                TextWrapper.wrap(subtitle),
                net.kyori.adventure.title.Title.Times.times(in, stay, out)
        );
        for(Player player: players) player.showTitle(output);
        output = null;
    }

}
