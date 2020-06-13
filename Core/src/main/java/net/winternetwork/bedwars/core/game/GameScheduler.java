package net.winternetwork.bedwars.core.game;

import net.winternetwork.bedwars.api.game.stage.Stage;
import net.winternetwork.bedwars.core.Core;
import net.winternetwork.bedwars.core.game.stage.StageManager;
import org.bukkit.Bukkit;

public class GameScheduler {

    private final Game game;
    private final StageManager stageManager;

    public GameScheduler(Game game) {
        this.game = game;
        this.stageManager = game.getStageManager();

        Bukkit.getScheduler().runTaskTimerAsynchronously(
                Core.getInstance(),
                this::scheduleTime,
                0,
                20
        );
    }

    private void scheduleTime() {
        final Stage stage = stageManager.getActualStage();

        System.out.println(stage.getTimeLeft());

        stage.onSecondPassed();
        stage.operateTime();

        if (stage.getTimeLeft() <= 0) {
            stage.onTimeLeft();
        }
    }
}
