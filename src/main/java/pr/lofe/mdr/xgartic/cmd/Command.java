package pr.lofe.mdr.xgartic.cmd;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.command.CommandSender;

public abstract class Command {

    public final CommandAPICommand cmd;

    abstract void execute(CommandSender sender, CommandArguments args);

    public Command(String cmd) {
        this.cmd = new CommandAPICommand(cmd).executes(this::execute);
    }

    public void register() {
        cmd.register();
    }

    public void unregister() {
        CommandAPI.unregister(cmd.getName());
    }

}
