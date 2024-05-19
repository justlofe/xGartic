package pr.lofe.mdr.xgartic.cmd;

import dev.jorel.commandapi.arguments.TextArgument;
import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.command.CommandSender;
import pr.lofe.mdr.xgartic.util.TextWrapper;
import pr.lofe.mdr.xgartic.xGartic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DebugCMD extends Command{

    public DebugCMD() {
        super("debug");
        cmd.withArguments(new TextArgument("tag"));
    }

    @Override
    void execute(CommandSender sender, CommandArguments args) {
        String tag = args.getRaw("tag");
        if(tag != null) {
            switch (tag) {
                case "version" -> {
                    InputStream in = xGartic.I.getResource("version.txt");
                    if (in == null) {
                        sender.sendMessage(TextWrapper.wrap("Невозможно получить версию. Плагин забилжен на коленках у араба."));
                        return;
                    }

                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    try {
                        String commit = reader.readLine();
                        sender.sendMessage(TextWrapper.wrap(String.format(
                                "Версия плагина... Commit: <blue><click:open_url:'https://github.com/justlofe/xGartic/commit/%s'>%s</click></blue>",
                                commit,
                                commit
                        )));
                    } catch (IOException ignored) {
                        sender.sendMessage(TextWrapper.wrap("Невозможно получить версию. Плагин забилжен на коленках у араба."));
                    }
                }
                default -> {}
            }
        }
    }

}
