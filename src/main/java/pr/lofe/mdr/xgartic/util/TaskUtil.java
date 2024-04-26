package pr.lofe.mdr.xgartic.util;

import org.bukkit.Bukkit;
import pr.lofe.mdr.xgartic.xGartic;

public class TaskUtil {

    public static void run(long offset, Runnable process) {
        Bukkit.getScheduler().runTaskLater(xGartic.I, process, offset);
    }

    public static void runAsync(long offset, Runnable process) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(xGartic.I, process, offset);
    }

}
