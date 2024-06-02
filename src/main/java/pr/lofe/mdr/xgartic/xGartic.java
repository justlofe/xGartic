package pr.lofe.mdr.xgartic;

import dev.jorel.commandapi.CommandAPI;
import org.bukkit.plugin.java.JavaPlugin;
import pr.lofe.mdr.xgartic.cmd.DebugCMD;
import pr.lofe.mdr.xgartic.cmd.GameCommand;
import pr.lofe.mdr.xgartic.cmd.RoflsCommand;
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

        new DebugCMD().register();
        new GameCommand().register();
        new RoflsCommand().register();
    }

    @Override
    public void onDisable() {
        CommandAPI.unregister("gartic");
        CommandAPI.unregister("done");
        CommandAPI.unregister("pidorlist");
    }

    public static MapManager getMaps() { return I.mapManager; }
    public static MenuManager getMenus() { return I.menuManager; }
    public static LobbyManager getLobbies() { return I.lobbyManager; }
    public static ItemManager getItems() { return I.itemManager; }
    public static GameManager getGames() { return I.gameManager; }
    public static DisplayManager getDisplay() { return I.displayManager; }
}