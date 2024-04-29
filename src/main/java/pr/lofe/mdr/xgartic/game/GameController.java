package pr.lofe.mdr.xgartic.game;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.ItemStack;
import pr.lofe.mdr.xgartic.game.obj.Point;
import pr.lofe.mdr.xgartic.game.obj.Room;
import pr.lofe.mdr.xgartic.xGartic;

public class GameController implements Listener {

    private final static ImmutableList<Material> blockedItems = ImmutableList.copyOf(Lists.newArrayList(Material.COMMAND_BLOCK,
            Material.COMMAND_BLOCK_MINECART,
            Material.REPEATING_COMMAND_BLOCK,
            Material.CHAIN_COMMAND_BLOCK,
            Material.JIGSAW,
            Material.STRUCTURE_BLOCK,
            Material.STRUCTURE_VOID,
            Material.BARRIER,
            Material.DEBUG_STICK,
            Material.LIGHT,
            Material.OAK_SIGN,
            Material.OAK_HANGING_SIGN,
            Material.SPRUCE_SIGN,
            Material.SPRUCE_HANGING_SIGN,
            Material.BIRCH_SIGN,
            Material.BIRCH_HANGING_SIGN,
            Material.ACACIA_SIGN,
            Material.ACACIA_HANGING_SIGN,
            Material.DARK_OAK_SIGN,
            Material.DARK_OAK_HANGING_SIGN,
            Material.MANGROVE_SIGN,
            Material.MANGROVE_HANGING_SIGN,
            Material.CHERRY_SIGN,
            Material.CHERRY_HANGING_SIGN,
            Material.BAMBOO_SIGN,
            Material.BAMBOO_HANGING_SIGN,
            Material.CRIMSON_SIGN,
            Material.CRIMSON_HANGING_SIGN,
            Material.WARPED_SIGN,
            Material.WARPED_HANGING_SIGN,
            Material.ITEM_FRAME,
            Material.GLOW_ITEM_FRAME,
            Material.NAME_TAG,
            Material.END_CRYSTAL
    ));

    private final static ImmutableList<Material> blockedBlocks = ImmutableList.copyOf(Lists.newArrayList(
            Material.CHEST,
            Material.CRAFTING_TABLE,
            Material.STONECUTTER,
            Material.CARTOGRAPHY_TABLE,
            Material.FLETCHING_TABLE,
            Material.SMITHING_TABLE,
            Material.GRINDSTONE,
            Material.LOOM,
            Material.FURNACE,
            Material.SMOKER,
            Material.BLAST_FURNACE,
            Material.CAMPFIRE,
            Material.SOUL_CAMPFIRE,
            Material.ANVIL,
            Material.CHIPPED_ANVIL,
            Material.DAMAGED_ANVIL,
            Material.COMPOSTER,
            Material.NOTE_BLOCK,
            Material.JUKEBOX,
            Material.ENCHANTING_TABLE,
            Material.BREWING_STAND,
            Material.CAULDRON,
            Material.BELL,
            Material.BEACON,
            Material.LODESTONE,
            Material.BEE_NEST,
            Material.BEEHIVE,
            Material.DECORATED_POT,
            Material.BARREL,
            Material.ENDER_CHEST,
            Material.RESPAWN_ANCHOR,
            Material.SHULKER_BOX,
            Material.WHITE_SHULKER_BOX,
            Material.LIGHT_GRAY_SHULKER_BOX,
            Material.GRAY_SHULKER_BOX,
            Material.BLACK_SHULKER_BOX,
            Material.BROWN_SHULKER_BOX,
            Material.RED_SHULKER_BOX,
            Material.ORANGE_SHULKER_BOX,
            Material.YELLOW_SHULKER_BOX,
            Material.LIME_SHULKER_BOX,
            Material.GREEN_SHULKER_BOX,
            Material.CYAN_SHULKER_BOX,
            Material.LIGHT_BLUE_SHULKER_BOX,
            Material.BLUE_SHULKER_BOX,
            Material.PURPLE_SHULKER_BOX,
            Material.MAGENTA_SHULKER_BOX,
            Material.PINK_SHULKER_BOX,
            Material.DISPENSER,
            Material.DROPPER,
            Material.HOPPER
    ));

    private final Game game;

    public GameController(Game game) {
        this.game = game;
    }

    @EventHandler public void onInventoryClick(InventoryCreativeEvent event) {
        Player player = (Player) event.getWhoClicked();
        Game game = xGartic.getGames().getPlayerGame(player);
        if(game != null) {
            ItemStack item = event.getCursor();
            if(blockedItems.contains(item.getType())) event.setCursor(new ItemStack(Material.AIR));
        }
    }

    @EventHandler public void onWorldLoad(WorldLoadEvent event) {
        if(event.getWorld().getName().equals(game.getProvider().getWorld().getName())) {
            game.markWorldLoaded();
        }
    }

    @EventHandler public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if(game.getPlayers().contains(player)) {
            Point point = Point.fromLocation(event.getBlock().getLocation());
            Room room = game.getAssigns().get(player);
            if(room != null) {
                if(!room.zone().isIn(point)) event.setCancelled(true);
            }
        }
    }

    @EventHandler public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if(game.getPlayers().contains(player) && block != null) {
            if(blockedBlocks.contains(block.getType()) && !player.isSneaking()) {
                event.setCancelled(true);
                return;
            }

            Point point = Point.fromLocation(block.getLocation());
            Room room = game.getAssigns().get(player);
            if(room != null) {
                if(!room.zone().isIn(point)) event.setCancelled(true);
            }
        }
    }

    @EventHandler public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if(game.getPlayers().contains(player)) {
            Point point = Point.fromLocation(event.getBlock().getLocation());
            Room room = game.getAssigns().get(player);
            if(room != null) {
                if(!room.zone().isIn(point)) event.setCancelled(true);
            }
        }
    }

    @EventHandler public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(game.getPlayers().contains(player)) {
            Point point = Point.fromLocation(event.getTo());
            Room room = game.getAssigns().get(player);
            if(room != null) {
                if(!room.zone().isIn(point)) player.teleport(Point.fromPoint(room.spawn(), game.getProvider().getWorld()));
            }
        }
    }

}
