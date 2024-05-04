package pr.lofe.mdr.xgartic.debug;

import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.StringArgument;
import dev.jorel.commandapi.executors.CommandArguments;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pr.lofe.mdr.xgartic.command.AbstractCommand;
import pr.lofe.mdr.xgartic.inv.MenuType;
import pr.lofe.mdr.xgartic.xGartic;

public class AdminCommand extends AbstractCommand {
    public AdminCommand() {
        super("admin");
        cmd()
                .withArguments(new GreedyStringArgument("action"))
                .withOptionalArguments(new StringArgument("type"));
        register();
    }

    @Override
    protected void execute(CommandSender sender, CommandArguments args) {
        if(sender instanceof Player player) {
            String action = (String) args.get("action");
            if(action != null) {
                switch (action.toLowerCase()) {
                    case "menu" -> {
                        String type = (String) args.getOrDefault("type", "MAIN");
                        MenuType menu = MenuType.valueOf(type);
                        Inventory inventory = xGartic.getMenus().getNew(menu, null);
                        if(inventory != null) player.openInventory(inventory);
                    }
                    default -> {}
                }
            }
        }
    }

}
