package pr.lofe.mdr.xgartic.cmd;

import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.TextArgument;
import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pr.lofe.mdr.xgartic.util.TextWrapper;
import pr.lofe.mdr.xgartic.xGartic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;

public class DebugCMD extends Command{

    public DebugCMD() {
        super("gartic");
        cmd.withSubcommands(
                new Command("git") {
                    @Override
                    void execute(CommandSender sender, CommandArguments args) {
                        String arg = args.getRaw("arg");
                        switch (arg) {
                            case "version" -> {
                                InputStream in = xGartic.I.getResource("version.txt");
                                if (in == null) {
                                    sender.sendMessage(TextWrapper.wrap("Невозможно получить версию. Плагин забилжен на коленках у араба."));
                                    return;
                                }

                                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                                try {
                                    String commit = reader.readLine();
                                    if(commit == null) throw new IOException();

                                    sender.sendMessage(TextWrapper.wrap(String.format(
                                            "Версия плагина... Commit: <blue><click:open_url:'https://github.com/justlofe/xGartic/commit/%s'>%s</click></blue>",
                                            commit,
                                            commit
                                    )));
                                } catch (IOException ignored) {
                                    sender.sendMessage(TextWrapper.wrap("Невозможно получить версию. Плагин забилжен на коленках у араба."));
                                }
                            }
                            case "url" -> {
                                sender.sendMessage(TextWrapper.wrap(
                                        "Repository URL: <blue><click:open_url:'https://github.com/justlofe/xGartic/'>[GitHub]</click></blue>"
                                ));
                            }
                            default -> {}
                        }
                    }
                }.cmd.withArguments(new TextArgument("arg").replaceSuggestions(ArgumentSuggestions.strings("version", "url"))),

                new Command("item") {
                    @Override
                    void execute(CommandSender sender, CommandArguments args) {
                        ItemStack item = xGartic.getItems().getMenu();
                        item.setAmount((Integer) args.get("amount"));

                        Player player = (Player) args.get("player");
                        player.getInventory().addItem(item);
                    }
                }.cmd.withArguments(new PlayerArgument("player").combineWith(new IntegerArgument("amount", 1, 64))),

                new Command("reload") {

                    @Override
                    void execute(CommandSender sender, CommandArguments args) {
                        xGartic.I.reloadConfig();
                        sender.sendMessage(TextWrapper.wrap("Конфигурация перезагружена."));
                    }
                }.cmd
        );
    }

    @Override void execute(CommandSender sender, CommandArguments args) {}

}
