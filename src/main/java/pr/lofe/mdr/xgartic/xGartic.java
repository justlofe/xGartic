package pr.lofe.mdr.xgartic;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import pr.lofe.mdr.xgartic.debug.DebugCommand;
import pr.lofe.mdr.xgartic.debug.DebugListener;
import pr.lofe.mdr.xgartic.manage.*;

public final class xGartic extends JavaPlugin {

    public static xGartic I;

    private MapManager mapManager;
    private MenuManager menuManager;
    private LobbyManager lobbyManager;
    private ItemManager itemManager;
    private GameManager gameManager;
    private DisplayManager displayManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        I = this;
        try {mapManager = new MapManager();} catch (RuntimeException ignored) {}
        menuManager = new MenuManager();
        lobbyManager = new LobbyManager();
        itemManager = new ItemManager();
        gameManager = new GameManager();
        displayManager = new DisplayManager();

        Bukkit.getPluginManager().registerEvents(new DebugListener(), this);

        PluginCommand command = getCommand("gartic");
        if(command != null) {
            DebugCommand debug = new DebugCommand();
            command.setExecutor(debug);
            command.setTabCompleter(debug);
        }
    }

    @Override
    public void onDisable() {}

    public static MapManager getMaps() { return I.mapManager; }
    public static MenuManager getMenus() { return I.menuManager; }
    public static LobbyManager getLobbies() { return I.lobbyManager; }
    public static ItemManager getItems() { return I.itemManager; }
    public static GameManager getGames() { return I.gameManager; }
    public static DisplayManager getDisplay() { return I.displayManager; }
}