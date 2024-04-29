package pr.lofe.mdr.xgartic.inv;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import pr.lofe.mdr.xgartic.api.LobbyDisbandEvent;
import pr.lofe.mdr.xgartic.config.ConfigAccessor;
import pr.lofe.mdr.xgartic.lobby.Lobby;
import pr.lofe.mdr.xgartic.manage.LobbyManager;
import pr.lofe.mdr.xgartic.manage.MenuManager;
import pr.lofe.mdr.xgartic.util.TextWrapper;
import pr.lofe.mdr.xgartic.xGartic;

import java.util.List;

public class MenuListener extends ConfigAccessor implements Listener {

    @EventHandler public void onInventoryClick(InventoryClickEvent event) {
        Inventory inv = event.getClickedInventory();
        if(inv != null) {
            InventoryHolder holder = inv.getHolder();
            if(holder == null) return;
            else if(!(holder instanceof GarticHolder)) return;

            event.setCancelled(true);

            int c = event.getSlot();
            ClickType click = event.getClick();
            Player player = (Player) event.getWhoClicked();
            ItemStack item = inv.getItem(c);

            if (item != null) player.playSound(player, "custom.sfx.menu-click", 1f, 1f);

            if(holder instanceof MenuManager.MainHolder) {
                if (c == 22) {
                    LobbyManager lobbies = xGartic.getLobbies();
                    if (lobbies.getPlayerLobby(player) == null) {
                        Boolean opened = null;
                        switch (event.getClick()) {
                            case SHIFT_RIGHT -> opened = false;
                            case RIGHT -> opened = true;
                            default -> {}
                        }
                        if (opened != null) {
                            Lobby lobby = lobbies.create(player, opened);
                            if (lobby != null) {
                                Inventory lobbyMenu = lobby.getInventory();
                                lobby.modify(lobbyMenu, player);
                                player.openInventory(lobbyMenu);
                            }
                        }
                    }
                }
                else if(c >= 0 && c < 18) {
                    if(item != null) {
                        PersistentDataContainer data = item.getItemMeta().getPersistentDataContainer();
                        if(data.has(Lobby.id)) {
                            String code = data.get(Lobby.id, PersistentDataType.STRING);
                            if(code != null) {
                                Lobby lobby = xGartic.getLobbies().getLobbyByCode(code);
                                if(lobby != null) {
                                    if(lobby.isOpened()) lobby.addPlayer(player);
                                    else player.openInventory(xGartic.getMenus().getNew(MenuType.MAIN, null));
                                }
                            }
                        }
                    }
                }
            }
            else if (holder instanceof Lobby) {
                Lobby lobby = xGartic.getLobbies().getPlayerLobby(player);
                if (lobby != null) {
                    switch (c) {
                        case 22 -> {
                            xGartic.getLobbies().remove(player);
                            player.closeInventory();
                        }
                        case 23 -> {
                            boolean opened = lobby.isOpened();
                            if(click == ClickType.RIGHT) opened = true;
                            else if (click == ClickType.SHIFT_RIGHT) opened = false;

                            lobby.setOpened(opened);
                        }
                        case 25, 26 -> {
                            if(lobby.isLeader(player)) xGartic.getGames().startGame(lobby);
                        }
                        default -> {
                            if(c >= 0 && c < 15) {
                                if(item != null && lobby.isLeader(player)) {
                                    PersistentDataContainer data = item.getItemMeta().getPersistentDataContainer();
                                    String name = data.get(Lobby.player, PersistentDataType.STRING);
                                    if(name != null && !name.equals(player.getName())) {
                                        Player actioned = Bukkit.getPlayer(name);
                                        if(actioned != null) {
                                            if(click == ClickType.RIGHT) {
                                                xGartic.getLobbies().remove(actioned);
                                            }
                                            else if (click == ClickType.SHIFT_RIGHT) lobby.setLeader(actioned);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
