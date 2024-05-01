package pr.lofe.mdr.xgartic.game;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import pr.lofe.mdr.xgartic.xGartic;

public class GameClock {

    private final Game parent;
    private Integer task;

    private int stageWait;

    public GameClock(@NotNull Game game) {
        parent = game;
    }

    public void start(long time) {
        stageWait = parent.getStage();
        task = Bukkit.getScheduler().runTaskLater(xGartic.I, () -> {
            if(stageWait < parent.getStage()) return;

            parent.completeObject(true);
        }, time).getTaskId();
    }

}
