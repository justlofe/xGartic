package pr.lofe.mdr.xgartic.command;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.command.CommandSender;

public abstract class AbstractCommand {

    final CommandAPICommand cmd;

    public AbstractCommand(String cmd) {
        this.cmd = new CommandAPICommand(cmd);
    }

    public CommandAPICommand cmd() {
        return cmd;
    }

    public void register() {
        cmd.executes(this::execute);
        cmd.register();
    }

    public void unregister() {
        CommandAPI.unregister(cmd.getName());
    }

    protected abstract void execute(CommandSender sender, CommandArguments args);

}
