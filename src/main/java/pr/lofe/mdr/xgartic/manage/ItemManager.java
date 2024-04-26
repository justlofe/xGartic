package pr.lofe.mdr.xgartic.manage;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import pr.lofe.mdr.xgartic.inv.MenuType;
import pr.lofe.mdr.xgartic.lobby.Lobby;
import pr.lofe.mdr.xgartic.util.TextWrapper;
import pr.lofe.mdr.xgartic.xGartic;

public class ItemManager implements Listener {

    private final static NamespacedKey menu = new NamespacedKey(xGartic.I, "menu");

    public ItemManager() {
        Bukkit.getPluginManager().registerEvents(this, xGartic.I);
    }

    public ItemStack getMenu() {
        ItemStack item = new ItemStack(Material.PAPER, 1);
        item.editMeta(meta -> {
           meta.setCustomModelData(1110);
           meta.displayName(TextWrapper.wrap("<gradient:#defcff:#c5c4ff>мᴇню</gradient>"));
           meta.getPersistentDataContainer().set(menu, PersistentDataType.BOOLEAN, true);
        });
        return item;
    }

    @EventHandler public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(event.getAction().name().contains("RIGHT_CLICK")) {
            if(item.getItemMeta() != null && item.getItemMeta().getPersistentDataContainer().has(menu)) {
                event.setCancelled(true);

                Lobby lobby = xGartic.getLobbies().getPlayerLobby(player);
                if(lobby == null) player.openInventory(xGartic.getMenus().getNew(MenuType.MAIN, null));
                else {
                    Inventory inv = lobby.getInventory();
                    lobby.modify(inv, player);
                    player.openInventory(inv);
                }
            }
        }
    }

}
