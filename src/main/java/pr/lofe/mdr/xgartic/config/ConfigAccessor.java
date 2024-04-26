package pr.lofe.mdr.xgartic.config;

import org.bukkit.configuration.file.FileConfiguration;
import pr.lofe.mdr.xgartic.xGartic;

public class ConfigAccessor {

    public FileConfiguration cfg() {
        return xGartic.I.getConfig();
    }

    public void save() {
        xGartic.I.saveConfig();
    }

    public void reload() {
        xGartic.I.reloadConfig();
    }

}
