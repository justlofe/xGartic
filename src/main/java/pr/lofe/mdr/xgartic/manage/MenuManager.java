package pr.lofe.mdr.xgartic.manage;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import pr.lofe.mdr.xgartic.config.ConfigAccessor;
import pr.lofe.mdr.xgartic.inv.GarticHolder;
import pr.lofe.mdr.xgartic.inv.MenuListener;
import pr.lofe.mdr.xgartic.inv.MenuType;
import pr.lofe.mdr.xgartic.lobby.Lobby;
import pr.lofe.mdr.xgartic.util.TextWrapper;
import pr.lofe.mdr.xgartic.xGartic;

import java.util.ArrayList;
import java.util.List;

public class MenuManager extends ConfigAccessor {

    public MenuManager() {
        Bukkit.getPluginManager().registerEvents(new MenuListener(), xGartic.I);
    }

    public Inventory getNew(MenuType type, InventoryHolder holder) {
        if(type == MenuType.MAIN && holder == null) holder = new MainHolder();
        switch (type) {
            case MAIN -> {
                Inventory inv = Bukkit.createInventory(holder, 27, TextWrapper.wrap(cfg().getString("display.invs.main.title", "")));

                ItemStack create = new ItemStack(Material.PAPER, 1){{
                    editMeta(meta -> {
                        meta.displayName(TextWrapper.wrap("<color:#d4eba7>создᴀть новоᴇ лобби</color>"));
                        meta.lore(TextWrapper.wrapArray(
                                "",
                                "<white><color:#d4eba7>▲ пкм</color> - создᴀть</white>",
                                "<white><color:#e1ff6b>△ шиȹт пкм</color> - создᴀть пᴘивᴀтноᴇ лобби</white>"));
                    });
                }};
                invisible(create);
                inv.setItem(22, create);

                List<ItemStack> items = new ArrayList<>();
                for (Lobby lobby : xGartic.getLobbies().getLobbies()) {
                    if(lobby.isOpened()) {
                        Player lead = lobby.getLeader();
                        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
                        SkullMeta meta = (SkullMeta) item.getItemMeta();

                        meta.setOwningPlayer(lead);
                        meta.displayName(TextWrapper.wrap("<white>лобби игᴘокᴀ <color:#ffda75>⛨ " + TextWrapper.mini(lead.getName()) + "</color></white>"));
                        meta.lore(TextWrapper.wrapArray(
                                "",
                                "<white><color:#90c961>▲ пкм</color> - войти в лобби</white>"
                        ));
                        meta.getPersistentDataContainer().set(Lobby.id, PersistentDataType.STRING, lobby.getCode());

                        item.setItemMeta(meta);
                        items.add(item);
                    }
                }

                for(int i = 0; i < Math.min(18, items.size()); i++) {
                    inv.setItem(i, items.get(i));
                }

                return inv;
            }
            case LOBBY -> {
                Inventory inv = Bukkit.createInventory(holder, 27, TextWrapper.wrap(cfg().getString("display.invs.lobby.title", "")));

                ItemStack leave = new ItemStack(Material.PAPER, 1){{
                    editMeta(meta -> {
                        meta.displayName(TextWrapper.wrap("<color:#c96174>покинʏть лобби</color>"));
                        meta.lore(TextWrapper.wrapArray(
                                "",
                                "<white><color:#c96174>❌ пкм</color> - покинʏть</white>"));
                    });
                }};
                invisible(leave);
                inv.setItem(22, leave);

                ItemStack privacy = new ItemStack(Material.PAPER, 1){{
                    editMeta(meta -> meta.displayName(TextWrapper.wrap("<color:#ffda75>измᴇнить пᴘивᴀтность лобби</color>")));
                }};
                invisible(privacy);
                inv.setItem(23, privacy);

                ItemStack start = new ItemStack(Material.PAPER, 1){{
                    editMeta(meta -> meta.displayName(TextWrapper.wrap("<color:#4287f5>нᴀчᴀть игᴘʏ</color>")));
                }};
                invisible(start);
                inv.setItem(25, start);
                inv.setItem(26, start);

                return inv;
            }
            default -> {}
        }
        return null;
    }

    private static void invisible(ItemStack item) {
        item.editMeta(meta -> meta.setCustomModelData(1002));
    }

    public static class MainHolder implements GarticHolder {
        private final Inventory inventory = Bukkit.createInventory(this, 27);
        @Override public @NotNull Inventory getInventory() {
            return inventory;
        }
    }

    public static class DescribeHolder implements GarticHolder {
        private final Inventory inventory = Bukkit.createInventory(this, 27);
        @Override public @NotNull Inventory getInventory() {
            return inventory;
        }
    }

}
