package pr.lofe.mdr.xgartic.game;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;

public class GameController implements Listener {

    private final Game game;

    public GameController(Game game) {
        this.game = game;
    }

    @EventHandler public void onWorldLoad(WorldLoadEvent event) {
        if(event.getWorld().getName().equals(game.getProvider().getWorld().getName())) {
            game.markWorldLoaded();
        }
    }



}
