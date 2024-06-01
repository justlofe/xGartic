package pr.lofe.mdr.xgartic.cmd;

import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pr.lofe.mdr.xgartic.game.Game;
import pr.lofe.mdr.xgartic.xGartic;

public class GameCommand extends Command {

    public GameCommand() {
        super("done");
    }


    @Override
    void execute(CommandSender sender, CommandArguments args) {
        if(sender instanceof Player player) {
            Game game = xGartic.getGames().getPlayerGame(player);
            if(game != null) game.completeBuild(player);
        }
    }

}
