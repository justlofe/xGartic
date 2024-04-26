package pr.lofe.mdr.xgartic.lobby;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import pr.lofe.mdr.xgartic.api.LobbyCreateEvent;
import pr.lofe.mdr.xgartic.api.LobbyDisbandEvent;
import pr.lofe.mdr.xgartic.inv.GarticHolder;
import pr.lofe.mdr.xgartic.inv.MenuType;
import pr.lofe.mdr.xgartic.util.CodeUtil;
import pr.lofe.mdr.xgartic.util.TextWrapper;
import pr.lofe.mdr.xgartic.xGartic;

import java.util.ArrayList;
import java.util.List;

public class Lobby implements GarticHolder {

    public static final NamespacedKey id = new NamespacedKey(xGartic.I, "lobby_id");
    public final static NamespacedKey player = new NamespacedKey(xGartic.I, "player");

    private final String code;

    private Player leader;
    private final List<Player> players;
    private boolean opened;

    public Lobby(@NotNull Player creator) {
        code = CodeUtil.gen("#AAA00");

        leader = creator;
        players = new ArrayList<>();
        players.add(creator);

        Bukkit.getPluginManager().callEvent(new LobbyCreateEvent(this, leader));
    }

    public String getCode() {
        return new String(code);
    }

    private void update() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            InventoryView view = player.getOpenInventory();
            Inventory inv = view.getTopInventory();
            InventoryHolder holder = inv.getHolder();
            if(holder instanceof Lobby lobby) {
                if(!lobby.getPlayers().contains(player)) player.closeInventory();
                else {
                    Inventory newInv = lobby.getInventory();
                    lobby.modify(newInv, player);
                    player.openInventory(newInv);
                }
            }
        }
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
        update();
    }
    public boolean isOpened() { return opened; }

    public void setLeader(Player leader) {
        this.leader = leader;
        update();
    }
    public boolean isLeader(Player player) { return leader.getName().equals(player.getName()); }
    public Player getLeader() { return leader; }

    public List<Player> getPlayers() { return players; }
    public boolean addPlayer(Player player) {
        if(players.size() < 14 || players.contains(player)) {
            players.add(player);
            update();

            Inventory inv = getInventory();
            modify(inv, player);
            player.openInventory(inv);

            return true;
        }
        return false;
    }
    public void removePlayer(Player player) {
        players.remove(player);
        update();
    }


    public void disband(LobbyDisbandEvent.Reason reason) {
        Bukkit.getPluginManager().callEvent(new LobbyDisbandEvent(this, reason));
        update();
    }

    @Override public @NotNull Inventory getInventory() {
        return xGartic.getMenus().getNew(MenuType.LOBBY, this);
    }

    public void modify(Inventory inv, Player forWho) {
        boolean isLeader = isLeader(forWho);
        if(!isLeader) inv.setItem(23, null);
        inv.setItem(0, getHead(leader, true));

        List<Player> copy = new ArrayList<>(players){{
            remove(leader);
        }};
        for(int i = 0; i < copy.size(); i++) {
            ItemStack head = getHead(copy.get(i), false);
            if(isLeader) head.editMeta(meta -> meta.lore(TextWrapper.wrapArray(
                    "",
                    "<white><color:#c96174>❌ пкм</color> - кикнʏть</white>",
                    "<white><color:#90c961>▲ шиȹт пкм</color> - нᴀзнᴀчить лидᴇᴘом</white>"
            )));
            inv.setItem(i + 1, head);
        }

        ItemStack item = inv.getItem(23);
        if (item != null) {
            item.editMeta(meta -> {
                if (opened) meta.lore(TextWrapper.wrapArray(
                        "",
                        "<white><color:gray>▲ пкм</color> - сдᴇлᴀть пʏбличным</white>",
                        "<white><color:#e1ff6b>△ шиȹт пкм</color> - сдᴇлᴀть пᴘивᴀтным</white>"));
                else meta.lore(TextWrapper.wrapArray(
                        "",
                        "<white><color:#d4eba7>⏺ пкм</color> - сдᴇлᴀть пʏбличным</white>",
                        "<white><color:gray>○ шиȹт пкм</color> - сдᴇлᴀть пᴘивᴀтным</white>"));
            });
            inv.setItem(23, item);
        }

        ItemStack start = inv.getItem(25);
        if (start != null) {
            start.editMeta(meta -> {
                if(isLeader) {
                    if (players.size() < 4) meta.lore(TextWrapper.wrapArray(
                            "",
                            "<white><color:#4287f5>⏵ пкм</color> - нᴀчᴀть игᴘʏ</white>",
                            "<color:dark_gray>⚠ игᴘᴀ в состᴀвᴇ мᴇньшᴇ чᴇтыᴘᴇх</color>",
                            "<color:dark_gray>    людᴇй нᴇ ᴘᴇкомᴇндʏᴇтся!</color>"));
                    else meta.lore(TextWrapper.wrapArray(
                            "",
                            "<white><color:gray>⏵ пкм</color> - нᴀчᴀть игᴘʏ</white>"));
                }
                else meta.lore(TextWrapper.wrapArray(//
                        "",
                        "<white><color:#4287f5>⏵ пкм</color> - нᴀчᴀть игᴘʏ</white>",
                        "<color:#8a231c>⚠ игᴘʏ можᴇт нᴀчᴀть только</color>",
                        "<color:#8a231c>    лидᴇᴘ лобби!</color>"));
            });
            //
            inv.setItem(25, start);
            inv.setItem(26, start);
        }

    }

    private ItemStack getHead(Player player, boolean isLeader) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwningPlayer(player);

        String member = "<white><color:#ffda75>%s</color> - ʏчᴀстник</white>",
                leader = "<white><color:#ffda75>⛨ %s</color> - лидᴇᴘ</white>";

        meta.displayName(TextWrapper.wrap(String.format(isLeader ? leader : member, TextWrapper.mini(player.getName()))));
        meta.getPersistentDataContainer().set(Lobby.player, PersistentDataType.STRING, player.getName());
        item.setItemMeta(meta);
        return item;
    }

}
