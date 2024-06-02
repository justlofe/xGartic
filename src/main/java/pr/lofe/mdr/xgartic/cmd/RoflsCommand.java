package pr.lofe.mdr.xgartic.cmd;

import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.command.CommandSender;

public class RoflsCommand extends Command {

    public RoflsCommand() {
        super("pidorlist");
    }


    @Override
    void execute(CommandSender sender, CommandArguments args) {
        sender.sendMessage("Pidorlist: FelexFox, leha_0, orisana, mottecord, Reverted, BoooFoo, ВСЕ УЧАСТНИКИ КУЛЬТА ОБЕЗЬЯН, НЧВК/ЧСВ");
    }
}
