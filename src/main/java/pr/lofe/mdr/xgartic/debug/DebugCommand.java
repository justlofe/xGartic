package pr.lofe.mdr.xgartic.debug;

import com.google.common.collect.Lists;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pr.lofe.mdr.xgartic.config.ConfigAccessor;
import pr.lofe.mdr.xgartic.inv.MenuType;
import pr.lofe.mdr.xgartic.util.SortUtil;
import pr.lofe.mdr.xgartic.util.TextWrapper;
import pr.lofe.mdr.xgartic.xGartic;

import java.util.ArrayList;
import java.util.List;

public class DebugCommand extends ConfigAccessor implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(commandSender instanceof Player player)) return true;
        else if (!commandSender.hasPermission("*")) return true;
        else if (strings.length == 0) return true;

        switch (strings[0].toLowerCase()) {
            case "reload" -> {
                reload();
                player.sendMessage(TextWrapper.wrap(cfg().getString("display.messages.reloaded", "")));
            }
            case "menu" -> {
                MenuType type = MenuType.valueOf(strings[1]);
                Inventory inventory = xGartic.getMenus().getNew(type, null);
                if(inventory != null) player.openInventory(inventory);
            }
            case "item" -> {
                player.sendMessage("Gived menu item");
                player.getInventory().addItem(xGartic.getItems().getMenu());
            }
            default -> {}
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        switch (strings.length) {
            case 1 -> {
                return SortUtil.searchFor(strings[0], "menu", "reload", "item");
            }
            case 2 -> {
                if(strings[0].equals("menu")) {
                    List<String> args = new ArrayList<>(MenuType.values().length);
                    for(MenuType type : MenuType.values()) {
                        args.add(type.name());
                    }
                    return SortUtil.search(args, strings[1]);
                }
            }
        }
        return Lists.newArrayList();
    }
}
